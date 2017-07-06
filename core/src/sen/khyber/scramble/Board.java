package sen.khyber.scramble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class Board extends Actor implements Loggable {
    
    private static final float MARGIN_PERCENT = 0.25f;
    
    private final Letters letters;
    
    private final Lexicon lexicon;
    
    private final Letter[][] rows;
    private final String[] words;
    
    private final TreeMap<Float, Integer> rowMap = new TreeMap<>();
    private final List<TreeMap<Float, Integer>> columnMaps;
    
    private Letter selectedLetter;
    
    public Board(final String[] words, final Letters letters, final Lexicon lexicon) {
        rows = new Letter[words.length][];
        this.words = words;
        this.letters = letters;
        this.lexicon = lexicon;
        columnMaps = new ArrayList<>(rows.length);
        for (int i = 0; i < rows.length; i++) {
            columnMaps.add(new TreeMap<>());
        }
        initialize();
    }
    
    private static String[] fromLexicon(final int numWords, final Lexicon lexicon) {
        final String[] words = new String[numWords];
        for (int i = 0; i < numWords; i++) {
            words[i] = lexicon.randomWord(i + 1);
        }
        return words;
    }
    
    public Board(final int numWords, final Lexicon lexicon, final Letters letters) {
        this(fromLexicon(numWords, lexicon), letters, lexicon);
    }
    
    /**
     * 
     * 
     * @author Khyber Sen
     */
    private class Letter {
        
        private final float x;
        private final float y;
        
        private char c;
        private boolean selected;
        
        private TextureRegion texture;
        private TextureRegion selectedTexture;
        
        public Letter(final char c, final float x, final float y) {
            //System.out.println("Letter " + c);
            this.c = c;
            this.x = x;
            this.y = y;
            texture = letters.get(c);
            selectedTexture = letters.getSelected(c);
        }
        
        public void draw(final Batch batch) {
            batch.draw(selected ? selectedTexture : texture, getX() + x, getY() + y);
        }
        
        @Override
        public String toString() {
            return String.valueOf(c);
        }
        
    }
    
    private void swap(final Letter letter1, final Letter letter2) {
        final char tempC = letter1.c;
        final TextureRegion tempTexture = letter1.texture;
        final TextureRegion tempSelectedTexture = letter1.selectedTexture;
        letter1.c = letter2.c;
        letter1.texture = letter2.texture;
        letter1.selectedTexture = letter2.selectedTexture;
        letter2.c = tempC;
        letter2.texture = tempTexture;
        letter2.selectedTexture = tempSelectedTexture;
    }
    
    public void scramble() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                final int m = random.nextInt(rows.length);
                final int n = random.nextInt(rows[m].length);
                swap(rows[i][j], rows[m][n]);
            }
        }
    }
    
    /**
     * 
     * 
     * @author Khyber Sen
     */
    private class LetterListener extends ClickListener implements Loggable {
        
        @Override
        public void clicked(final InputEvent event, final float x, final float y) {
            final Integer i = rowMap.ceilingEntry(y).getValue();
            if (i == null) {
                return;
            }
            final Integer j = columnMaps.get(i).floorEntry(x).getValue();
            if (j == null) {
                return;
            }
            if (selectedLetter == null) {
                selectedLetter = rows[i][j];
                selectedLetter.selected = true;
            } else {
                final Letter letter = rows[i][j];
                if (letter == selectedLetter) {
                    letter.selected = false;
                } else {
                    swap(letter, selectedLetter);
                    letter.selected = false;
                    selectedLetter.selected = false;
                    selectedLetter = null;
                }
            }
        }
        
    }
    
    private void setupBoard() {
        final float height = letters.height();
        final float width = letters.width();
        final float marginY = height * MARGIN_PERCENT;
        final float marginX = width * MARGIN_PERCENT;
        float y = rows.length * (marginY + height) + marginY; // rows are top down
        setHeight(y);
        float x = 0;
        for (int i = 0; i < rows.length; i++) {
            final String word = words[i];
            final Letter[] row = new Letter[word.length()];
            rows[i] = row;
            rowMap.put(y, null);
            y -= marginY;
            rowMap.put(y, i);
            y -= height;
            x = y * 0.5f; // TODO check
            final Map<Float, Integer> columnMap = columnMaps.get(i);
            for (int j = 0; j < row.length; j++) {
                row[j] = new Letter(word.charAt(j), x, y);
                columnMap.put(x, null);
                x += marginX;
                columnMap.put(x, j);
                x += width;
            }
            columnMap.put(x, null);
        }
        rowMap.put(y, null);
        setWidth(x);
    }
    
    private void initialize() {
        setupBoard();
        scramble();
        addListener(new LetterListener());
    }
    
    private static String toString(final Letter[] row) {
        final char[] chars = new char[row.length];
        for (int i = 0; i < row.length; i++) {
            chars[i] = row[i].c;
        }
        return new String(chars);
    }
    
    public boolean isSolved() {
        for (int i = 0; i < rows.length; i++) {
            if (!lexicon.isWord(toString(rows[i]))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        for (final Letter[] row : rows) {
            for (final Letter letter : row) {
                letter.draw(batch);
            }
        }
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (final Letter[] row : rows) {
            sj.add(toString(row));
        }
        return "Board[pos=" + toString(getX(), getY()) + ", size="
                + toString(getWidth(), getHeight()) + "]"
                + "\n\tLetters" + sj + "\n\tWords" + Arrays.toString(words);
    }
    
}

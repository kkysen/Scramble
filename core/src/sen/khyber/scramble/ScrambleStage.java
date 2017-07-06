package sen.khyber.scramble;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class ScrambleStage extends Stage {
    
    private Board board;
    
    private static float center(final float parentSize, final float childSize) {
        return (parentSize - childSize) * 0.5f;
    }
    
    private Board createBoard() {
        final Board board = new Board(6, Lexicon.english(), Letters.latin());
        addActor(board);
        board.setPosition(center(getWidth(), board.getWidth()),
                center(getHeight(), board.getHeight()));
        System.out.println(board);
        return board;
    }
    
    public ScrambleStage(final Batch batch) {
        super(new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT), batch);
        board = createBoard();
    }
    
    @Override
    public void act(final float delta) {
        super.act(delta);
        if (board.isSolved()) {
            board.remove();
            board = createBoard();
        }
    }
    
}

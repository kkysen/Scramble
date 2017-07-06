package sen.khyber.scramble;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.ExtensionMethod;

/**
 * 
 * 
 * @author Khyber Sen
 */
@ExtensionMethod({Assets.class})
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(fluent = true, chain = false)
public class LatinLetters implements Letters {
    
    private static LatinLetters INSTANCE;
    
    public static Letters get() {
        return INSTANCE == null ? INSTANCE = new LatinLetters() : INSTANCE;
    }
    
    private final @Getter float width;
    private final @Getter float height;
    
    private final TextureRegion[] textures;
    private final TextureRegion[] selectedTextures;
    
    private void splitTexture(final TextureRegion[] regions, final int m, final int n,
            final Texture texture, final int width, final int height) {
        for (int k = 0, i = 0; i < m; i++) {
            int start;
            int end;
            if (i == 0 || i == m - 1) {
                start = 1;
                end = n - 1;
            } else {
                start = 0;
                end = n;
            }
            for (int j = start; j < end; j++) {
                regions[k++] = new TextureRegion(texture, j * width, i * height, width, height);
            }
        }
        Disposables.add(texture);
    }
    
    public LatinLetters() {
        final Texture texture = new Texture(DIR.resolve("latin.jpg").asset());
        final Texture selectedTexture = new Texture(DIR.resolve("latinSelected.jpg").asset());
        final int numLetters = 26;
        final int m = 5;
        final int n = 6;
        textures = new TextureRegion[numLetters];
        selectedTextures = new TextureRegion[numLetters];
        width = (float) texture.getWidth() / n;
        height = (float) texture.getHeight() / m;
        final int width = (int) this.width;
        final int height = (int) this.height;
        splitTexture(textures, m, n, texture, width, height);
        splitTexture(selectedTextures, m, n, selectedTexture, width, height);
    }
    
    private int i(final char c) {
        return c - 'a';
    }
    
    @Override
    public TextureRegion get(final char c) {
        return textures[i(c)];
    }
    
    @Override
    public TextureRegion getSelected(final char c) {
        return selectedTextures[i(c)];
    }
    
}

package sen.khyber.scramble;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 
 * 
 * @author Khyber Sen
 */
public interface Letters {
    
    public static final Path DIR = Paths.get("letters");
    
    public static Letters latin() {
        return LatinLetters.get();
    }
    
    public float width();
    
    public float height();
    
    public TextureRegion get(char c);
    
    public TextureRegion getSelected(char c);
    
}

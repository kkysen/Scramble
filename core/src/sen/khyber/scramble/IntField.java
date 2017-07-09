package sen.khyber.scramble;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class IntField extends TextField {
    
    public IntField(final String text, final TextFieldStyle style) {
        super(text, style);
    }
    
    @Override
    protected boolean isWordCharacter(final char c) {
        return c >= '0' && c <= '9';
    }
    
    public int getInt() {
        return Integer.parseInt(getText());
    }
    
}

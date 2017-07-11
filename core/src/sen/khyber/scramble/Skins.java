package sen.khyber.scramble;

import java.nio.file.Paths;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import lombok.experimental.ExtensionMethod;

/**
 * 
 * 
 * @author Khyber Sen
 */
@ExtensionMethod({Assets.class})
public class Skins {
    
    private static Skin INSTANCE;
    
    public static Skin get() {
        return INSTANCE == null ? INSTANCE = create() : INSTANCE;
    }
    
    private static Pixmap coloredPixmap(final Color color) {
        final Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
        Disposables.add(pixmap);
        pixmap.setColor(color);
        pixmap.fill();
        return pixmap;
    }
    
    private static TextureRegionDrawable coloredDrawable(final Color color) {
        final Pixmap pixmap = coloredPixmap(color);
        final Texture texture = new Texture(pixmap);
        Disposables.add(texture);
        return new TextureRegionDrawable(new TextureRegion(texture));
    }
    
    private static Skin create() {
        final Skin skin = new Skin();
        Disposables.add(skin);
        
        final Drawable white = coloredDrawable(Color.WHITE);
        skin.add("white", white);
        
        final Drawable gray = skin.newDrawable(white, Color.GRAY);
        final Drawable green = skin.newDrawable(white, Color.GREEN);
        final ProgressBarStyle timerStyle = new ProgressBarStyle(gray, green);
        skin.add("timerStyle", timerStyle);
        
        final FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(
                Paths.get("fonts", "times-new-roman.ttf").asset());
        final FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();
        fontParameter.size = 96;
        final BitmapFont font = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();
        font.getData().setScale(.5f);
        skin.add("font", font);
        
        skin.add("labelStyle", new LabelStyle(font, Color.WHITE));
        
        final Drawable brown = skin.newDrawable(white, Color.BROWN);
        final TextButtonStyle textButtonStyle = new TextButtonStyle(brown, brown, gray, font);
        skin.add("textButtonStyle", textButtonStyle);
        
        final Drawable black = skin.newDrawable(white, Color.BLACK);
        final Drawable blue = skin.newDrawable(white, Color.BLUE);
        final TextFieldStyle textFieldStyle = new TextFieldStyle(font, Color.BLACK, black, blue,
                white);
        skin.add("textFieldStyle", textFieldStyle);
        
        skin.add("isTimedCheckBoxStyle", new CheckBoxStyle(gray, green, font, Color.WHITE));
        
        return skin;
    }
    
}

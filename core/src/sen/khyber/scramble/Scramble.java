package sen.khyber.scramble;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lombok.experimental.ExtensionMethod;

/**
 * 
 * 
 * @author Khyber Sen
 */
@ExtensionMethod(Assets.class)
public class Scramble extends Game {
    
    private Batch batch;
    
    private Screen options;
    private Screen scramble;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        options = new OptionsScreen(batch);
        scramble = new ScrambleScreen(batch);
        setScreen(scramble);
    }
    
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }
    
    @Override
    public void dispose() {
        super.dispose();
        options.dispose();
        scramble.dispose();
        Disposables.dispose();
    }
    
}

package sen.khyber.scramble;

import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.experimental.ExtensionMethod;

/**
 * 
 * 
 * @author Khyber Sen
 */
@ExtensionMethod(Assets.class)
public class ScrambleScreen implements Screen {
    
    private final Batch batch;
    private final Stage stage;
    
    private final TextureRegion letters = new TextureRegion(
            new Texture(Paths.get("letters", "latin.jpg").asset()));
    
    public ScrambleScreen(final Batch batch) {
        this.batch = batch;
        stage = new ScrambleStage(batch);
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void render(final float delta) {
        stage.act(delta);
        stage.draw();
    }
    
    @Override
    public void resize(final int width, final int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void pause() {
        
    }
    
    @Override
    public void resume() {
        
    }
    
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
    
}

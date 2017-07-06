package sen.khyber.scramble;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * 
 * 
 * @author Khyber Sen
 */
public abstract class StageScreen implements Screen {
    
    private final Batch batch;
    
    private final Stage stage;
    
    public OptionsScreen(final Batch batch) {
        this.batch = batch;
        stage = createStage(batch);
    }
    
    protected abstract Stage createStage(Batch batch);
    
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
    public void pause() {}
    
    @Override
    public void resume() {}
    
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
    
    @Override
    public void dispose() {
        stage.dispose();
    }
    
}

package sen.khyber.scramble;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import lombok.Getter;

/**
 * 
 * 
 * @author Khyber Sen
 * @param <S> stage class
 */
public abstract class StageScreen<S extends Stage> implements Screen {
    
    protected @Getter long frameNum = 0;
    
    protected final Batch batch;
    
    protected final S stage;
    
    public StageScreen(final Batch batch) {
        this.batch = batch;
        stage = createStage(batch);
    }
    
    protected abstract S createStage(Batch batch);
    
    public S getStage() {
        return stage;
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void render(final float delta) {
        stage.act(delta);
        stage.draw();
        frameNum++;
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

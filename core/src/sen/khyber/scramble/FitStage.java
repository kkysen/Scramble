package sen.khyber.scramble;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import lombok.Getter;

/**
 * 
 * 
 * @author Khyber Sen
 */
public abstract class FitStage extends Stage implements Debuggable {
    
    protected final Batch batch;
    
    protected final @Getter StageScreen<? extends FitStage> screen;
    
    protected FitStage(final Batch batch, final StageScreen<? extends FitStage> screen) {
        super(new FitViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT), batch);
        this.batch = batch;
        this.screen = screen;
    }
    
    public abstract boolean isFinished();
    
}

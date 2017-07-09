package sen.khyber.scramble;

import com.badlogic.gdx.graphics.g2d.Batch;

import lombok.experimental.ExtensionMethod;

/**
 * 
 * 
 * @author Khyber Sen
 */
@ExtensionMethod(Assets.class)
public class ScrambleScreen extends StageScreen<ScrambleStage> {
    
    public ScrambleScreen(final Batch batch) {
        super(batch);
    }
    
    @Override
    protected ScrambleStage createStage(final Batch batch) {
        return new ScrambleStage(batch, this);
    }
    
}

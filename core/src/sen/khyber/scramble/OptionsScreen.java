package sen.khyber.scramble;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class OptionsScreen extends StageScreen<OptionsStage> {
    
    public OptionsScreen(final Batch batch) {
        super(batch);
    }
    
    @Override
    protected OptionsStage createStage(final Batch batch) {
        return new OptionsStage(batch, this);
    }
    
}

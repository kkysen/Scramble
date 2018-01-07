package sen.khyber.scramble;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;

/**
 * 
 * 
 * @author Khyber Sen
 */
public interface Debuggable {
    
    public static Debuggable get() {
        return new Debuggable() {};
    }
    
    public default float round(final float x) {
        return MathUtils.round(x * 100) / 100;
    }
    
    public default String toString(final Object o) {
        return String.valueOf(o);
    }
    
    public default String toString(final float x, final float y) {
        return "(" + round(x) + ", " + round(y) + ")";
    }
    
    public default String toString(final TextureRegion region) {
        return "TextureRegion[pos=" + toString(region.getRegionX(), region.getRegionY()) + ", size="
                + toString(region.getRegionWidth(), region.getRegionHeight()) + "]";
    }
    
    public default String toString(final Actor actor) {
        return actor.getClass().getSimpleName() + "[pos=" + toString(actor.getX(), actor.getY())
                + ", size=" + toString(actor.getWidth(), actor.getHeight()) + ", center="
                + toString(actor.getOriginX(), actor.getOriginY()) + "]";
    }
    
    public default String toString(final ProgressBarStyle style) {
        return style.getClass().getSimpleName()
                + "[background=" + toString(style.background)
                + ", knob=" + toString(style.knob) + ", knobBefore=" + toString(style.knobBefore)
                + ", knobAfter=" + toString(style.knobAfter)
                + "]";
    }
    
}

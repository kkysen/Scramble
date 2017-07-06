package sen.khyber.scramble;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * 
 * 
 * @author Khyber Sen
 */
public interface Loggable {
    
    public default float round(final float x) {
        return MathUtils.round(x * 100) / 100;
    }
    
    public default String toString(final float x, final float y) {
        return "(" + round(x) + ", " + round(y) + ")";
    }
    
    public default String toString(final TextureRegion region) {
        return "TextureRegion[pos=" + toString(region.getRegionX(), region.getRegionY()) + ", size="
                + toString(region.getRegionWidth(), region.getRegionHeight()) + "]";
    }
    
}

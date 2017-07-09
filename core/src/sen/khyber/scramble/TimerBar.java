package sen.khyber.scramble;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import lombok.Setter;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class TimerBar extends ProgressBar {
    
    private static final float MIN = 0;
    private static final float MAX = 1;
    private static final float DIFF_RECIPROCAL = 1 / (MAX - MIN);
    
    private @Setter float duration;
    
    private float remaining;
    
    public TimerBar(final float duration, final boolean vertical, final Drawable background,
            final Drawable knob) {
        super(MIN, MAX, 1, vertical, new ProgressBarStyle(background, knob));
        final ProgressBarStyle style = getStyle();
        style.knobBefore = style.knob;
        this.duration = duration;
        remaining = duration;
    }
    
    public TimerBar(final float duration, final boolean vertical, final ProgressBarStyle style) {
        this(duration, vertical, style.background, style.knob);
    }
    
    public boolean isDone() {
        return getVisualValue() == MAX;
    }
    
    public float elapsedTime() {
        return duration * getVisualValue() * DIFF_RECIPROCAL;
    }
    
    public float remainingTime() {
        return duration - elapsedTime();
    }
    
    public void start() {
        setAnimateDuration(remaining);
        setValue(MAX);
    }
    
    public void reset() {
        setAnimateDuration(0);
        setValue(MIN);
    }
    
    public void restart() {
        reset();
        remaining = duration;
        start();
    }
    
    public void stop() {
        setAnimateDuration(0);
        final float value = getVisualValue();
        setValue(value);
        remaining = duration - duration * value * DIFF_RECIPROCAL;
    }
    
    @Override
    public String toString() {
        return "Timer[" + elapsedTime() + " out of " + duration + "]";
    }
    
}

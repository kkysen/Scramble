package sen.khyber.scramble;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class AndroidLauncher extends AndroidApplication {
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new Scramble(), config);
    }
    
}

package sen.khyber.scramble.client;

import sen.khyber.scramble.Scramble;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class HtmlLauncher extends GwtApplication {
    
    @Override
    public GwtApplicationConfiguration getConfig() {
        return new GwtApplicationConfiguration(480, 320);
    }
    
    @Override
    public ApplicationListener createApplicationListener() {
        return new Scramble();
    }
    
}
package sen.khyber.scramble;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class Disposables {
    
    private static final Array<Disposable> disposables = new Array<>();
    
    public static void add(final Disposable disposable) {
        disposables.add(disposable);
    }
    
    public static void dispose() {
        for (final Disposable disposable : disposables) {
            disposable.dispose();
        }
        disposables.clear();
    }
    
}

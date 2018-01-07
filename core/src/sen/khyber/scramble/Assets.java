package sen.khyber.scramble;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import lombok.experimental.ExtensionMethod;

/**
 * 
 * 
 * @author Khyber Sen
 */
@ExtensionMethod({Assets.class})
public class Assets {
    
    private static final Path getBaseDir() {
        Path path = Paths.get("").toAbsolutePath();
        if (path.getFileName().endsWith("desktop")) {
            path = path.getParent();
        }
        return path;
    }
    
    private static final Path ASSETS = getBaseDir().resolve("android").resolve("assets");
    
    public static FileHandle asset(final String path) {
        return Paths.get(path).asset();
    }
    
    public static FileHandle asset(final Path path) {
        return Gdx.files.internal(ASSETS.resolve(path).toString());
    }
    
    public static Path toPath(final FileHandle fileHandle) {
        return fileHandle.file().toPath();
    }
    
}

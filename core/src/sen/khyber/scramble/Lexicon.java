package sen.khyber.scramble;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import lombok.experimental.ExtensionMethod;

/**
 * 
 * 
 * @author Khyber Sen
 */
@ExtensionMethod({Assets.class})
public class Lexicon {
    
    private static Lexicon ENGLISH;
    
    public static Lexicon english() {
        if (ENGLISH != null) {
            return ENGLISH;
        }
        try {
            return ENGLISH = new Lexicon(Paths.get("lexicons", "english.txt").asset().toPath());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private final Set<String> words;
    private final Map<Integer, List<String>> wordsByLength;
    
    private Map<Integer, List<String>> wordsByLength() {
        return words.stream().collect(Collectors.groupingBy(String::length));
    }
    
    public Lexicon(final Collection<String> words) {
        this.words = new HashSet<>(words);
        wordsByLength = wordsByLength();
    }
    
    public Lexicon(final Path path) throws IOException {
        words = Files.lines(path).collect(Collectors.toSet());
        wordsByLength = wordsByLength();
    }
    
    public boolean isWord(final String word) {
        return words.contains(word);
    }
    
    public String randomWord(final int length) {
        final List<String> words = wordsByLength.get(length);
        if (words == null) {
            return null;
        }
        return words.get(ThreadLocalRandom.current().nextInt(words.size()));
    }
    
    @Override
    public String toString() {
        return wordsByLength
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .map(Entry::toString)
                .collect(Collectors.joining("\n"));
    }
    
}
package corejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.ahocorasick.trie.Trie.TrieBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * An alternative to:
 * string.replaceAll("key1", "value1").replaceAll("key2", "value2")....
 *
 * ReplaceStrings, This class is used to replace multiple strings in a section of text, with high
 * time efficiency. The chosen algorithms were adapted from:
 * @see <a href = "https://stackoverflow.com/a/40836618">Stackoverflow link</a>
 */
public class EffecientMultipleStringReplacement {

    /**
     * replace, This replaces multiple strings in a section of text, according to the supplied
     * search and replace definitions. For maximum efficiency, this will automatically choose
     * between two possible replacement algorithms.
     * <p>
     * Performance note: If it is known in advance that the source text is long, then this method
     * signature has a very small additional performance advantage over the other method signature.
     * (Although either method signature will still choose the best algorithm.)
     */
    public static String replace(
            final String sourceText, final Map<String, String> searchReplaceDefinitions) {
        final boolean useLongAlgorithm
                = (sourceText.length() > 1000 || searchReplaceDefinitions.size() > 25);
        if (useLongAlgorithm) {
            // No parameter adaptations are needed for the long algorithm.
            return replaceUsing_AhoCorasickAlgorithm(sourceText, searchReplaceDefinitions);
        } else {
            // Create search and replace arrays, which are needed by the short algorithm.
            final ArrayList<String> searchList = new ArrayList<>();
            final ArrayList<String> replaceList = new ArrayList<>();
            final Set<Map.Entry<String, String>> allEntries = searchReplaceDefinitions.entrySet();
            for (Map.Entry<String, String> entry : allEntries) {
                searchList.add(entry.getKey());
                replaceList.add(entry.getValue());
            }
            return replaceUsing_StringUtilsAlgorithm(sourceText, searchList, replaceList);
        }
    }

    /**
     * replace, This replaces multiple strings in a section of text, according to the supplied
     * search strings and replacement strings. For maximum efficiency, this will automatically
     * choose between two possible replacement algorithms.
     * <p>
     * Performance note: If it is known in advance that the source text is short, then this method
     * signature has a very small additional performance advantage over the other method signature.
     * (Although either method signature will still choose the best algorithm.)
     */
    public static String replace(final String sourceText,
                                 final ArrayList<String> searchList, final ArrayList<String> replacementList) {
        if (searchList.size() != replacementList.size()) {
            throw new RuntimeException("ReplaceStrings.replace(), "
                    + "The search list and the replacement list must be the same size.");
        }
        final boolean useLongAlgorithm = (sourceText.length() > 1000 || searchList.size() > 25);
        if (useLongAlgorithm) {
            // Create a definitions map, which is needed by the long algorithm.
            HashMap<String, String> definitions = new HashMap<>();
            final int searchListLength = searchList.size();
            for (int index = 0; index < searchListLength; ++index) {
                definitions.put(searchList.get(index), replacementList.get(index));
            }
            return replaceUsing_AhoCorasickAlgorithm(sourceText, definitions);
        } else {
            // No parameter adaptations are needed for the short algorithm.
            return replaceUsing_StringUtilsAlgorithm(sourceText, searchList, replacementList);
        }
    }

    /**
     * replaceUsing_StringUtilsAlgorithm, This is a string replacement algorithm that is most
     * efficient for sourceText under 1000 characters, and less than 25 search strings.
     */
    private static String replaceUsing_StringUtilsAlgorithm(final String sourceText,
                                                            final ArrayList<String> searchList, final ArrayList<String> replacementList) {
        final String[] searchArray = searchList.toArray(new String[]{});
        final String[] replacementArray = replacementList.toArray(new String[]{});
        return StringUtils.replaceEach(sourceText, searchArray, replacementArray);
    }

    /**
     * replaceUsing_AhoCorasickAlgorithm, This is a string replacement algorithm that is most
     * efficient for sourceText over 1000 characters, or large lists of search strings.
     */
    private static String replaceUsing_AhoCorasickAlgorithm(final String sourceText,
                                                            final Map<String, String> searchReplaceDefinitions) {
        // Create a buffer sufficiently large that re-allocations are minimized.
        final StringBuilder sb = new StringBuilder(sourceText.length() << 1);
        final TrieBuilder builder = Trie.builder();
        builder.onlyWholeWords();
        builder.ignoreOverlaps();
        for (final String key : searchReplaceDefinitions.keySet()) {
            builder.addKeyword(key);
        }
        final Trie trie = builder.build();
        final Collection<Emit> emits = trie.parseText(sourceText);
        int prevIndex = 0;
        for (final Emit emit : emits) {
            final int matchIndex = emit.getStart();

            sb.append(sourceText.substring(prevIndex, matchIndex));
            sb.append(searchReplaceDefinitions.get(emit.getKeyword()));
            prevIndex = emit.getEnd() + 1;
        }
        // Add the remainder of the string (contains no more matches).
        sb.append(sourceText.substring(prevIndex));
        return sb.toString();
    }

    /**
     * main, This contains some test and example code.
     */
    public static void main(String[] args) {
        String shortSource = "The quick brown fox jumped over something. ";
        StringBuilder longSourceBuilder = new StringBuilder();
        for (int i = 0; i < 50; ++i) {
            longSourceBuilder.append(shortSource);
        }
        String longSource = longSourceBuilder.toString();
        HashMap<String, String> searchReplaceMap = new HashMap<>();
        ArrayList<String> searchList = new ArrayList<>();
        ArrayList<String> replaceList = new ArrayList<>();
        searchReplaceMap.put("fox", "grasshopper");
        searchReplaceMap.put("something", "the mountain");
        searchList.add("fox");
        replaceList.add("grasshopper");
        searchList.add("something");
        replaceList.add("the mountain");
        String shortResultUsingArrays = replace(shortSource, searchList, replaceList);
        String shortResultUsingMap = replace(shortSource, searchReplaceMap);
        String longResultUsingArrays = replace(longSource, searchList, replaceList);
        String longResultUsingMap = replace(longSource, searchReplaceMap);
        System.out.println(shortResultUsingArrays);
        System.out.println("----------------------------------------------");
        System.out.println(shortResultUsingMap);
        System.out.println("----------------------------------------------");
        System.out.println(longResultUsingArrays);
        System.out.println("----------------------------------------------");
        System.out.println(longResultUsingMap);
        System.out.println("----------------------------------------------");
    }
}

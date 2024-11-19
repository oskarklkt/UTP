/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package excercises1.anagrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    private final List<String> allWords;

    public Anagrams(String path) {
        allWords = readWordsFromFile(path);
    }

    private List<String> readWordsFromFile(String path) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.addAll(Arrays.asList(line.split(" ")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<List<String>> getSortedByAnQty() {
        List<List<String>> result = new ArrayList<>();

        for (String word : allWords) {
            boolean added = false;
            for (List<String> anagrams : result) {
                if (isAnagram(word, anagrams.get(0))) { 
                    anagrams.add(word);
                    added = true;
                    break;
                }
            }
            if (!added) {
                result.add(new ArrayList<>(Collections.singletonList(word)));
            }
        }

        result.sort(Comparator.<List<String>>comparingInt(List::size).reversed()
                .thenComparing(list -> list.get(0))); 

        return result;
    }

    private boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        char[] array1 = word1.toCharArray();
        char[] array2 = word2.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        return Arrays.equals(array1, array2);
    }

    public String getAnagramsFor(String word) {
        List<String> anagrams = allWords.stream()
                .filter(w -> isAnagram(w, word) && !word.equals(w))
                .collect(Collectors.toList()); 
        return word + ": " + anagrams;
    }
}

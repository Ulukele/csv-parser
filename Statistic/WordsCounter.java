package Statistic;

import java.util.*;

public class WordsCounter {

    private static Set<Word> countWords(Iterable<String> tokens) {
        TreeSet<Word> words = new TreeSet<>();

        for (String token: tokens) {
            Word searchable = new Word(token, 1);

            Word update = words.ceiling(searchable);
            if (update != null && update.getToken().equals(searchable.getToken())) { // This word already in set
                update.setCount(update.getCount() + 1);
            }
            else { // This word not in set yet
                words.add(searchable);
            }
        }
        return words;
    }

    private static Iterable<Word> sortWordsByCount(Collection<Word> words) {
        Set<Word> sorted = new TreeSet<>(new WordsCountComparator());
        sorted.addAll(words);
        return sorted;
    }

    public static Iterable<Word> getSortedStatistic(Iterable<String> tokens) {
        Set<Word> words = countWords(tokens);
        return sortWordsByCount(words);
    }
}

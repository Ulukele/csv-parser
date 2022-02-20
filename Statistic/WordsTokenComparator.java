package Statistic;

import java.util.Comparator;

public class WordsTokenComparator implements Comparator<Word> {
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getToken().compareTo(o2.getToken());
    }
}

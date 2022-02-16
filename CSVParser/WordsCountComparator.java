package CSVParser;

import java.util.Comparator;

public class WordsCountComparator implements Comparator<Word> {
    @Override
    public int compare(Word o1, Word o2) {
        if (o1.getCount() == o2.getCount()) {
            return o2.getToken().compareTo(o1.getToken());
        }
        return o2.getCount() - o1.getCount();
    }
}

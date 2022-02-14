package CSVParser;

import java.util.Comparator;

public class WordsFrequencyComparator implements Comparator<Word> {
    @Override
    public int compare(Word o1, Word o2) {
        if (o1.getFrequency() == o2.getFrequency()) {
            return o2.getToken().compareTo(o1.getToken());
        }
        return o2.getFrequency() - o1.getFrequency();
    }
}

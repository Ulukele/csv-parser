import Statistic.StatisticWriter;
import Statistic.TokensParser;
import Statistic.Word;
import Statistic.WordsCounter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length >= 2) {
            List<String> tokens = TokensParser.readFile(args[0]);
            int tokensCount = tokens.size();

            Iterable<Word> wordsStatistic = WordsCounter.getSortedStatistic(tokens);

            StatisticWriter.writeStatistic(args[1], wordsStatistic, tokensCount);
        }
        else {
            System.out.println("Not enough args provided (need 2)");
        }
    }
}

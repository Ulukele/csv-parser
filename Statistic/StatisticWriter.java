package Statistic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class StatisticWriter {
    private static void writeFile(String filename, StringBuilder str) {
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filename));
            writer.write(str.toString());
        }
        catch (IOException e) {
            System.err.println("Error while writing file " + filename + ": " + e.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    private static StringBuilder formatToCSV(Iterable<Word> words, int wordsCount) {
        StringBuilder sb = new StringBuilder();
        for (Word w: words) {
            sb.append(w.getToken());
            sb.append(",");
            sb.append(w.getCount());
            sb.append(",");
            sb.append((float) w.getCount() / wordsCount * 100);
            sb.append("\n");
        }
        return sb;
    }

    public static void writeStatistic(String filename, Iterable<Word> words, int wordsCount) {
        StringBuilder sb = formatToCSV(words, wordsCount);
        writeFile(filename, sb);
    }
}

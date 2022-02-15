package CSVParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CSVParser {
    private static List<String> readFile(String filename) {
        Reader reader = null;
        List<String> tokens = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            StringBuilder sb = new StringBuilder();
            int symbol;
            while (true) {
                symbol = reader.read();
                if (symbol == -1) { // End of stream case
                    if(sb.length() > 0) tokens.add(sb.toString());
                    break;
                }

                char character = (char) symbol;
                if (Character.isLetterOrDigit(character)) { // Add word to token
                    sb.append(character);
                }
                else { // Reach end of token, add it to list and clear builder
                    if(sb.length() > 0) tokens.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error while reading file " + filename + ": " + e.getLocalizedMessage());
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return tokens;
    }

    private static Iterable<Word> countWords(List<String> tokens) {
        TreeSet<Word> words = new TreeSet<>();

        for (String token: tokens) {
            Word searchable = new Word(token, 1);

            Word update = words.ceiling(searchable);
            if (update != null && update.equals(searchable)) { // This word already in set
                update.setFrequency(update.getFrequency() + 1);
            }
            else { // This word not in set yet
                words.add(searchable);
            }
        }

        Set<Word> sorted = new TreeSet<>(new WordsFrequencyComparator());
        sorted.addAll(words);
        return sorted;
    }

    private static void writeFile(String filename, Iterable<Word> words, int wordsCount) {
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filename));

            StringBuilder sb = new StringBuilder();
            for (Word w: words) {
                sb.append(w.getToken());
                sb.append(",");
                sb.append(w.getFrequency());
                sb.append(",");
                sb.append((float) w.getFrequency() / wordsCount * 100);
                sb.append("\n");
            }
            writer.write(sb.toString());
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

    public static void parseFile(String inFilename, String outFilename) {
        List<String> tokens = readFile(inFilename);
        int tokensCount = tokens.size();
        Iterable<Word> words = countWords(tokens);
        writeFile(outFilename, words, tokensCount);
    }
}

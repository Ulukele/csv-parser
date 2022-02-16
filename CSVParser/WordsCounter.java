package CSVParser;

import java.io.*;
import java.util.*;

public class WordsCounter {
    private static List<String> readFile(String filename) {
        Reader reader = null;
        List<String> tokens = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filename));
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

    private static Set<Word> countWords(List<String> tokens) {
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

    public static void parseFile(String inFilename, String outFilename) {
        List<String> tokens = readFile(inFilename);
        int tokensCount = tokens.size();
        Set<Word> words = countWords(tokens);
        Iterable<Word> sortedWords = sortWordsByCount(words);
        writeFile(outFilename, formatToCSV(sortedWords, tokensCount));
    }
}

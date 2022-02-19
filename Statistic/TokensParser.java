package Statistic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class TokensParser {
    public static List<String> readFile(String filename) {
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
}

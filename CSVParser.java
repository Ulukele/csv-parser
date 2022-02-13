import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSVParser {
    private List<String> readFile(String filename) {
        Reader reader = null;
        List<String> tokens = new ArrayList<>();
        try {
            reader = new InputStreamReader(new FileInputStream(filename));

            StringBuilder sb = new StringBuilder();
            int symbol;
            while (true) {
                symbol = reader.read();
                if (symbol == -1) { // End of stream case
                    tokens.add(sb.toString());
                    break;
                }

                char character = (char) symbol;
                if (Character.isLetterOrDigit(character)) { // Add word to token
                    sb.append(character);
                }
                else { // Reach end of token, add it to list and clear builder
                    tokens.add(sb.toString());
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

    public void parseFile(String inFilename, String outFilename) {
        List<String> tokens = readFile(inFilename);
        Set<Word> words = new HashSet<>();
    }

}

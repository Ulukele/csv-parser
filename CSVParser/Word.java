package CSVParser;

import java.util.Objects;

public class Word implements Comparable<Word>{
    private String token;
    private int frequency;

    Word(String token, int frequency) {
        this.token = token;
        this.frequency = frequency;
    }

    public String getToken() { return token; }
    public int getFrequency() { return frequency; }
    public void setFrequency(int value) { frequency = value; }
    public void setToken(String value) { token = value; }

    public boolean equals(Object o) {
        if (!(o instanceof Word wo)) {
            return false;
        }
        return Objects.equals(wo.getToken(), this.token);
    }

    public int hashCode() {
        return Objects.hash(token);
    }

    public int compareTo(Word w) {
        return token.compareTo(w.getToken());
    }
}

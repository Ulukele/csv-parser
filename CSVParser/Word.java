package CSVParser;

import java.util.Objects;

public class Word implements Comparable<Word>{
    private final String token;
    private int count;

    Word(String token, int frequency) {
        this.token = token;
        this.count = frequency;
    }

    public String getToken() { return token; }
    public int getCount() { return count; }
    public void setCount(int value) { count = value; }

    public boolean equals(Object o) {
        if (!(o instanceof Word wo)) {
            return false;
        }
        return Objects.equals(wo.getToken(), this.token) && this.count == wo.getCount();
    }

    public int hashCode() {
        return Objects.hash(token, count);
    }

    public int compareTo(Word w) {
        return token.compareTo(w.getToken());
    }
}

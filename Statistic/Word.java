package Statistic;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Word wo)) {
            return false;
        }
        return token.equals(wo.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public int compareTo(Word w) {
        return token.compareTo(w.getToken());
    }
}

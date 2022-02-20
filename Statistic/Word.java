package Statistic;

import java.util.Objects;

public class Word {
    private final String token;
    private int count;

    Word(String token, int frequency) {
        this.token = token;
        this.count = frequency;
    }

    public String getToken() { return token; }
    public int getCount() { return count; }
    public void setCount(int value) { count = value; }
}

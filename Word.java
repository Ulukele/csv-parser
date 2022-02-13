import java.util.Objects;

public class Word {
    private final String token;
    private int frequency;

    Word(String token) {
        this.token = token;
        this.frequency = 0;
    }

    public String getToken() { return token; }
    public int getFrequency() { return frequency; }
    public void setFrequency(int value) { frequency = value; }

    public boolean equals(Object o) {
        if (!(o instanceof Word wo)) {
            return false;
        }
        return Objects.equals(wo.token, this.token) && wo.frequency == this.frequency;
    }

    public int hashCode() {
        return Objects.hash(token, frequency);
    }
}

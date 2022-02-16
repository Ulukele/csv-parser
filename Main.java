import CSVParser.WordsCounter;

public class Main {
    public static void main(String[] args) {
        if (args.length >= 2) {
            WordsCounter.parseFile(args[0], args[1]);
        }
        else {
            System.out.println("Not enough args provided (need 2)");
        }
    }
}

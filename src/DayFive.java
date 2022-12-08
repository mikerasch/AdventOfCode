import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DayFive {
    public static List<Deque<Character>> game = new ArrayList<>();


    public static List<String> readFile() throws IOException {
        Path file = Paths.get("data.txt");
        return new ArrayList<>(Files.readAllLines(file));
    }

    private static void addToDeque(List<String> data) {
        Deque<Character> deque;
        for(int i = 0; i < 9; i++){
            deque = new LinkedList<>();
            String[] splitLine = data.get(i).split(" ");
            for(String j: splitLine){
                deque.add(j.charAt(0));
            }
            game.add(deque);
        }
    }


    private static void readMoves(List<String> data) {
        for(int i = 9; i < data.size(); i++){
            String[] split = data.get(i).split(" ");
            int numberToMove = Integer.parseInt(split[1]);
            int takeFrom = Integer.parseInt(split[3]);
            int goTo = Integer.parseInt(split[5]);
            startMovingStuff(numberToMove, takeFrom, goTo);
        }
    }

    private static void startMovingStuff(int numberToMove, int takeFrom, int goTo) {
        Deque<Character> take = game.get(takeFrom - 1); // might need to - 1
        Deque<Character> give = game.get(goTo - 1);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < numberToMove; i++){
            builder.append(take.removeLast());
        }
        // should just loop backwards but fawk it
        char[] test = builder.reverse().toString().toCharArray();
        for(char z: test){
            give.add(z);
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> data = readFile();
        addToDeque(data);
        readMoves(data);
        for(Deque<Character> hello: game){
            System.out.print(hello.removeLast());
        }
    }

}

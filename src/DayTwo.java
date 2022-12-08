import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DayTwo {
    public static final Map<String, Integer> win = Map.of(
            "A", 8,
            "B", 9,
            "C", 7
    );
    public static final Map<String, Integer> lose = Map.of(
            "A", 3,
            "B", 1,
            "C", 2
    );
    public static final Map<String,Integer> tie = Map.of(
            "A", 4,
            "B", 5,
            "C", 6
    );

    public static void main(String[] args) throws IOException {
        List<String> data = readFile();
        List<RoundAnswers> answers = getAnswers(data);
        System.out.println(forcedPoints(answers));
    }
    private static int forcedPoints(List<RoundAnswers> answers) {
        int points = 0;
        for(RoundAnswers roundAnswers: answers){
            points += getForcedPoints(roundAnswers.getEnemy(),roundAnswers.getFriendly());
        }
        return points;
    }

    private static int getForcedPoints(String enemy, String friendly) {
        int total;
        if(friendly.equals("Y")){
            total = tie.get(enemy);
        }
        else if(friendly.equals("Z")){
            total = win.get(enemy);
        }
        else{
            total = lose.get(enemy);
        }
        return total;
    }

    private static List<RoundAnswers> getAnswers(List<String> data) {
        List<RoundAnswers> answers = new ArrayList<>();
        for (String currentIndex : data) {
            answers.add(new RoundAnswers(String.valueOf(currentIndex.charAt(0)), String.valueOf(currentIndex.charAt(2))));
        }
        return answers;
    }
    public static List<String> readFile() throws IOException {
        Path path = Paths.get("data.txt");
        return Files.readAllLines(path);
    }

}
class RoundAnswers{
    private final String enemy;
    private final String friendly;
    public RoundAnswers(String first, String second){
        this.enemy = first;
        this.friendly = second;
    }

    @Override
    public String toString() {
        return "enemy: " + enemy + " friendly: " + friendly;
    }

    public String getEnemy() {
        return enemy;
    }

    public String getFriendly() {
        return friendly;
    }
}

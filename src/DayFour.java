import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayFour {
    public static void main(String[] args) throws IOException {
        List<String> content = readFile();
        List<Pair> paired = dividePairs(content);
        System.out.println(getUselessWork(paired));
    }

    public static List<Pair> dividePairs(List<String> content){
        List<Pair> pairs = new ArrayList<>();
        for(String line: content){
            String[] lineArray = line.split(",");
            String[] firstPair = lineArray[0].split("-");
            String[] secondPair = lineArray[1].split("-");
            pairs.add(new Pair(Integer.parseInt(firstPair[0]),Integer.parseInt(firstPair[1])));
            pairs.add(new Pair(Integer.parseInt(secondPair[0]),Integer.parseInt(secondPair[1])));
        }

        return pairs;
    }

    public static int getUselessWork(List<Pair> paired){
        int total = 0;
        for(int i = 0; i < paired.size(); i+=2){
            Pair first = paired.get(i);
            Pair second = paired.get(i + 1);
//            if((first.first <= second.first && first.second >= second.second) || (second.first <= first.first && second.second >= first.second)){
//                total++;
//            }
            if((second.first >= first.first && second.first <= first.second) || (first.first >= second.first && first.first <= second.second)){
                total++;
            }
        }
        return total;
    }
    public static List<String> readFile() throws IOException {
        Path file = Paths.get("data.txt");
        return new ArrayList<>(Files.readAllLines(file));
    }
}

class Pair{
    int first;
    int second;

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}

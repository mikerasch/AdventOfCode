
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DayOne {
    public static int[] dumbSolution;
    public static int size = 0;
    public static ArrayList<Integer> readFile(String fileName) throws IOException {
        Path file = Paths.get(fileName);
        ArrayList<Integer> data = new ArrayList<>();
        Files.lines(file).forEach(x -> {
            if(x.equals("")){
                data.add(-1);
                size++;
            }
            else{
                data.add(Integer.valueOf(x));
            }
        });
        return data;
    }
    public static int getHighestCalorie(ArrayList<Integer> data){
        dumbSolution = new int[size+1];
        int count = 0;
        for(int calorie: data){
            if(calorie == -1){
                count++;
            }
            else{
                dumbSolution[count] += calorie;
            }
        }
        Comparator<Integer> comparator = (o1, o2) -> o2 - o1;
        List<Integer> results = Arrays.stream(dumbSolution).boxed().sorted(comparator).limit(3).toList();
        return results.get(0) + results.get(1) + results.get(2);
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> data = readFile("data.txt");
        System.out.println(getHighestCalorie(data));
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayEight {
    public static void main(String[] args) throws IOException {
        List<List<Integer>> data = readFile();
        getVisibleTrees(data);
    }

    /**
     * Could start looping (inside) the box if wanting to. (Starting at 1 on both i and j)
     * The outer shell does not matter and will always be constant.
     * Would require a way to get the parameter of the matrix however, which does not seem worth it
     * as this solution covers that
     */
    private static void getVisibleTrees(List<List<Integer>> data) {
        int total = 0;
        int count = 0;
        for(int i = 0; i < data.size(); i++){
            for(int j = 0; j < data.get(i).size(); j++){
                List<?> list = getAllFourDirections(i,j,data);
                if((boolean)list.get(0)){
                    total++;
                    count = Math.max(count,(Integer)list.get(1));
                }
            }
        }
        System.out.println(total);
        System.out.println(count);
    }

    private static List<?> getAllFourDirections(int i, int j,List<List<Integer>> data) {
        int current = data.get(i).get(j);
        boolean top = true;
        boolean bottom = true;
        boolean left = true;
        boolean right = true;
        int topInt = 0;
        int bottomInt = 0;
        int leftInt = 0;
        int rightInt = 0;
        // here
        for(int x = i - 1; x >= 0; x--){
            ++topInt;
            if(data.get(x).get(j) >= current){
                top = false;
                break;
            }
        }

        // bottom
        for(int x = i + 1; x < data.size(); x++){
            ++bottomInt;
            if(data.get(x).get(j) >= current){
                bottom = false;
                break;
            }
        }

        // left
        for(int x = j - 1; x >= 0; x--){
            ++leftInt;
            if(data.get(i).get(x) >= current){
                left = false;
                break;
            }
        }

        // right
        for(int x = j + 1; x < data.get(i).size(); x++){
            ++rightInt;
            if(data.get(i).get(x) >= current){
                right = false;
                break;
            }
        }
        return List.of(top || bottom || left || right, topInt * bottomInt * leftInt * rightInt);
    }

    public static List<List<Integer>> readFile() throws IOException {
        List<List<Integer>> lists = new ArrayList<>();
        Path file = Paths.get("data.txt");
        Files.lines(file).forEach(x -> {
            List<Integer> list = new ArrayList<>();
            String[] seperate = x.split("");
            for(String i: seperate){
                list.add(Integer.parseInt(i));
            }
            lists.add(list);
        });
        return lists;
    }
}

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

    private static void getVisibleTrees(List<List<Integer>> data) {
        int total = 0;
        int count = 0;
        for(int i = 0; i < data.size(); i++){
            for(int j = 0; j < data.get(i).size(); j++){
                if(getAllFourDirections(i,j,data)){
                    total++;
                    count = Math.max(count,getCount(i,j,data));
                }
            }
        }
        System.out.println(total);
        System.out.println(count);
    }

    private static boolean getAllFourDirections(int i, int j,List<List<Integer>> data) {
        int current = data.get(i).get(j);
        boolean top = true;
        boolean bottom = true;
        boolean left = true;
        boolean right = true;
        // here
        for(int x = i - 1; x >= 0; x--){
            if(data.get(x).get(j) >= current){
                top = false;
                break;
            }
        }

        // bottom
        for(int x = i + 1; x < data.size(); x++){
            if(data.get(x).get(j) >= current){
                bottom = false;
                break;
            }
        }

        // left
        for(int x = j - 1; x >= 0; x--){
            if(data.get(i).get(x) >= current){
                left = false;
                break;
            }
        }

        // right
        for(int x = j + 1; x < data.get(i).size(); x++){
            if(data.get(i).get(x) >= current){
                right = false;
                break;
            }
        }
        return top || bottom || left || right;
    }

    private static int getCount(int i, int j,List<List<Integer>> data) {
        int current = data.get(i).get(j);
        int top = 0;
        int bottom = 0;
        int right = 0;
        int left = 0;
        // top
        for(int x = i - 1; x >= 0; x--){
            top++;
            if(data.get(x).get(j) >= current){
                break;
            }
        }

        // bottom
        for(int x = i + 1; x < data.size(); x++){
            bottom++;
            if(data.get(x).get(j) >= current){
                break;
            }
        }

        // left
        for(int x = j - 1; x >= 0; x--){
            left++;
            if(data.get(i).get(x) >= current){
                break;
            }
        }

        // right
        for(int x = j + 1; x < data.get(i).size(); x++){
            right++;
            if(data.get(i).get(x) >= current){
                break;
            }
        }
        return top * bottom * left * right;
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

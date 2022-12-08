import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class DaySix {
    public static void main(String[] args) throws IOException {
        String content = readFile();
        System.out.println(getLongestMarker(content));
    }

    private static int getLongestMarker(String content) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < content.length() - 14; i++){
            String possibleMatch = content.substring(i,i+14);
            possibleMatch.chars().forEach(x -> set.add((char) x));
            if(set.size() == 14){
                return i + 14;
            }
            set.clear();
            // much better way of doing it imo
/*            if(content.substring(1,i+14).chars().distinct().count() == 14){
                return i + 14;
            }*/
        }
        return -1;
    }

    public static String readFile() throws IOException {
        Path path = Path.of("data.txt");
        return Files.readString(path);
    }
}

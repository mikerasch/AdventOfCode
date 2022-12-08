import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayThree {
    private static final ArrayList<String> threeAPiece = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        List<String> content = readFile();
        splitInHalve(content);
        System.out.println(calculateBundledPoints());
    }

    private static int calculateBundledPoints() {
        int total = 0;
        List<Character> checkContained;
        for (String s : threeAPiece) {
            checkContained = new ArrayList<>();
            String[] split = s.split(" ");
            String first = split[0];
            String second = split[1];
            String third = split[2];
            for (int j = 0; j < first.length(); j++) {
                if (second.indexOf(first.charAt(j)) >= 0 && third.indexOf(first.charAt(j)) >= 0 && !checkContained.contains(first.charAt(j))) {
                    total += pointsOnCharacter(first.charAt(j));
                }
                checkContained.add(first.charAt(j));
            }
        }
        return total;
    }

    private static int pointsOnCharacter(char charAt) {
        if(charAt >= 97 && charAt <= 122){
            return charAt - 'a' + 1;
        }
        else {
            return charAt - 38;
        }
    }

    private static void splitInHalve(List<String> content) {
        StringBuilder data = new StringBuilder();
        int count = 0;
        for (String s : content) {
            count++;
            data.append(s).append("2");
            if (count % 3 == 0) {
                threeAPiece.add(data.toString());
                data = new StringBuilder();
            }
        }
    }

    public static List<String> readFile() throws IOException {
        Path file = Paths.get("data.txt");
        return new ArrayList<>(Files.readAllLines(file));
    }
}

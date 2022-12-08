import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DaySeven {
    private static final List<Directory> directoryList = new ArrayList<>();
    public static Directory directory = new Directory(null);
    public static void main(String[] args) throws IOException {
        List<String> content = readFile();
        parseLines(content);
        getPartOne();
        getPartTwo();
    }

    private static void getPartTwo() {
        List<Integer> list = new ArrayList<>();
        int total = 0;
        for(Directory dir: directoryList){
            total += dir.sum;
        }
        int deleteSpace = 30000000 - (70000000 - total);
        for(Directory dir: directoryList){
            list.add(dir.sumSize());
        }
       Optional<Integer> answer = list.stream().sorted().filter(x -> x > deleteSpace).findFirst();
        System.out.println(answer.get());
    }

    private static void getPartOne() {
        int total = 0;
        for(Directory dir: directoryList){
            int localTotal = dir.sumSize();
            if(localTotal <= 100000){
                total += localTotal;
            }
        }
        System.out.println(total);
    }


    private static void parseLines(List<String> content) {
        directoryList.add(directory);
        Directory currentDir = directory;
        for(String data: content){
            if(data.contains("$ cd ..")){
                currentDir = currentDir.parentDirectory;
            }
            else if(data.contains(" cd")){
                Directory newDirectory = new Directory(currentDir);
                directoryList.add(newDirectory);
                currentDir.childDirectories.add(newDirectory);
                currentDir = newDirectory;
            }
            else if(data.matches("\\d+.*")){
                String[] splitData = data.split(" ");
                currentDir.sum += Integer.parseInt(splitData[0]);
            }
        }
    }
    public static List<String> readFile() throws IOException {
        Path path = Path.of("data.txt");
        return Files.readAllLines(path);
    }
}

class Directory{
    public Directory parentDirectory;
    public List<Directory> childDirectories;
    public int sum = 0;
    public Directory(Directory directory){
        childDirectories = new ArrayList<>();
        this.parentDirectory = directory;
    }
    public int sumSize() {
        Queue<Directory> directoryQueue = new LinkedList<>();
        directoryQueue.add(this);
        int total = 0;
        while(!directoryQueue.isEmpty()){
            Directory currentdir = directoryQueue.poll();
            total += currentdir.sum;
            directoryQueue.addAll(currentdir.childDirectories);
        }
        return total;
    }
    @Override
    public String toString() {
        return String.valueOf(sum);
    }
}


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static String rootTwo;
    private static String cRootTwo;

    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";


    private static List<Integer> differences = new ArrayList();

    public static void main(String[] args) {
        //TODO 1: Add a text document with your Root-Two implementation
        //TODO 2: Change source to this textfile
        String source = "src/cRootTwo.txt";
        readCFile(source);
        readDigits(cRootTwo.length()-2);
        differences(cRootTwo, rootTwo);
        System.out.println("C-Programm:");
        printDifferences(cRootTwo);
        System.out.println("Actual root two:");
        printDifferences(rootTwo);
    }

    private static void readDigits(int digits) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/RootTwo.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int iterations = digits/80 + 1;
            while (iterations != 0) {
                sb.append(line);
                line = br.readLine();
                iterations--;
            }
            rootTwo = sb.substring(0, digits+2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void differences(String first, String second) {
        int index = 0;
        int positionInString = 0;
        String cs1 = first;
        String cs2 = second;
        while(true) {
            index = indexOfDifference(cs1, cs2);
            if(index == -1){
                break;
            }
            differences.add(index+positionInString);
            cs1 = cs1.substring(index+1);
            cs2 = cs2.substring(index+1);
            positionInString = positionInString + index + 1;
        }
    }

    private static void readCFile(String source) {
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            cRootTwo = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int indexOfDifference(CharSequence cs1, CharSequence cs2) {
        if (cs1 == cs2) {
            return -1;
        }
        if (cs1 == null || cs2 == null) {
            return 0;
        }
        int i;
        for (i = 0; i < cs1.length() && i < cs2.length(); ++i) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                break;
            }
        }
        if (i < cs2.length() || i < cs1.length()) {
            return i;
        }
        return -1;
    }

    private static void printDifferences(String difference) {
        for(int i = 0; i < difference.length(); i++) {
            if(differences.contains(i)) {
                System.out.print(RED + difference.charAt(i) + RESET);
            }
            else {
                System.out.print(difference.charAt(i));
            }
        }
        System.out.println("\n");
    }
}

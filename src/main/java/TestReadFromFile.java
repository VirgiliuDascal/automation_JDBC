import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestReadFromFile {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/vdascal/Documents/DataForInsert.csv"));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            System.out.print(scanner.next()+"|");
        }
        scanner.close();
    }
}

import java.io.IOException;
import java.util.Scanner;


public class printCourse {
	public static void main(String[] args) throws IOException {
		Util.readFile("data.txt");
		Util.parse();
		Scanner scanner = new Scanner(System.in);
		String word = scanner.nextLine();
		Util.printCourse(word);
	}
}

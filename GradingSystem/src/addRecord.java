import java.io.IOException;


public class addRecord {
	public static void main(String[] args) throws IOException {
		Util.readFile("data.txt");
		Util.addRecord();
		Util.parse();
		Util.writeBack();
	}
}

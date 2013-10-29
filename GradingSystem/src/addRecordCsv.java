import java.io.IOException;


public class addRecordCsv {
	public static void main(String[] args) throws IOException {
		Util.readFile("data.txt");
		Util.readFile("record.csv");
		Util.parse();
		Util.writeBack();
	}
}

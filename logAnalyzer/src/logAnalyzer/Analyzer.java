package logAnalyzer;

import java.io.IOException;
import java.text.ParseException;

public class Analyzer {
	public static void main(String[] args) throws IOException, ParseException {
		Util util = new Util();
		util.readFile();
		util.doAnalyze();
		util.showAnalyze();
	}
}

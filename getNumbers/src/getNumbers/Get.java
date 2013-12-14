package getNumbers;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Get {
	static String str;
	public static void main(String[] args) throws IOException {
		getInput();
		System.out.println("=("+getNumber().replaceAll(" ", "+")+")/10");
	}
	
	public static void getInput() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			str += scanner.nextLine();
		}
	}
	public static String getNumber()
	{
		  StringBuffer sBuffer = new StringBuffer();
		  Pattern p = Pattern.compile("[0-9]+.[0-9]*|[0-9]*.[0-9]+|[0-9]+");
		  Matcher m = p.matcher(str);
		  while (m.find()) {
			  sBuffer.append(m.group());
		  }
		  return sBuffer.toString();
	}
}
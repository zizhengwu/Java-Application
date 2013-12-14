package fourColumnsGet;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class GetIt {
	static Vector <Double> first = new Vector <Double> (); 
	static Vector <Double> second = new Vector <Double> (); 
	static Vector <Double> third = new Vector <Double> (); 
	static Vector <Double> fourth = new Vector <Double> (); 
	
	public static void getInput() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			String[] words = str.split(" ");
			first.add(Double.parseDouble(words[0]));
			second.add(Double.parseDouble(words[1]));
//			third.add(Double.parseDouble(words[2]));
//			fourth.add(Double.parseDouble(words[3]));
		}
	}
	
	public static void parse() {
		System.out.print("=(");
		for (int i = 0; i < first.size(); i++) {
			System.out.print("+" + Double.toString(first.get(i)));
		}
		
		System.out.println(") / " + first.size());
		
		System.out.print("=(");
		for (int i = 0; i < second.size(); i++) {
			System.out.print("+" + Double.toString(second.get(i)));
		}
		System.out.println(") / " + second.size());
	}
	
	public static void main(String[] args) throws IOException {
		getInput();
		parse();
	}
	
}

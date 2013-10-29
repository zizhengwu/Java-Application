import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collection;

public class Util {

	static Vector <Entry> entries = new Vector<Entry>();
	static Map <String, Collection<Entry>> nameMap = new HashMap<String, Collection<Entry>>();
	static Map <String, Collection<Entry>> courseMap = new HashMap<String, Collection<Entry>>();

	public static void readFile(String args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(args));

		while (in.ready()) {
			String line = in.readLine();
			String[] words = line.split("[\\s,-]+");
			Entry entry = new Entry(words[0], words[1], Integer.parseInt(words[2]));
			entries.add(entry);
		}
		in.close();
	}

	public static void parse() {

		for (int i = 0; i < entries.size(); i++) {
			Collection<Entry> values = nameMap.get(entries.get(i).getName());
			if (values==null) {
				values = new ArrayList<Entry>();
				nameMap.put(entries.get(i).getName(), values);
			}
			else {
				java.util.Iterator<Entry> iter = values.iterator();
				while (iter.hasNext()) {
					Entry object = iter.next();
					if (object.getName().equals(entries.get(i).getName()) && object.getCourseName().equals(entries.get(i).getCourseName())) {
						iter.remove();
					}	
				}
			}
			values.add(entries.get(i));
		}

		for (int i = 0; i < entries.size(); i++) {
			Collection<Entry> values = courseMap.get(entries.get(i).getCourseName());
			if (values==null) {
				values = new ArrayList<Entry>();
				courseMap.put(entries.get(i).getCourseName(), values);
			}
			values.add(entries.get(i));
		}
	}


	public static void printStudent(String args) {
		int count = 0;
		int total = 0;
		Collection<Entry> values = nameMap.get(args);
		if (values == null) {
			System.out.println("Student not found");
		}
		else {
			for(Entry object : values) {
				count ++;
				total += object.getScore();
				System.out.println(object.getCourseName());
				System.out.println(object.getScore());
			}
		}
		System.out.println("Total:");
		System.out.println(Integer.toString(total));
		System.out.println("Average:");
		System.out.println(Integer.toString(total / count));

	}


	public static void printCourse(String args) {
		int count = 0;
		int total = 0;
		Collection<Entry> values = courseMap.get(args);
		if (values == null) {
			System.out.println("Course not found");
		}
		else {
			for(Entry object : values) {
				count ++;
				total += object.getScore();
				System.out.println(object.getName());
				System.out.println(object.getScore());
			}
		}
		System.out.println("Count:");
		System.out.println(Integer.toString(count));
		System.out.println("Average:");
		System.out.println(Integer.toString(total / count));
	}

	public static void addRecord() {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] words = line.split("[\\s,-]+");
		Entry entry = new Entry(words[0], words[1], Integer.parseInt(words[2]));
		entries.add(entry);
	}
	
	public static void writeBack() throws IOException {
		FileWriter writer = new FileWriter("data.txt", false);
		for (Iterator<Collection<Entry>> iterator = nameMap.values().iterator(); iterator
				.hasNext();) {
			Collection<Entry> values = iterator.next();
			for (Entry object : values) {
				writer.write(object.getName());
				writer.write(", ");
				writer.write(object.getCourseName());
				writer.write(", ");
				writer.write(Integer.toString(object.getScore()));
				writer.write("\n");
			}	
		}
		writer.flush();
		writer.close();
	}
}
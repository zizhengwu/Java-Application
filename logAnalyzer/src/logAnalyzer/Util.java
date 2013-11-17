package logAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Calendar;

import logAnalyzer.model.*;

public class Util {
	static Vector <Entry> entries = new Vector<Entry>();
	HashMap <String, Integer> mapPage = new HashMap <String, Integer>();
	HashMap <String, Integer> mapIP = new HashMap <String, Integer>();
	HashMap <String, Integer> mapError = new HashMap <String, Integer>();
	int busyHour[] = new int[24];
	int busyWeekDay[] = new int[7];
	int busyDay[] = new int[31];
	Double totalBytes = (double) 0;
	
	public void doAnalyze() throws ParseException {

		for (Entry entry : entries) {
			String countPage = entry.getOperation().getPath();
			String countIp = entry.getIp().getIp();
			String countBytes = entry.getTotalbytes().getBytes();
			Operation operation = entry.getOperation();
			theMostPopularPage(countPage);
			theMostPopularIP(countIp);
			theBrokenLink(operation);
			if (!countBytes.equals("-")) {
				totalBytes += Double.parseDouble(countBytes);
			}
			theBusiestPeriods(entry.getTime());
			}
	}
	
	int largestInArrayIndex(int t[]) {
		int number = t[0];
		int index = 0;
		for (int i = 1; i < t.length; i++) {
			if (t[i] > number) {
				number = t[i];
				index = i;
			}
		}
		index += 1;
		return index;
	}

	public void showAnalyze() {
		String tempString = null;
		Integer tempInteger = 0;

		// whether other sites appear to have broken links to this site’s pages;
		for (Map.Entry <String, Integer> it : mapError.entrySet()) {
			System.out.println("The link " + it.getKey() + " is broken");
		}

		// which are the most popular pages they provide;
		for (Map.Entry <String, Integer> it : mapPage.entrySet()) {
			if (it.getValue() > tempInteger) {
				tempInteger = it.getValue();
				tempString = it.getKey();
			}
		}
		System.out.println("The mostly viewed Page is: " + tempString);
	
		// which ip took the most pages from the site;
		tempString = null;
		tempInteger = 0;
		for (Map.Entry <String, Integer> it : mapIP.entrySet()) {
			if (it.getValue() > tempInteger) {
				tempInteger = it.getValue();
				tempString = it.getKey();
			}
		}
		System.out.println("The most active IP is: " + tempString);

		// how much data is being delivered to clients;
		System.out.println("The total bytes delivered is: " + totalBytes);

		// the busiest periods over the course of a day, or week, or month.
		String[] dict = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		System.out.println("The busiest hour of a day is hour " + largestInArrayIndex(busyHour));
		System.out.println("The busiest day of a week is the day " + dict[largestInArrayIndex(busyWeekDay) - 1]);
		System.out.println("The busiest day of a month is the day " + largestInArrayIndex(busyDay));
	}
	
	void theBusiestPeriods(Time time) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(time.getYear()), monthStringToInt(time.getMonth()), Integer.parseInt(time.getDay()));
		// course of a day
		busyHour[Integer.parseInt(time.getHour())] += 1;
		// course of a week
		busyWeekDay[c.get(Calendar.DAY_OF_WEEK) - 1] += 1;
		// course of a month
		busyDay[Integer.parseInt(time.getDay()) - 1] += 1;
	}
	

	void theMostPopularPage(String countPage) {
		if(mapPage.containsKey(countPage)){
		     mapPage.put(countPage, mapPage.get(countPage)+1);
		}
		else
		{
		    mapPage.put(countPage, 1);
		}
	}

	void theMostPopularIP(String countIP) {
		if(mapIP.containsKey(countIP)){
		     mapIP.put(countIP, mapIP.get(countIP)+1);
		}
		else
		{
		    mapIP.put(countIP, 1);
		}
	}
	
	void theBrokenLink(Operation operation) {
		if (Integer.parseInt(operation.getCode()) >= 400) {
			if(!mapError.containsKey(operation.getPath())){
			    mapError.put(operation.getPath(), 1);
			}
		}
	}
	
	public void readFile() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("access.log"));

		while (in.ready()) {
			String line = in.readLine();
			String line0;
			if (line.matches(".* - \\w+ \\[.*")) {
				line0 = line.replaceAll(" - \\w+ \\[", " - - \\[");
			}
			else {
				line0 = line;
			}
			String [] parts1 = line0.split (" \\- \\- \\[", 2);
			String [] parts2 = parts1[1].split ("] \"", 2);
			String [] parts3 = parts2[1].split (" ", 2);
			String [] parts4 = parts3[1].split (" ", 2);
			String [] parts5 = parts4[1].split ("\" ", 2);
			String [] parts6 = parts5[1].split (" ", 2);
			Ip ip = new Ip();
			ip.setIp(parts1[0]);
			Operation operation = new Operation();
			operation.setOp(parts3[0]);
			operation.setPath(parts4[0]);
			operation.setProtocol(parts5[0]);
			operation.setCode(parts6[0]);
			Time time = new Time();
			time.setTime(parts2[0]);
			time.parse();
			TotalBytes totalBytes = new TotalBytes();
			totalBytes.setBytes(parts6[1]);
			Entry entry = new Entry(ip, operation, time, totalBytes);
			entries.add(entry);
		}
		in.close();
	}
	
	int monthStringToInt(String month) {
		int mon = 0;
		String[] dict = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
		for (int i =0; i < dict.length; i++) {
			if (month.equals(dict[i])) {
				mon = i + 1;
			}
		}
		return mon;
	}
}
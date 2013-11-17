package logAnalyzer.model;

public class Time {
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public void parse(){
		String line0 = time;
		String [] parts1 = line0.split ("/", 2);
		String [] parts2 = parts1[1].split ("/", 2);
		String [] parts3 = parts2[1].split (":", 2);
		String [] parts4 = parts3[1].split (":", 2);
		String [] parts5 = parts4[1].split (":", 2);
		String [] parts6 = parts5[1].split (" ", 2);
		day = parts1[0];
		month = parts2[0];
		year = parts3[0];
		hour = parts4[0];
		minute = parts5[0];
		second = parts6[0];
		timeZone = parts6[1];
	}
	private String day;
	private String month;
	private String year;
	private String hour;
	private String minute;
	private String second;
	private String timeZone;
	private String time;
}

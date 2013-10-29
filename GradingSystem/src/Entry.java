
public class Entry {
	private String name;
	private String courseName;
	private int score;
	
	Entry(String a, String b, int c)
	{
		setName(a);
		setCourseName(b);
		setScore(c);
	}

	public  void printEntry() {
		System.out.println(getName());
		System.out.println(getCourseName());
		System.out.println(getScore());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

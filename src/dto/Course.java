package dto;

import java.io.Serializable;

public class Course extends BaseDTO implements Comparable<Course>, Serializable {
	private Integer no;
	private String courseName;
	private String category;
	private Integer score;

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public int compareTo(Course obj) {
		return (this.no - obj.no);
	}

}

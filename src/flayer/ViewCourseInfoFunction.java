package flayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dlayer.CourseDAO;
import dto.Course;
import dto.CourseInfo;

public class ViewCourseInfoFunction {
	String tablePath;

	public ViewCourseInfoFunction(String tablePath) {
		this.tablePath = tablePath;
	}

	public List<CourseInfo> getCourseInfoList() throws FileNotFoundException, ClassNotFoundException, IOException{
		List<Course> selectCourse = new CourseDAO(tablePath).select();
		List<CourseInfo> courseInfoList = new ArrayList<>();

		for(Course course:selectCourse) {
			//受講データ作成
			CourseInfo courseInfo = new CourseInfo();
			courseInfo.setCourseName(course.getCourseName());
			courseInfo.setCategory(course.getCategory());
			courseInfo.setNo(course.getNo());
			courseInfo.setScore(course.getScore());
			/*
			 *
			 * ToDo：成績評価
			 *
			 */
			courseInfoList.add(courseInfo);
		}

		return courseInfoList;

	}
}

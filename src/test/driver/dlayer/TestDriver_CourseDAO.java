package test.driver.dlayer;

import java.io.FileNotFoundException;
import java.io.IOException;

import dlayer.CourseDAO;
import dto.Course;

public class TestDriver_CourseDAO {

	public static void main(String[] args) {
		CourseDAO testCourseDAO;
		String path = "WebContent/WEB-INF/resource/COURSE.bin";

		try {
			testCourseDAO = new CourseDAO(path);

			Course data = new Course();
			data.setNo(3);
			data.setCourseName("course3");
			data.setCategory("IT");
			data.setScore(30);

			testCourseDAO.insert(data);
			testCourseDAO.select().stream().forEach(e -> System.out
					.println(e.getNo() + "," + e.getCourseName() + "," + e.getCategory() + "," + e.getScore()));
		} catch (IOException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
			try {
				testCourseDAO = new CourseDAO(path);
				testCourseDAO.select().stream().forEach(e -> System.out
						.println(e.getNo() + "," + e.getCourseName() + "," + e.getCategory() + "," + e.getScore()));
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

}

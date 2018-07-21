package dlayer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;

import dto.Course;

public class CourseDAO extends DataBaseOperator<Course> {

	private ArrayList<Course> courseList;

	public CourseDAO(String tablePath) throws IOException, ClassNotFoundException {
		super(tablePath);
		if (!tableFile.exists()) {
			tableFile.createNewFile();
			try (ObjectOutputStream objOutStream = new ObjectOutputStream(
					new FileOutputStream(tableFile));) {
				objOutStream.writeObject(new ArrayList<Course>());
				objOutStream.close();
			}
			courseList = new ArrayList<>();

		}else {
			courseList = readTableFile();
		}
	}

	@Override
	public ArrayList<Course> select() throws FileNotFoundException, IOException, ClassNotFoundException {
		courseList = readTableFile();
		return (ArrayList<Course>) courseList.stream().sorted().collect(Collectors.toList());
	}

	@Override
	public int insert(Course data) throws Exception {
		int cnt=0;
		for (int i=0;i<courseList.size();i++) {
			Course course = courseList.get(i);
			if(course.getNo().equals(data.getNo())) {
				throw new Exception("主キー重複です。");
			}
		}
		courseList.add(data);
		++cnt;
		writeTableFile(courseList);
		return cnt;
	}

	@Override
	public int update(Course data) throws FileNotFoundException, IOException {
		int cnt = 0;
		for (int i=0;i<courseList.size();i++) {
			Course course = courseList.get(i);
			if(course.getNo().equals(data.getNo())) {
				courseList.set(i, data);
				++cnt;
				writeTableFile(courseList);
				break;
			}
		}
		return cnt;
	}

	@Override
	public int delete(Course data) throws FileNotFoundException, IOException {
		int cnt = 0;
		for (int i=0;i<courseList.size();i++) {
			Course course = courseList.get(i);
			if(course.getNo().equals(data.getNo())) {
				courseList.remove(i);
				++cnt;
				writeTableFile(courseList);
				break;
			}
		}
		return cnt;
	}
}

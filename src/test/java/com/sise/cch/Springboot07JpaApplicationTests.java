package com.sise.cch;

import com.sise.cch.dao.TeacherRepository;
import com.sise.cch.dao.UserRepository;
import com.sise.cch.domain.Course;
import com.sise.cch.domain.Teacher;
import com.sise.cch.domain.User;


import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

@SpringBootTest
class Springboot07JpaApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TeacherRepository teacherRepository;
	@Test
	void contextLoads() {
		User user = new User();
		user.setId(3);
		user.setName("王五");
		user.setAge(32);
		user.setAddress("上海");
		userRepository.save(user);
	}

	@Test
	void testFindById(){
		Optional<User> user = userRepository.findById(2);
		System.out.println(user);
	}

	@Test
	void testFindAll(){
		List<User> userList = userRepository.findAll();
		for (User user:userList) {
			System.out.println(user);
		}
	}

	@Test
	void testSort(){
		//Order order = new Order(Direction.DESC,"id");
		Sort sort = Sort.by(Sort.Direction.DESC,"id");
		List<User> users = userRepository.findAll(sort);
		for (User user:users){
			System.out.println(user);
		}
	}

	@Test
	void testPaging(){
		Sort sort = Sort.by(Sort.Direction.ASC,"age");
		Pageable pageable =PageRequest.of(0,2,sort);
		Page<User> page = userRepository.findAll(pageable);
		System.out.println("总条数："+page.getTotalElements());
		System.out.println("总页数:"+page.getTotalPages());
		System.out.println("当前页数:"+(page.getNumber()+1));
		for (User user:page){
			System.out.println(user);
		}
	}

	@Test
	void testOneToMany(){
		User user = new User();
		user.setName("朱八");
		user.setAge(23);
		user.setAddress("东莞");
		User user1 = new User();
		user1.setName("易九");
		user1.setAge(21);
		user1.setAddress("东莞");

		Teacher teacher = new Teacher();
		teacher.setName("王大致");

		teacher.getUsers().add(user);
		teacher.getUsers().add(user1);
		user.setTeacher(teacher);
		user1.setTeacher(teacher);

		//userRepository.save(user);
		userRepository.save(user1);
		//teacherRepository.save(teacher);
	}

	@Test
	void testOneToManyFind(){
		Optional<User> user = userRepository.findById(4);
		System.out.println(user);
		Teacher teacher = user.get().getTeacher();
		System.out.println(teacher.getName());

	}
	@Test
	void testDelete(){
		//userRepository.deleteById(4);
		teacherRepository.deleteById(2);
	}

	@Test
	void testManyToMany(){
		Teacher teacher = new Teacher();
		teacher.setName("黄殷瑜");

		Course course = new Course();
		course.setName("大学英语");
		course.setScore(4);

		Course course1 = new Course();
		course1.setName("法语");
		course1.setScore(2);

		teacher.getCourses().add(course);
		teacher.getCourses().add(course1);
		course.getTeachers().add(teacher);
		course1.getTeachers().add(teacher);

		teacherRepository.save(teacher);
	}

	@Test
	void  testFind(){
		Optional<Teacher> teacher = teacherRepository.findById(5);
		System.out.println(teacher.get().getName());
		Set<Course> courses = teacher.get().getCourses();
		for (Course course:courses){
			System.out.println(course);
		}
	}
}

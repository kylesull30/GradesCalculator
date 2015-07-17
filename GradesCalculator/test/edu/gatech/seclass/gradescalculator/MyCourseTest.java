package edu.gatech.seclass.gradescalculator;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyCourseTest {

    Students students = null;
    Grades grades = null;
    Course course = null;
    static final String GRADES_DB = "DB" + File.separator + "GradesDatabase6300-grades.xlsx";
    static final String GRADES_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-grades-goldenversion.xlsx";
    static final String STUDENTS_DB = "DB" + File.separator + "GradesDatabase6300-students.xlsx";
    static final String STUDENTS_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-students-goldenversion.xlsx";
    
    @Before
    public void setUp() throws Exception {
        FileSystem fs = FileSystems.getDefault();
        Path gradesdbfilegolden = fs.getPath(GRADES_DB_GOLDEN);
        Path gradesdbfile = fs.getPath(GRADES_DB);
        Files.copy(gradesdbfilegolden, gradesdbfile, REPLACE_EXISTING);
        Path studentsdbfilegolden = fs.getPath(STUDENTS_DB_GOLDEN);
        Path studentsdbfile = fs.getPath(STUDENTS_DB);
        Files.copy(studentsdbfilegolden, studentsdbfile, REPLACE_EXISTING);    	
    	students = new Students(STUDENTS_DB);
        grades = new Grades(GRADES_DB);
        course = new Course(students, grades);
    }

    @After
    public void tearDown() throws Exception {
        students = null;
        grades = null;
        course = null;
    }

    // New tests
    @Test
    public void testAddProject() {
        course.addProject("Project 4");
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(4, course.getNumProjects());
        course.addProject("Project 5");
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(5, course.getNumProjects());
    }

    @Test
    public void testAddStudent1() {
        Student student1 = new Student("Jack One", "901234517", course);       
        course.addStudent(student1);
        course.updateStudents(new Students(STUDENTS_DB));
        assertEquals(17, course.getNumStudents());

        Student student2 = new Student("Jill Two", "901234518", course);
        course.addStudent(student2);
        course.updateStudents(new Students(STUDENTS_DB));
        assertEquals(18, course.getStudents().size());

        Student student3 = new Student("Mark Three", "901234519", course);
        course.addStudent(student3);
        course.updateStudents(new Students(STUDENTS_DB));
        assertEquals(19, course.getNumStudents());
        
        Student student4 = new Student("Lorena Four", "901234520", course);
        course.addStudent(student4);
        course.updateStudents(new Students(STUDENTS_DB));
        assertEquals(20, course.getStudents().size());

    }
    
    @Test
    public void testAddStudent2() {
        Student student1 = new Student("Jack One", "901234517", course);       
        course.addStudent(student1);
        course.updateStudents(new Students(STUDENTS_DB));
        assertEquals(17, course.getNumStudents());
        Student student = course.getStudentByID("901234517");
        assertEquals("Jack One", student.getName());
        student = null;
        student = course.getStudentByName("Jack One");
        assertEquals("901234517", student.getGtid());
    }

    
    @Test
    public void testAddTeamGrades1() {
    	String projectName = "Project 2";
        String team2 = "Team 2";
        String team3 = "Team 3";
        HashMap<String, Integer> grades = new HashMap<String, Integer>();
        grades.put(team2, 94);
        grades.put(team3, 96);
        course.addTeamGrades(projectName, grades);
        course.updateGrades(new Grades(GRADES_DB));
        Student student1 = new Student("Rastus Kight", "901234512", course);
        assertEquals(89, course.getAverageProjectsGrade(student1));
        Student student2 = new Student("Josepha Jube", "901234502", course);
        assertEquals(87, course.getAverageProjectsGrade(student2));
    }
    
    @Test
    public void testAddTeamGrades2() {
    	String projectName = "Project 4";
        String team3 = "Team 3";
        Student student1 = new Student("Rastus Kight", "901234512", course);

        HashMap<String, Integer> teamGrades = new HashMap<String, Integer>();
        teamGrades.put(team3, 99);
        course.addTeamGrades(projectName, teamGrades);
        course.updateGrades(new Grades(GRADES_DB));
        
        HashMap<Student, Integer> contributions = new HashMap<Student, Integer>();
        contributions.put(student1, 93);
        course.addIndividualContributions(projectName, contributions);
        
        assertEquals(88, course.getAverageProjectsGrade(student1));
        
    }
}
    

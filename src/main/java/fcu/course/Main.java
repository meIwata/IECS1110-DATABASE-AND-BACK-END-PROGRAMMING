package fcu.course;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        StudentDatabaseManager manager = new StudentDatabaseManager();
        // 新增學生資料
        // manager.createStudent("Sue", "Davis",
        // "2001-08-11", "sue@fcu.edu.tw");

        // 更新學生資料
        // 注意：這裡的 studentId 是學生的唯一識別碼，要去資料庫看學生id
        // manager.updateStudent(12, "Sue", "Davis","2003-08-11", "sue123456@fcu.edu.tw");


        // 刪除學生資料
        // 注意：這裡的 studentId 是學生的唯一識別碼，要去資料庫看學生id
        // manager.deleteStudent(12);

        // 查詢所有學生資料
        // manager.listStudents();

        // 查詢學生資料
        // manager.getStudentById(100);

        //------------------------------

        // 新增課程
        // manager.createCourse("計算機概論", "資訊工程基礎課程", 3);

        // 更新課程
        // manager.updateCourse(5, "計算機概論", "",4);

        // 查詢所有課程
        // manager.listCourses();

        // 查詢課程
        manager.getCourseById(5);
    }
}

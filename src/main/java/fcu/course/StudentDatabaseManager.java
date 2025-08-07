package fcu.course;

import java.sql.*;

public class StudentDatabaseManager {
    // 資料庫連線資訊
    private static final String URL = "jdbc:mariadb://140.134.24.157:23306/D1397221_0724";
    private static final String USER = "root";
    private static final String PASSWORD = "pbiecs123456";

    // 建立資料庫連線
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 新增學生
    public void createStudent(String firstName, String lastName, String dateOfBirth, String email) {
        //這裡一定要跟資料庫的欄位名稱一致
        String sql = "INSERT INTO Student (first_name, last_name, date_of_birth, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // id 欄位是自動遞增的，所以不需要在這裡設定
            // 設定參數
            pstmt.setString(1, firstName); // 第一個問號對應 first_name
            pstmt.setString(2, lastName);  // 第二個問號對應 last_name
            pstmt.setString(3, dateOfBirth); // 第三個問號對應 date_of_birth
            pstmt.setString(4, email); // 第四個問號對應 email
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改學生
    // 注意：這裡的 studentId 是學生的唯一識別碼
    public void updateStudent(int studentId, String firstName, String lastName, String dateOfBirth, String email)  {
        String sql = "UPDATE Student SET first_name = ?, last_name = ?, date_of_birth = ?, email = ? WHERE student_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, dateOfBirth);
            pstmt.setString(4, email);
            pstmt.setInt(5, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 刪除學生
    // 注意：這裡的 studentId 是學生的唯一識別碼
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM Student WHERE student_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查詢所有學生
    public void listStudents()  {
        String sql = "SELECT * FROM Student";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // 執行查詢並獲取結果集
            while (rs.next()) { // 如果資料還有下一筆的話就是 true
                int studentId = rs.getInt("student_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String dateOfBirth = rs.getString("date_of_birth");
                String email = rs.getString("email");

                // 輸出學生資料
                System.out.printf("ID: %d, Name: %s %s, Date of Birth: %s, Email: %s%n",
                        studentId, firstName, lastName, dateOfBirth, email);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 透過studentId查詢學生
    public void getStudentById(int studentId) throws SQLException {
        String sql = "SELECT * FROM Student WHERE student_id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // 如果有找到資料
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String dateOfBirth = rs.getString("date_of_birth");
                    String email = rs.getString("email");

                    // 輸出學生資料
                    System.out.printf("ID: %d, Name: %s %s, Date of Birth: %s, Email: %s%n",
                            studentId, firstName, lastName, dateOfBirth, email);
                } else {
                    System.out.println("No student found with ID: " + studentId);
                }
            }
        }
//        這裡沒有寫catch(SQLException e) 是因為這個方法已經在方法簽名中聲明了會拋出 SQLException，所以不需要在這裡再次捕獲。
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
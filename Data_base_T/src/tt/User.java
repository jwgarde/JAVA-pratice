package tt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    private Connection con;
    private Statement stmt;

    public User() {
        try {
            // MySQL JDBC URL, 데이터베이스 포함
            String url = "jdbc:mysql://127.0.0.1:3306/host";
            String user = "jung";
            String passwd = "Wjddnjs123@";

            // 데이터베이스 연결
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("DB 연결 성공");

            // Statement 객체 생성
            stmt = con.createStatement();
            System.out.println("Statement 객체 생성 성공");

            // 테이블 조회
            ResultSet rs = stmt.executeQuery("SELECT * FROM new_table");
            System.out.println("테이블을 조회했습니다.");
            // 결과 처리
            while (rs.next()) {
                System.out.println("User_ID: " + rs.getString("User_ID") + 
                                   ", Name: " + rs.getString("Name") +
                                   ", Phone: " + rs.getString("Phone") +
                                   ", Password: " + rs.getString("password"));
            }
            rs = stmt.executeQuery("SELECT * FROM new_table WHERE Phone = '" + "55511223344" + "'");
            
            String insertQuery = "INSERT INTO new_table (User_ID, Name, Phone, Password) " +
                    "VALUES ('user009', 'Eve', '55511223342', 'password123'), " +
                    "('user010', 'Frank', '55522334400', 'password456')";
            int rowsInserted = stmt.executeUpdate(insertQuery); //데이터가 삽입된 행의 수 반환
            System.out.println(rowsInserted + " rows inserted.");
            
            // 자원 해제
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("DB 연결 실패 또는 SQL 오류 발생");
            System.out.println("사유: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("자원 해제 중 오류 발생");
                System.out.println("사유: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new User(); // User 클래스의 인스턴스 생성
    }
}

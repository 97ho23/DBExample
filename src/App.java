import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {

        String driverName = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
        String id = "root";
        String password = "juho";

        // String sql = "CREATE TABLE comm_cd ( CODE VARCHAR(100) NOT NULL, PARENT_CODE VARCHAR(20)
        // NOT NULL, NAME VARCHAR(100) NOT NULL, DESCRIPTION VARCHAR(4000), REG_USR_ID VARCHAR(50)
        // NOT NULL, REG_DT VARCHAR(14) NOT NULL, MOD_USR_ID VARCHAR(50), MOD_DT VARCHAR(14))";

        String sql =
                "INSERT INTO comm_cd (CODE,PARENT_CODE,NAME,DESCRIPTION,REG_USR_ID,REG_DT,MOD_USR_ID,MOD_DT) VALUES ('IF_TYPE','GROUP','연계 방식',NULL,'2ac9211d-6942-4870-95e7-900249385aff','20170131112000',NULL,NULL);\r\n"
                        + "INSERT INTO comm_cd (CODE,PARENT_CODE,NAME,DESCRIPTION,REG_USR_ID,REG_DT,MOD_USR_ID,MOD_DT) VALUES ('WS1','IF_TYPE','WebService 1.1','웹서비스 1.1','2ac9211d-6942-4870-95e7-900249385aff','20170131112000',NULL,NULL);\r\n"
                        + "INSERT INTO comm_cd (CODE,PARENT_CODE,NAME,DESCRIPTION,REG_USR_ID,REG_DT,MOD_USR_ID,MOD_DT) VALUES ('WS2','IF_TYPE','WebService 1.2','웹서비스 1.2','2ac9211d-6942-4870-95e7-900249385aff','20170131112000',NULL,NULL);\r\n"
                        + "INSERT INTO comm_cd (CODE,PARENT_CODE,NAME,DESCRIPTION,REG_USR_ID,REG_DT,MOD_USR_ID,MOD_DT) VALUES ('HTP','IF_TYPE','HTTP','HTTP POST','2ac9211d-6942-4870-95e7-900249385aff','20170131112000',NULL,NULL);\r\n"
                        + "INSERT INTO comm_cd (CODE,PARENT_CODE,NAME,DESCRIPTION,REG_USR_ID,REG_DT,MOD_USR_ID,MOD_DT) VALUES ('TCP','IF_TYPE','TCP','TCP','2ac9211d-6942-4870-95e7-900249385aff','20170131112000',NULL,NULL);\r\n"
                        + "";

        sql = sql.replace(";", "");
        String[] change_sql = sql.split("\\n");

        Connection conn = null;
        Statement st = null; // DB와 소통하는 통로
        ResultSet rs = null; // 결과 받아서 처리할때
        try {
            Class.forName(driverName);
            System.out.println("driver load 성공!");
            conn = DriverManager.getConnection(url, id, password);
            System.out.println("DB 연결 성공!");

            st = conn.createStatement();

            System.out.println("===============");
            for (String changedSql : change_sql) {
                System.out.println(changedSql);
                rs = st.executeQuery(changedSql);
            }
            System.out.println("===============");

            // rs = st.executeQuery(sql); // 쿼리 실행 후 데이터들이 rs 저장
            while (rs.next()) { // 한건씩 처리
                // int empid = rs.getInt(1); // 첫번째 칼럼 조회
                // String fname = rs.getString("first_name"); // 컬럼이름도 지정 가능
                // int sal = rs.getInt("salary");
                // Date hireDate = rs.getDate("hire_date");
                System.out.println(rs + "=============");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("driver load 실패!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 연결 실패!");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

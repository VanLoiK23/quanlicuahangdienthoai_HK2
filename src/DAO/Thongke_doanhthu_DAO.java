package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Thongke_doanhthu_DAO {
	public void updateData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quan_ly_ban_hang", "root", "binvaloi123");
            Statement statement = connection.createStatement();

            // Update data
            String updateQuery = "UPDATE thongke_doanhthutheothang " +
                    "SET sotienvon = (SELECT SUM(pn.tongtien) FROM phieunhap pn WHERE MONTH(pn.thoigian) = 6), " +
                    "    sotienthuvao = (SELECT IFNULL(SUM(px.tongtien), 0) FROM phieuxuat px WHERE MONTH(px.thoigian) = 6) " +
                    "Where month = 6";
            statement.executeUpdate(updateQuery);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

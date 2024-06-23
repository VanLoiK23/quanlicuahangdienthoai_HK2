package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate_util {
	 private static final SessionFactory sessionFactory=buildSessionFactory();
	   private static SessionFactory buildSessionFactory() {
		   try {
			   return new Configuration().configure().buildSessionFactory();
		   }catch(Exception e) {
			   e.printStackTrace();
			   return null;
		   }
	   }
	   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/quan_ly_ban_hang";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "binvaloi123";
	   public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	    }
	   public static SessionFactory getSessionFactory() {
		return sessionFactory;
	   }
	   public static void shutdown() {
		   getSessionFactory().close();
	   }
}

package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
	    private static final SessionFactory sessionFactory = buildSessionFactory();

	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create a new Configuration instance
	            Configuration configuration = new Configuration();

	            // Apply database connection properties
	            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
	            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
	            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/quan_ly_ban_hang");
	            configuration.setProperty("hibernate.connection.username", "root");
	            configuration.setProperty("hibernate.connection.password", "1122");

	            // Set hbm2ddl.auto to update
	            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

	            // Set show_sql to true for debugging
	            configuration.setProperty("show_sql", "true");

	            // Add mappings
	            configuration.addAnnotatedClass(ModelUserPhone.modelsanpham.class);
	            configuration.addAnnotatedClass(ModelUserPhone.ModelSanPhamDatHang.class);
	            configuration.addAnnotatedClass(ModelUserPhone.ModelGiohang.class);
	          
	            
	            
	            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();

	            // Build the SessionFactory
	            return configuration.buildSessionFactory(serviceRegistry);
	        } catch (Exception e) {
	            System.out.println("Loi khong the tao SessionFactory: " + e.getMessage());
	            throw new ExceptionInInitializerError(e);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public static void shutdown() {
	        getSessionFactory().close();
	    }
	}


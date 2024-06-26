package DAO_UserPhone;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
	

	public class hbnutil {
	    private static final SessionFactory sessionFactory = buildSessionFactory();
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	    // Hibernate 5:
	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Tạo đối tượng ServiceRegistry từ hibernate.cfg.xml
	            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .configure("DAO./hibernate.cfg.xml").build();
	            // Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
	            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
	            return metadata.getSessionFactoryBuilder().build();
	        } catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	    public static void shutdown() {
	        // Giải phóng cache và Connection Pools.
	        getSessionFactory().close();
	    	}
}

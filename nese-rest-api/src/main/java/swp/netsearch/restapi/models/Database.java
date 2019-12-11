package swp.netsearch.restapi.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */
public class Database {
    private static Session session;
    private static SessionFactory sessionFactory;

    private Database() {
    }

    public static Session session() {
        assert sessionFactory != null;
        assert session != null;
        return session;
    }

    public static void init() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        assert sessionFactory != null;
        session = sessionFactory.openSession();
    }

    public static void close() {
        session.close();
        sessionFactory.close();
    }
}

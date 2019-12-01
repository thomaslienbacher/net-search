package swp.netsearch.restapi;

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
    private SessionFactory sessionFactory;
    public Session session;

    public Database() {
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

    public void close() {
        session.close();
        sessionFactory.close();
    }
}

package swp.netsearch.restapi.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 28.12.2019.
 *
 * @author Thomas Lienbacher
 */
public class GenericDao<T> {

    private Session session;
    private Transaction transaction;
    private Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    public void openSession() {
        session = getSessionFactory().openSession();
    }

    public void openSessionTransactional() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void closeSession() {
        session.close();
    }

    public void closeSessionTransactional() {
        transaction.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        return sessionFactory;
    }

    public List<T> all() {
        return session.createQuery("SELECT T FROM " + type.getName() + " T", type).getResultList();
    }

    public T get(Serializable id) {
        return session.get(type, id);
    }

    public void insert(T object) {
        session.save(object);
    }

    public void update(T object) {
        session.merge(object);
    }

    public void delete(T object) {
        session.delete(object);
    }
}

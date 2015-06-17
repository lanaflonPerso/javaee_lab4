package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Service that provides CRUD-operations for Product Entity
 */
@Stateless
public class DAO {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("easySaleTracking");
    }

    public DAO() {
    }

    public <T> void add(T t){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    public <T> void update(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        em.close();
    }

    public <T> void delete(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(t));
        em.getTransaction().commit();
        em.close();
    }

    public <T> T getById(int id, Class<T> type) {
        EntityManager em = emf.createEntityManager();
        T entity = (T) em.createNamedQuery(type.getSimpleName() + ".findById").setParameter("id", id).getSingleResult();
        em.close();
        return entity;
    }

    public <T> List<T> getAll(Class<T> type) {
        EntityManager em = emf.createEntityManager();
        List<T> entities = em.createNamedQuery(type.getSimpleName() + ".findAll").getResultList();
        em.close();
        return entities;
    }

}

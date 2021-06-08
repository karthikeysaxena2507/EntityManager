package demoProject.repositories;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/** Like adding NoRepository Bean in case of JpaRepo
/**Spring dynamically creates a proxy that implements the same interface(s)
 * as the class you're annotating. And when clients make calls into your object,
 * the calls are intercepted and the behaviors injected via the proxy mechanism.**/

public abstract class EntityRepositoryImpl<E, ID> implements EntityRepository<E, ID> {

    @PersistenceContext(unitName = "app")
    EntityManager em;

    @Transactional
    public E save(E entity) {
        return em.merge(entity);
    }

    @Transactional
    public List<E> saveAll(List<E> entities) {
        return entities
                .stream()
                .map(entity -> save(entity))
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(E entity){
        em.persist(entity);
    }

    @Transactional
    public List<E> findAll(int start, int max) {
        return em.createNamedQuery(getFindAllQueryName())
                .setMaxResults(max)
                .setFirstResult(start * max)
                .getResultList();
    }

    @Transactional
    public E findById(ID primaryKey) {
        return (E) em.find(getEntityClass(), primaryKey);
    }

    @Transactional
    public void delete(E entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
    }

    @Transactional
    public void deleteById(ID primaryKey) {
        E entity = findById(primaryKey);
        delete(entity);
    }

    @Transactional
    public void deleteAll(List<E> entities) {
        entities.stream().forEach(entity -> delete(entity));
    }

    protected EntityManager getEntityManager()
    {
        return em;
    }

    public abstract String getFindAllQueryName();

    public abstract Class getEntityClass();

}

package demoProject.repositories;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/** Without @Transactional, exception will occur because Persistence context will close in between
  * and not at the end, so no track will be maintained.
  * Persistence context does 2 things :=>
  * 1. Gives access to database entities.
  * 2. Tracks and stores instances created during each transactional method
  **/

/**
 * @Transactional is used to rollback changes made in a database if the
 * annotated method fails at any point. Helps to create a persistence Context
 * for the annotated method.
 * Database is changed only if the transaction is successful. Persistence
 * context keeps track of all changes and is created at start of method and d
 * destroyed at the end. Each entityManager methods call its own transaction
 **/

@Transactional
@Slf4j
public abstract class EntityRepositoryImpl<E, ID> implements EntityRepository<E, ID> {

    /**
     * Persistence Context is a place where all defined entities are stored.
     * Entity manager allows us to interact with the persistence context
     * Transactional annotation is required in methods where database is changed
     **/
    @PersistenceContext
    EntityManager em;

    public E save(E entity) {
        return em.merge(entity);
    }

    public List<E> saveAll(List<E> entities) {
        return entities
                .stream()
                .map(entity -> save(entity))
                .collect(Collectors.toList());
    }

    public void create(E entity){
        log.info("CREATE CALLED");
        em.persist(entity);
    }

    public List<E> findAll(int start, int max) {
        log.info("FIND ALL CALLED");
        return em.createNamedQuery(getFindAllQueryName())
                .setMaxResults(max)
                .setFirstResult(start * max)
                .getResultList();
    }

    public E findById(ID primaryKey) {
        return (E) em.find(getEntityClass(), primaryKey);
    }

    public void delete(E entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
    }

    public void deleteById(ID primaryKey) {
        E entity = findById(primaryKey);
        delete(entity);
    }

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

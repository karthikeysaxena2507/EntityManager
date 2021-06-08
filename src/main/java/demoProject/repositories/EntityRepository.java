package demoProject.repositories;

import java.util.List;

public interface EntityRepository<E, ID> {
    E save(E entity);
    E findById(ID primaryKey);
    List<E> saveAll(List<E> entities);
    void create(E entity);
    List<E> findAll(int start, int max);
    void delete(E entity);
    void deleteById(ID primaryKey);
    void deleteAll(List<E> entities);
}

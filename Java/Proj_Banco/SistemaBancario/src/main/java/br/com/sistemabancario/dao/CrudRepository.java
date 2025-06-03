package main.java.br.com.sistemabancario.dao;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, ID> {
    E save (E enity);
    Optional<E> findByid(ID id);
    List<E> findAll();
    void deleteById (ID id);
    void delete(E entity);
}
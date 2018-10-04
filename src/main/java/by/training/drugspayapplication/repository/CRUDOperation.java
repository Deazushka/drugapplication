package by.training.drugspayapplication.repository;
import java.util.List;


public interface CRUDOperation<T, K> {
    boolean create(T entity);
    T read(K id);
    boolean update(T entity);
    boolean delete(K id);
    List<T> readAll();
}

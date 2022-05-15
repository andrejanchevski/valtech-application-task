package ch.codeflair.application.mock.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {

    Optional<T> findById(ID id);

    List<T> findAll();
}

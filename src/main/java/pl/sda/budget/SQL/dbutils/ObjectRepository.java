package pl.sda.budget.SQL.dbutils;

import pl.sda.budget.SQL.role.Role;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ObjectRepository<T> {



    public List<T> getAll() throws SQLException;

    public Optional<T> findOneById(int id) throws SQLException;

    public T save(T t) throws SQLException;

    public void update(Optional<T> t) throws SQLException;

    public List<T> save(List<T> t);

    public void delete( int id) throws SQLException;
}

package jdbc;

import java.util.List;

public abstract class BaseDAO<T> {

	public abstract void insert(T entity);
	
	public abstract void delete(Integer id);
	
	public abstract void update(T entity);
	
	public abstract T find(Integer id);
	
	public abstract List<T> findAll();
}

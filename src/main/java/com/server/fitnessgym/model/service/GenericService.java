package com.server.fitnessgym.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericService<T, ID> {
	
	public abstract JpaRepository<T,ID> getDao();
	
	public List<T> findAll() {
		return getDao().findAll();
	}
	
	public Optional<T> findByID(ID id) {
		return getDao().findById(id);
	}
	
	public T create(T entity) {
		return getDao().save(entity);
	}
	
	public T edit(T entity, ID id) {
		if (getDao().existsById(id)) {
			return getDao().save(entity);
		}
		throw new RuntimeException("No existe el registro");
	}
	
	public void delete(ID id) {
		getDao().deleteById(id);
	}

}
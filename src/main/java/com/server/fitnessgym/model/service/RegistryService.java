package com.server.fitnessgym.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.RegistryDAO;
import com.server.fitnessgym.model.entity.Registry;

@Service
public class RegistryService extends GenericService<Registry, Long>{
	
	private RegistryDAO dao;
	
	public RegistryService(RegistryDAO dao) {
		this.dao = dao;
	}

	@Override
	public JpaRepository<Registry, Long> getDao() {
		return dao;
	}

}
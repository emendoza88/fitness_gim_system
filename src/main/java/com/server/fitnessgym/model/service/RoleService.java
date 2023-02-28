package com.server.fitnessgym.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.RoleDAO;
import com.server.fitnessgym.model.entity.Role;

@Service
public class RoleService extends GenericService<Role, Long>{
	
	private RoleDAO dao;
	
	public RoleService(RoleDAO dao) {
		this.dao = dao;
	}

	@Override
	public JpaRepository<Role, Long> getDao() {
		return dao;
	}
}
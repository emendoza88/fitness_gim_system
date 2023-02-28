package com.server.fitnessgym.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.OptionRoleDAO;
import com.server.fitnessgym.model.entity.OptionRole;

@Service
public class OptionRoleService extends GenericService<OptionRole, Long>{

	private OptionRoleDAO dao;
	
	public OptionRoleService(OptionRoleDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public JpaRepository<OptionRole, Long> getDao() {
		return dao;
	}
	
}
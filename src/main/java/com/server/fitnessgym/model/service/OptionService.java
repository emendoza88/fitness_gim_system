package com.server.fitnessgym.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.OptionDAO;
import com.server.fitnessgym.model.entity.Option;

@Service
public class OptionService extends GenericService<Option, Long>{
	
	private OptionDAO dao;
	
	public OptionService(OptionDAO dao) {
		this.dao = dao;
	}

	@Override
	public JpaRepository<Option, Long> getDao() {
		return dao;
	}

}
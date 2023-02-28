package com.server.fitnessgym.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.PeriodicityDAO;
import com.server.fitnessgym.model.entity.Periodicity;

@Service
public class PeriodicityService extends GenericService<Periodicity, Long> {
	
	@Autowired
	private PeriodicityDAO dao;

	@Override
	public JpaRepository<Periodicity, Long> getDao() {
		return dao;
	}
}
package com.server.fitnessgym.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.SettingGymDayValueDAO;
import com.server.fitnessgym.model.entity.SettingGymDayValue;

@Service
public class SettingGymDayValueService extends GenericService<SettingGymDayValue, Long> {

	@Autowired
	private SettingGymDayValueDAO dao;
	
	@Override
	public JpaRepository<SettingGymDayValue, Long> getDao() {
		return dao;
	}
	
	public SettingGymDayValue getGymDayValue() {
		return dao.getById(1L);
	}

}
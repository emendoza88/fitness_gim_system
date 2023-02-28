package com.server.fitnessgym.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.fitnessgym.model.entity.SettingGymDayValue;

public interface SettingGymDayValueDAO extends JpaRepository<SettingGymDayValue, Long> {
	
}
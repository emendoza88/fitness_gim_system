package com.server.fitnessgym.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.fitnessgym.model.entity.Option;

public interface OptionDAO extends JpaRepository<Option, Long>{

}

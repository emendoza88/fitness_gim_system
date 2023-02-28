package com.server.fitnessgym.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.fitnessgym.model.entity.Periodicity;

public interface PeriodicityDAO extends JpaRepository<Periodicity, Long> {

}

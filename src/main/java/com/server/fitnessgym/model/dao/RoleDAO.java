package com.server.fitnessgym.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.fitnessgym.model.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Long>{

}

package com.server.fitnessgym.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.fitnessgym.model.entity.Membership;

public interface MembershipDAO extends JpaRepository<Membership, Long>{

}

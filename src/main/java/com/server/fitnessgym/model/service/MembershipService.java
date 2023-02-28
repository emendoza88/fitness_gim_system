package com.server.fitnessgym.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.MembershipDAO;
import com.server.fitnessgym.model.entity.Membership;

@Service
public class MembershipService extends GenericService<Membership, Long> {
	
	private MembershipDAO dao;
	
	public MembershipService(MembershipDAO dao) {
		this.dao = dao;
	}

	@Override
	public JpaRepository<Membership, Long> getDao() {
		return dao;
	}

}

package com.server.fitnessgym.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.InvoiceDetaillDAO;
import com.server.fitnessgym.model.entity.InvoiceDetaill;

@Service
public class InvoiceDetaillService extends GenericService<InvoiceDetaill, Long>{
	
	private InvoiceDetaillDAO dao;
	
	public InvoiceDetaillService(InvoiceDetaillDAO dao) {
		this.dao = dao;
	}

	@Override
	public JpaRepository<InvoiceDetaill, Long> getDao() {
		return dao;
	}	

}

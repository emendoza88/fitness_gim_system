package com.server.fitnessgym.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.fitnessgym.model.entity.Invoice;

public interface InvoiceDAO extends JpaRepository<Invoice, Long>{

}

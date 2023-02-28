package com.server.fitnessgym.model.service;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.InvoiceDAO;
import com.server.fitnessgym.model.entity.Invoice;
import com.server.fitnessgym.model.entity.InvoiceDetaill;

@Service
public class InvoiceService extends GenericService<Invoice, Long>{
	
	private InvoiceDAO dao;
	
	private InvoiceDetaillService invoiceDetaillService;
	
	public InvoiceService(InvoiceDAO dao, InvoiceDetaillService invoiceDetaillService) {
		this.dao = dao;
		this.invoiceDetaillService = invoiceDetaillService;
	}

	@Override
	public JpaRepository<Invoice, Long> getDao() {
		return dao;
	}
	
	@Override
	public Invoice create(Invoice entity) {
		Invoice invoice = super.create(entity);
		generateInvoiceDetaill(entity);
		return invoice;
	}

	private void generateInvoiceDetaill(Invoice entity) {
		for (InvoiceDetaill invoiceDetaillEntity : entity.getInvoiceDetaills()) {
			invoiceDetaillEntity.setInvoice(entity);
			invoiceDetaillService.create(invoiceDetaillEntity);
		}
	}

	public void updateStatusPayment(Invoice invoice) {
		invoice.setState("PAID");
		invoice.setPaymentdDate(new Timestamp(System.currentTimeMillis()));
		getDao().save(invoice);
	}
	
}
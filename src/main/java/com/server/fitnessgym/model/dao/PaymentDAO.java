package com.server.fitnessgym.model.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.server.fitnessgym.model.dto.IPaymentDao;
import com.server.fitnessgym.model.dto.IPaymentValueTotalResult;
import com.server.fitnessgym.model.entity.Payment;

public interface PaymentDAO extends JpaRepository<Payment, Long>{
	
	@Query(value = "SELECT i.id, p.created_date paymentDate, p.payment_method paymentMethod, idl.value, i.type_concept typeConcept, idl.reference concept, c.name," +
			" c.surname, c.email, c.phone " + 
			"FROM payment p " + 
			"	INNER JOIN invoice i ON p.invoice_id = i.id " + 
			"	INNER JOIN invoice_detaill idl ON idl.invoice = i.id " + 
			"	INNER JOIN customer c ON i.customer_id = c.id " + 
			"WHERE p.created_date BETWEEN ?1 AND ?2 ", nativeQuery = true)
	List<IPaymentDao> findByRangeDate(Timestamp timestamp, Timestamp timestamp2);
	
	@Query(value = "SELECT i.type_concept typeConcept, SUM(p.value) totalValue " + 
			"FROM payment p " + 
			"	INNER JOIN invoice i ON p.invoice_id = i.id " + 
			"WHERE p.created_date BETWEEN ?1 AND ?2 " + 
			"GROUP BY i.type_concept", nativeQuery = true)
	List<IPaymentValueTotalResult> sumValuePaymentGroupConceptByRangeDate(Timestamp initialDate, Timestamp endDate);

}
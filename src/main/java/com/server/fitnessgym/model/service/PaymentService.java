package com.server.fitnessgym.model.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.PaymentDAO;
import com.server.fitnessgym.model.dto.IPaymentDao;
import com.server.fitnessgym.model.dto.IPaymentValueTotalResult;
import com.server.fitnessgym.model.dto.PaymentTotalValueByConcept;
import com.server.fitnessgym.model.entity.Payment;
import com.server.fitnessgym.model.enumerated.ETypeConcept;

@Service
public class PaymentService extends GenericService<Payment, Long>{
	
	private PaymentDAO dao;
	
	public PaymentService(PaymentDAO dao) {
		this.dao = dao;
	}

	@Override
	public JpaRepository<Payment, Long> getDao() {
		return dao;
	}
	
	public Payment create(Payment entity) {
		return super.create(entity);
	}
	
	public List<IPaymentDao> findByDate(LocalDate initialDate, LocalDate endDate) {
		LocalDateTime initialDateTime = LocalDateTime.of(initialDate, LocalTime.of(0, 0, 0));
		LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));		
		return dao.findByRangeDate(Timestamp.valueOf(initialDateTime), Timestamp.valueOf(endDateTime));
	}
	
	public PaymentTotalValueByConcept getPaymentTotalValueByConcept(LocalDate initialDate, LocalDate endDate) {
		LocalDateTime initialDateTime = LocalDateTime.of(initialDate, LocalTime.of(0, 0, 0));
		LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
		
		PaymentTotalValueByConcept dto = new PaymentTotalValueByConcept();
		
		List<IPaymentValueTotalResult> queryResult = dao.sumValuePaymentGroupConceptByRangeDate(Timestamp.valueOf(initialDateTime), Timestamp.valueOf(endDateTime));
		queryResult.forEach(x -> {
			if(x.getTypeConcept().equals(ETypeConcept.GYM)) {
				dto.setTotalValueGym(x.getTotalValue());
			} else {
				dto.setTotalValueStore(x.getTotalValue());
			}
		});
		
		return dto;
	}
	
	public List<IPaymentDao> findByCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		return findByDate(currentDate, currentDate);
	}
	
	public List<IPaymentDao> findByCurrentMonth() {
		LocalDate currentDate = LocalDate.now();
		LocalDate initialDateMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate endDateMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
		return findByDate(initialDateMonth, endDateMonth);
	}
	
	public PaymentTotalValueByConcept getPaymentTotalValueByConceptCurrentMonth() {
		LocalDate currentDate = LocalDate.now();
		LocalDate initialDateMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate endDateMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
		return getPaymentTotalValueByConcept(initialDateMonth, endDateMonth);
	}
	
	public PaymentTotalValueByConcept getPaymentTotalValueByConceptCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		return getPaymentTotalValueByConcept(currentDate, currentDate);
	}

}
package com.server.fitnessgym.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CustomerService customerService;
	
	public Long getTotalEarningsDay() {
		long totalValueGym = paymentService.getPaymentTotalValueByConceptCurrentDate().getTotalValueGym();
		long totalValueStore = paymentService.getPaymentTotalValueByConceptCurrentDate().getTotalValueStore();
		
		return totalValueGym + totalValueStore;
	}
	
	public Long getTotalEarningsMonth() {
		long totalValueGym = paymentService.getPaymentTotalValueByConceptCurrentMonth().getTotalValueGym();
		long totalValueStore = paymentService.getPaymentTotalValueByConceptCurrentMonth().getTotalValueGym();
		
		return totalValueGym + totalValueStore;
	}
	
	public Long getTotalUserActive() {
		return customerService.getTotalCustomer();
	}

}

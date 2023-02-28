package com.server.fitnessgym.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dao.CustomerDAO;
import com.server.fitnessgym.model.dto.ISubscriptionResultSet;
import com.server.fitnessgym.model.entity.Customer;
import com.server.fitnessgym.model.entity.Membership;
import com.server.fitnessgym.model.entity.Subscription;

@Service
public class CustomerService extends GenericService<Customer, Long> {

	private CustomerDAO dao;
	
	@Autowired
	private SubscriptionService subscriptionService;

	public CustomerService(CustomerDAO dao) {
		this.dao = dao;
	}

	@Override
	public JpaRepository<Customer, Long> getDao() {
		return dao;
	}
	
	@Override
	public Customer create(Customer entity) {
		return super.create(entity);
	}
	
	@Transactional
	public Customer createWhitSupscription(Customer entity) {
		Customer customer = super.create(entity);
		Subscription supscription = createMembership(customer);
		customer.setSupscription(supscription.getId());
		return customer;
	}
	
	private Subscription createMembership(Customer userEntity){
		Subscription subscriptionEntity = new Subscription();
		String membership = userEntity.getMemberships().split("-")[0];
		
		subscriptionEntity.setMembership(new Membership(Long.valueOf(membership)));
		subscriptionEntity.setUser(userEntity);
		subscriptionEntity.setStartDate(Date.valueOf(LocalDate.now()));
		
		return subscriptionService.create(subscriptionEntity);
	}
	
	public List<ISubscriptionResultSet> findSuscriptionClient(){
		return dao.findSubscriptionClient();
	}
	
	public ISubscriptionResultSet getSuscriptionClient(Long user){
		return dao.getSubscriptionClient(user);
	}
	
	public Long getTotalCustomer() {
		return dao.count();
	}

}
package com.server.fitnessgym.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.fitnessgym.model.dto.InvoiceDto;
import com.server.fitnessgym.model.dto.PaymentsDayRequest;
import com.server.fitnessgym.model.dto.PaymentsProductRequest;
import com.server.fitnessgym.model.dto.PaymentsSubscriptionRequest;
import com.server.fitnessgym.model.dto.ProductItemDto;
import com.server.fitnessgym.model.entity.Customer;
import com.server.fitnessgym.model.entity.Invoice;
import com.server.fitnessgym.model.entity.InvoiceDetaill;
import com.server.fitnessgym.model.entity.Membership;
import com.server.fitnessgym.model.entity.Payment;
import com.server.fitnessgym.model.entity.Product;
import com.server.fitnessgym.model.entity.SettingGymDayValue;
import com.server.fitnessgym.model.entity.Subscription;
import com.server.fitnessgym.model.enumerated.EPaymentMethod;
import com.server.fitnessgym.model.enumerated.ETypeConcept;


@Service
public class ProcessPaymentService {
	
	@Autowired
	private InvoiceService invoiceService; 
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SettingGymDayValueService gymDayValueService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	private void paymentInvoice(Invoice invoice, String paymentMethod) {
		Payment entity = new Payment();
		entity.setUser(invoice.getUser().getId());
		entity.setInvoice(invoice);
		entity.setValue(invoice.getValue());
		entity.setPaymentMethod(EPaymentMethod.valueOf(paymentMethod));
		
		paymentService.create(entity);
		invoiceService.updateStatusPayment(invoice);
	}
	
	private Invoice generateInvoice(InvoiceDto invoiceDto, String concept) {
		return generateInvoice(invoiceDto, concept, null);
	}
	
	private Invoice generateInvoice(InvoiceDto invoiceDto, String concept, Long conceptId) {
		List<ProductItemDto> productsList = invoiceDto.getProductsList();
		List<InvoiceDetaill> invoiceDetaillList = new ArrayList<InvoiceDetaill>();
		long valueTotal = 0;
		
		Invoice invoice = new Invoice();
		invoice.setState("NEW");
		invoice.setConcept(concept);
		invoice.setConceptRefId(conceptId);
		invoice.setUser(new Customer(invoiceDto.getUser()));
		invoice.setInvoiceDetaills(invoiceDetaillList);
		
		for (ProductItemDto productItem : productsList) {
			InvoiceDetaill invoiceDetaill = new InvoiceDetaill();
			invoiceDetaill.setInvoice(invoice);
			invoiceDetaill.setReference(productItem.getTypeReference());
			invoiceDetaill.setIdReference(productItem.getIdReference());
			invoiceDetaill.setValue(productItem.getValue());
			invoiceDetaillList.add(invoiceDetaill);
			valueTotal = valueTotal + productItem.getValue();
		}
		
		invoice.setValue(valueTotal);
		return invoiceService.create(invoice);
	}
	
	private InvoiceDto makeInvoiceDtoSingleProduct(Long user, String typeReference, Long idReference, Long value) {
		InvoiceDto dto = new InvoiceDto();
		dto.setUser(user);
		dto.setProductsList(new ArrayList<ProductItemDto>());
		dto.getProductsList().add(new ProductItemDto(typeReference, idReference, value));
		return dto;
	}

	@Transactional
	public void paymentDayProcess(PaymentsDayRequest request) {
		SettingGymDayValue gymDayValue = gymDayValueService.getGymDayValue();
		if(gymDayValue != null) {
			InvoiceDto invoiceSingleProduct = makeInvoiceDtoSingleProduct(request.getUser(), gymDayValue.getName(), gymDayValue.getId(), gymDayValue.getPrice());
			Invoice invoice = generateInvoice(invoiceSingleProduct, "DAY");
			invoice.setTypeConcept(ETypeConcept.GYM);
			paymentInvoice(invoice, request.getPaymentMethod());
		} else {
			throw new RuntimeException("No está confifurado correctamente el valor del día de gimnasio.");
		}
	}


	@Transactional
	public void paymentSubscriptionProcess(PaymentsSubscriptionRequest request) {
		Optional<Subscription> suscriptionOption = subscriptionService.findByID(request.getIdSubscription());
		Subscription subscription = suscriptionOption.isPresent() ? suscriptionOption.get() : null;
		if(subscription != null) {
			Membership membership = subscription.getMembership();
			InvoiceDto invoiceSingleProduct = makeInvoiceDtoSingleProduct(request.getUserId(), membership.getName(), membership.getId(), subscription.getPrice());
			Invoice invoice = generateInvoice(invoiceSingleProduct, "SUBSCRIPTION", subscription.getId());
			invoice.setTypeConcept(ETypeConcept.GYM);
			paymentInvoice(invoice, request.getPaymentMethod());
			subscriptionService.renew(subscription);
		} else {
			throw new RuntimeException("La suscripción no existe.");
		}
	}

	public void paymentProductProcess(PaymentsProductRequest request) {
		System.out.println("request == > " + request);
		String[] productsList = request.getListProducts().split(",");
		InvoiceDto invoiceDto = new InvoiceDto();
		invoiceDto.setUser(request.getUser());
		invoiceDto.setProductsList(new ArrayList<ProductItemDto>());
		
		for (int i = 0; i < productsList.length; i++) {
			Product product = productService.findByID(Long.parseLong(productsList[i])).orElse(null);
			if(product != null) {
				invoiceDto.getProductsList().add(new ProductItemDto(product.getName(), product.getId(), product.getValue()));
			}
		}
		
		Invoice invoice = generateInvoice(invoiceDto, "TIENDA");
		invoice.setTypeConcept(ETypeConcept.STORE);
		paymentInvoice(invoice, request.getPaymentMethod());
	}
	
}
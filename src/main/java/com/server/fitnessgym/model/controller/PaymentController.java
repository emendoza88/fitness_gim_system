package com.server.fitnessgym.model.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.server.fitnessgym.model.dto.IPaymentDao;
import com.server.fitnessgym.model.dto.PaymentDto;
import com.server.fitnessgym.model.dto.PaymentTotalValueByConcept;
import com.server.fitnessgym.model.dto.PaymentsDayRequest;
import com.server.fitnessgym.model.dto.PaymentsProductRequest;
import com.server.fitnessgym.model.dto.PaymentsSubscriptionRequest;
import com.server.fitnessgym.model.service.CustomerService;
import com.server.fitnessgym.model.service.PaymentService;
import com.server.fitnessgym.model.service.ProcessPaymentService;
import com.server.fitnessgym.model.service.ProductService;
import com.server.fitnessgym.model.util.Utils;

@Controller
@RequestMapping(path = "/payments")
public class PaymentController {
	
	private static final String TITLE = "Pagos";
	
	private ProcessPaymentService service;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CustomerService clientService;
	
	@Autowired
	private ProductService productService;
	
	public PaymentController (ProcessPaymentService service) {
		this.service = service;
	}
	
	private PaymentDto toPaymentDao(IPaymentDao interfacePayment) {
		PaymentDto dao = new PaymentDto();
		dao.setId(interfacePayment.getId());
		dao.setConcept(interfacePayment.getConcept());
		dao.setEmail(interfacePayment.getEmail());
		dao.setValue(Utils.numberToString(interfacePayment.getValue()));
		dao.setName(interfacePayment.getName());
		dao.setSurname(interfacePayment.getSurname());
		dao.setPaymentDate(Utils.dateTimeToString(interfacePayment.getPaymentDate()));
		dao.setPaymentMethod(interfacePayment.getPaymentMethod().getName());
		dao.setPhone(interfacePayment.getPhone());
		dao.setTypeConcept(interfacePayment.getTypeConcept().getName());
		return dao;
	}
	
	@GetMapping
	public String list(Map<String, Object> model, @RequestParam(required = false) String initialDate, @RequestParam(required = false) String endDate) {
		List<PaymentDto> list = new ArrayList<PaymentDto>();
		PaymentTotalValueByConcept paymentTotal = null;
		
		if(initialDate != null && endDate != null) {
			LocalDate localDate = LocalDate.parse(initialDate);
			LocalDate localDate2 = LocalDate.parse(endDate);
			
			list = paymentService.findByDate(localDate, localDate2).stream().map(x -> toPaymentDao(x)).collect(Collectors.toList());
			paymentTotal = paymentService.getPaymentTotalValueByConcept(localDate, localDate2);
		} else {
			list = paymentService.findByCurrentMonth().stream().map(x -> toPaymentDao(x)).collect(Collectors.toList());
			paymentTotal = paymentService.getPaymentTotalValueByConceptCurrentMonth();
		}
		
		model.put("paymentList", list);
		model.put("totalPayments", paymentTotal);
		model.put("title", TITLE);
		model.put("initialDate", initialDate);
		model.put("endDate", endDate);
		return "pages/paymentReport";
	}
	
	@GetMapping("/day")
	public String paymnetDay(Model model) {
		model.addAttribute("payment", new PaymentsDayRequest());
		model.addAttribute("userList", clientService.findAll());
		return "pages/paymentDayCreate";
	}
	
	@PostMapping("/day")
	public String paymnetDaySave(PaymentsDayRequest request) {
		service.paymentDayProcess(request);
		return "redirect:/payments/day";
	}
	
	@GetMapping("/subscription")
	public String paymentSubscription(Model model, @RequestParam Long user) {
		model.addAttribute("userSubscription", clientService.getSuscriptionClient(user));
		model.addAttribute("payment", new PaymentsSubscriptionRequest());
		return "pages/paymentSubscription";
	}
	
	@PostMapping("/subscription")
	public String paymentSubscriptionSave(PaymentsSubscriptionRequest request) {
		service.paymentSubscriptionProcess(request);
		return "redirect:/customers";
	}
	
	@GetMapping("/product")
	public String paymentProduct(Model model) {
		model.addAttribute("payment", new PaymentsProductRequest());
		model.addAttribute("userList", clientService.findAll());
		model.addAttribute("productList", productService.findAll().stream().map(ProductMapperData::toDto).collect(Collectors.toList()));
		return "pages/paymentProductCreate";
	}
	
	@PostMapping("/product")
	public String paymentProductSave(PaymentsProductRequest request) {
		service.paymentProductProcess(request);
		return "redirect:/payments/product";
	}
}
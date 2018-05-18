package com.luxmart.store.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxmart.store.model.OrderProduct;
import com.luxmart.store.model.OrderReport;
import com.luxmart.store.repository.ReportRepository;
import com.luxmart.store.service.ReportService;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

		@Autowired
		ReportRepository reportRepository;

	@Override
	public void addOrderReport(OrderReport orderReport) {
		reportRepository.addOrderReport(orderReport);
		
	}


	@Override
	public void addOrderProducts(OrderProduct orderProducts) {
		reportRepository.addOrderProducts(orderProducts);
		
	}


	@Override
	public OrderReport findByOrderId(long id) {
		
		return reportRepository.findByOrderId(id);
	}

}

package com.luxmart.store.service;

import com.luxmart.store.model.OrderProduct;
import com.luxmart.store.model.OrderReport;

public interface ReportService {
	
	
public void addOrderReport(OrderReport orderReport);
public void addOrderProducts(OrderProduct orderProducts);

public OrderReport findByOrderId(long id);
	
}

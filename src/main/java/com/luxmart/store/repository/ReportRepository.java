package com.luxmart.store.repository;

import com.luxmart.store.model.OrderProduct;
import com.luxmart.store.model.OrderReport;

public interface ReportRepository {
	
	public void addOrderReport(OrderReport orderReport);
	public void addOrderProducts(OrderProduct orderProducts);
	public OrderReport findByOrderId(long id);

}

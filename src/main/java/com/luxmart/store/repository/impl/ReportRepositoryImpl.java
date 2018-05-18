package com.luxmart.store.repository.impl;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luxmart.store.model.OrderProduct;
import com.luxmart.store.model.OrderReport;
import com.luxmart.store.repository.ReportRepository;


@Repository("reportRepository")
@Transactional
public class ReportRepositoryImpl implements ReportRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	@Autowired
	  public ReportRepositoryImpl(EntityManagerFactory factory) {
	    if(factory.unwrap(SessionFactory.class) == null){
	      throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.sessionFactory = factory.unwrap(SessionFactory.class);
	  }

	@Override
	public void addOrderReport(OrderReport orderReport) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(orderReport);
		session.flush();
		

	}

	@Override
	public void addOrderProducts(OrderProduct orderProducts) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(orderProducts);
		session.flush();
		
	}

	@Override
	public OrderReport findByOrderId(long id) {
		
           Session session= sessionFactory.getCurrentSession();
		
		// get the cart a by Id
		 return (OrderReport) session.get(OrderReport.class, id);
		

	}

}

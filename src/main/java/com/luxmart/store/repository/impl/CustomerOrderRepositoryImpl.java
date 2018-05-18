package com.luxmart.store.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luxmart.store.model.CustomerOrder;
import com.luxmart.store.repository.CustomerOrderRepository;


@Repository("customerOrderRepository")
@Transactional
public class CustomerOrderRepositoryImpl implements CustomerOrderRepository {

	@Autowired
	SessionFactory  sessionFactory;

	@Override
	public void addCustomerOrder(CustomerOrder customerOrder) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(customerOrder);
		session.flush();

	}

	

}

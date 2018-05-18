package com.luxmart.stripe;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.luxmart.store.model.Cart;
import com.luxmart.store.model.CartItem;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.CustomerOrder;
import com.luxmart.store.model.OrderProduct;
import com.luxmart.store.model.OrderReport;
import com.luxmart.store.service.CartItemService;
import com.luxmart.store.service.CartService;
import com.luxmart.store.service.CustomerService;
import com.luxmart.store.service.ReportService;
import com.stripe.model.Charge;

@Controller
public class StripeController {
	
	@Autowired
	CustomerService  customerService;
	
	@Autowired
	PaymentService  paymentService;
	
	@Autowired
	CartService cartService;

	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	ReportService reportService;
	
	@RequestMapping(value = "/orderConfirmed")
	public String paymentSuccessfull(){
		
		return "Checkout/thankCustomer";
	}
	
	
	@RequestMapping(value = "/orderFailed")
	public String paymentFaliled(){
		
		return "Checkout/checkoutError";
	}
	
	
	@RequestMapping(value = "/addPayment", method =RequestMethod.POST)
	public String addPayment(@RequestParam(value = "stripeToken", required=false) String stripeToken, @AuthenticationPrincipal User activeUser){
	
	//	System.out.println("Add payment " + stripeToken);
		
		// get the customer info of the customer that is currently logged in
				Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
				if(stripeToken != null && !stripeToken.isEmpty()){
					paymentService.addpayment(stripeToken, customer.getStripeCustomerId());		
				}
				
				 return "redirect:/checkout?cartId=1";
	}
	
	
	@RequestMapping(value = "paypalPayment/{id}", method= RequestMethod.GET)
	public String paypalPayment(@PathVariable String id, Model model, @AuthenticationPrincipal User activeUser){
		
		System.out.println("paypal " + id);
		
		//String string = id;
		String[] parts = id.split("-");
		//String total = parts[0]; // 004 int result = Integer.parseInt(number);
		double paypalTotal = Double.parseDouble(parts[0]);
		String address = parts[1]; // 034556
		
		// get the customer info of the customer that is currently logged in
		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
		
		
		
		CustomerOrder order = new CustomerOrder();
		//order.setChargeAmount(amount.getAmount());;
		order.setCustomer(customer);
	
		
		 // remove all items from the cart
		 Cart cart = cartService.getCartById(order.getCustomer().getCart().getCartId());
		 
		 
		 
		 // get the current date and time 
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		 LocalDateTime now = LocalDateTime.now();
   
		 // save the current order
		 OrderReport orderReport = new OrderReport();
		
		 
		 orderReport.setCustomerId(customer.getCustomerId());
		 orderReport.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
		 orderReport.setCustomerEmail(customer.getEmail());
		 orderReport.setDate(dtf.format(now));
		 orderReport.setPaymentMethod("Paypal");
		 orderReport.setTotalPrice(new BigDecimal(paypalTotal));
		 orderReport.setDeliveryAddress(address);
		 orderReport.setCartSubtotal(new BigDecimal(customer.getCart().getGrandTotal()));
		 
		
		 // save orderReport
		 reportService.addOrderReport(orderReport);
		 
		// fetch the cart items list
			List<CartItem> cartItems = cart.getCartItems();
			// iterate through the items in the cart
			for(int i= 0; i < cartItems.size(); i++){
				
				 OrderProduct orderProduct = new OrderProduct();
				
				 CartItem item = cartItems.get(i);
				 orderProduct.setCode(item.getProduct().getProductCode());
				 orderProduct.setName(item.getProduct().getName());
				 orderProduct.setCategory(item.getProduct().getCategory());
				 orderProduct.setDescription(item.getProduct().getDescription());
				 orderProduct.setType(item.getProduct().getType());
				 orderProduct.setQuantity(item.getQuantity());
				 orderProduct.setPrice(new BigDecimal(item.getProduct().getPrice()));
				 orderProduct.setManufacturer(item.getProduct().getManufacturer());					 
				 orderProduct.setOrderReport(orderReport);
				 

				// save orderProduct
				 reportService.addOrderProducts(orderProduct);
			}
		 
		 

			model.addAttribute("orderReport", reportService.findByOrderId(orderReport.getId()));
			
			// remove all items from cart
		     cartItemService.removelAllCartItems(cart);
		
			return "forward:/orderConfirmed";
	
		
	}
	
	
	@RequestMapping(value = "customer/placeOrder", method= RequestMethod.POST)
	public String paymentSuccesful(@Valid  @ModelAttribute ("amount") AmountStripe amount, @AuthenticationPrincipal User activeUser, Model model ) {
		
		// get the customer info of the customer that is currently logged in
		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
		
		
		
		CustomerOrder order = new CustomerOrder();
		order.setChargeAmount(amount.getAmount());;
		order.setCustomer(customer);
	
		
			//  charge the customer credit card
			 Charge charge =  paymentService.chargeCreditCard(order);
			 
			 
			 if(charge  != null){	 // success	
				 
				 // get the current date and time 
				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				 LocalDateTime now = LocalDateTime.now();
		     /// System.out.println(dtf.format(now)); //2016/11/16 12:08:43
				 
				 // remove all items from the cart
				 Cart cart = cartService.getCartById(order.getCustomer().getCart().getCartId());
				 
				 // save the current order
				 OrderReport orderReport = new OrderReport();
				
				 
				 orderReport.setCustomerId(customer.getCustomerId());
				 orderReport.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
				 orderReport.setCustomerEmail(customer.getEmail());
				 orderReport.setDate(dtf.format(now));
				 orderReport.setPaymentMethod("Stripe");
				 orderReport.setTotalPrice(new BigDecimal(order.getChargeAmount() / 100 ));
				 orderReport.setDeliveryAddress(amount.getDeliveryAddress());
				 orderReport.setCartSubtotal(new BigDecimal(customer.getCart().getGrandTotal()));
				 
				
				 // save orderReport
				 reportService.addOrderReport(orderReport);
				 
				// fetch the cart items list
					List<CartItem> cartItems = cart.getCartItems();
					// iterate through the items in the cart
					for(int i= 0; i < cartItems.size(); i++){
						
						 OrderProduct orderProduct = new OrderProduct();
						
						 CartItem item = cartItems.get(i);
						 orderProduct.setCode(item.getProduct().getProductCode());
						 orderProduct.setName(item.getProduct().getName());
						 orderProduct.setCategory(item.getProduct().getCategory());
						 orderProduct.setDescription(item.getProduct().getDescription());
						 orderProduct.setType(item.getProduct().getType());
						 orderProduct.setQuantity(item.getQuantity());
						 orderProduct.setPrice(new BigDecimal(item.getProduct().getPrice()));
						 orderProduct.setManufacturer(item.getProduct().getManufacturer());					 
						 orderProduct.setOrderReport(orderReport);
						 
	
						// save orderProduct
						 reportService.addOrderProducts(orderProduct);
					}
				 
				
				
				// removes all items form the cart
				 cartItemService.removelAllCartItems(cart);
				 
			
			
				
				model.addAttribute("orderReport", reportService.findByOrderId(orderReport.getId()));
			
				
				
					return "forward:/orderConfirmed";
				} else {	 //failure				
					
					return "redirect:/orderFailed";
				}	
		 
	
	}
	
}

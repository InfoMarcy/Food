package com.luxmart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luxmart.security.model.User;
import com.luxmart.store.model.Customer;
import com.luxmart.store.service.CustomerService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
@Component
public class RegisterController {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	CustomerService customerService;
	
	
	@Autowired
  public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder) {    
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   
  }
	
	
	
	// Return registration form template
		@RequestMapping(value="/register", method = RequestMethod.GET)
		public ModelAndView registerCustomer(ModelAndView modelAndView){
			
			// add a new Customer 
			Customer customer = new Customer();
		
			
			// Relationship between Customer and cart is one to one
			
			// add the customer to the model to work on the Jsp page
			modelAndView.addObject("customer", customer);		
			
			
			// set view view page
			modelAndView.setViewName("/Login/register");
			
			//return the view page 
			return modelAndView;
		}
	
		
		@RequestMapping(value="/register", method = RequestMethod.POST)
		public String registerCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, HttpServletRequest request, Model model){
			
			
			// check if there are any errors in the form
			if(result.hasErrors()){
				
				return "/Login/register";
			}
			
			
			//check if the user already exist
			List<Customer> customerList = customerService.getAllCustomers();
			for(int i=0; i < customerList.size(); i++){
				
				// check if the customer is in the list of already registered customers
				if(customer.getEmail().equals(customerList.get(i).getEmail())) {
					
				 //add message
					model.addAttribute("emailsMsg" , "Email Already exist");
					
					//send them back to register form
					return "/Login/register";
				}
				
				
			}
			
			
			// password encryption
						Zxcvbn passwordCheck = new Zxcvbn();			
						Strength strength = passwordCheck.measure(customer.getPassword());
						
			
			if (strength.getScore() < 3) {
				result.reject("password");
				
			//	redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

                model.addAttribute("passwordMsg" , "Your password is too weak.  Choose a stronger one.");
				
				return "/Login/register";
			}
			
			///Set new password			
			customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
			
			
		
			
			// add new Customer to the database
			customer.setEnabled(true);
			customerService.addCustomer(customer, request);
			
			// write a confirmation message
			model.addAttribute("confirmationMessage", "A confirmation e-mail has been sent to " + customer.getEmail());
			
			 return "/Login/registerMessage";
		
		}
		
		// Process confirmation link
		@RequestMapping(value="/confirm", method = RequestMethod.GET)
		public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
				
			User user = customerService.findByConfirmationToken(token);
			
			if (user == null) { // No token found in DB
				modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
			} else { // Token found
				// write a confirmation message
				modelAndView.addObject("confirmationMessage", "Customer registered successfully!");
				
				
				user.setEnabled(true);
				customerService.enableUser(user);
			}
				
			modelAndView.setViewName("/Login/confirm");
			return modelAndView;		
		}
		
		

}

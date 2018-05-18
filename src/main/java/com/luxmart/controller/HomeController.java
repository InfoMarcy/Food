package com.luxmart.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luxmart.model.GoogleLatLng;
import com.luxmart.model.Restaurant;
import com.luxmart.model.RestaurantHeaderType;
import com.luxmart.service.RestaurantService;
import com.luxmart.store.model.Cart;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.CustomerOrder;
import com.luxmart.store.model.ShippingAddress;
import com.luxmart.store.model.util.CheckoutInfo;
import com.luxmart.store.service.CartItemService;
import com.luxmart.store.service.CartService;
import com.luxmart.store.service.CustomerService;
import com.luxmart.stripe.AmountStripe;
import com.luxmart.stripe.PaymentModel;
import com.luxmart.stripe.PaymentService;

@Controller
public class HomeController implements ErrorController {

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	CartItemService CartItemService;

	@Autowired
	CartService cartService;

	@Autowired
	CustomerService customerService;

	@Autowired
	PaymentService paymentService;
	


	String lat;
	String lng;
	String deliveryAddress;
	String postalCode;
	String streetNumber;
	String streetName;
	String city;
	String state;
	String country;

	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/about-us")
	public String aboutUs() {

		return "Home/about";
	}

	// error page
     private static final String PATH = "/error";
    @RequestMapping(value = { PATH },  method = RequestMethod.GET)
    public ModelAndView error(){        
         
        // set the view as the Jsp page name index
        ModelAndView mv = new ModelAndView("Home/error");
         
     
        // return the ModelView
        return mv;
    }
     

	// overide the default error White label
	@Override
	public String getErrorPath() {
		return PATH;
	}

	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("coords") GoogleLatLng coords) {

		// set the view as the Jsp page name index
		ModelAndView mv = new ModelAndView("Home/index");

		// return the ModelView
		return mv;

	}

	@RequestMapping(value = { "/index" }, method = RequestMethod.POST)
	public ModelAndView getMap(@ModelAttribute("coords") GoogleLatLng coords) {

		// System.out.println("lat: " + coords.getGoogleLat() + " , Lng: " +
		// coords.getGoogleLng() );

		// get the LatLng from the Jsp and Javascript
		if (coords.getGoogleLat() != null && coords.getGoogleLng() != null) {
			lat = null;
			lng = null;
			deliveryAddress = null;
			postalCode = null;
			streetNumber = null;
			streetName = null;
			city = null;
			state = null;
			country = null;

			streetNumber = (String) coords.getStreetNumber();
			streetName = (String) coords.getStreetName();
			city = (String) coords.getCity();
			state = (String) coords.getState();
			country = (String) coords.getCountry();

			lat = (String) coords.getGoogleLat();
			lng = (String) coords.getGoogleLng();
			deliveryAddress = (String) coords.getDeliveryAddress();
			postalCode = (String) coords.getPostalCode();
		}

		return new ModelAndView("redirect:" + "/restaurants");
	}

	@RequestMapping(value = { "/restaurants" }, method = RequestMethod.GET)
	public String getRestaurants(Model model) {

		if (lat == null || lng == null) {

			return "redirect:/index";
		}

		int km = 10;

		// get a list of Restaurants from the service model
		if (lat != null && lng != null) {
			List<Restaurant> restaurants = restaurantService.findAllRestaurants(lat, lng, km);

			model.addAttribute("restaurants", restaurants);
		}

		// Horizontal Scroll Bar Restaurant Type
		List<RestaurantHeaderType> foodHeaders = restaurantService.getHeaders();
		model.addAttribute("foodHeaders", foodHeaders);
		model.addAttribute("deliveryAddress", deliveryAddress);

		// return the ModelView
		return "Home/restaurants";
	}

	@RequestMapping(value = { "/restaurants/{foodType}" }, method = RequestMethod.GET)
	public ModelAndView getFoodTypeRest(@PathVariable String foodType) {

		// System.out.println(foodType);
		// @RequestParam
		// set the view as the Jsp page name index
		ModelAndView mv = new ModelAndView("Home/restaurants");

		if (foodType.equals("View All")) {

			List<Restaurant> restaurants = restaurantService.findAllRestaurants(lat, lng, 10);

			mv.addObject("restaurants", restaurants);

		} else {

			List<Restaurant> restaurants = restaurantService.getFoodTypeRest(lat, lng, foodType);

			int restCount = restaurants.size();
			// add the restaurant
			mv.addObject("restaurants", restaurants);
			mv.addObject("foodType", foodType);
			mv.addObject("restCount", restCount);

		}

		// Horizontal Scroll Bar Restaurant Type
		List<RestaurantHeaderType> foodHeaders = restaurantService.getHeaders();
		mv.addObject("foodHeaders", foodHeaders);
		mv.addObject("deliveryAddress", deliveryAddress);

		return mv;

	}

	@RequestMapping(value = { "/restaurants" }, method = RequestMethod.POST)
	public ModelAndView findRetaurant(@RequestParam(value = "search", required = false) String search) {

		// set the view as the Jsp page name index
		ModelAndView mv = new ModelAndView("Home/restaurants");

		if (search != null) {

			List<Restaurant> restaurants = restaurantService.findRestaurant(lat, lng, 10, search);

			int restCount = restaurants.size();

			// add the restaurant
			mv.addObject("restaurants", restaurants);
			mv.addObject("foodType", search);
			mv.addObject("restCount", restCount);

		}

		// Horizontal Scroll Bar Restaurant Type
		List<RestaurantHeaderType> foodHeaders = restaurantService.getHeaders();
		mv.addObject("foodHeaders", foodHeaders);
		mv.addObject("deliveryAddress", deliveryAddress);

		return mv;
	}

	// // checkout page
	@RequestMapping("/checkout")
	public String checkout(@RequestParam(value = "cartId", required = false) Integer cartId, Model model,
			@ModelAttribute("checkoutInfo") CheckoutInfo checkoutInfo, @AuthenticationPrincipal User activeUser)
			throws IOException {

		if (lat == null || lng == null) {

			return "redirect:/index";
		} else if (cartId == null) {

			return "redirect:/store";
		}

		// get the customer info of the customer that is currently logged in
		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());

		// validate that the cart exist

		Cart cart = new Cart();

		// check if the cart exist if not redirect to index
		try {

			cart = cartService.validate(cartId);
		} catch (Exception e) {

			return "redirect:/store";
		}

		CustomerOrder order = new CustomerOrder();
		order.setCart(cart);

		// set a new order
		checkoutInfo.setOrder(order);

		// create a new instance of the shipping address
		checkoutInfo.setShipping(new ShippingAddress());

		// set the shipping info for new shipping and delivery instructions
		checkoutInfo.getShipping().setGoogleLat(lat);
		checkoutInfo.getShipping().setGoogleLng(lng);
		checkoutInfo.getShipping().setDeliveryAddress(deliveryAddress);
		checkoutInfo.getShipping().setPostalCode(postalCode);
		checkoutInfo.getShipping().setStreetNumber(streetNumber);
		checkoutInfo.getShipping().setStreetName(streetName);
		checkoutInfo.getShipping().setCity(city);
		checkoutInfo.getShipping().setState(state);
		checkoutInfo.getShipping().setCountry(country);

		// get the restaurant by name
		Restaurant restaurant = restaurantService
				.getRestaurantByName(cart.getCartItems().get(0).getProduct().getManufacturer());

		// set the restaurant to the bundle class
		checkoutInfo.setRestaurant(restaurant);

		// set a new payment method
		checkoutInfo.setPayment(new PaymentModel());
		// get the key for stripe payments
		checkoutInfo.getPayment().setKey(paymentService.getKey());

		AmountStripe amountStripe = new AmountStripe();

		model.addAttribute("amountStripe", amountStripe);
		model.addAttribute("checkoutInfo", checkoutInfo);
		model.addAttribute("cartId", cartId);
		model.addAttribute("customer", customer);

		return "Checkout/checkout";

	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String changeAddress(@Valid @ModelAttribute("checkoutInfo") CheckoutInfo checkoutInfo,
			@Valid @ModelAttribute("amountStripe") AmountStripe amountStripe, Model model,
			@AuthenticationPrincipal User activeUser) {

		// get the customer info of the customer that is currently logged in
		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());

		// get the restaurant by name
		Restaurant restaurant = restaurantService
				.getRestaurantByName(customer.getCart().getCartItems().get(0).getProduct().getManufacturer());

		// set the restaurant to the bundle class
		checkoutInfo.setRestaurant(restaurant);

		// set the customer for the shipping
		checkoutInfo.getShipping().setCustomer(customer);

		// get the LatLng from the Jsp and Javascript
		if (checkoutInfo.getShipping().getGoogleLat() != null && checkoutInfo.getShipping().getGoogleLng() != null) {
			lat = null;
			lng = null;
			deliveryAddress = null;

			streetName = (String) checkoutInfo.getShipping().getStreetName();
			city = (String) checkoutInfo.getShipping().getCity();
			lat = (String) checkoutInfo.getShipping().getGoogleLat();
			lng = (String) checkoutInfo.getShipping().getGoogleLng();
			deliveryAddress = (String) checkoutInfo.getShipping().getDeliveryAddress();
			postalCode = (String) checkoutInfo.getShipping().getPostalCode();
			streetNumber = (String) checkoutInfo.getShipping().getStreetNumber();

			state = (String) checkoutInfo.getShipping().getState();
			country = (String) checkoutInfo.getShipping().getCountry();
		} else { // set the google coords from the global

			checkoutInfo.getShipping().setGoogleLat(lat);
			checkoutInfo.getShipping().setGoogleLng(lng);
			checkoutInfo.getShipping().setDeliveryAddress(deliveryAddress);
			checkoutInfo.getShipping().setPostalCode(postalCode);
			checkoutInfo.getShipping().setStreetNumber(streetNumber);
			checkoutInfo.getShipping().setStreetName(streetName);
			checkoutInfo.getShipping().setCity(city);
			checkoutInfo.getShipping().setState(state);
			checkoutInfo.getShipping().setCountry(country);

		}

		customerService.addShipping(checkoutInfo);

		// add the shipping info to the model

		model.addAttribute("checkoutInfo", checkoutInfo);
		model.addAttribute("cartId", customer.getCart().getCartId());

		return "Checkout/checkout";

	}

}

package com.luxmart.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.luxmart.store.model.Cart;
import com.luxmart.store.model.CartItem;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.Product;
import com.luxmart.store.service.CartItemService;
import com.luxmart.store.service.CartService;
import com.luxmart.store.service.CustomerService;
import com.luxmart.store.service.ProductService;

@Controller
@RequestMapping("/rest/cart")
public class CartResources {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping("/{cartId}")
	//@ResponseBody sends the respond in json format
	public @ResponseBody Cart getCartById(@PathVariable(value="cartId") int cartId){
		
		
		// return a Cart object in json format get the cart by Id
		return cartService.getCartById(cartId);
		
	}
	
	@RequestMapping(value="/add/{productId}/{productQuantity}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable(value="productId") int productId,
			@PathVariable(value="productQuantity") int productQuantity,
			@AuthenticationPrincipal User activeUser){
		
		//System.out.println("productQuantity " + productQuantity);
		// get the customer info from the session
		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
		// get the cart for that customer
		
		Cart cart = customer.getCart();
		
		// define a product
		Product product = productService.getProductById(productId);
		
		
		// fetch the cart items list
		List<CartItem> cartItems = cart.getCartItems();
	
		// iterate through the items in the cart
		for(int i= 0; i < cartItems.size(); i++){
			
						// check if product is already in the cart
						if(product.getId() == cartItems.get(i).getProduct().getId()){
							// products is already on the cart
							// so we retrieve the cart item from the item list
							CartItem cartItem = cartItems.get(i);
							
							// set the quantity of the item 
							cartItem.setQuantity(cartItem.getQuantity() + productQuantity);
							
							// get the total price of the product product price * quantity
							cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
							
							//update Cart item
							cartItemService.addCartItem(cartItem);
							
							return;
						     } // end if check if product is already in the cart
						
			  }
		
			// create new cartItem
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(productQuantity);
			
			
			// get total price
			cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
			
			cartItem.setProduct(product);
			
			// add the cartItem to the cart
			cartItem.setCart(cart);
			
			
			
			//update Cart item
			cartItemService.addCartItem(cartItem);
			
			
		
	}
	
	//remove an item from the  cart
	@RequestMapping(value="/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable(value="productId") int productId){

		// get the cart item of an specific product
	//CartItem cartItem = cartItemService.getCartItemByProductId(productId);
	
	//	System.out.println(cartItem.getCartItemId());
		
		//void removeProductFromCartItem(int productId);
		// remove the the item from the cart
		cartItemService.removeProductFromCartItem(productId);
		
		
	}
	
	
	//remove an item from the  cart
		@RequestMapping(value="/{cartId}", method = RequestMethod.DELETE)
		@ResponseStatus(value = HttpStatus.NO_CONTENT)
		public void clearCart(@PathVariable(value="cartId") int cartId){
			
			// get the cart 
			Cart cart = cartService.getCartById(cartId);
			
			// remove the the  all the items from the cart
			cartItemService.removelAllCartItems(cart);
		}
		
	
		
		// handler exceptions 
		@ExceptionHandler(IllegalArgumentException.class)
		@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason =" Ilegal Request, please verify your payload.")
        public void handleClientException(Exception e){
			
		}
		
		
		@ExceptionHandler(Exception.class)
		@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason =" Internal Server Error.")
        public void handleServerErrors(Exception e){
			
		}
}

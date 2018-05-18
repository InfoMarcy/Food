package com.luxmart.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.luxmart.model.RestaurantHeaderType;
import com.luxmart.service.RestaurantService;
import com.luxmart.store.model.Location;
import com.luxmart.store.model.RestaurantDb;
import com.luxmart.store.model.util.CreateRestaurant;
import com.luxmart.store.service.AmazonS3Services;
import com.luxmart.store.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminRestaurant {

	// get path to create or update image
	private Path path;

	@Autowired
	ProductService productService;
	@Autowired
	RestaurantService restaurantService;

	@Autowired
	AmazonS3Services amazonS3Services;

	// add Restaurant function
	@RequestMapping("/restaurant/addRestaurant")
	public String addRestaurant(Model model) {

		// set default values to set the values on the jsp page
		CreateRestaurant restaurant = new CreateRestaurant();
		restaurant.setRestaurant(new RestaurantDb());
		restaurant.setLocation(new Location());

		model.addAttribute("restaurant", restaurant);

		return "Admin/addRestaurant";
	}

	// save Restaurant to database
	@RequestMapping(value = "/restaurant/addRestaurant", method = RequestMethod.POST)
	public String addRestaurantPost(@Valid @ModelAttribute("restaurant") CreateRestaurant restaurant,
			BindingResult result, HttpServletRequest request) {

		// check if the data submitted from jsp has errors
		if (result.hasErrors()) {

			return "Admin/addRestaurant";
		}

		// save image
		MultipartFile restaurantLogo = restaurant.getRestaurant().getLogo();

		// add new restaurant to Database
		restaurantService.addRestaurant(restaurant);

		String imageUrl = null;
		if (restaurantLogo != null && !restaurantLogo.isEmpty()) {

			try {
				// convert image to pass it to amazon s3
				InputStream s3Image = restaurantLogo.getInputStream();

				String restaurantImageName = restaurant.getRestaurant().getId() + "_rest_logo.png";
				imageUrl = amazonS3Services.uploadFile(restaurantImageName, s3Image);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Restaurant Image saving Failed ", e);
			}

		}

		if (imageUrl != null) {
			restaurant.getRestaurant().setImageUrl(imageUrl);
		}

		// add the image_url
		restaurantService.updateRestaurant(restaurant);

		return "redirect:/admin/restaurant";
	}

	// edit Product function
	@RequestMapping("/restaurant/editRestaurant/{id}")
	public String editRestaurant(@PathVariable("id") int id, Model model) {

		RestaurantDb restaurant = restaurantService.getRestaurantById(id);

		model.addAttribute("restaurant", restaurant);
		return "Admin/editRestaurant";
	}

	// save product to database
	@RequestMapping(value = "restaurant/editRestaurant", method = RequestMethod.POST)
	public String editRestaurant(@Valid @ModelAttribute("product") CreateRestaurant restaurant, BindingResult result,
			HttpServletRequest request) {

		// check if the data submitted from jsp has errors
		if (result.hasErrors()) {

			return "Admin/editRestaurant";
		}

		// save image
		MultipartFile restaurantLogo = restaurant.getRestaurant().getLogo();
		// get root directory for the image
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		path = Paths.get(rootDirectory + "\\resources\\Images\\RestaurantLogos\\"
				+ restaurant.getRestaurant().getId() + ".png");
		if (restaurantLogo != null && !restaurantLogo.isEmpty()) {

			try {
				restaurantLogo.transferTo(new File(path.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Restaurant Logo saving Failed ", e);
			}
		}

		// Update the current Product in the Database
		restaurantService.updateRestaurant(restaurant);

		return "redirect:/admin/restaurant";
	}

	@RequestMapping(value = { "/restaurant/deleteRestaurant/{id}" }, method = RequestMethod.GET)
	public String deleteRestaurant(@PathVariable("id") int id, HttpServletRequest request) {

		// get root directory for the image
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		path = Paths.get(rootDirectory + "\\resources\\Images\\RestaurantLogos\\" + id + ".png");

		// delete Image
		if (Files.exists(path)) {

			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Restaurant Logo Failed ", e);
			}
		}

		// delete the product
		restaurantService.deleteRestaurant(id);

		// return the ModelView
		return "redirect:/admin/restaurant";

	}

	// ribon

	// add Restaurant function
	@RequestMapping("/foodTypeImage")
	public String addRestaurantImageType(Model model) {

		model.addAttribute("foodType", new RestaurantHeaderType());

		// Horizontal Scroll Bar Restaurant Type
		List<RestaurantHeaderType> foodHeaders = restaurantService.getHeaders();
		model.addAttribute("foodHeaders", foodHeaders);

		return "Admin/addRestaurantTypeImage";
	}

	// save Restaurant to database
	@RequestMapping(value = "/foodTypeImage", method = RequestMethod.POST)
	public String addRestImageTypePost(Model model, @Valid @ModelAttribute("foodType") RestaurantHeaderType foodType,
			BindingResult result, HttpServletRequest request) {

		// check if the data submitted from jsp has errors
		if (result.hasErrors()) {
			model.addAttribute("msg", "You need to provide a rstaurant food type");
			return "redirect:/admin/foodTypeImage";
		}

		// save image u
		MultipartFile headerImage = foodType.getImage();
		// add new restaurant to Database
		restaurantService.addHeaderType(foodType);

		String imageUrl = null;
		if (headerImage != null && !headerImage.isEmpty()) {

			try {
				// convert image to pass it to amazon s3
				InputStream s3Image = headerImage.getInputStream();

				String restaurantImageName = foodType.getId() + "_Rest_food_Type_.png";
				imageUrl = amazonS3Services.uploadFile(restaurantImageName, s3Image);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Image saving Failed ", e);
			}

		}

		if (imageUrl != null) {
			foodType.setImageUrl(imageUrl);

		}

		// add the image_url
		restaurantService.updateHeaderType(foodType);

		return "redirect:/admin/foodTypeImage";

	}

	@RequestMapping("/editFoodTypeImage/{id}")
	public String editRestaurantImageType(Model model, @PathVariable int id) {

		model.addAttribute("foodType", restaurantService.getfoodTypeById(id));

		return "Admin/editRestaurantTypeImage";
	}

	@RequestMapping(value = "/editFoodTypeImage", method = RequestMethod.POST)
	public String editRestaurantImageTypePost(@Valid @ModelAttribute("foodType") RestaurantHeaderType foodType,
			BindingResult result, HttpServletRequest request) {

		// save image u
		MultipartFile headerImage = foodType.getImage();

		String imageUrl = null;
		if (headerImage != null && !headerImage.isEmpty()) {

			try {
				// convert image to pass it to amazon s3
				InputStream s3Image = headerImage.getInputStream();

				String restaurantImageName = foodType.getId() + "_Rest_food_Type_.png";
				imageUrl = amazonS3Services.uploadFile(restaurantImageName, s3Image);

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Image saving Failed ", e);
			}

		}

		if (imageUrl != null && !imageUrl.isEmpty()) {
			foodType.setImageUrl(imageUrl);

		}

		// add the image_url
		restaurantService.updateHeaderType(foodType);

		return "redirect:/admin/foodTypeImage";
	}

	// delete a Restaurant Header type
	@RequestMapping(value = "/deleteFoodType/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteType(@PathVariable int id) {

		if (id > 0) {

			restaurantService.deleteHeaderFoodType(id);
		}

	}

}

package sg.edu.ntu.cart_api.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.cart_api.Entity.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private static List<Product> productList = new ArrayList<>();
	
	static {
		productList.add(new Product((long)1, "apple", "red and fresh", (float)0.99));
		productList.add(new Product((long)2, "banana", "yellow and fresh", (float)1.50));
		productList.add(new Product((long)3, "carrot", "orange and chewy", (float)2.50));
	}

	    
	@GetMapping
	public @ResponseBody List<Product> findAll() {
		return productList;
	}
	

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody Product findById(@PathVariable int id) {
		Product productSearch = productList.stream()
				.filter((prod)-> prod.getId() == id)
				.findFirst()
				.orElse(null);
		return productSearch;
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Product create(@RequestBody Product product) {
		productList.add(product);
		return product;
	}
	

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public @ResponseBody Product editProduct(@RequestBody Product product, @PathVariable Long id) {
		Product productSearch = productList.stream()
							.filter((prod)-> prod.getId() == id)
							.findFirst()
							.orElse(null);
		productList.set(productList.indexOf(productSearch), product);
		return productList.get(productList.indexOf(product));
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public String editProduct( @PathVariable Long id) {
		Product productSearch = productList.stream()
				.filter((prod)-> prod.getId() == id)
				.findFirst()
				.orElse(null);
		productList.remove(productList.indexOf(productSearch));
		return "Product with " + id +  " has been successfully removed!";
	}
	
	
}

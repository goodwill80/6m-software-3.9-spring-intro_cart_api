package sg.edu.ntu.cart_api.Controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.cart_api.Entity.Product;
import sg.edu.ntu.cart_api.Service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	
	@Autowired
	private ProductService service;
	

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> findAll() {
			return service.findAllProducts();
	}
	

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Product findById(@PathVariable long id) {
		return service.findProductById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		return service.createNewProduct(product);
	}
	

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public String editProduct(@RequestBody Product product, @PathVariable Long id) {
		Product finalProduct = service.editProductById(product, id);
		return "The product - " + finalProduct.getName() + " with id of " + finalProduct.getId() + " has been successfully updated!";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public String deleteProduct( @PathVariable Long id) {
		service.deleteProduct(id);
		return "Product with " + id +  " has been successfully removed!";
	}
	
	
}

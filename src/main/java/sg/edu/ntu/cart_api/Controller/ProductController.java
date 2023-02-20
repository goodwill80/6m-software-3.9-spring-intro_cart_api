package sg.edu.ntu.cart_api.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@GetMapping
	public String findAll() {
		return "Get /products is being called";
	}
	
//	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@GetMapping("/{id}")
	public String findById(@PathVariable int id) {
		return "Get /products/" + id  + " is being called";
	}
	
}

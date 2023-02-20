package sg.edu.ntu.cart_api.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.cart_api.Entity.Product;

@Service
public class ProductService {
	
	private static List<Product> productList = new ArrayList<>();
	
	
	static {
		productList.add(new Product((long)1, "apple", "red and fresh", (float)0.99));
		productList.add(new Product((long)2, "banana", "yellow and fresh", (float)1.50));
		productList.add(new Product((long)3, "carrot", "orange and chewy", (float)2.50));
	}
	
	static Long id = (long)productList.size();
	
	public List<Product> findAllProducts() {
		return productList;
	}
	
	public Product findProductById(Long id) {
		Product productSearch = productList.stream()
				.filter((prod)-> prod.getId() == id)
				.findFirst()
				.orElse(null);
		if(productSearch == null) {
			throw new NullPointerException("The product id cannot be found!");
		}
		return productSearch;
	}
	
	public Product createNewProduct(Product product) {
		if(product.getDescription().isBlank() || product.getName().isBlank() || product.getPrice() == 0) {
			throw new NullPointerException("Fields cannot be blank!");
		}
		product.setId(++id);
		productList.add(product);
		return product;
	}
	
	public Product editProductById(Product product, Long id) {
		Product productSearch = productList.stream()
				.filter((prod)-> prod.getId() == id)
				.findFirst()
				.orElse(null);
		if(productSearch == null) {
			throw new NullPointerException("The product id cannot be found!");
		}
		productList.set(productList.indexOf(productSearch), product);
		Product finalProduct = productList.get(productList.indexOf(product));
		return finalProduct;
	}
	
	public String deleteProduct(Long id) {
		Product productSearch = productList.stream()
				.filter((prod)-> prod.getId() == id)
				.findFirst()
				.orElse(null);
		if(productSearch == null) {
			throw new NullPointerException("The product id cannot be found!");
		}
		productList.remove(productList.indexOf(productSearch));
		return "Product with " + id +  " has been successfully removed!";
	}
	
	
	
}

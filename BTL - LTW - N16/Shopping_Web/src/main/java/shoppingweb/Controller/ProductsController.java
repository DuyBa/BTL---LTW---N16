package shoppingweb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import shoppingweb.Model.AddedProduct;
import shoppingweb.Model.Product;

@Controller
//@RequestMapping("/homepage")
public class ProductsController {

	
	@Autowired 
	ProductsRepository productsRepository;
	
	
	@GetMapping("/product")
	public String pd(Model model)
	{
		List<Product> pds= productsRepository.findAll();
		model.addAttribute("pds", pds);
		return "product";
	}
}

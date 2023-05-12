package shoppingweb.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import shoppingweb.Model.Account;
import shoppingweb.Model.AddedProduct;

@Controller
public class CartController {
	@Autowired
	AddedProductRepository addedProductRepository;
	@Autowired
	AccountsRepository accountsRepository;
	@Autowired
	ProductsRepository productsRepository;
	
	
	@GetMapping("/cart")
	public String cart(HttpSession ss, Model model)
	{
		Optional<Account> aa= (Optional<Account>) ss.getAttribute("ssacc");
		Account acc= accountsRepository.getById(aa.get().getGmail());
		
		List<AddedProduct> apd= new ArrayList<>();
		
		for(AddedProduct i: acc.getAddedProducts())
		{
			if(i.getStt()== null)
				apd.add(i);
		}
		
		int quantity= apd.size();
		
		AddedProduct store= new AddedProduct();
		
		model.addAttribute("quantity", quantity);
		model.addAttribute("apds", apd);
		model.addAttribute("store", store);
		return "cart";
	}
}

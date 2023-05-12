package shoppingweb.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import shoppingweb.Model.*;

@Controller
public class Product_DetailController {
	
	@Autowired
	ProductsRepository productsRepository;
	@Autowired
	AccountsRepository accountsRepository;
	@Autowired
	AddedProductRepository addedProductRepository;
	@Autowired
	EntityManager entityManager;
	
	
	
	@GetMapping("{code}")
	public String dt(@PathVariable String code, Model model)
	{
		Product a= productsRepository.getById(code);
		model.addAttribute("pd", a);
		return "product_detail";
	}
	
	
	
	

	@PostMapping("/addproduct/{code}")
	public ResponseEntity<String> addProductToCart(@PathVariable("code") String productCode,
													Model model, 
													HttpServletRequest request,
													HttpSession ss) {

		
		String successMessage= new String();
		
	    int quantity = Integer.parseInt(request.getParameter("quantity"));
	    String size = request.getParameter("size");
	    String color = request.getParameter("color");
		Optional<Account> a = (Optional<Account>) ss.getAttribute("ssacc");
		System.out.println(productCode+ quantity+ size+ color);
		Account aa= accountsRepository.getById(a.get().getGmail());
		
		int ck= 0;
		for(AddedProduct apd: aa.getAddedProducts())
		{
//			System.out.println(apd.getPd().getCode()+ " "+ apd.getStt());
			if (apd.getPd().getCode().equals(productCode) && apd.getStt()== null)
			{
				
				ck= 1;
				successMessage= "The product has been in the cart!";
				break;
			}
		}
		
		if(ck== 0)
		{
			AddedProduct apd= new AddedProduct();
			ArrayList<AddedProduct> listapd= (ArrayList<AddedProduct>) addedProductRepository.findAll();
			
			ArrayList<Integer> sort= new ArrayList<>();
			for(AddedProduct i: listapd)
			{
				sort.add(Integer.parseInt(i.getCode().substring(5)));
			}
			
			Collections.sort(sort);
			
			
//			String lastID= listapd.get(listapd.size()- 1).getCode();
//			int so= Integer.parseInt(lastID.substring(5))+ 1;
			Product pd= productsRepository.getById(productCode);
			
			
			apd.setCode("ADDPD"+ String.valueOf(sort.get(sort.size()-1)+1));
			apd.setColor(color);
			apd.setPd(pd);
			apd.setPrice(pd.getPrice());
			apd.setQuantity(quantity);
			apd.setSize(size);
			
			aa.getAddedProducts().add(apd);
			pd.getAddedProducts().add(apd);
			
			addedProductRepository.save(apd);
			productsRepository.save(pd);
			accountsRepository.save(aa);
			
			successMessage= "Product was added to cart successfully!";
			
		}
		
	    return ResponseEntity.ok(successMessage);
	}
	
	
	@GetMapping("/remove/{code}")
	public String remove(@PathVariable String code, HttpSession ss)
	{
		AddedProduct apdrm= addedProductRepository.getById(code);
		
		Optional<Account> aa= (Optional<Account>) ss.getAttribute("ssacc");
		Account a= accountsRepository.getById(aa.get().getGmail());
		
		a.getAddedProducts().remove(apdrm);
		accountsRepository.save(a);
		
		Product pd= apdrm.getPd();
		
		pd.getAddedProducts().remove(apdrm);
		productsRepository.save(pd);
		
		
		addedProductRepository.delete(addedProductRepository.getById(code));
		return "redirect:/cart";
	}
	
	
	@GetMapping("/update/{code}")
	public String update (@PathVariable String code, Model model)
	{
		
		
		AddedProduct apd= addedProductRepository.getById(code);
		
		model.addAttribute("apd", apd);
		return "updateapd";
	}
	
	@PostMapping("/updated/{code}")
	public String updated(@ModelAttribute AddedProduct apd, @PathVariable String code)
	{
		AddedProduct apdd= addedProductRepository.getById(code);
		apdd.setColor(apd.getColor());
		apdd.setQuantity(apd.getQuantity());
		apdd.setSize(apd.getSize());
		addedProductRepository.save(apdd);
		return "redirect:/cart";
	}
	
}

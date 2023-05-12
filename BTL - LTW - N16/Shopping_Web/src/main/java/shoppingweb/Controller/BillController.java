package shoppingweb.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import shoppingweb.Model.Account;
import shoppingweb.Model.AddedProduct;
import shoppingweb.Model.Bill;

@Controller
public class BillController {

	@Autowired
	AccountsRepository accountsRepository;
	@Autowired
	ProductsRepository productsRepository;
	@Autowired
	AddedProductRepository addedProductRepository;
	@Autowired
	BillRepository billRepository;
	
	@GetMapping("/bill")
	public String bill(Model model, HttpSession ss)
	{
		Optional<Account> aa=  (Optional<Account>) ss.getAttribute("ssacc");
		Bill bill= new Bill();
		
		
		Account a= accountsRepository.getById(aa.get().getGmail());

//		bill.setAcc(a);
		
		double summ= 0.0;
		
		ArrayList<AddedProduct> apddd= new ArrayList<>();
		
		for(AddedProduct apd : a.getAddedProducts())
		{
			if(apd.getStt()== null)
			{
				apddd.add(apd);
				summ+= apd.getQuantity()* apd.getPrice();
			}

		}
//		bill.setListapds(apddd);
		
		
		model.addAttribute("bill", bill);
		model.addAttribute("apds", apddd);
		model.addAttribute("acc", a);
		model.addAttribute("sum", summ);
		
		return "checkout";
	}
	
	
	@GetMapping("/mybill")
	public String mybill(Model model, HttpSession ss)
	{
		Optional<Account> aa= (Optional<Account>) ss.getAttribute("ssacc");
		Account acc= accountsRepository.getById(aa.get().getGmail());
		
		List<Bill> listbill= acc.getListbill();
		
		for(Bill bill: listbill)
		{
			double tong = 0;
			for(AddedProduct apd : bill.getListapds())
			{
				tong+= apd.getPrice()* apd.getQuantity(); 
			}
//			bill.setCost(tong);
//			billRepository.save(bill);
		}
		
		
		model.addAttribute("quantity", listbill.size());
		
		model.addAttribute("bills", listbill);
		return "mybill";
	}
	
	@GetMapping("/viewbill/{code}")
	public String viewbill(@PathVariable String code, Model model)
	{
		Bill bill= billRepository.getById(code);
		
		List<AddedProduct> apd= bill.getListapds();
		
		model.addAttribute("bill", bill);
		model.addAttribute("apds", apd);
		model.addAttribute("code", bill.getId());
		return "viewabill";
	}
	
	@PostMapping("/updatebill/{code}")
	public String updatebill(@ModelAttribute Bill bill, @PathVariable String code)
	{
		bill.setId(code);
		bill.setStt("pending");
		billRepository.save(bill);
		return "redirect:/mybill";
	}
}

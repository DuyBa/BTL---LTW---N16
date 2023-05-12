package shoppingweb.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import shoppingweb.Model.Account;
import shoppingweb.Model.AddedProduct;
import shoppingweb.Model.Bill;

@Controller
public class PurchaseController {
	
	@Autowired
	BillRepository billRepository;
	@Autowired
	AccountsRepository accountsRepository;
	@Autowired
	AddedProductRepository addedProductRepository;
	
	@PostMapping("/confirm")
	public String confirm(Model model, HttpSession ss, @ModelAttribute Bill bill)
	{
		Optional<Account> accc= (Optional<Account>) ss.getAttribute("ssacc");
		Account acc= accountsRepository.getById(accc.get().getGmail());
		
		List<Bill> listbill= billRepository.findAll();
		
		
		ArrayList<Integer> sort= new ArrayList<>();
		for(Bill i: listbill)
		{
			sort.add(Integer.parseInt(i.getId().substring(4)));
		}
		
		Collections.sort(sort);
		
		
		String id= String.valueOf(sort.get(sort.size()-1)+ 1);
		
		List<AddedProduct> apdss= new ArrayList<>();
		for(AddedProduct i: acc.getAddedProducts())
		{
			if(i.getStt()== null)
				apdss.add(i);
		}
		
		Bill b= new Bill();
		
		b.setId("BILL"+ id);
		b.setAcc(acc);
		b.setAddress(bill.getAddress());
		b.setDes(bill.getDes());
		b.setEmail(bill.getEmail());
		b.setName(bill.getName());
		b.setListapds(apdss);
		b.setTel(bill.getTel());
		b.setStt("pending");
		
		acc.getListbill().add(b);
		
		billRepository.save(b);
		accountsRepository.save(acc);
		
		for(AddedProduct i: apdss)
		{
			i.setStt("pass");
			addedProductRepository.save(i);
		}

		
		model.addAttribute("bill", b);
		
//		System.out.println(bill.getName());
		return "order_confirmation";
	}
}

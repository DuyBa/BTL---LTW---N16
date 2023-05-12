package shoppingweb.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import shoppingweb.Model.Account;

@Controller
public class RegisterController {
	@Autowired
	AccountsRepository accountsRepository;
	
	@GetMapping("/register")
	public String register(Model model)
	{
		Account accc= new Account();
		model.addAttribute("Account", accc);
		return "register";
	}
	
	@PostMapping("/regist")
	public String clickregist(@ModelAttribute Account acc, Model model)
	{
		Optional<Account> newacc= accountsRepository.findById(acc.getGmail());
		
		if(newacc.isEmpty())
		{
			acc.setRole("client");
			accountsRepository.save(acc);
			model.addAttribute("message", "successfully");
			model.addAttribute("Account", acc);
			
		}
		if(!newacc.isEmpty())
		{
			model.addAttribute("message", "account existed!");
			model.addAttribute("Account", acc);
			
		}
		return "register";
	}
}

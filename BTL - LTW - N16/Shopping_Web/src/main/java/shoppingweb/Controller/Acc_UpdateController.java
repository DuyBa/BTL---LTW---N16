package shoppingweb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shoppingweb.Model.Account;

@Controller
public class Acc_UpdateController {
	@Autowired
	AccountsRepository accountsRepository;
	
	@GetMapping("/changein4/{gm}")
	public String change(@PathVariable String gm, Model model)
	{
		Account acc= accountsRepository.getById(gm);
		model.addAttribute("acc", acc);
		return "accin4";
	}
	
	@PostMapping("/save/{gm}")
	public String save(@ModelAttribute Account acc, @PathVariable String gm, Model model)
	{
		Account accc= accountsRepository.getById(gm);
		accc.setName(acc.getName());
		accc.setPhonenumber(acc.getPhonenumber());
		acc.setAddress(acc.getAddress());
		accountsRepository.save(accc);
		model.addAttribute("message", "successfully");
		model.addAttribute("acc", accc);
		return "accin4";
	}
}

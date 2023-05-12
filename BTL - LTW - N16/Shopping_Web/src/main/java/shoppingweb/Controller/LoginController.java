package shoppingweb.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import shoppingweb.Model.*;


@Controller
//@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	AccountsRepository accountsRepository;
	
	
	
	@GetMapping("/login")
	public String loginnn()
	{
		return "Login";
	}
	
	
	@PostMapping("/loginbyacc")
	public String loginsuccessfully(HttpSession ss,  @ModelAttribute Account acc, Model model)
	{
		Optional<Account> a= accountsRepository.findById(acc.getGmail());
		
		if(a.isPresent())
		{
			String pw= a.get().getPassword();
			if(pw.equals(acc.getPassword()))
			{
				
				int size= a.get().getAddedProducts().size();
				String gm= a.get().getGmail();
				
				ss.setAttribute("size", size);
				ss.setAttribute("ssacc", a);
				ss.setAttribute("gm", gm);
				return "redirect:/homepage";
//				return "test";
			}	
		}
		return "redirect:/login";
		
	}
	
	@GetMapping("/logout")
	public String logoutsucessfully(HttpSession ss)
	{
		ss.removeAttribute("ssacc");
		
		return "redirect:/homepage";
	}
}

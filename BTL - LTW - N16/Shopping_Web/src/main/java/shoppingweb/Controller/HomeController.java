package shoppingweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
//	@GetMapping()
//	public String home()
//	{
//		return "index";
//	}
	
	@GetMapping("/homepage")
	public String home1()
	{
		return "index";
	}
	
	@GetMapping("/category")
	public String category()
	{
		return "category";
	}
	
	@GetMapping("/about")
	public String about()
	{
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact()
	{
		return "contact";
	}
	
	
}

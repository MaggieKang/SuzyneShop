package com.hannamsm.shop.domain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}
	
//	@RequestMapping("/{name}")
//	public Home home(@PathVariable String name) {
//		Home home = homeMapper.readHome(name);          
//		return home;
//	}
	
	 @RequestMapping("/admin")
     public String admin() {
          return "This is admin page";
     }
    
     @RequestMapping("/user")
     public String user() {
          return "this is user page";
     }
}

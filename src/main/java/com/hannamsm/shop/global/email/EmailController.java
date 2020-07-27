package com.hannamsm.shop.global.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hannamsm.shop.global.email.EmailDto;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EmailController {
	private final EmailService mailService;
	@GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("/mail")
    public void execMail(EmailDto mailDto) {
        mailService.mailSend(mailDto);
    }
}
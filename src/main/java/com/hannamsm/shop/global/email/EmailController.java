package com.hannamsm.shop.global.email;

import javax.validation.Valid;

import org.springframework.hateoas.MediaTypes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping(value ="/mailInvoice",produces = MediaTypes.HAL_JSON_VALUE)
    public void sendInvoice (@RequestBody @Valid EmailDto mailDto
            ) throws Exception {
        mailService.sendInvoice(mailDto);
    	}
}
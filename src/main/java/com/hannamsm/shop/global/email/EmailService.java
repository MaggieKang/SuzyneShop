package com.hannamsm.shop.global.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.global.email.EmailDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
	private JavaMailSender mailSender;
//    private static final String FROM_ADDRESS = "feel7678@gmail.com";

    public void newCustomerMailSend(EmailDto mailDto) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage(); 
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8"); 
        
         String confirmTitle ="HNS SHOP 회원가입을 축하드립니다.";
        String confirmBody = " <!DOCTYPE html>\n" + 
        		"<html lang=\"en\">\n" + 
        		"  <head>\n" + 
        		"    <meta charset=\"utf-8\" />\n" + 
        		"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no\" />\n" + 
        		"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" + 
        		"    <script src=\"//kit.fontawesome.com/a076d05399.js\"></script> \n" + 
        		"  </head>\n" + 
        		"  <body>\n" + 
        		"      <div style=\"width: 100%;font-size: 15px;\">\n" + 
        		"        <div style=\"\n" + 
        		"        margin-bottom: 50px;\n" + 
        		"        background-color: #fff;\n" + 
        		"        background: #FFFFFF 0% 0% no-repeat padding-box;\n" + 
        		"        box-shadow: 0px 3px 6px #00000029;\n" + 
        		"        border: 1px solid #DBDBDB;\">\n" + 
        		"     \n" + 
        		"          <div style=\"border-radius: 10px;\n" + 
        		"          box-shadow: 0px 3px #DE1F26;\n" + 
        		"          color: #323232;\n" + 
        		"          font-weight: bold;\n" + 
        		"          margin: 30px 10px 10px 10px;\n" + 
        		"          padding: 5px 10px 5px 10px;\n" + 
        		"          background-color: #F2F2F2;\n" + 
        		"          \">Complete your order</div>\n" + 
        		"\n" + 
        		"          <div id=\"myLogin\" style=\"width: 100%;\n" + 
        		"          margin: 0px auto;\">\n" + 
        		"            <ul style=\"list-style-type: none;\n" + 
        		"            padding-left: 0px;margin-bottom: 0px;\">\n" + 
        		"              <li>\n" + 
        		"                <div style=\"width: 80%;\n" + 
        		"                background: #fff;\n" + 
        		"                overflow: hidden;\n" + 
        		"                margin: auto;\n" + 
        		"                line-height: 50px;\n" + 
        		"                padding-top: 20px;\">\n" + 
        		"                  <ul style=\"list-style-type: none;\n" + 
        		"                  padding-left: 0px;margin-bottom: 0px;\">\n" + 
        		"                    <li><div style=\"\n" + 
        		"                        font-weight: bold;\n" + 
        		"                        color: #474747;\">Welcome!</div></li>\n" + 
        		"                    <li>\n" + 
        		"                      <div class=\"memInfoBox\">\n" + 
        		"                        Welcome to HNS online order & pick up service. You can\n" + 
        		"                        use all services on this site from now.\n" + 
        		"                      </div>\n" + 
        		"                    </li>\n" + 
        		"                    <li>\n" + 
        		"                      <div style=\"margin: 10px 2px 10px 2px;\n" + 
        		"                      cursor: pointer;\">\n" + 
        		"                        <a href=\"http://localhost/accountWelcome.html\" target=\"_blank\">\n" + 
        		"                            <input type=\"button\" value=\"Continue shopping\"  style=\"width: 100%;height: 50px;background-color: #B4B4B4;color: #ffffff;\"/> </a>\n" + 
        		"                      </div>\n" + 
        		"                    </li>\n" + 
        		"                  </ul>\n" + 
        		"                </div>\n" + 
        		"              </li>\n" + 
        		"              <li>\n" + 
        		"                <div style=\"background-color: #222222; color: #ffffff; margin-top: 20px;\n" + 
        		"                height: 80px;\">\n" + 
        		"                <div style=\"float: left;width: 80px; padding: 10px;\">\n" + 
        		"                    <img src=\"http://localhost/img/HNSHOP-LOGO-small.png\"> </div>\n" + 
        		"                  <div style=\"font-size: 0.7em;\n" + 
        		"                  padding-top: 30px;\">  Copyright 2020 © Hannam Supermarket. All Rights Reserved.</div>\n" + 
        		"                  </div>\n" + 
        		"              </li>\n" + 
        		"            </ul>\n" + 
        		"          </div>\n" + 
        		"        </div>\n" + 
        		"      </div>\n" + 
        		"      \n" + 
        		"  </body>\n" + 
        		"</html>\n" + 
        		"";
        message.setTo(mailDto.getAddress());
        //message.setFrom(EmailService.FROM_ADDRESS); 세팅하지 않으면 application.yml 에서 세팅
        message.setSubject(confirmTitle);
        message.setText(confirmBody);

        
        mimeMessage.setContent(confirmBody, "text/html"); 
        helper.setTo(mailDto.getAddress()); 
        helper.setSubject(confirmTitle); 
       // helper.setFrom("abc@gmail.com"); 
        mailSender.send(mimeMessage);
    }
    public void resetPassword(EmailDto mailDto) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage(); 
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8"); 
        
         String confirmTitle ="HNS SHOP Reset Password";
        String confirmBody = " <div style=\"max-width: 800px;  margin: 0px auto;box-shadow: 0px 3px 6px #00000029;border: 1px solid #DBDBDB;\" >\n" + 
        		"          <div style=\"border-radius: 10px;\n" + 
        		"          box-shadow: 0px 3px #DE1F26;\n" + 
        		"          color: #323232;\n" + 
        		"          font-weight: bold;\n" + 
        		"          margin: 30px 10px 10px 10px;\n" + 
        		"          padding: 5px 10px 5px 10px;\n" + 
        		"          background-color: #F2F2F2; \n" + 
        		"          font-size:2vw;\n" + 
        		"          \">Reset Password E-mail</div>\n" + 
        		"          \n" + 
        		"          <div id=\"myLogin\" class=\"login_container\">\n" + 
        		"            <ul style=\"list-style-type: none; padding-left: 0px;\">\n" + 
        		"              <li>\n" + 
        		"                <div style=\"width: 80%;\n" + 
        		"                background: #fff;\n" + 
        		"                margin: auto;\n" + 
        		"                line-height: 2;\n" + 
        		"                padding-top: 20px;\">\n" + 
        		"                  <ul style=\"list-style-type: none; padding-left: 0px;\">\n" + 
        		"                    <li><div style=\"font-size: 2.0vw;\n" + 
        		"                        font-weight: bold;\n" + 
        		"                        color: #474747;\">Please read this carefully</div></li>\n" + 
        		"                    <li>\n" + 	
        		"						<li>Your Temporary Password : \n"+
        		"							<div style=\"width: font-size: 1.5vw;font-weight: bold;color: red;\">\n"+
        								mailDto.getPassword()+
        		"						<div></li> \n"+
        		"                      <div class=\"memInfoBox\">\n" + 
        		"                        - This is an password change confirmation mail from your request. <br>\n" + 
        		"                        - Please click the confirm button below to finish this process.\n" + 
        		"                      </div>\n" + 
        		"                    </li>\n" + 
        		"                   \n" + 
        		"                    <li>\n" + 
        		"                      <div >\n" + 
        		"                        <a href=\"http://localhost/account-resetPassword.html\" target=\"_blank\">\n" + 
        		"                            <input type=\"button\" value=\"Confirm\"  style=\"width: 100%;height: 50px;background-color: #AC0000;color: #ffffff;font-size: 1.2vw\"/> </a>\n" + 
        		"                    </div>\n" + 
        		"                    </li>\n" + 
        		"                    <li><div style=\"line-height: 2;\n" + 
        		"                        height: 80px;\n" + 
        		"                        padding-top: 20px;\n" + 
        		"                        color: #AC0000;\n" + 
        		"                        font-weight: bold;\">* If you didn't try to change your password before,<br> don't click the below link and change your password on the HNS site.</div></li>\n" + 
        		"                    \n" + 
        		"                    <li>\n" + 
        		"                      \n" + 
        		"                    </li>\n" + 
        		"                  </ul>\n" + 
        		"                </div>\n" + 
        		"              </li>\n" + 
        		"              <li>\n" + 
        		"                <div style=\"background-color: #222222; color: #ffffff; font-size: 1.3vw;margin-top: 20px;\n" + 
        		"                height: 80px;\">\n" + 
        		"                    <img src=\"http://localhost/img/HNSHOP-LOGO-small.png\"> Copyright 2020 © Hannam Supermarket. All Rights Reserved.\n" + 
        		"                  </div>\n" + 
        		"              </li>\n" + 
        		"            </ul>\n" + 
        		"          </div>\n" + 
        		"        </div>";
        message.setTo(mailDto.getAddress());
        //message.setFrom(EmailService.FROM_ADDRESS); 세팅하지 않으면 application.yml 에서 세팅
        message.setSubject(confirmTitle);
        message.setText(confirmBody);

        
        mimeMessage.setContent(confirmBody, "text/html"); 
        helper.setTo(mailDto.getAddress()); 
        helper.setSubject(confirmTitle); 
       // helper.setFrom("abc@gmail.com"); 
        mailSender.send(mimeMessage);
    }
    //after shopping send Invoice Email
    public void sendInvoice(EmailDto mailDto) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage(); 
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8"); 
        
        message.setTo(mailDto.getAddress());
        //message.setFrom(EmailService.FROM_ADDRESS); 세팅하지 않으면 application.yml 에서 세팅
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mimeMessage.setContent(mailDto.getMessage(), "text/html"); 
        helper.setTo(mailDto.getAddress()); 
        helper.setSubject(mailDto.getTitle()); 
       // helper.setFrom("abc@gmail.com"); 
        mailSender.send(mimeMessage);
    }
    public void mailSend(EmailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo(mailDto.getAddress());
        //message.setFrom(EmailService.FROM_ADDRESS); 세팅하지 않으면 application.yml 에서 세팅
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
         
        mailSender.send(message);
    }
}
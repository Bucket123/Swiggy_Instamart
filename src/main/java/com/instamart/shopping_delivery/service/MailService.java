package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.configuration.AppConfiguration;
import com.instamart.shopping_delivery.models.AppUser;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;

@Service
@Slf4j
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);
    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;
    @Autowired
    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendWarehouseInvitationEmail(AppUser wareHouseAdmin){

        String acceptLink = "http://localhost:8080/api/v1/appuser/warehouse/admin/invite/"+wareHouseAdmin.getId().toString();
        Context context = new Context();
        context.setVariable("warehouseName","GroceryGo");
        context.setVariable("warehouseAdminName",wareHouseAdmin.getName());
        context.setVariable("acceptLink",acceptLink);

        String htmlContent = templateEngine.process("warehouse_Admin_Invite",context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            //mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(wareHouseAdmin.getEmail());
            mimeMessageHelper.setSubject("Welcome to Swiggy-Instamart");
            mimeMessageHelper.setText(htmlContent ,true);
        }catch (Exception e){
            log.error(e.getMessage());
            //System.out.print(e.getMessage());
        }
        javaMailSender.send(mimeMessage);

    }
}

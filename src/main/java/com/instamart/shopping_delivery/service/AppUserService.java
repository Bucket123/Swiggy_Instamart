package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.exceptions.UserNotExistException;
import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.repository.AppUserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.UUID;

@Service
public class AppUserService {

    AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    public AppUser registerCustomer(AppUser customer){
        // database m save krne k baad data return v ata hai
        return appUserRepository.save(customer);
    }

    public AppUser wareHouseAdminInvite(UUID userId, AppUser wareHouseAdmin){

        // verify the type of user is that correct or not
        AppUser admin = appUserRepository.findById(userId).orElse(null);
        if(admin == null){
            throw new UserNotExistException("User Not Exist");
        }

        if(!admin.getUserType().equals("APP_ADMIN")){
            throw new InvalidOperationException("User is not allowed to Invite admin");
        }

        wareHouseAdmin.setStatus("Inactive");
        this.sendEmailInvite(wareHouseAdmin);
        return appUserRepository.save(wareHouseAdmin);
    }

    public void sendEmailInvite(AppUser wareHouseAdmin){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("shubham7ja@gmail.com");
        javaMailSender.setPassword("eyolwtcqqezyldkb");
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            //mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(wareHouseAdmin.getEmail());
            mimeMessageHelper.setSubject("Welcome to Swiggy-Instamart");
            mimeMessageHelper.setText("How are you!" + wareHouseAdmin.getName(),true);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
        javaMailSender.send(mimeMessage);

    }


}

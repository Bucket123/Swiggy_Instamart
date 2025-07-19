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
    MailService mailService;
    @Autowired
    public AppUserService(AppUserRepository appUserRepository, MailService mailService){
        this.appUserRepository = appUserRepository;
        this.mailService = mailService;
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
        wareHouseAdmin = appUserRepository.save(wareHouseAdmin);
        mailService.sendWarehouseInvitationEmail(wareHouseAdmin);
        return wareHouseAdmin;
    }

    public void acceptWarehouseAdminInvite(UUID warehouseAdminId){
        AppUser warehouseAdmin = appUserRepository.findById(warehouseAdminId).orElse(null);
        warehouseAdmin.setStatus("Active");
        appUserRepository.save(warehouseAdmin);
    }





}

package com.instamart.shopping_delivery.controllers;

import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.exceptions.UserNotExistException;
import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appuser")
public class AppUserController {

    AppUserService appUserService;
    @Autowired
    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @PostMapping("/customer/registration")
    public AppUser customerRegistration(@RequestBody AppUser customer){
        // customer service
        System.out.println(customer);
        return appUserService.registerCustomer(customer);
    }

    @PostMapping("/warehouse/admin/invite")
    public ResponseEntity warehouseAdminInvite(@RequestParam UUID userId, @RequestBody AppUser warehouseAdmin){
        // appUserService ko call karenge
        try {
            appUserService.wareHouseAdminInvite(userId,warehouseAdmin);
            return new ResponseEntity("Inactive User record created in User DB", HttpStatus.CREATED);
        } catch (UserNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (InvalidOperationException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/warehouse/admin/invite/{warehouseAdminId}")
    public ResponseEntity acceptWarehouseAdminInvite(@PathVariable UUID warehouseAdminId){
        appUserService.acceptWarehouseAdminInvite(warehouseAdminId);
        return new ResponseEntity<>("Warehouse Admin Created",HttpStatus.CREATED);
    }
}

package com.instamart.shopping_delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;
    @Column(unique = true,nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    Long phoneNumber;

    String userType;
    String status;

    @OneToMany
    List<Location> locations;
    LocalDateTime createdAt;
    LocalDateTime updatedBy;

    public AppUser() {
    }

    public AppUser(UUID id, String name, String email, String password, Long phoneNumber, String userType, String status, List<Location> locations, LocalDateTime createdAt, LocalDateTime updatedBy) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.status = status;
        this.locations = locations;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(LocalDateTime updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", userType='" + userType + '\'' +
                ", status='" + status + '\'' +
                ", locations=" + locations +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                '}';
    }
}

package com.yaplab.user;

import com.yaplab.enums.UserStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * User entity to store user details like userName, email ID, mobile number and password.
 */
@Entity
@Table(name = "user_details")
public class User {

    /**
     * Unique identifier for each user which is assigned automatically.
     * Long is preferred for large datasets.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The userName of the user (cannot be null).
     */
    @Column(name = "userName", nullable = false)
    private String userName;

    /**
     * The emailId of the user (cannot be null and is unique for all users).
     */
    @Column(name = "email_id", unique = true, nullable = false)
    private String emailId;

    /**
     * The mobile number of the user (cannot be null and is unique for all users).
     */
    @Column(name = "mobile_number", unique = true, nullable = false)
    private String mobileNumber;

    /**
     * The password of the account (cannot be null).
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * New field to track if the user's email is verified.
     */
    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified = false;

    /**
     * New timestamp fields for auditing.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Updated timestamp field.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Status of the user(ONLINE or OFFLINE).
     */
    @Column(name = "user_status")
    private UserStatus status;

    @Column(name = "profile-picture-url")
    private String profilePictureUrl;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Parameterized constructor.
     */
    public User(Long id, String userName, String emailId, String mobileNumber, String password) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }


     /**
     *  Getters and Setters for returning the details and setting the details for the private fields
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
package com.cloud.rest.webservices.webapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;


    @JsonProperty("username")
    @NotBlank(message = "username is not entered")
    private String username;


    @JsonProperty("first_name")
    @NotBlank(message="Firstname is required")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message="Lastname is required")
    private String lastName;

    @JsonProperty(value ="account_created",access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime accountCreated;

    @JsonProperty(value = "account_updated",access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime accountUpdated;


    @JsonIgnore
    @JsonProperty("password")
    private String password;

    @JsonIgnore
    @JsonProperty("verified")
    private boolean verified;

    public User() {
    }

    public User(UUID id, String username, String firstName, String lastName, String password, LocalDateTime accountCreated,LocalDateTime accountUpdated,boolean verified) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.accountCreated =accountCreated;
        this.accountUpdated = accountUpdated;
        this.verified = verified;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }


    public LocalDateTime getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(LocalDateTime accountCreated) {
        this.accountCreated = accountCreated;
    }

    public LocalDateTime getAccountUpdated() {
        return accountUpdated;
    }

    public void setAccountUpdated(LocalDateTime accountUpdated) {
        this.accountUpdated = accountUpdated;
    }

    @JsonIgnore
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountCreated=" + accountCreated +
                ", accountUpdated=" + accountUpdated +
                ", password='" + password + '\'' +
                '}';
    }
}

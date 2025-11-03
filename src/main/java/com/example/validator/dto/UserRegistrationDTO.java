package com.example.validator.dto;

public class UserRegistrationDTO {

    private String username;
    private String email;
    private String password;
    private String birthDate;

    public UserRegistrationDTO() {}

    public UserRegistrationDTO(String username, String email, String password, String birthDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getBirthDate() { return birthDate; }

    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
}

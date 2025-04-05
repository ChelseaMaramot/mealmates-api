package org.example.mealmatesapi.dto;

import org.example.mealmatesapi.model.User;

public class UserDTO {

    private String username;
    private String email;
    private String password;

    public UserDTO(){};

    public UserDTO(String username, String password, String email){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

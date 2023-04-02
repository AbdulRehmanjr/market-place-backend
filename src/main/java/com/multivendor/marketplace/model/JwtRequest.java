package com.multivendor.marketplace.model;

public class JwtRequest {
    private String email;
    private String password;

    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "JwtRequest [email=" + email + ", password=" + password + "]";
    }
}

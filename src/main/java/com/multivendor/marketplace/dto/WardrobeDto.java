package com.multivendor.marketplace.dto;

public class WardrobeDto {
    

    private String id;
    
    private String title;

    private String description;
    
    private String code;
    

    private UserDto user;


    public WardrobeDto(String id, String title, String description, String code, UserDto user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.code = code;
        this.user = user;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public UserDto getUser() {
        return user;
    }


    public void setUser(UserDto user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "WardrobeDto [id=" + id + ", title=" + title + ", description=" + description + ", code=" + code
                + ", user=" + user + "]";
    }
}

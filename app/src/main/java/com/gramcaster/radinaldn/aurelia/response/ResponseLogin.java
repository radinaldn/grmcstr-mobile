package com.gramcaster.radinaldn.aurelia.response;

/**
 * Created by radinaldn on 10/02/19.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gramcaster.radinaldn.aurelia.model.User;

public class ResponseLogin {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
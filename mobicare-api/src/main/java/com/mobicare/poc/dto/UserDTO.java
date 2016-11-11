package com.mobicare.poc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by vinicius.martins on 01/06/2016.
 */
@ApiModel(value = "UserDTO")
public class UserDTO {


    private String login;
    @ApiModelProperty(name = "login",dataType = "string",example = "vinicius")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

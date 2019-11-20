package net.tabplus.api.modules.dto;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class NewAdminDto {
    @NotNull(message = "Number can not be null")
    @ApiModelProperty("账号名")
    private String number;

    @NotNull(message = "Password can not be null")
    @ApiModelProperty("密码")
    private String password;

    @Email(message = "Email is not correct")
    @ApiModelProperty("邮箱")
    private String email;

    @NumberFormat
    @ApiModelProperty("手机号")
    private String tel;

    @NumberFormat
    @ApiModelProperty("手机号前缀")
    private String telPrefix;

    @ApiModelProperty("角色主键列表")
    private List<Integer> roleIds = new ArrayList<>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTelPrefix() {
        return telPrefix;
    }

    public void setTelPrefix(String telPrefix) {
        this.telPrefix = telPrefix;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}

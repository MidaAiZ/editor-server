package net.tabplus.api.modules.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMenuItemDto implements Serializable {
    @NotNull
    @NotBlank
    @Length(min = 1, max = 64)
    @ApiModelProperty("网站标题")
    private String title;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 1024)
    @ApiModelProperty("网站url")
    private String url;

//    @NotNull
//    @NotBlank
    @Length(max = 1024)
    @ApiModelProperty("网站图标")
    private String iconSrc;

    @ApiModelProperty("网站对应的数据库记录主键")
    private Integer siteId;

    @ApiModelProperty("是否是目录")
    private Boolean isFolder = false;

    @Valid
    @Size(max = 100)
    @ApiModelProperty("当是目录时的子菜单列表，不允许嵌套")
    private List<UserMenuItemDto> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Boolean getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }

    public List<UserMenuItemDto> getChildren() {
        if (isFolder) {
            return children;
        } else {
            return null;
        }
    }

    public void setChildren(List<UserMenuItemDto> children) {
        this.children = children;
    }
}

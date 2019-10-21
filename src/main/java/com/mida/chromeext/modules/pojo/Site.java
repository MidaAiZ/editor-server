package com.mida.chromeext.modules.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Site implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="网站主键ID")
    private Integer sid;
    @NotBlank(message = "Title can not be null")
    @ApiModelProperty(value="网站名称")
    private String title;
    @NotBlank(message = "Url can not be null")
    @ApiModelProperty(value="网站链接")
    private String url;
    @NotBlank(message = "Icon can not be null")
    @ApiModelProperty(value="网站图标地址")
    private String icon;
    @ApiModelProperty(value="网站分类ID")
    private Integer cateId;
    @JsonIgnore
    private Float weight;
    @ApiModelProperty(value="网站状态，0表示未审核，1表示审核通过，2表示禁止访问")
    private Short state;
    @ApiModelProperty(value="网站标签")
    private String marks;
    @ApiModelProperty(hidden = true)
    private Admin createdAdmin;
    @ApiModelProperty(hidden = true)
    private SiteCategory category;
    @ApiModelProperty(hidden = true)
    private List<Country> countryList;
    @ApiModelProperty(hidden = true)
    private Short creatorType;
    @ApiModelProperty(hidden = true)
    private Integer creatorId;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer usedCount;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer pageViews;
    @ApiModelProperty(hidden = true)
    private Date createdAt;
    @ApiModelProperty(hidden = true)
    private Date updatedAt;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks == null ? null : marks.trim();
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Short getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(Short creatorType) {
        this.creatorType = creatorType;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", title=").append(title);
        sb.append(", url=").append(url);
        sb.append(", icon=").append(icon);
        sb.append(", cateId=").append(cateId);
        sb.append(", weight=").append(weight);
        sb.append(", marks=").append(marks);
        sb.append(", state=").append(state);
        sb.append(", creatorType=").append(creatorType);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", usedCount=").append(usedCount);
        sb.append(", pageViews=").append(pageViews);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
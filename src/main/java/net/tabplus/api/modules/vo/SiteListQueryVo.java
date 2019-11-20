package net.tabplus.api.modules.vo;

import net.tabplus.api.utils.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lihaoyu
 * @date 2019/9/24 18:52
 */
@ApiModel
public class SiteListQueryVo extends ListQueryVo {

    @ApiModelProperty("搜索关键字")
    private String keyword;

    @ApiModelProperty("网站状态")
    private Short state;

    @ApiModelProperty("创建者类型, 0管理员，1用户")
    private Short creatorType;

    @ApiModelProperty("创建者id数组")
    private List<Integer> creatorIds;

    @ApiModelProperty("分类id数组")
    private List<Integer> categoryIds;

    @ApiModelProperty("国家(地区)码数组")
    private ArrayList<String> countryCodes; // = Lists.newArrayList(Constant.DEFAULT_AREA);

    /**
     * 为前端设置默认条件
     * 屏蔽不允许前端进行搜索的内容
     */
    public void setDefaultConsForClient() {
        setCreatorIds(null);
        setState(Constant.SITE_STATE_OK);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public ArrayList<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(ArrayList<String> countryCodes) {
        this.countryCodes = countryCodes;
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

    public List<Integer> getCreatorIds() {
        return creatorIds;
    }

    public void setCreatorIds(List<Integer> creatorIds) {
        this.creatorIds = creatorIds;
    }
}

package net.tabplus.api.modules.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author lihaoyu
 * @date 2019/9/26 19:05
 */
@ApiModel(value = "分页查询对象")
public class ListQueryVo {
    @Min(value = 1, message = "当前页数，最小值为1")
    @ApiModelProperty(value = "当前页数，最小值为1", required = true)
    private Integer pageNum = 1;

    @Min(value = 1, message = "每页数量，最小值为1")
    @Max(value = 100, message = "每页数量，最大值为100")
    @ApiModelProperty(value = "每页数量，最小值为1，最大值为100", required = true)
    private Integer pageSize = 10;

    public ListQueryVo() {
    }

    public ListQueryVo(@NotNull @Min(1) Integer pageNum, @NotNull @Min(0) @Max(100) Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

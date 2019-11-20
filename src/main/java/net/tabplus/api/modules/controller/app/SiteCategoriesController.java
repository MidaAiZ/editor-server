package net.tabplus.api.modules.controller.app;

import com.github.pagehelper.util.StringUtil;
import com.mchange.v2.lang.StringUtils;
import net.tabplus.api.modules.service.SiteCategoryService;
import net.tabplus.api.utils.LocaleUtils;
import net.tabplus.api.utils.Result;
import net.tabplus.api.modules.pojo.SiteCategory;
import net.tabplus.api.modules.vo.ListQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("site_categories")
@Api(value = "前台网站分类操作获取接口", tags = "{}")
@Validated
public class SiteCategoriesController {
    @Autowired
    private SiteCategoryService siteCategoryService;

    @GetMapping("")
    @ApiOperation(value = "获取网站分类列表", notes = "返回的分类是经过本地化翻译后的分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页数，最小为1", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据量，最大为100", required = true, dataType = "Integer", paramType = "query"),})
    public Result<List<SiteCategory>> getCategories(@ApiIgnore @Validated ListQueryVo queryVo) {
        List<SiteCategory> categories = siteCategoryService.listCategories(queryVo).getList();
        // 设置本地化翻译
        // TODO：后期加缓存噢
        for (SiteCategory c : categories) {
            String localeTitle = LocaleUtils.getMsg("categories." + c.getTitle());
            if (!StringUtil.isEmpty(localeTitle)) {
                c.setTitle(localeTitle);
            }
        }
        return Result.ok(categories);
    }
}

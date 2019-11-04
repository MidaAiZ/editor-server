package com.mida.chromeext.modules.controller.manage;

import com.mida.chromeext.annotation.CurrentAdmin;
import com.mida.chromeext.components.shiro.PermisConstant;
import com.mida.chromeext.modules.pojo.Admin;
import com.mida.chromeext.modules.pojo.BgPicture;
import com.mida.chromeext.modules.service.BgPictureService;
import com.mida.chromeext.modules.vo.ListQueryVo;
import com.mida.chromeext.modules.vo.ListResultVo;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController("mngBgPicturesController")
@RequestMapping("manage/bg_pictures")
@Api(value = "后台壁纸图片操作接口", tags = "{}")
public class BgPicturesController {
    @Autowired
    private BgPictureService bgPictureService;

    @GetMapping("")
    @ApiOperation("获取系统中的壁纸列表")
    @RequiresPermissions(PermisConstant.SHOW_BG_PICTURE)
    public Result<ListResultVo<BgPicture>> list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return Result.ok(bgPictureService.getList(new ListQueryVo(pageNum, pageSize)));
    }

    @GetMapping("{pid}")
    @ApiOperation("获取主键获取一条记录")
    @RequiresPermissions(PermisConstant.SHOW_BG_PICTURE)
    public Result<BgPicture> show(@PathVariable Integer pid) {
        BgPicture bgPicture = bgPictureService.getById(pid);
        return bgPicture != null ? Result.ok(bgPicture) : Result.error(ResultCode.NOT_FOUND.code(), "No such picture with ID = " + pid.toString());
    }

    @PostMapping("")
    @ApiOperation("添加一张壁纸")
    @RequiresPermissions(PermisConstant.ADD_BG_PICTURE)
    public Result<BgPicture> create(@RequestBody @Validated BgPicture bgPicture, @ApiIgnore @CurrentAdmin Admin admin) {
        return bgPictureService.create(admin.getAid(), bgPicture) ? Result.ok(bgPicture) : Result.error();
    }

    @PutMapping("{pid}")
    @ApiOperation("通过主键更新一条数据")
    @RequiresPermissions(PermisConstant.MODIFY_BG_PICTURE)
    public Result<Boolean> update(@PathVariable Integer pid, @RequestBody BgPicture bgPicture) {
        bgPicture.setPid(pid);
        return bgPictureService.updateById(bgPicture) ? Result.ok(true) : Result.error();
    }

    @DeleteMapping("{pid}")
    @ApiOperation("通过主键删除一条数据")
    @RequiresPermissions(PermisConstant.DELETE_BG_PICTURE)
    public Result<Boolean> delete(@PathVariable Integer pid) {
        return bgPictureService.deleteById(pid) ? Result.ok(true) : Result.error();
    }
}

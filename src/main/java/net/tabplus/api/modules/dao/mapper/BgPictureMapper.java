package net.tabplus.api.modules.dao.mapper;

import net.tabplus.api.modules.pojo.BgPicture;
import net.tabplus.api.modules.pojo.BgPictureExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BgPictureMapper {
    long countByExample(BgPictureExample example);

    int deleteByExample(BgPictureExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(BgPicture record);

    int insertSelective(BgPicture record);

    List<BgPicture> selectByExample(BgPictureExample example);

    BgPicture selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") BgPicture record, @Param("example") BgPictureExample example);

    int updateByExample(@Param("record") BgPicture record, @Param("example") BgPictureExample example);

    int updateByPrimaryKeySelective(BgPicture record);

    int updateByPrimaryKey(BgPicture record);
}
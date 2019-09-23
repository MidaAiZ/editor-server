package com.mida.chromeext.mapper;

import com.mida.chromeext.pojo.BgPicture;
import com.mida.chromeext.pojo.BgPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysNoticeUserMapper {

    void deleteRelationByIds(Long[] noticeIds);
}

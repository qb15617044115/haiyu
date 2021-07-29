package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class VideoVO extends BaseDomain{
    // 视频 id
    private Long videoId;
    // 视频名称
    private String videoName;
    // 视频封面
    private String logoUrl;
    // 视频链接
    private String videoUrl;
    // 观看人数
    private Long watchNum;
    // 视频类型
    private Integer videoType;
    // 用户id
    private Integer userId;
    // 时间
    private String expireTime;
    // 机构id
    private List<Long> deptIds;

}

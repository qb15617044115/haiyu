package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.JsonObject;
import com.ruoyi.system.domain.vo.BaseDomain;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TxzhConfigAll extends BaseDomain {
    private Long id;
    private String codeNumber;
    private String titleName;
    private String configContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private ConfigVO configVO;
    private List<TxzhConfigAll> txzhConfigAllList = new ArrayList<>();
    private Integer status;
}

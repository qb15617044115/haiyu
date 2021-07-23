package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.domain.vo.BaseDomain;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 * 
 * @author ruoyi
 */
@Data
public class SysWalletLog extends BaseDomain {
    // id
    private Long id;
    // 主播id
    private Long liveUserId;
    // 主播昵称
    private String liveUserNickname;
    // 操作者id
    private Long operationId;
    // 操作者昵称
    private String operationNickname;
    // 操作前金额
    private BigDecimal beforeMoney;
    // 操作后金额
    private BigDecimal afterMoney;
    // 此次操作id
    private BigDecimal operationMoney;
    // 操作类型  1为收入  2为支出
    private Integer type;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}

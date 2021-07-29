package com.ruoyi.common.core.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysDeptVO {
    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    private List<SysDeptVO> children = new ArrayList<SysDeptVO>();
}

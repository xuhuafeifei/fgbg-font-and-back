package com.fgbg.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 用户所属单位设置表
 * @TableName tb_unit
 */
@TableName(value ="tb_unit")
@Data
public class TbUnit implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 单位名称
     */
    private String unit;

    /**
     * 父id
     */
    private Integer pid;

    @TableField(exist = false)
    private List<TbUnit> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
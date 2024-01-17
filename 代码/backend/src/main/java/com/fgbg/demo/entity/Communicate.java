package com.fgbg.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName communicate
 */
@TableName(value ="communicate")
@Data
public class Communicate implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private Integer pid;

    /**
     * 
     */
    private Integer userId;

    private Integer replyUserId;

    @TableField(exist = false)
    private List<Communicate> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
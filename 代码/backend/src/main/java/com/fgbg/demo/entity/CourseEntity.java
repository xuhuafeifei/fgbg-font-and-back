package com.fgbg.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author xuhuafei
 * @email 2508020102@qq.com
 * @date 2024-01-15 10:08:03
 */
@Data
@TableName("course")
public class CourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer courseId;
	/**
	 * 
	 */
	private String courseName;

}

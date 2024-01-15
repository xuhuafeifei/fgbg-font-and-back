package com.fgbg.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fgbg.common.utils.PageUtils;
import com.fgbg.demo.entity.CourseEntity;

import java.util.Map;

/**
 * 
 *
 * @author xuhuafei
 * @email 2508020102@qq.com
 * @date 2024-01-15 10:08:03
 */
public interface CourseService extends IService<CourseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


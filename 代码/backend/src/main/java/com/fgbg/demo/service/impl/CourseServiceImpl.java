package com.fgbg.demo.service.impl;

import com.fgbg.common.utils.PageUtils;
import com.fgbg.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fgbg.demo.dao.CourseDao;
import com.fgbg.demo.entity.CourseEntity;
import com.fgbg.demo.service.CourseService;


@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, CourseEntity> implements CourseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourseEntity> page = this.page(
                new Query<CourseEntity>().getPage(params),
                new QueryWrapper<CourseEntity>()
        );

        return new PageUtils(page);
    }

}
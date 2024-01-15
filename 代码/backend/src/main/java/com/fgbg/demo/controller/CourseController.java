package com.fgbg.demo.controller;

import java.util.Arrays;
import java.util.Map;

import com.fgbg.common.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fgbg.demo.entity.CourseEntity;
import com.fgbg.demo.service.CourseService;
import com.fgbg.common.utils.R;



/**
 * 
 *
 * @author xuhuafei
 * @email 2508020102@qq.com
 * @date 2024-01-15 10:08:03
 */
@RestController
@RequestMapping("demo/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("demo:course:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    @RequiresPermissions("demo:course:info")
    public R info(@PathVariable("courseId") Integer courseId){
		CourseEntity course = courseService.getById(courseId);

        return R.ok().put("course", course);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("demo:course:save")
    public R save(@RequestBody CourseEntity course){
		courseService.save(course);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("demo:course:update")
    public R update(@RequestBody CourseEntity course){
		courseService.updateById(course);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("demo:course:delete")
    public R delete(@RequestBody Integer[] courseIds){
		courseService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}

package com.fgbg.demo.controller;

import com.fgbg.common.utils.R;
import com.fgbg.demo.entity.Communicate;
import com.fgbg.demo.service.CommunicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("comm")
@RestController
public class CommunicateController {
    @Autowired
    private CommunicateService service;

    /**
     * 返回树形结构评论数据
     */
    @RequestMapping("/list")
    public R list() {
        List<Communicate> list = service.listTree();
        return R.ok().put("data", list);
    }

    /**
     * 保存评论
     */
    @RequestMapping("/save")
    public R save(@RequestBody Communicate entity) {
        entity.setCreateTime(LocalDateTime.now());
        service.save(entity);
        return R.ok();
    }
}

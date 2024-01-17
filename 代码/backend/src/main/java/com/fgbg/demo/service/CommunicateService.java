package com.fgbg.demo.service;

import com.fgbg.demo.entity.Communicate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface CommunicateService extends IService<Communicate> {

    /**
     * 返回树形评论数据
     * @return
     */
    List<Communicate> listTree();
}

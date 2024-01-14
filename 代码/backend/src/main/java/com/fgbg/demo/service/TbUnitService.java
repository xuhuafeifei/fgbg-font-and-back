package com.fgbg.demo.service;

import com.fgbg.demo.entity.TbUnit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface TbUnitService extends IService<TbUnit> {

    /**
     * 查询所有的unit数据, 并返回树形结构
     * @return
     */
    List<TbUnit> listAll();
}

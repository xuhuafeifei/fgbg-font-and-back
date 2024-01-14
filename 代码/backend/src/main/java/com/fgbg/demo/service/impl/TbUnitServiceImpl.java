package com.fgbg.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fgbg.demo.dao.TbUnitDao;
import com.fgbg.demo.entity.TbUnit;
import com.fgbg.demo.service.TbUnitService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class TbUnitServiceImpl extends ServiceImpl<TbUnitDao, TbUnit>
    implements TbUnitService {

    /**
     * 查询所有的unit数据, 并返回树形结构
     *
     * @return
     */
    @Override
    public List<TbUnit> listAll() {
        // 查询所有数据
        List<TbUnit> list = this.list();
        // 建立map映射(id->index)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < list.size(); index++) {
            Integer id = list.get(index).getId();
            map.put(id, index);
        }
        // ...
        for (int i = 0; i < list.size(); i++) {
            TbUnit node = list.get(i);
            Integer pid = node.getPid();
            // 有父亲
            if (pid != null) {
                // 找到pid的父亲, 并把当前节点(node)添加到父亲节点的children里面
                Integer indexParent = map.get(pid);
                // 获取父亲节点
                TbUnit parent = list.get(indexParent);
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                // 向父亲节点的children字段添加当前node
                parent.getChildren().add(node);
            }
        }
        // 过滤出一级节点
        List<TbUnit> ans = list.stream().filter(e -> e.getPid() == null).collect(Collectors.toList());
        return ans;
    }
}





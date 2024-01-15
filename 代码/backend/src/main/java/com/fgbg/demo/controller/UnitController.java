package com.fgbg.demo.controller;

import com.fgbg.common.utils.R;
import com.fgbg.demo.entity.TbUnit;
import com.fgbg.demo.service.TbUnitService;
import com.fgbg.demo.vo.LabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("unit")
@RestController
public class UnitController {
    @Autowired
    private TbUnitService unitService;

    @RequestMapping("/list")
    public R list() {
        List<TbUnit> list = unitService.listAll();
        /*----
        {
            code: 0,
            msg: 'xxx',
            data: [A, B, C, D, E, F, G, H]
        }
        ---*/
        return R.ok().put("data", list);
    }

    @RequestMapping("/listTreeSelect")
    public R listTreeSelect() {
        List<TbUnit> tbUnitList = unitService.list();
        List<LabelVo> list = tbUnitList.stream().map(e -> {
            LabelVo vo = new LabelVo();
            vo.setValue(e.getUnit());
            vo.setLabel(e.getUnit());
            vo.setId(e.getId());
            vo.setPid(e.getPid());
            return vo;
        }).collect(Collectors.toList());
        // TbUnit -> LabelVo
        // 建立map映射(id->index)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < list.size(); index++) {
            Integer id = list.get(index).getId();
            map.put(id, index);
        }
        // ...
        for (int i = 0; i < list.size(); i++) {
            LabelVo node = list.get(i);
            Integer pid = node.getPid();
            // 有父亲
            if (pid != null) {
                // 找到pid的父亲, 并把当前节点(node)添加到父亲节点的children里面
                Integer indexParent = map.get(pid);
                // 获取父亲节点
                LabelVo parent = list.get(indexParent);
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                // 向父亲节点的children字段添加当前node
                parent.getChildren().add(node);
            }
        }
        // 过滤出一级节点
        List<LabelVo> ans = list.stream().filter(e -> e.getPid() == null).collect(Collectors.toList());
        return R.ok().put("data", ans);
    }

    // 根据id查询节点数据{value id label}
    @RequestMapping("/listNode")
    public R listNode(@RequestParam Integer id) {
        TbUnit unit = unitService.getById(id);
        LabelVo labelVo = new LabelVo();
        labelVo.setLabel(unit.getUnit());
        labelVo.setValue(unit.getUnit());
        labelVo.setId(unit.getId());
        return R.ok().put("data", labelVo);
    }

    @RequestMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody TbUnit unit) {
        unitService.saveOrUpdate(unit);
        return R.ok();
    }
}

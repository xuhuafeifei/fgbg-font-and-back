package com.fgbg.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fgbg.demo.dao.CommunicateDao;
import com.fgbg.demo.entity.Communicate;
import com.fgbg.demo.service.CommunicateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class CommunicateServiceImpl extends ServiceImpl<CommunicateDao, Communicate>
    implements CommunicateService{

    /**
     * 返回树形评论数据
     *
     * @return
     */
    @Override
    public List<Communicate> listTree() {
        List<Communicate> list = this.list();
        // 映射id->index
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < list.size(); index++) {
            map.put(list.get(index).getId(), index);
        }
        // 遍历寻找父节点
        for (Communicate communicate : list) {
            Integer pid = communicate.getPid();
            // 有父节点
            if (pid != null) {
                // 获取父节点id
                Integer indexFather = map.get(pid);
                Communicate father = list.get(indexFather);
                if (father.getChildren() == null) {
                    father.setChildren(new ArrayList<>());
                }
                // 在父节点上添加当前节点
                father.getChildren().add(communicate);
            }
        }
        // 过滤出一级节点
        List<Communicate> ans = list.stream().filter(child -> child.getPid() == null).collect(Collectors.toList());
        return ans;
    }
}

package com.fgbg.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class LabelVo {
    private String label;
    private String value;
    private Integer id;
    private Integer pid;
    private List<LabelVo> children;
}

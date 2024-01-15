/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.fgbg.common.utils;

import com.fgbg.common.validator.group.AliyunGroup;
import com.fgbg.common.validator.group.QcloudGroup;
import com.fgbg.common.validator.group.QiniuGroup;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Constant {
    /**
     * 分隔符
     */
    public static final String ACTIVITY_DELIMITER = ",";

    public static final String MATERIAL = "material";
    public static final String USE_COL = "use_col";
    public static final String FESTIVAL = "festival";
    public static final String TOPIC = "topic";
    public static final String BRAND = "brand";

    /**
     * 订单状态
     */
    public enum OrderStatus {
        /**
         * 待接单
         */
        WAITING_ORDER("待接单"),
        /**
         * 进行中
         */
        IN_PROGRESS("进行中"),
        /**
         * 待验收
         */
        WAITING_FOR_ACCEPT("待验收"),
        /**
         * 已完成
         */
        COMPLETED("已完成");

        private String value;
        OrderStatus(String value) {
            this.value = value;
        }
        /**
         * 判断传递的String是否符合订单状态
         */
        public static boolean isValid(String value) {
            if (WAITING_FOR_ACCEPT.value.equals(value)) return true;
            if (WAITING_ORDER.value.equals(value)) return true;
            if (IN_PROGRESS.value.equals(value)) return true;
            return COMPLETED.value.equals(value);
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 文件类型
     */
    public enum FileType {
        /**
         * 图片
         */
        PICT("图片"),
        /**
         * 源文件
         */
        SOURCE_FILE("源文件");

        private String value;
        FileType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
    /**
     * 适用等级
     */
    public enum ApplicableGrade {
        /**
         * 白银会员
         */
        SILVER_MEMBER("白银会员"),
        /**
         * 黄金会员
         */
        GOLD_MEMBER("黄金会员"),
        /**
         * 钻石会员
         */
        DIAMOND_MEMBER("钻石会员");

        private final String value;

        ApplicableGrade(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 活动库选项类型
     */
    public enum ActivityOptionType {
        /**
         * 物料类型
         */
        MATERIAL("物料"),
        /**
         * 用途类型
         */
        USE("用途"),
        /**
         * 节日类型
         */
        FESTIVAL("节日"),
        /**
         * 专题类型
         */
        TOPIC("专题"),
        /**
         * 品牌类型
         */
        BRAND("品牌");

        private final String value;

        ActivityOptionType(String value)  {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 选项状态
     */
    public enum OptionState {
        /**
         * 选项状态为启用
         */
        ENABLE("启用"),
        /**
         * 选项状态为禁用
          */
        DISABLE("禁用");

        private String value;

        OptionState(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;
    /**
     * 主管
     */
    public static final int MANAGER = 0;
    /**
     * 普通用户
     */
    public static final int USER = 1;
    /**
     * repair 设施维修
     */
    public static final int REPAIR = 0;
    /**
     * complain 市政投诉
     */
    public static final int COMPLAIN = 1;
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     * 升序
     */
    public static final String ASC = "asc";

    /**
     * 菜单类型
     *
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 匹配特殊字符的正则表达式
     */
    private final static String regEx = "[\\^%`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    /**
     * 编译后的正则表达式
     */
    public final static Pattern specialCharacterPattern = Pattern.compile(regEx);

    /**
     * 加密盐值
     */
    public static final String SALT_VALUE = "tmxk";

    /**
     * 定时任务状态
     *
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1, QiniuGroup.class),
        /**
         * 阿里云
         */
        ALIYUN(2, AliyunGroup.class),
        /**
         * 腾讯云
         */
        QCLOUD(3, QcloudGroup.class);

        private int value;

        private Class<?> validatorGroupClass;

        CloudService(int value, Class<?> validatorGroupClass) {
            this.value = value;
            this.validatorGroupClass = validatorGroupClass;
        }

        public int getValue() {
            return value;
        }

        public Class<?> getValidatorGroupClass() {
            return this.validatorGroupClass;
        }

        public static CloudService getByValue(Integer value) {
            Optional<CloudService> first = Stream.of(CloudService.values()).filter(cs -> value.equals(cs.value)).findFirst();
            if (!first.isPresent()) {
                throw new IllegalArgumentException("非法的枚举值:" + value);
            }
            return first.get();
        }
    }

}

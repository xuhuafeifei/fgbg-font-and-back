package com.fgbg.common.exception;

public enum ErrorCode {
    MANAGER_NOT_FOUND(412001, "主管未设置或不存在"),
    MANAGER_NOT_EQUAL(412002, "主管id不一致, 维修设施信息和维修工单的负责主管不一致"),
    INSTALLREPAIR_STATE_NOT_CORRECT(412003, "维修信息状态(status)错误,用户新上传的维修信息状态应为0(等待维修)"),
    INSTALLREPAIR_ISREPAIR_NOT_CORRECT(412004, "维修信息是否需要维修字段设置错误,允许的状态有0(不需要维修),1(需要维修),2(审核中)"),
    STATUS_NOT_FOUND(412005, "维修信息状态设置错误,允许的状态有0(等待维修),1(正在维修),2(维修完成)"),
    SMS_SEND_FAIL(412006, "短信发送错误, 请联系管理员"),
    SYSTEM_ERROR(412007, "服务器异常"),
    USER_NOT_FOUND(412008, "用户不存在"),
    DATA_ERROR(412009, "数据异常,服务器未接收到数据或传输数据为空"),
    NOT_CONTAIN_SPECIAL_CHAR(412010, "填写字符串信息不应该包含特殊字符"),
    PASSWORD_TO_SHORT(412011, "密码过短,不该小于6位"),
    REGISTER_ERROR(412012, "注册失败"),
    PASSWORD_NOT_EQUAL(412013, "两次密码不一致"),
    USERNAME_DUPLICATE(412014, "用户名已存在"),
    NOT_LOGIN(412015, "未登录"),
    INVALID_SESSION_KEY(412016, "sessionKey异常,请重试"),
    DECRYPTION_ERROR(412017, "用户信息解密异常,请重试"),
    NOT_MANAGER(412018, "您不是主管,请通过普通用户方式登录或者联系管理员升级为主管"),
    SESSIONID_INVALID(412019, "sessionId有误,服务器中不存在"),
    NOT_NULL_MARK(412020, "mark为空"),
    CODE_ERROR(412021, "code错误"),
    APPEALS_LACK_PROPERTY(412022, "appeals缺少property作为区分三表的标志"),
    APPEALS_WRONG_PROPERTY(412023, "appeals的property错误"),
    LOGIN_TIMEOUT(412024, "登录超时"),
    NO_WORKER_REPAIR(412025, "本条记录没有工人负责,请先设置负责工人"),
    WORKER_HAD_FOUND(412026, "本条记录已有负责的工人,请勿重复设置"),
    WORKER_NOT_EXIST(412027, "工人不存在"),
    VISIT_TOO_FREQUENCY(412028, "您的访问过于频繁,被系统认定为机器人,请稍后访问"),
    THINGS_DATA_LENGTH_INVALID(412029, "微信订阅消息的thing.DATA类型数据长度不能大于20"),
    REAPEAT_ORDER(412030, "重复下单"),
    REAPEAT_RECE_ORDER(412031, "重复接单"),
    REAPEAT_UPLOAD(412032, "重复提交");

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 错误信息
     */
    private String msg;

    ErrorCode() {}

    ErrorCode(int errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }
}

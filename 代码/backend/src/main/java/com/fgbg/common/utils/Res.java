package com.fgbg.common.utils;


import com.fgbg.common.exception.ErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Res extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

//	public boolean success;
//
//	public Object data;
//
//	public String accessToken;

//    public static String data = "data";

    public Res() {
        put("success", true);
    }

    public static Res error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Res error(ErrorCode code) {
        return error(code.getErrorCode(), code.getMsg());
    }

    public static Res error(String msg) {
        return error(500, msg);
    }

    public static Res error(int code, String msg) {
        Res r = new Res();
        r.put("success", false);
        r.put("msg", msg);
        r.put("code", code);
        return r;
    }

    public static Res ok(String msg) {
        Res r = new Res();
        r.put("msg", msg);
        return r;
    }

    public static Res ok(Map<String, Object> map) {
        Res r = new Res();
        r.putAll(map);
        return r;
    }

    public static Res ok() {
        return new Res();
    }

    public Res put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

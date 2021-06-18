package com.transfer.rest;

import lombok.Data;

/**
 * @Description:
 * @Author: lsw
 * @Date: 2021-06-17 14:20
 * @Version v1.0
 **/
@Data
public class R {
    private Integer code ;
    private String message ;
    private Object data ;

    public R(){}

    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    private R(Object data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

    public static R ok(Object data){
        return new R(data);
    }
    public static R error(String message){
        return new R(400, message);
    }
}

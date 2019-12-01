package com.wwmust.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    //异常代码
    private int code = 500;
    //异常URI
    private String uri;
    //异常描述
    private String msg;
}

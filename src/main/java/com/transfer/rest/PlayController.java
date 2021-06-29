package com.transfer.rest;

import cn.hutool.core.lang.Dict;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description: 语音播报
 * @Author: lsw
 * @Date: 2021-06-28 09:56
 * @Version v1.0
 **/
@RestController
@Log4j2
public class PlayController {

    @PostMapping("play")
    public ResponseEntity<Object> play(@RequestBody Dict data) {
        int loop = data.getInt("loop");
        for (int i = 0; i < loop; i++) {
            PlayUtil.play(data.getStr("content"));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

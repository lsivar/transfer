package com.transfer.rest;

import cn.hutool.core.io.BufferUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.socket.nio.NioClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @Description: 基恩士扫码器
 * @Author: lsw
 * @Date: 2021-06-15 18:50
 * @Version v1.0
 **/
@RestController
@RequestMapping("keyence")
@Log4j2
public class KeyenceController {

    @Value("${keyence.ip}")
    private String ip;

    /**
     * 执行命令
     */
    @GetMapping(value = "send/{command}")
    public R test(@PathVariable("command") String command) {
        Socket client = null;
        try {
            client = new Socket(ip, 9004);
            client.setSoTimeout(50000);
            //获取Socket的输出流，用来发送数据到服务端
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从服务端发送过来的数据
            BufferedReader buf = new BufferedReader
                    (new InputStreamReader(client.getInputStream(), CharsetUtil.UTF_8));
            //发送数据到服务端
            out.println(command);
            String echo = buf.readLine();

            return R.ok(echo);
        } catch (Exception e) {
            return R.error("读码失败");
        } finally {
            try {
                assert client != null;
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

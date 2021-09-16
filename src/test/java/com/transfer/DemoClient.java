package com.transfer;

import cn.hutool.core.io.BufferUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.socket.nio.NioClient;
import com.transfer.rest.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @Description:
 * @Author: lsw
 * @Date: 2021-06-17 10:48
 * @Version v1.0
 **/
public class DemoClient {
    public static void main(String[] args) {
        Socket client = null;
        String ip = "172.30.10.171";
        String command = "LOFF";
//        String command = "LON";
        try {
            client = new Socket(ip, 9004);
            client.setSoTimeout(3000);

            //获取Socket的输出流，用来发送数据到服务端
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从服务端发送过来的数据
            BufferedReader buf = new BufferedReader
                    (new InputStreamReader(client.getInputStream(), CharsetUtil.UTF_8));
            //发送数据到服务端
            out.println(command);
            String echo = buf.readLine();
            System.out.println(echo);
        } catch (Exception e) {
            e.printStackTrace();
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

package com.transfer.rest;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:    斑马打印机
 * @Author: lsw
 * @Date: 2021-06-17 14:01
 * @Version v1.0
 **/
@RestController
@RequestMapping("zebra")
@Log4j2
public class ZebraController {

    @Value("${zebra.write}")
    public String writeUrl ;
    @Value("${zebra.info}")
    public String infoUrl ;
    @Value("${zebra.available}")
    public String availableUrl ;

    @GetMapping("available")
    public String available(){
        return HttpUtil.get(availableUrl);
    }

    @GetMapping("default")
    public String info(){
        return HttpUtil.get(infoUrl);
    }

    /** 执行打印 */
    @PostMapping("write")
    public void write(HttpServletRequest request, HttpServletResponse response){
        try {
            ServletInputStream inStream = request.getInputStream();
            String read = IoUtil.read(inStream, CharsetUtil.UTF_8);
            log.info("print data :{}", read);
            HttpRequest req = new HttpRequest(writeUrl);
            req.setMethod(Method.POST);
            req.header(Header.CONTENT_TYPE, ContentType.TEXT_PLAIN.getValue());
            req.body(read);
            HttpResponse httpResponse = req.execute();
            log.info("response status:{}", httpResponse.getStatus());
            response.setStatus(httpResponse.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(444);
        }
    }
}

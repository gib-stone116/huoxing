package cn.itcast.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    /***
     * 请求 /hello  输出hello springboot!
     * @return
     */
    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello springboot!";
    }
}
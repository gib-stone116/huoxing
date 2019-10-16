package cn.itcast.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/thy")
public class ThymeleafController {

    @Autowired
    private TemplateEngine templateEngine;

    /***
     * 访问/test/hello  跳转到demo1页面
     * @param model
     * @return
     */
    @RequestMapping("/test")
    public String hello(Model model) {
        System.out.println("创建静态页面~~");
        model.addAttribute("hello", "hello welcome");
        return "demo1";
    }

    /**
     * 生成静态页面
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/createHtml/{id}")
    public String createHtml(@PathVariable(name = "id") Long id) {
        this.createPageHtml(id);
        return "ok";
    }

    /***
     * 生成静态页
     * @param spuId
     */

    public void createPageHtml(Long spuId) {
        // 1.上下文
        Context context = new Context();
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("name", "shixian");
        context.setVariables(dataModel);
        // 2.准备文件
        File dir = new File("D:\\code\\idea\\pro1\\demo02_springboot\\src\\main\\resources\\templates\\items");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dest = new File(dir, spuId + ".html");
        // 3.生成页面
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            templateEngine.process("item", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
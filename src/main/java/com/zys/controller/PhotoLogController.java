package com.zys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhotoLogController {
    @RequestMapping("/addLog.action")
    public void addLog(){
        System.out.println("记录");

    }
}

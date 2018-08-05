package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/console")
public class ConsoleController {

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllConsoles() {
        return "console";
    }

}

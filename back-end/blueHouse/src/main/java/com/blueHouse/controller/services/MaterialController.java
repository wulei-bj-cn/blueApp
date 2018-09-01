package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getMaterialList(HttpServletRequest req) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Emma Watson");
        map.put("age", "28");

        return map;
    }

}

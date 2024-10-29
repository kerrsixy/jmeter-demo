package com.zjp.jmeterdemo.web;

import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: BasicController
 * Package: com.zjp.jemeterdemo.web
 * Description:
 *
 * @author zjp
 * @version 1.0
 */
@RestController
public class BasicController {
    private static final Logger logger = LoggerFactory.getLogger(BasicController.class);

    @RequestMapping("/hello/{id}")
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name,
                        @PathVariable("id") String pathVariable,
                        @RequestBody(required = false) Map<String, Object> requestBody) {
        if ("fail".equals(name)) {
            throw new RuntimeException("fail test");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("requestParam", name);
        result.put("pathVariable", pathVariable);
        if (requestBody != null) {
            result.put("requestBody", requestBody);
        }
        logger.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }

    @PostMapping("/file/{id}")
    public String file(@RequestParam(name = "name", defaultValue = "unknown user") String name,
                       @PathVariable("id") String pathVariable,
                       @RequestParam("file") MultipartFile file,
                       @RequestPart(value = "requestBody", required = false) String requestBody) {
        Map<String, Object> result = new HashMap<>();
        result.put("requestParam", name);
        result.put("pathVariable", pathVariable);
        if (file != null) {
            result.put("filename", file.getOriginalFilename());
            result.put("contentType", file.getContentType());
            result.put("size", file.getSize());
        }
        if (requestBody != null) {
            result.put("requestBody", requestBody);
        }
        logger.info("result:{}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }
}

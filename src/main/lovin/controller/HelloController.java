package lovin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bixin on 2017/10/18.
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public Map<String, Object> hello() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("msg", "hello springboot");
        return result;
    }
}
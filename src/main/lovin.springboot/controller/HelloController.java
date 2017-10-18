package lovin.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bixin on 2017/10/18.
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello() {
        return "hello springboot";
    }
}
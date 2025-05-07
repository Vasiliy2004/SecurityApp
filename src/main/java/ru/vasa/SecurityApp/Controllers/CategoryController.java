package ru.vasa.SecurityApp.Controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sddssd")
public class CategoryController {
    @GetMapping()
    public String index() {
        return "hello";
    }
    @GetMapping("/suppliers")
    public String suppliers() {
        return "suppliers";
    }

}

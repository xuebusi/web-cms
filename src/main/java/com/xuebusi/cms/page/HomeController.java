package com.xuebusi.cms.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping({"/", "/index", "/index.html"})
    public String home(Model model) {
        model.addAttribute("message", "WebCMS");
        return "index";
    }

    @GetMapping({"/about", "/about.html"})
    public String about(Model model) {
        model.addAttribute("message", "WebCMS");
        return "about";
    }
}

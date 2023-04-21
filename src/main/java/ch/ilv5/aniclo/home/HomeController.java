package ch.ilv5.aniclo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}

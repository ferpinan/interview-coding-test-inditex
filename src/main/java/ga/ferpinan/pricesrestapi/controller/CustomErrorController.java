package ga.ferpinan.pricesrestapi.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Hidden
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView error() {
        return new ModelAndView("error.html");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

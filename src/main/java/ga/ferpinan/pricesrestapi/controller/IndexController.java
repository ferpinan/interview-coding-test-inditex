package ga.ferpinan.pricesrestapi.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Hidden
public class IndexController {

	private static final String REDIRECT_SWAGGER_UI = "redirect:/swagger-ui.html";

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String index() {
		return REDIRECT_SWAGGER_UI;
	}
}

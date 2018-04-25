package cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("cms")
@Controller
public class CmsController {

	// 入口
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String addItemToCart(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
}

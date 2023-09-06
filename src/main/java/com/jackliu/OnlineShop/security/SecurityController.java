package com.jackliu.OnlineShop.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/myLogin")
    public String showMyLoginPage() {
        return "Mylogin";
    }

}

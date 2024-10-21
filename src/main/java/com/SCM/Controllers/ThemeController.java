package com.SCM.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.SCM.Helper.ThemeConstants;
import com.SCM.SuccessResponse.Theme;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class ThemeController {

    @GetMapping("/getTheme")
    public ResponseEntity<Theme> getTheme(HttpServletRequest request) {
        try {

            String themeValue = ThemeConstants.LIGHT;

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Optional<Cookie> themeCookie = Arrays.stream(cookies)
                        .filter(cookie -> ThemeConstants.THEME.equals(cookie.getName()))
                        .findFirst();

                if (themeCookie.isPresent()) {
                    themeValue = themeCookie.get().getValue();
                }
            }

            return ResponseEntity.ok(new Theme(themeValue));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/setTheme")
    public ResponseEntity<Theme> setTheme(@RequestBody Theme theme, HttpServletResponse response) {
        try {

            Cookie themeCookie = new Cookie(ThemeConstants.THEME, theme.getTheme());
            themeCookie.setHttpOnly(true);
            themeCookie.setPath("/");
            response.addCookie(themeCookie);

            return ResponseEntity.ok(theme);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

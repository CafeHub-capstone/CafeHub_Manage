package com.CafeHub.Manage.theme.controller;

import com.CafeHub.Manage.theme.request.ThemeCreateRequest;
import com.CafeHub.Manage.theme.response.ThemeListResponse;
import com.CafeHub.Manage.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;



    @GetMapping("/themes")
    public String themeManagePage(Model model){

        ThemeListResponse response = themeService.getAllThemes();
        model.addAttribute("response",response);

        return "themes";
    }

    @GetMapping("/themeCreateForm")
    public String themeCreatePage(){

        return "themeCreateForm";
    }

    @PostMapping("/createTheme")
    public String themeCreate(@ModelAttribute ThemeCreateRequest request){

        themeService.createNewTheme(request);

        return "redirect:/themes";
    }


}

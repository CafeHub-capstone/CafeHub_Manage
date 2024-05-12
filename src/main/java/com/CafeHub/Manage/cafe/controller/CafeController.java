package com.CafeHub.Manage.cafe.controller;


import com.CafeHub.Manage.cafe.request.AllCafeGetRequest;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;


    @GetMapping("/cafes")
    public String getAllCafe(@RequestParam(defaultValue = "1", name = "page") int page,
                             @RequestParam(defaultValue = "15", name = "size") int size,
                             Model model){

        AllCafeGetRequest request = new AllCafeGetRequest(page,size);
        AllCafeGetResponse response = cafeService.getAllCafeList(request);

        model.addAttribute("response", response);

        return "cafes";
    }

}

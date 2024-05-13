package com.CafeHub.Manage.cafe.controller;


import com.CafeHub.Manage.cafe.request.AllCafeGetRequest;
import com.CafeHub.Manage.cafe.request.CafeInfoRequest;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.response.CafeInfoResponse;
import com.CafeHub.Manage.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/cafes/{cafeId}")
    public String getCafeInfo(@PathVariable("cafeId") Long cafeId, Model model){

        CafeInfoRequest request = new CafeInfoRequest(cafeId);
        CafeInfoResponse response = cafeService.getCafeInfo(request);

        model.addAttribute("response", response);
        return "cafeInfo";
    }


    @GetMapping("/cafeCreateForm")
    public String cafeCreateForm(){

        return "cafeCreateForm";
    }


    @PostMapping("/createCafe/{cafeId}")
    public String createCafe(){

        return "redirect:/cafes/{cafeId}";
    }

    @GetMapping("/cafeUpdateForm")
    public String cafeUpdateForm(){

        return "cafeUpdateForm";
    }

    @PostMapping("/updateCafe/{cafeId}")
    public String updateCafe(){

        return "redirect:/cafes/{cafeId}";
    }

    @PostMapping("/deleteCafe/{cafeId}")
    public String deleteCafe(){

        return "redirect:/cafes";
    }


}

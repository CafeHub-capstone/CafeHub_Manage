package com.CafeHub.Manage.cafe.service;

import com.CafeHub.Manage.cafe.entity.Cafe;
import com.CafeHub.Manage.cafe.repository.CafeRepository;
import com.CafeHub.Manage.cafe.request.AllCafeGetRequest;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.response.CafeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService{

    private final CafeRepository cafeRepository;


    @Override
    public AllCafeGetResponse getAllCafeList(AllCafeGetRequest request) {

        AllCafeGetResponse response = new AllCafeGetResponse();

        Pageable pageable = PageRequest.of(request.getPage()-1,
                                            request.getSize(),
                                            Sort.by(Sort.Direction.ASC,"id")
                                            );

        Page<Cafe> cafeList = cafeRepository.findAll(pageable);
        List<CafeResponse> cafeResponses = new ArrayList<>();

        for (Cafe cafe : cafeList){
            CafeResponse cafeResponse =
                    new CafeResponse(cafe.getId(),cafe.getName(),cafe.getTheme().toString());

            cafeResponses.add(cafeResponse);
        }

        response.setCafeResponses(cafeResponses);
        response.setTotalPage(cafeList.getTotalPages());
        response.setCurrentPage(cafeList.getNumber());
        response.setSize(cafeList.getSize());
        response.setFirst(cafeList.isFirst());
        response.setLast(cafeList.isLast());

        return response;
    }
}

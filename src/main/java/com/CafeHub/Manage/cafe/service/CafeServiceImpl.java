package com.CafeHub.Manage.cafe.service;

import com.CafeHub.Manage.cafe.entity.Cafe;
import com.CafeHub.Manage.cafe.repository.CafeRepository;
import com.CafeHub.Manage.cafe.request.AllCafeGetRequest;
import com.CafeHub.Manage.cafe.request.CafeCreateRequest;
import com.CafeHub.Manage.cafe.request.CafeDeleteRequest;
import com.CafeHub.Manage.cafe.request.CafeInfoRequest;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.response.CafeInfoResponse;
import com.CafeHub.Manage.cafe.response.CafeResponse;
import com.CafeHub.Manage.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService{

    private final CafeRepository cafeRepository;

    private final ThemeRepository themeRepository;


    @Override
    public AllCafeGetResponse getAllCafeList(AllCafeGetRequest request) {

        Pageable pageable
                = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(Sort.Direction.DESC, "id"));

        Page<Cafe> cafes = cafeRepository.findAll(pageable);

        List<CafeResponse> cafeList = cafes.stream()
                .map(cafe -> new CafeResponse(cafe.getId(), cafe.getName(), cafe.getTheme().getName()))
                .collect(Collectors.toList());

        return new AllCafeGetResponse(
                cafeList,
                cafes.getTotalPages(),
                cafes.getNumber(),
                cafes.getSize(),
                cafes.isFirst(),
                cafes.isLast()
        );
    }



    // 여기서 map은 자료구조가 아니라 Optional의 메소드임 Optional안에 값이 있는 경우만 실행됨
    @Override
    public CafeInfoResponse getCafeInfo(CafeInfoRequest request) {
        return cafeRepository.findById(request.getCafeId())
                .map(cafe -> new CafeInfoResponse(
                        cafe.getId(),
                        cafe.getName(),
                        cafe.getAddress(),
                        cafe.getCafePhotoUrl(),
                        cafe.getPhone(),
                        cafe.getRating(),
                        cafe.getReviewCount(),
                        cafe.getOperationHours(),
                        cafe.getClosedDays(),
                        cafe.getTheme()
                ))
                .orElseThrow(() -> new RuntimeException("에러처리는 나중에 해당 카페id로 카페 정보를 찾을 수 없음: " + request.getCafeId()));
    }

    @Override
    @Transactional
    public Long createNewCafe(CafeCreateRequest request) {

        Cafe cafe = Cafe.builder()
                .name(request.getName())
                .address(request.getAddress())
                .cafePhotoUrl(request.getCafePhotoUrl())
                .phone(request.getPhone())
                .rating(BigDecimal.valueOf(0))
                .reviewCount(0)
                .operationHours(request.getOperationHours())
                .closedDays(request.getClosedDays())
                .theme(themeRepository.findById(request.getThemeId()).get())
                .build();

        cafeRepository.save(cafe);
        return cafe.getId();
    }


    @Override
    @Transactional
    public void deleteCafe(CafeDeleteRequest request) {

        cafeRepository.deleteById(request.getCafeId());
    }

}

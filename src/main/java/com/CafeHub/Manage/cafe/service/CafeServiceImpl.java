package com.CafeHub.Manage.cafe.service;

import com.CafeHub.Manage.cafe.entity.Cafe;
import com.CafeHub.Manage.cafe.repository.CafeRepository;
import com.CafeHub.Manage.cafe.request.*;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.response.CafeInfoResponse;
import com.CafeHub.Manage.cafe.response.CafeResponse;
import com.CafeHub.Manage.s3.S3Manager;
import com.CafeHub.Manage.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService{

    private final CafeRepository cafeRepository;

    private final ThemeRepository themeRepository;

    private final S3Manager s3Manager;



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

    // 일단 단순 업로드만 가능
    @Override
    @Transactional
    public Long createNewCafe(CafeCreateRequest request) throws IOException {

        String cafePhotoUrl = s3Manager.uploadFile(s3Manager.generateCafePhotoKeyName(), request.getCafePhoto());

        Cafe cafe = Cafe.builder()
                .name(request.getName())
                .address(request.getAddress())
                .cafePhotoUrl(cafePhotoUrl)
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
    public void updateCafe(CafeUpdateRequest request) {

        Cafe prev = cafeRepository.findById(request.getCafeId()).get();


        Cafe cafe = Cafe.builder()
                .id(request.getCafeId())
                .name(request.getName())
                .address(request.getAddress())
                .cafePhotoUrl(request.getCafePhotoUrl())
                .phone(request.getPhone())
                .rating(prev.getRating())
                .reviewCount(prev.getReviewCount())
                .operationHours(request.getOperationHours())
                .closedDays(request.getClosedDays())
                .theme(themeRepository.findById(request.getThemeId()).get())
                .build();

        cafeRepository.save(cafe);
    }


    @Override
    @Transactional
    public void deleteCafe(CafeDeleteRequest request) {

        cafeRepository.deleteById(request.getCafeId());
    }

}

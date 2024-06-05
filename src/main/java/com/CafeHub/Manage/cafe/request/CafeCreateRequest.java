package com.CafeHub.Manage.cafe.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CafeCreateRequest {

    private String name;

    private String address;

    private MultipartFile cafePhoto;

    private String phone;

    private String operationHours;

    private String closedDays;

    // 선택한 옵션을 줄거임
    private Long themeId;
}

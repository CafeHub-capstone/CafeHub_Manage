package com.CafeHub.Manage.cafe.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CafeCreateRequest {

    private String name;

    private String address;

    private String cafePhotoUrl;

    private String phone;

    private String operationHours;

    private String closedDays;

    // 선택 옵션을 줄거임
    private Long themeId;
}

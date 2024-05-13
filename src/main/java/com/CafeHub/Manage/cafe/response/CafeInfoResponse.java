package com.CafeHub.Manage.cafe.response;


import com.CafeHub.Manage.theme.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CafeInfoResponse {

    private Long cafeId;

    private String name;

    private String address;

    private String cafePhotoUrl;

    private String phone;

    private BigDecimal rating;

    private Integer reviewCount;

    private String operationHours;

    private String closedDays;

    private Theme theme;

}

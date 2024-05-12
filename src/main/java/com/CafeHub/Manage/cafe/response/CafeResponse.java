package com.CafeHub.Manage.cafe.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CafeResponse {

    private Long cafeId;

    private String cafeName;

    private String cafeTheme;
}

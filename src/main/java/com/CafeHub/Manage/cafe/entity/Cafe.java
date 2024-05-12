package com.CafeHub.Manage.cafe.entity;

import com.CafeHub.Manage.common.BaseEntity;
import com.CafeHub.Manage.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cafe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Long id;

    private String name;

    private String address;

    @Lob
    private String cafePhotoUrl;

    private String phone;

    // 초기 값은 0, 이후 컨트롤 x, 자동 반영
    private BigDecimal rating;

    // 초기 값은 0, 이후 컨트롤 x, 자동 반영
    private Integer reviewCount;

    private String operationHours;

    private String closedDays;


    @OneToMany(mappedBy = "cafe")
    private List<Menu> menus = new ArrayList<>();
}

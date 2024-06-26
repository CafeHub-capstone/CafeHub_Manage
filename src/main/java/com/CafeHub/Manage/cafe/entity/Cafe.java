package com.CafeHub.Manage.cafe.entity;

import com.CafeHub.Manage.common.BaseEntity;
import com.CafeHub.Manage.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.*;

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

    private Theme theme;

    private String address;

    private String phone;

    private String operationHours;

    private String closeDays;

    private Double rating;

    private Integer reviewCnt;

    private String photoUrl;

    private String photoKey;


    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();
}

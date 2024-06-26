package com.CafeHub.Manage.menu.entity;

import com.CafeHub.Manage.cafe.entity.Cafe;
import com.CafeHub.Manage.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private Category category;

    private String name;

    private Integer price;

    private Boolean isBest;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

}
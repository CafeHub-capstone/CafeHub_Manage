package com.CafeHub.Manage.menu.entity;

import com.CafeHub.Manage.cafe.entity.Cafe;
import com.CafeHub.Manage.common.Timestamped;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Menu extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;

    private Integer price;

    private Boolean best;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;
}
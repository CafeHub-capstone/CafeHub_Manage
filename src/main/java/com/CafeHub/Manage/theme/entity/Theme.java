package com.CafeHub.Manage.theme.entity;

import com.CafeHub.Manage.cafe.entity.Cafe;
import com.CafeHub.Manage.common.Timestamped;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Theme extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    private Long id;

    private String name;


    // 관리 서버에서는 One TO Many를 모두 활성화 할 예정
    @OneToMany(mappedBy = "theme")
    private List<Cafe> cafes = new ArrayList<>();

}
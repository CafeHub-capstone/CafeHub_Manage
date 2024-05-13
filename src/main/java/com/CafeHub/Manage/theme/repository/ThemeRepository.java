package com.CafeHub.Manage.theme.repository;

import com.CafeHub.Manage.theme.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}

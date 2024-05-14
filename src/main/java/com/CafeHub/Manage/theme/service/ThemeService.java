package com.CafeHub.Manage.theme.service;

import com.CafeHub.Manage.theme.request.ThemeCreateRequest;
import com.CafeHub.Manage.theme.response.ThemeListResponse;

public interface ThemeService {
    ThemeListResponse getAllThemes();

    void createNewTheme(ThemeCreateRequest request);
}

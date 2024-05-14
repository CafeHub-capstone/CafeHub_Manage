package com.CafeHub.Manage.theme.service;

import com.CafeHub.Manage.theme.entity.Theme;
import com.CafeHub.Manage.theme.repository.ThemeRepository;
import com.CafeHub.Manage.theme.request.ThemeCreateRequest;
import com.CafeHub.Manage.theme.response.ThemeListResponse;
import com.CafeHub.Manage.theme.response.ThemeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Override
    public ThemeListResponse getAllThemes() {

        List<Theme> themes = themeRepository.findAll(Sort.by("id"));

        List<ThemeResponse> themeResponseList = themes.stream()
                .map(theme -> new ThemeResponse(theme.getId(), theme.getName(), (long) theme.getCafes().size()))
                .collect(Collectors.toList());

        return new ThemeListResponse(themeResponseList);
    }

    @Override
    @Transactional
    public void createNewTheme(ThemeCreateRequest request) {

        Theme theme = Theme.builder()
                .name(request.getName())
                .build();

        themeRepository.save(theme);
    }


}


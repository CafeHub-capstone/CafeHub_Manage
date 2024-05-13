package com.CafeHub.Manage.cafe.service;

import com.CafeHub.Manage.cafe.request.*;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.response.CafeInfoResponse;

public interface CafeService {
    AllCafeGetResponse getAllCafeList(AllCafeGetRequest allCafeGetRequest);

    CafeInfoResponse getCafeInfo(CafeInfoRequest request);

    Long createNewCafe(CafeCreateRequest request);

    void deleteCafe(CafeDeleteRequest request);

    void updateCafe(CafeUpdateRequest request);
}

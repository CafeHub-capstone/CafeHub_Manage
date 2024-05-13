package com.CafeHub.Manage.cafe.service;

import com.CafeHub.Manage.cafe.request.AllCafeGetRequest;
import com.CafeHub.Manage.cafe.request.CafeCreateRequest;
import com.CafeHub.Manage.cafe.request.CafeDeleteRequest;
import com.CafeHub.Manage.cafe.request.CafeInfoRequest;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.response.CafeInfoResponse;

public interface CafeService {
    AllCafeGetResponse getAllCafeList(AllCafeGetRequest allCafeGetRequest);

    CafeInfoResponse getCafeInfo(CafeInfoRequest request);

    Long createNewCafe(CafeCreateRequest request);

    void deleteCafe(CafeDeleteRequest request);
}

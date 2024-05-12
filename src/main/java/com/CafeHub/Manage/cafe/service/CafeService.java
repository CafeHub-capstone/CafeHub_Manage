package com.CafeHub.Manage.cafe.service;

import com.CafeHub.Manage.cafe.request.AllCafeGetRequest;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;

public interface CafeService {
    AllCafeGetResponse getAllCafeList(AllCafeGetRequest allCafeGetRequest);
}

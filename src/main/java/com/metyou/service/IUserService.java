package com.metyou.service;

import com.metyou.common.ServerResponse;
import com.metyou.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);
}

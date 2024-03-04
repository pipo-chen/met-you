package com.metyou.service;

import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);

    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);
    //backend
    ServerResponse checkAdminRole(User user);

    ServerResponse<User> getUserInfoById(Integer userId);

    ServerResponse<PageInfo> getMemberList(int pageNum, int pageSize, String wechat);

    ServerResponse<String> resetUserPassword(String passwordNew, Integer userId);
}

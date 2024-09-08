package com.sms.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sms.sms.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listAll();
}

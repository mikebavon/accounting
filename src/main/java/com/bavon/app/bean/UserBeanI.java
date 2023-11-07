package com.bavon.app.bean;

import com.bavon.app.model.entity.User;

public interface UserBeanI {

    boolean register(User user);

    boolean unregister(User user);
}

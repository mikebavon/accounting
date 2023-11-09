package com.bavon.app.bean;

import com.bavon.app.model.User;

public interface UserBeanI {

    boolean register(User user);

    boolean unregister(User user);
}

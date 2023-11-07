package com.bavon.app.bean;

import com.bavon.app.model.entity.User;

public interface AuthBeanI {

    User authenticate(User loginUser);
}

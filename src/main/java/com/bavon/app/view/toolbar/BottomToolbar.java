package com.bavon.app.view.toolbar;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class BottomToolbar  implements Menu, Serializable {

    @Override
    public String menu(int activeLinkIndex) {
        return StringUtils.EMPTY;
    }
}

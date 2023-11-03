package com.bavon.app.view.toolbar;

import com.bavon.app.model.view.MenuLink;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

public class BottomToolbar  implements Menu, Serializable {

    @Override
    public String menu(int activeLinkIndex) {
        return StringUtils.EMPTY;
    }
}

package com.bavon.app.view.toolbar;

import com.bavon.app.model.view.MenuLink;
import com.bavon.app.model.view.MenuLinkStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TopToolbar implements Menu,Serializable {

    private final List<MenuLink> links = new ArrayList<>();

    {
        links.add(new MenuLink("./home", "Home", MenuLinkStatus.ACTIVE));
        links.add(new MenuLink("./journals", "Journals", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./invoices", "Invoices", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./bills", "Bills", MenuLinkStatus.NOT_ACTIVE));
    }

    @Override
    public String menu(int activeLinkIndex) {

        this.activateLink(activeLinkIndex);

        String menuBar = "<div class=\"topnav\">";

        for (MenuLink link : links)
            menuBar += "<a " + (link.getStatus() == MenuLinkStatus.ACTIVE? "class=\"active\"" : "")
                + " href=\"" + link.getUrl() + "\">" + link.getLabel() + "</a>";

        menuBar += "</div>";

        return menuBar;
    }

    private void activateLink(int linkIndex){
        for (int index = 0; index < links.size(); index++){
            if (index == linkIndex)
                links.get(index).setStatus(MenuLinkStatus.ACTIVE);
            else
                links.get(index).setStatus(MenuLinkStatus.NOT_ACTIVE);
        }

    }
}

package com.bavon.app.view.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HtmlMenuToolbar implements HtmlMenu,Serializable {

    private String menu;

    private int activeLink;

    private final List<MenuLink> links = new ArrayList<>();

    {
        links.add(new MenuLink("./home", "Home", MenuLinkStatus.ACTIVE));
        links.add(new MenuLink("./accounts", "Account", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./journals", "Journals", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./customers", "Customers", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./invoices", "Invoices", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./vendors", "Vendors", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./bills", "Bills", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./payments", "Payments", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./reports", "Reports", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./items", "Items", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./atm", "Atm", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./logout", "Logout", MenuLinkStatus.NOT_ACTIVE));
    }

    public String getMenu() {

        this.activateLink(getActiveLink());

        String menuBar = "<div class=\"topnav\">";

        for (MenuLink link : links)
            menuBar += "<a " + (link.getStatus() == MenuLinkStatus.ACTIVE? "class=\"active\"" : "")
                    + " href=\"" + link.getUrl() + "\">" + link.getLabel() + "</a>";

        menuBar += "</div>";

        return menuBar;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getActiveLink() {
        return activeLink;
    }

    public void setActiveLink(int activeLink) {
        this.activeLink = activeLink;
    }

    private void activateLink(int linkIndex){
        for (int index = 0; index < links.size(); index++){
            if (index == linkIndex)
                links.get(index).setStatus(MenuLinkStatus.ACTIVE);
            else
                links.get(index).setStatus(MenuLinkStatus.NOT_ACTIVE);
        }

    }

    @Override
    public String menu(int activeLinkIndex) {
        return null;
    }
}

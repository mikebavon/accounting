package com.bavon.app.bean;

import com.bavon.app.model.Item;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class ItemBean extends GenericBean<Item> implements ItemBeanI {

}

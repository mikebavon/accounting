package com.bavon.app.bean;

import com.bavon.app.model.Vendor;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class VendorBean extends GenericBean<Vendor> implements VendorBeanI {
}

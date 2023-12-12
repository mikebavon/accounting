package com.bavon.app.rest.api;

import com.bavon.app.bean.VendorBeanI;
import com.bavon.app.model.Vendor;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vendors")
public class VendorRestApi extends BaseRestApi{

    @EJB
    private VendorBeanI vendorBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Vendor vendor){
        vendorBean.addOrUpdate(vendor);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return respond(vendorBean.list(new Vendor()));
    }
}

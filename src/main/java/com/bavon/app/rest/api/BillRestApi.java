package com.bavon.app.rest.api;

import com.bavon.app.bean.BillBeanI;
import com.bavon.app.model.Bill;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bills")
public class BillRestApi extends BaseRestApi{

    @EJB
    private BillBeanI billBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Bill bill){
        billBean.addOrUpdate(bill);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return respond(billBean.list(new Bill()));
    }
}

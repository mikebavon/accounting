package com.bavon.app.rest.api;

import com.bavon.app.bean.CustomerBeanI;
import com.bavon.app.model.Customer;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerRestApi  extends BaseRestApi{

    @EJB
    private CustomerBeanI customerBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Customer customer){
        customerBean.addOrUpdate(customer);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return respond(customerBean.list(new Customer()));
    }
}

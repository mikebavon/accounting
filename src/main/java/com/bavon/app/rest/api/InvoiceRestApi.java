package com.bavon.app.rest.api;

import com.bavon.app.bean.InvoiceBeanI;
import com.bavon.app.model.Invoice;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/invoices")
public class InvoiceRestApi extends BaseRestApi{

    @EJB
    private InvoiceBeanI invoiceBean;

    @RolesAllowed("LOGGED_IN")
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Invoice invoice){
        invoiceBean.addOrUpdate(invoice);
        return respond();
    }

    @RolesAllowed("LOGGED_IN")
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("id") Long id,
         @QueryParam("customerId") Long customerId,
         @HeaderParam("company") String company,
         @HeaderParam("Host") String host,
         @HeaderParam("Postman-Token") String postmanToken){

        System.out.println("========= company name from header param is " + company);
        System.out.println("========= Host name from header param is " + host);
        System.out.println("========= Postman-Token name from header param is " + postmanToken);

        Invoice filter = new Invoice();
        filter.setId(id);
        filter.setCustomerId(customerId);
        return respond(invoiceBean.list(filter));
    }

    @Path("/list/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listByCustomer(@PathParam("id") Long id){
        System.out.println("id " + id);
        Invoice filter =  new Invoice();
        filter.setId(id);
        return respond(invoiceBean.list(filter));
    }

    @Path("/list/customer/{customerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@BeanParam InvoiceFilter filter){

        Invoice invoiceFilter = new Invoice();

        invoiceFilter.setId(filter.getId());
        invoiceFilter.setCustomerId(filter.getCustomerId());

        System.out.println(filter.getCompany());

        return respond(invoiceBean.list(invoiceFilter));
    }
}

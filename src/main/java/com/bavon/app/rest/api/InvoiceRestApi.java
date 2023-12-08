package com.bavon.app.rest.api;

import com.bavon.app.bean.InvoiceBeanI;
import com.bavon.app.model.Invoice;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/invoices")
public class InvoiceRestApi  extends BaseRestApi{

    @EJB
    private InvoiceBeanI invoiceBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Invoice invoice){
        invoiceBean.addOrUpdate(invoice);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return respond(invoiceBean.list(new Invoice()));
    }
}

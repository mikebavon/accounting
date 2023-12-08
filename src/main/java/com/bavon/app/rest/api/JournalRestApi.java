package com.bavon.app.rest.api;

import com.bavon.app.bean.JournalBeanI;
import com.bavon.app.model.Journal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/journals")
public class JournalRestApi extends BaseRestApi{

    @EJB
    private JournalBeanI journalBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Journal journal){
        journalBean.addOrUpdate(journal);
        return respond();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return respond(journalBean.list(new Journal()));
    }
}

package org.app.scrum.rest;

import javax.ejb.*;
import javax.ws.rs.*;

@Path("name")
@Stateless
public class NameService {
    @EJB
    private NameBean nameBean;

    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<h2>Hello REST "+nameBean.getName()+"</h2>";
    }

    @PUT
    @Consumes("text/plain")
    public void put(String content) {
        nameBean.setName(content);
    }

}

// http://localhost:8080/RestApplication/resources/name

// http://localhost:8080/ScrumREST/resources/name

// 
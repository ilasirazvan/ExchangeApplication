package com.exchange.rest;

import com.exchange.entities.Channel;
import com.exchange.service.interfaces.IQuerryDataSet;
import com.exchange.service.interfaces.IUrlXmlReader;
import com.exchange.utils.BuildResponse;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@DeclareRoles({"admin", "user", "guest"})
public class ExchangeRest
{

    @Inject
    IUrlXmlReader urlXmlReader;

    @Inject
    IQuerryDataSet querryDataSet;

    @GET
    @Path("/healthCheck")
    @Produces({MediaType.TEXT_PLAIN})
    @RolesAllowed({"admin","user"})
    public String getHealthCheck()
    {
        return "System is running";
    }


    @GET
    @Path("/{currency2}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin","user"})
    public Response getMsg(@DefaultValue("RON") @QueryParam("transform") String currency1, @PathParam("currency2") String currency2)
    {
        Channel chanel = urlXmlReader.readUmlFile();
        if (chanel!=null){
            String jsonObject = querryDataSet.getJsonForCurrencies(currency1, currency2, chanel);
            if(jsonObject!=null){
                return BuildResponse.buildResponseWithObject(Response.Status.OK, jsonObject);
            }
            return BuildResponse.buildResponseWithObject(Response.Status.INTERNAL_SERVER_ERROR, BuildResponse.ERR_500_MSG);
        }
        return BuildResponse.buildResponseWithObject(Response.Status.INTERNAL_SERVER_ERROR, BuildResponse.ERR_500_MSG);
    }
}

package com.exchange.utils;

import javax.ws.rs.core.Response;

public class BuildResponse {
    public static final String ERR_400_MSG = "{\"Status\":\"ERR 400: Bad Request\"}";
    public static final String ERR_500_MSG = "{\"Status\":\"ERR 500: Internal Error\"}";

    private BuildResponse(){}

    public static Response buildResponseWithError(Response.Status status, String result){
        Response response = Response.status(status).entity(result).build();
        return  response;
    }


    public static Response buildResponseWithObject(Response.Status status, String result){
        Response response = Response.status(status).entity(result).build();
        return  response;
    }
}

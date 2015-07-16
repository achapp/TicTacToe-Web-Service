package com.achappell.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.ws.rs.core.Response;

@JsonPropertyOrder({"status", "message"})
public class ExceptionResponse {
    private Response.Status httpStatus;
    private String message;

    @JsonProperty
    public Response.Status getStatus()
    {
        return httpStatus;
    }

    @JsonProperty
    public String getMessage()
    {
        return message;
    }

    public ExceptionResponse(Response.Status httpStatus, String message)
    {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}

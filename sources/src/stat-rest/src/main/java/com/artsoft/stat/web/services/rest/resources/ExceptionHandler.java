/*
 * ArtSoft 2014.
 */

package com.artsoft.stat.web.services.rest.resources;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artsoft.stat.web.services.rest.api.StatResourceUnexpectedException;
import com.artsoft.stat.web.services.rest.common.Helper;


/**
 * This is a handler class for general exceptions occurred in the system.
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>
{
    private static final Log logger = LogFactory.getLog(ExceptionHandler.class);


    @Override
    public Response toResponse(final Exception e)
    {
        // set internal server error for response
        logger.error("Internal server error occurred.", e);

        StatResourceUnexpectedException e2 = new StatResourceUnexpectedException("Internal server error occurred.", e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Helper.exceptionCauseChain(e2)).build();
    }
}

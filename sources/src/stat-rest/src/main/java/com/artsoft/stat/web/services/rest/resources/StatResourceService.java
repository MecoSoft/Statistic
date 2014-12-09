/*
 * ArtSoft 2014.
 */

package com.artsoft.stat.web.services.rest.resources;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artsoft.stat.business.logic.api.StatisticServiceRemote;
import com.artsoft.stat.web.services.rest.api.StatResourceRest;
import com.artsoft.stat.web.services.rest.domain.Statistic;


/**
 * This class is implementation of statistic resource RESTful service.
 */
public class StatResourceService implements StatResourceRest
{
    private static final Log logger = LogFactory.getLog(StatResourceService.class);
    private StatisticRequestHandler handler;

    @EJB
    private StatisticServiceRemote statisticLogicBean;


    @Override
    public Response createNewStatistics(final Statistic statistic)
    {
        if (logger.isInfoEnabled()) {
            logger.info("-------------> Message received for creating new statistic with data: "
                + IOUtils.LINE_SEPARATOR + statistic);
        }

        if (statistic == null) {
            // received statistic data can not be null
            logger.info("Received message is null.");
            
            throw new IllegalArgumentException("Received message is null.");
        }

        logger.trace("Initiate sending statistic object to ejb.");

        // invoke statistic logic
        handler.invoke(statistic);

        logger.info("<------------- Message handled successfully.");
        return null;
    }


    @PostConstruct
    private void initialize()
    {
        logger.info("Start creating staticstic request handler.");

        // create handler with statistic logic reference
        handler = new StatisticRequestHandler(statisticLogicBean);
    }
}

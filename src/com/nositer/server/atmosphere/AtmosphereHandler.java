package com.nositer.server.atmosphere;

import java.io.IOException;
import java.util.concurrent.Executors;
import javax.servlet.ServletException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;
import org.atmosphere.gwt.server.GwtAtmosphereResource;
import org.atmosphere.gwt.server.AtmosphereGwtHandler;
/**
 *
 * @author p.havelaar
 */
public class AtmosphereHandler extends AtmosphereGwtHandler {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		Logger.getLogger("").setLevel(Level.INFO);
		Logger.getLogger("gwtcomettest").setLevel(Level.ALL);
		Logger.getLogger("").getHandlers()[0].setLevel(Level.ALL);
		logger.trace("Updated logging levels");
	}

	@Override
	public int doComet(GwtAtmosphereResource resource) throws ServletException, IOException {
		resource.getBroadcaster().setID("GWT_COMET");
		HttpSession session = resource.getAtmosphereResource().getRequest().getSession(false);
		if (session != null) {
			logger.debug("Got session with id: " + session.getId());
			logger.debug("Time attribute: " + session.getAttribute("time"));
		} else {
			logger.warn("No session");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Url: " + resource.getAtmosphereResource().getRequest().getRequestURL()
					+ "?" + resource.getAtmosphereResource().getRequest().getQueryString());
		}
		return NO_TIMEOUT;
	}

	@Override
	protected GwtAtmosphereResource lookupResource(int connectionId) {
		GwtAtmosphereResource retval = null;
		try {
		retval = super.lookupResource(connectionId);
		} catch (Exception e){}
		return retval;	
	}

	@Override
	public void cometTerminated(GwtAtmosphereResource cometResponse, boolean serverInitiated) {
		super.cometTerminated(cometResponse, serverInitiated);
		logger.debug("Comet disconnected");
	}

}

package com.nositer.server.atmosphere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.gwt.server.AtmosphereGwtHandler;
import org.atmosphere.gwt.server.GwtAtmosphereResource;
import org.atmosphere.gwt.server.impl.GwtAtmosphereResourceImpl;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.nositer.client.dto.generated.User;
import com.nositer.client.groupchat.ChatEvent;
import com.nositer.client.groupchat.ChatEventType;
import com.nositer.util.HTMLPurifier;
import com.nositer.webapp.Application;

/**
 * 
 * @author p.havelaar
 */
public class AtmosphereHandler extends AtmosphereGwtHandler {
	public static HashMap<String, ArrayList<GwtAtmosphereResource>> loginsByGrouptagname;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		Application.log.info("inside init");
		Logger.getLogger("").setLevel(Level.INFO);
		Logger.getLogger("gwtcomettest").setLevel(Level.ALL);
		Logger.getLogger("").getHandlers()[0].setLevel(Level.ALL);
		logger.trace("Updated logging levels");
		loginsByGrouptagname = new HashMap<String, ArrayList<GwtAtmosphereResource>>();
	}

	@Override
	public int doComet(GwtAtmosphereResource resource) throws ServletException,
	IOException {
		return super.doComet(resource);
	}

	@Override
	protected GwtAtmosphereResource lookupResource(int connectionId) {
		GwtAtmosphereResource retval = null;
		try {
			retval = super.lookupResource(connectionId);
		} catch (Exception e) {
		}
		return retval;
	}


	@Override
	public void onRequest(
			AtmosphereResource<HttpServletRequest, HttpServletResponse> resource)
	throws IOException {
		HttpServletRequest req = resource.getRequest();
		HttpServletResponse resp = resource.getResponse();

		super.onRequest(resource);
	}

	@Override
	public void cometTerminated(GwtAtmosphereResource cometResponse,
			boolean serverInitiated) {
		super.cometTerminated(cometResponse, serverInitiated);
		logger.debug("Comet disconnected");
	}

	@Override
	public void broadcast(List<Serializable> messages,
			GwtAtmosphereResource resource) {
		super.broadcast(messages, resource);
	}

	@Override
	public void doPost(List<Serializable> messages, GwtAtmosphereResource r) {
		super.doPost(messages, r);
	}

	@Override
	protected void doServerMessage(BufferedReader data, int connectionID) {
		List<Serializable> postMessages = new ArrayList<Serializable>();
		GwtAtmosphereResource resource = lookupResource(connectionID);

		if (resource == null) {
			return;
		}
		try {
			while (true) {
				String event = data.readLine();
				if (event == null) {
					break;
				}
				String messageData = "";
				String lineRead = data.readLine();
				while (lineRead != null) {
					messageData += lineRead + "\r\n";
					lineRead = data.readLine();
				}
				if (messageData.equals("")) {
					break;
				}
				//data.readLine();

				if (event.equals("o")) {
					if (messageData.charAt(0) == 'p') {
						Serializable message = deserialize(messageData
								.substring(1));
						if (message != null) {
							postMessages.add(message);
						}
					} else if (messageData.charAt(0) == 'b') {
						Serializable message = deserialize(messageData
								.substring(1));
						ChatEvent chatEventMsg = (ChatEvent) message;

						reBroadcastMsg(chatEventMsg, resource);
						//GwtAtmosphereResource resource = lookupResource(connectionID);
						//broadcast(message, resource);
						// broadcast(message);
					}

				} else if (event.equals("s")) {

					if (messageData.charAt(0) == 'p') {
						String message = messageData.substring(1);
						postMessages.add(message);
					} else if (messageData.charAt(0) == 'b') {
						Serializable message = messageData.substring(1);

						ChatEvent chatEvent = (ChatEvent) message;
						reBroadcastMsg(chatEvent, resource);
						//broadcast(eventMsg, resource);
						// broadcast(message);
					}

				} else if (event.equals("c")) {

					if (messageData.equals("d")) {
						// disconnect(connectionID);
					}
				}
			}
		} catch (IOException ex) {
			logger.error("[" + connectionID + "] Failed to read", ex);
		}

	}


	private void reBroadcastMsg(ChatEvent chatEvent, GwtAtmosphereResource resource) {
		if (loginsByGrouptagname.get(chatEvent.getGrouptagname()) == null) {
			loginsByGrouptagname.put(chatEvent.getGrouptagname(), new ArrayList<GwtAtmosphereResource>());
		}
		ArrayList<GwtAtmosphereResource> loginsOfAGrouptagname = loginsByGrouptagname.get(chatEvent.getGrouptagname());

		if (chatEvent.getChatEventType().equals(ChatEventType.NORMAL) ||
				chatEvent.getChatEventType().equals(ChatEventType.WHISPER) 
		) {
			chatEvent.setData(HTMLPurifier.getCleanHTML(chatEvent.getData()));
		} else {


			resource.setAttribute(User.TABLENAME, chatEvent.getUser());

			if (chatEvent.getChatEventType().equals(ChatEventType.CONNECT)) {
				loginsOfAGrouptagname.add(resource);
			} else if (chatEvent.getChatEventType().equals(ChatEventType.DISCONNECT)) {
				loginsOfAGrouptagname.remove(resource);
			}
			populateLogins(loginsOfAGrouptagname, chatEvent);
		}

		for (GwtAtmosphereResource gwtAtmosphereResource : loginsOfAGrouptagname) {
			User user = gwtAtmosphereResource.getAttribute(User.TABLENAME);
			List<Serializable> messages = new ArrayList<Serializable>();
			messages.add(chatEvent);
			if (chatEvent.getChatEventType().equals(ChatEventType.WHISPER) && !userIsInWhisperSet(user, chatEvent.getUsers()))
			{
				continue;
			} else {
				post(messages, gwtAtmosphereResource);
			}
		}
	}

	private boolean userIsInWhisperSet(User user, TreeSet<User> users) {
		boolean retval = false;
		for (User userInSet : users) {
			if (user.getId().equals(userInSet.getId())) {
				retval = true;
				break;
			}
		}
		return retval;
	}

	private void populateLogins(
			ArrayList<GwtAtmosphereResource> loginsOfAGrouptagname,
			ChatEvent chatEvent) {
		TreeSet<User> users = new TreeSet<User>();
		for (GwtAtmosphereResource gwtAtmosphereResource : loginsOfAGrouptagname) {
			users.add((User)gwtAtmosphereResource.getAttribute(User.TABLENAME));
		}
		chatEvent.setUsers(users);
	}

	@Override
	protected Serializable deserialize(String arg0) {
		return super.deserialize(arg0);
	}
}

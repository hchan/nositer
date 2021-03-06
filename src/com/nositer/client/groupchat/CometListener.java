package com.nositer.client.groupchat;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;

import org.atmosphere.gwt.client.AtmosphereListener;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.nositer.client.Nositer;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.HtmlContainerPlus;

public class CometListener implements AtmosphereListener {
	private GroupChatContainer groupChatContainer;

	public CometListener(GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
	}

	public GroupChatContainer getGroupChatContainer() {
		return groupChatContainer;
	}

	public void setGroupChatContainer(GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
	}

	@Override
	public void onConnected(int heartbeat, int connectionID) {
		GWT.log("comet.connected [" +heartbeat+", "+connectionID+"]");
		ChatEvent chatEvent = new ChatEvent();
		chatEvent.setUser(Nositer.getInstance().getUser());
		chatEvent.setGrouptagname(groupChatContainer.getGroupPlusView().getTagname());
		chatEvent.setChatEventType(ChatEventType.CONNECT);
		groupChatContainer.getClient().broadcast(chatEvent);
	}

	@Override
	public void onBeforeDisconnected() {
		//logger.log(Level.INFO, "comet.beforeDisconnected");
	}

	@Override
	public void onDisconnected() {
		GWT.log("comet.disconnected");
		ChatEvent chatEvent = new ChatEvent();
		chatEvent.setUser(Nositer.getInstance().getUser());
		chatEvent.setGrouptagname(groupChatContainer.getGroupPlusView().getTagname());
		chatEvent.setChatEventType(ChatEventType.DISCONNECT);
		groupChatContainer.getClient().broadcast(chatEvent);
	}

	@Override
	public void onError(Throwable exception, boolean connected) {
		int statuscode =-1;
		if (exception instanceof StatusCodeException) {
			statuscode = ((StatusCodeException)exception).getStatusCode();
		}
		//GWT.log("comet.error [connected=" + connected + "] ("+statuscode+")", exception);
	}

	@Override
	public void onHeartbeat() {
		//GWT.log("comet.heartbeat ["+client.getConnectionID()+"]");
	}

	@Override
	public void onRefresh() {
		//GWT.log("comet.refresh ["+client.getConnectionID()+"]");
	}


	@Override
	public void onMessage(List<? extends Serializable> messages) {
		StringBuilder result = new StringBuilder();
		for(Serializable obj : messages) {
			ChatEvent chatEvent = (ChatEvent)obj;
			if (chatEvent.getChatEventType().equals(ChatEventType.NORMAL)) {
				String html = groupChatContainer.getGroupChatMainPanel().getHtmlContainerPlus().getHtmlHistory();
				html += "<SPAN style='color: blue'>" + chatEvent.getUser().getLogin() + ":</SPAN> " + chatEvent.getData() + "<BR/>";
				displayMessage(html);
			} else if (chatEvent.getChatEventType().equals(ChatEventType.WHISPER)) { 
				String html = groupChatContainer.getGroupChatMainPanel().getHtmlContainerPlus().getHtmlHistory();
				html += "<SPAN style='color: pink'>" + chatEvent.getUser().getLogin() + ": " + chatEvent.getData() + "</SPAN><BR/>";
				displayMessage(html);
			} else if (chatEvent.getChatEventType().equals(ChatEventType.CONNECT) || chatEvent.getChatEventType().equals(ChatEventType.DISCONNECT)) {
				addOrRemoveUsers(chatEvent);
			}
		}
	}
	
	private void addOrRemoveUsers(ChatEvent chatEvent) {
		ListField<BaseModel> listField = groupChatContainer.getGroupChatLeftPanel().getListField();
		TreeSet<User> usersFromChatEvent = chatEvent.getUsers();
		
		List<BaseModel> userBeanModels = listField.getStore().getModels();
		
		// add
		for (User user : usersFromChatEvent) {
			if (!isUserInList(user, userBeanModels)) {
				BeanModelFactory factory = BeanModelLookup.get().getFactory(user.getClass());
				BeanModel beanModel = factory.createModel(user);
				//baseModel.set(User.Column.login.name(), user.getLogin());
				listField.getStore().add(beanModel);
			}				
		}
		
		// subtract
		Iterator<BaseModel> iteratorBeanModels = userBeanModels.iterator();
		while (iteratorBeanModels.hasNext()) {
			BaseModel baseModel = iteratorBeanModels.next();
			User userBean = ((BeanModel)baseModel).getBean();
			if (!isUserInSet(userBean, usersFromChatEvent)) {
				//iteratorBeanModels.remove();
				listField.getStore().remove(baseModel);
			}
		}
	
	}

	private boolean isUserInSet(User userBean, TreeSet<User> usersFromChatEvent) {
		boolean retval = false;
		for (User userFromChatEvent : usersFromChatEvent) {			
			if (userBean.getId().equals(userFromChatEvent.getId())) {
				retval = true;
				break;
			}
		}
		return retval;
	}

	private boolean isUserInList(User user, List<BaseModel> userBeanModels) {
		boolean retval = false;
		for (BaseModel baseModel : userBeanModels) {
			User userBean = ((BeanModel)baseModel).getBean();
			if (user.getId().equals(userBean.getId())) {
				retval = true;
				break;
			}
		}
		return retval;
	}

	private void displayMessage(String html) {
		HtmlContainerPlus htmlContainerPlus = groupChatContainer.getGroupChatMainPanel().getHtmlContainerPlus();
		htmlContainerPlus.setHtmlHistory(html);
		htmlContainerPlus.setHtml(html);
		htmlContainerPlus.
		getElement().
		setScrollTop(
				htmlContainerPlus.getElement().getScrollHeight()
		);

		groupChatContainer.getGroupChatMainPanel().layout();
	}
};
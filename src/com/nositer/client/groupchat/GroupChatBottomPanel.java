package com.nositer.client.groupchat;

import java.io.Serializable;
import java.util.List;

import org.atmosphere.gwt.client.AtmosphereClient;
import org.atmosphere.gwt.client.AtmosphereGWTSerializer;
import org.atmosphere.gwt.client.AtmosphereListener;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Element;
import com.nositer.client.Nositer;
import com.nositer.client.left.LeftPanel;
import com.nositer.client.main.MainPanel;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.widget.Resizable;
import com.nositer.shared.Global;

public class GroupChatBottomPanel extends ContentPanel implements Resizable {


	private GroupChatContainer groupChatContainer;
	private TextArea textArea;
	private Button button;
	private boolean whisper;

	
	
	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public boolean isWhisper() {
		return whisper;
	}

	public void setWhisper(boolean whisper) {
		this.whisper = whisper;
	}

	public GroupChatContainer getGroupChatContainer() {
		return groupChatContainer;
	}

	public void setGroupChatContainer(GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public GroupChatBottomPanel() {
		init();
	}

	public GroupChatBottomPanel(
			GroupChatContainer groupChatContainer) {
		this.groupChatContainer = groupChatContainer;
		init();
	}

	public void init() {
		whisper = false;
		this.setId(this.getClass().getName());
		this.setLayout(new RowLayout(Orientation.HORIZONTAL));
		this.setHeaderVisible(false);
		initTextArea();
		initButton();
		this.add(textArea);
		this.add(button);
		resize(0,0);
	}

	private void initTextArea() {
		textArea = new TextArea();
		textArea.addListener(Events.KeyPress, new KeyListener() {
			@Override
			public void componentKeyPress(ComponentEvent event) {
				super.componentKeyPress(event);
				// CTRL-Enter
				if (event.isControlKey() && event.getKeyCode() == KeyCodes.KEY_ENTER) {
					sendMessage();
				}
			}

		});
	}

	private void initButton() {
		button = new Button("Send");
		button.setWidth(50);
		Listener<BaseEvent> listener = new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				sendMessage();
				textArea.clear();
			}
		};
		button.addListener(Events.Select, listener);
	}

	private void sendMessage() {
		ChatEvent chatEvent = new ChatEvent();
		chatEvent.setUser(Nositer.getInstance().getUser());
		chatEvent.setGrouptagname(groupChatContainer.getGroupPlusView().getTagname());
		chatEvent.setData(textArea.getValue());
		groupChatContainer.getClient().broadcast(chatEvent);
	}


	// called when the borderlayout split is resized
	@Override
	protected void onResize(int width, int height) {
		super.onResize(width, height);
		resize(0,0);
	}

	@Override
	public void resize(int width, int height) {
		this.setHeight(52);
		//listField.setHeight(MainPanel.getInstance().getHeight() - 160);
		if (this.isRendered()) {
			//this.setWidth(Nositer.getInstance().getMainPanel().getWidth());
			GWTUtil.log("width:" + MainPanel.getInstance().getWidth());
			textArea.setHeight(this.getHeight());
			textArea.setWidth(this.getWidth()-button.getWidth());
			button.setHeight(this.getHeight());
		}
		//groupChatContainer.getGroupChatMainPanel().resize(0,0);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		resize(0,0);
	}
	
	public void enableWhisper() {
		setWhisper(true);
		Element textAreaElement = (Element) textArea.getElement().getFirstChild();
		textAreaElement.setClassName("whisper");
	}

	public void disableWhisper() {
		setWhisper(false);
		Element textAreaElement = (Element) textArea.getElement().getFirstChild();
		textAreaElement.removeClassName("whisper");
	}
}

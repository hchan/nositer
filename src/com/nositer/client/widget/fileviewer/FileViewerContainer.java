package com.nositer.client.widget.fileviewer;

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.nositer.client.util.GWTUtil;
import com.nositer.client.util.HttpGetFileHelper;
import com.nositer.client.widget.SelectedFilePanel;
import com.nositer.client.widget.directorytree.FileModel;

public class FileViewerContainer extends LayoutContainer {
	private ContentPanel contentPanel;
	private HtmlContainer fileContainer;
	private SelectedFilePanel selectedFilePanel;
	private FileViewerMenuBar fileViewerMenuBar;

	public SelectedFilePanel getSelectedFilePanel() {
		return selectedFilePanel;
	}

	public void setSelectedFilePanel(SelectedFilePanel selectedFilePanel) {
		this.selectedFilePanel = selectedFilePanel;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public HtmlContainer getFileContainer() {
		return fileContainer;
	}

	public void setFileContainer(HtmlContainer fileContainer) {
		this.fileContainer = fileContainer;
	}

	public FileViewerContainer() {
		init();
	}

	private void init() {
		setLayout(new FlowLayout(0));  
		contentPanel = new ContentPanel();
		contentPanel.setHeading("File Viewer");
		contentPanel.setFrame(true);
		fileContainer = new HtmlContainer("<BR/>&nbsp;No File Selected");

		contentPanel.add(fileContainer);

		fileViewerMenuBar = new FileViewerMenuBar();
		fileViewerMenuBar.disable();
		contentPanel.setTopComponent(fileViewerMenuBar);
		selectedFilePanel = new SelectedFilePanel();
		contentPanel.setBottomComponent(selectedFilePanel);
		this.add(contentPanel, new FlowData(new Margins(0, 0, 0, 0)));
	}


	public void setImage(FileModel fileModel) {
		fileViewerMenuBar.enable();
		String imageUrl = HttpGetFileHelper.getUserPathURL(fileModel.getPath());
		fileContainer.setHtml("<IMG SRC='" + imageUrl + "' CLASS='imageViewer'/>");		
	}

	public void setImage(String fileModelPath, String widthAndHeight) {
		fileViewerMenuBar.enable();
		String imageUrl = HttpGetFileHelper.getUserPathURL(fileModelPath);
		String style = "";
		if (widthAndHeight != null) {
			style = "STYLE='width:" + widthAndHeight + ";height:" + widthAndHeight + "'";
		}
		fileContainer.setHtml("<IMG SRC='" + imageUrl + "' CLASS='imageViewer' " + style + "/>");		
	}

	public void setText(FileModel fileModel) {
		String url = HttpGetFileHelper.getUserPathURL(fileModel.getPath());
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					GWTUtil.log("", exception);
				}
				@Override
				public void onResponseReceived(Request request, Response response) {
					fileContainer.setHtml("<DIV ID=\"textFile\" class=\"textFile\"><PRE>" + response.getText() + "</PRE></DIV>");
					fileContainer.setStyleName("textFile");
					fileContainer.setAutoHeight(true);
				}
			});
		} catch (Exception e) {
			GWTUtil.log("", e);
		}	
	}

}

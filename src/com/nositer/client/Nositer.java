package com.nositer.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Element;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.nositer.client.util.GWTUtil;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Nositer implements EntryPoint {

	private TopPanel topPanel;
	private LeftPanel leftPanel;
	private MainPanel mainPanel;
	private LayoutContainer layoutContainer;

	public LayoutContainer getLayoutContainer() {
		return layoutContainer;
	}
	public void setLayoutContainer(LayoutContainer layoutContainer) {
		this.layoutContainer = layoutContainer;
	}
	public MainPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public TopPanel getTopPanel() {
		return topPanel;
	}
	public void setTopPanel(TopPanel topPanel) {
		this.topPanel = topPanel;
	}
	public LeftPanel getLeftPanel() {
		return leftPanel;
	}
	public void setLeftPanel(LeftPanel leftPanel) {
		this.leftPanel = leftPanel;
	}
	public void onModuleLoad() {
		init();
	}

	public void init() {
		Viewport viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		
		LayoutContainer layoutContainer = new LayoutContainer() {
			protected void onRender(Element target, int index) {

				super.onRender(target, index);
				final BorderLayout layout = new BorderLayout();
				setLayout(layout);
				setStyleAttribute("padding", "10px");

				ContentPanel north = new ContentPanel();
				ContentPanel west = new ContentPanel();
				ContentPanel center = new ContentPanel();
				center.setHeading("BorderLayout Example");
				center.setScrollMode(Scroll.AUTOX);

				FlexTable table = new FlexTable();
				table.getElement().getStyle().setProperty("margin", "10px");
				table.setCellSpacing(8);
				table.setCellPadding(4);

				for (int i = 0; i < LayoutRegion.values().length; i++) {
					final LayoutRegion r = LayoutRegion.values()[i];
					if (r == LayoutRegion.CENTER) {
						continue;
					}
					SelectionListener<ButtonEvent> sl = new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							String txt = ce.getButton().getText();
							if (txt.equals("Expand")) {
								layout.expand(r);
							} else if (txt.equals("Collapse")) {
								layout.collapse(r);
							} else if (txt.equals("Show")) {
								layout.show(r);
							} else {
								layout.hide(r);
							}

						}
					};
					table.setHTML(i, 0, "<div style='font-size: 12px; width: 100px'>" + r.name() + ":</span>");
					table.setWidget(i, 1, new Button("Expand", sl));
					table.setWidget(i, 2, new Button("Collapse", sl));
					table.setWidget(i, 3, new Button("Show", sl));
					table.setWidget(i, 4, new Button("Hide", sl));
				}
				center.add(table);

				ContentPanel east = new ContentPanel();
				ContentPanel south = new ContentPanel();

				BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 100);
				northData.setCollapsible(true);
				northData.setFloatable(true);
				northData.setHideCollapseTool(true);
				northData.setSplit(true);
				northData.setMargins(new Margins(0, 0, 5, 0));

				BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 150);
				westData.setSplit(true);
				westData.setCollapsible(true);
				westData.setMargins(new Margins(0,5,0,0));

				BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
				centerData.setMargins(new Margins(0));

				BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.EAST, 150);
				eastData.setSplit(true);
				eastData.setCollapsible(true);
				eastData.setMargins(new Margins(0,0,0,5));

				BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);
				southData.setSplit(true);
				southData.setCollapsible(true);
				southData.setFloatable(true);
				southData.setMargins(new Margins(5, 0, 0, 0));

				add(north, northData);
				add(west, westData);
				add(center, centerData);
				add(east, eastData);
				add(south, southData);
			}
		};
		viewport.add(layoutContainer, new BorderLayoutData(LayoutRegion.CENTER));
		RootPanel.get().add(viewport);
	}
}

		/*
	public void init2() {

		BorderLayout borderLayout = new BorderLayout();
		topPanel = new TopPanel(this);		
		leftPanel = new LeftPanel(this);
		mainPanel = new MainPanel(this);

		LayoutContainer layoutContainer = new LayoutContainer() {
			@Override
			protected void onRender(Element parent, int index) {
				GWTUtil.log("HERE I AM");
				super.onRender(parent, index);
				BorderLayoutData topPanelData = new BorderLayoutData(LayoutRegion.NORTH, 200);  

				add(topPanel, topPanelData);

				BorderLayoutData leftPanelData = new BorderLayoutData(LayoutRegion.WEST, 100);  

				add(leftPanel, leftPanelData);


				BorderLayoutData eastPanelData = new BorderLayoutData(LayoutRegion.EAST, 100);  
				ContentPanel eastPanel = new ContentPanel();
				add(eastPanel, eastPanelData);

				BorderLayoutData southPanelData = new BorderLayoutData(LayoutRegion.SOUTH, 100);  
				ContentPanel southPanel = new ContentPanel();
				add(southPanel, southPanelData);

				BorderLayoutData mainPanelData = new BorderLayoutData(LayoutRegion.CENTER);  
				mainPanelData.setSplit(true);
				mainPanelData.setCollapsible(true);
				mainPanelData.setMargins(new Margins(0));

				mainPanel.add(new Button("HI"));
				mainPanel.setScrollMode(Scroll.AUTOX);  
				add(mainPanel, mainPanelData);

			}
		};

		layoutContainer.setLayout(borderLayout);



		RootPanel.get().add(layoutContainer);
	}
		 */


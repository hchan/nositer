package com.nositer.client.viewprofile;

import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.nositer.client.dto.generated.User;
import com.nositer.client.util.GWTUtil;

public class Note extends LayoutContainer {
	private Label noteLabel;
	private Label noteModifiedtimeLabel;
	private HtmlContainer note;
	
	public Label getNoteLabel() {
		return noteLabel;
	}
	
	public void setNoteLabel(Label noteLabel) {
		this.noteLabel = noteLabel;
	}
	
	public HtmlContainer getNote() {
		return note;
	}
	
	public void setNote(HtmlContainer note) {
		this.note = note;
	}
		
	public Note() {
		init();
	}
	
	public void init() {
		TableLayout layout = new TableLayout(2);
		setLayout(layout);
		noteLabel = new Label("Quick Note");
		noteLabel.setStyleName("profileNoteLabel");
		this.add(noteLabel);
		noteModifiedtimeLabel = new Label();
		noteModifiedtimeLabel.setStyleName("profileNoteModifiedtimeLabel");
		this.add(noteModifiedtimeLabel);
		note = new HtmlContainer();
		note.setStyleName("profileNote");
		this.add(note);
	}
	
	public void populate(User user) {
		if (user.getNote() != null) {
			if (user.getNotemodifedtime() != null) {
				noteModifiedtimeLabel.setText("&nbsp;(Modifed on: " + GWTUtil.getFormattedDate(user.getNotemodifedtime()) + ")");
				noteModifiedtimeLabel.show();
			} else {
				noteModifiedtimeLabel.hide();
			}
			note.setHtml(user.getNote());
			note.show();
		} else {
			note.hide();
		}
	}
}

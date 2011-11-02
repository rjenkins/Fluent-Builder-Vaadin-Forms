package com.aceevo.examples.fluentbuilderforms.view;

import com.aceevo.examples.fluentbuilderforms.item.BeanItemAdapter;
import com.aceevo.examples.fluentbuilderforms.item.BuildableObjectProperty;
import com.aceevo.examples.fluentbuilderforms.model.PersistentObject;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class BeanItemForm<T extends BeanItemAdapter<PersistentObject>> extends Form {

	private static final long serialVersionUID = -134463363910505697L;

	public BeanItemForm() {

		// Enable buffering.
		setWriteThrough(false);
		setImmediate(true);
		// The Ok button calls form.commit().
		Button commit = new Button("Save", this, "commit");

		// The Restore button calls form.discard().
		Button restore = new Button("Cancel", this, "discard");

		Button showState = new Button("Show Pojo State", new ClickListener() {

			private static final long serialVersionUID = 472558219751562947L;

			public void buttonClick(ClickEvent event) {
				showPojoState(getItemDataSource());
			}
		});

		getFooter().addComponent(commit);
		getFooter().addComponent(restore);
		getFooter().addComponent(showState);

		Layout footerLayout = getFooter();
		if (footerLayout instanceof AbstractOrderedLayout)
			((AbstractOrderedLayout) footerLayout).setSpacing(true);
	}

	@Override
	public void setData(Object data) {
		if (data instanceof BeanItemAdapter<?>) {
			setFormFieldFactory((BeanItemAdapter<?>) data);
			setItemDataSource((BeanItemAdapter<?>) data);
			setDescription(((BeanItemAdapter<?>) data).getBean().getClass().getSimpleName()
					+ " Form");
		}
	}

	private void showPojoState(Item item) {
		StringBuffer stringBuffer = new StringBuffer();
		for (Object propId : item.getItemPropertyIds()) {
			Property objectProp = item.getItemProperty(propId);
			stringBuffer.append(((BuildableObjectProperty) objectProp).getDisplayName() + ": "
					+ objectProp.getValue() + "<br/>");
		}

		Window.Notification n = new Window.Notification("POJO state",
				Window.Notification.TYPE_TRAY_NOTIFICATION);
		n.setPosition(Window.Notification.POSITION_CENTERED);
		n.setDescription(stringBuffer.toString());
		getWindow().showNotification(n);
	}
}
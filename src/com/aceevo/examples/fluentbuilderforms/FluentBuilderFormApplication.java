package com.aceevo.examples.fluentbuilderforms;

import java.util.GregorianCalendar;

import com.aceevo.examples.fluentbuilderforms.item.BeanItemAdapter;
import com.aceevo.examples.fluentbuilderforms.item.CustomerAdapter;
import com.aceevo.examples.fluentbuilderforms.model.Customer;
import com.aceevo.examples.fluentbuilderforms.model.PersistentObject;
import com.aceevo.examples.fluentbuilderforms.view.BeanItemForm;
import com.aceevo.examples.fluentbuilderforms.view.FluentBuilderActionPanel;
import com.vaadin.Application;
import com.vaadin.ui.Form;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;

public class FluentBuilderFormApplication extends Application {

	private static final long serialVersionUID = 6201749526536590040L;
	private Window mainWindow;

	@Override
	public void init() {
		mainWindow = new Window("FluentBuilderForm Application");
		setMainWindow(mainWindow);

		Customer customer = new Customer("Bob", "Smith", new GregorianCalendar(1980, 5, 3)
				.getTime());
		customer.setCustomerType("Silver");
		customer.setGender("Male");

		CustomerAdapter customerAdapter = new CustomerAdapter(customer);

		Form form = new BeanItemForm<BeanItemAdapter<PersistentObject>>();
		form.setData(customerAdapter);

		Panel panel = new Panel("Builder Pattern Form Example");
		panel.addComponent(new FluentBuilderActionPanel(form));
		panel.addComponent(form);

		mainWindow.addComponent(panel);

	}

}

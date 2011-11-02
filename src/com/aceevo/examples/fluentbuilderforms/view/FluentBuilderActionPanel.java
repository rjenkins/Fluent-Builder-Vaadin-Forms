package com.aceevo.examples.fluentbuilderforms.view;

import java.util.GregorianCalendar;

import com.aceevo.examples.fluentbuilderforms.item.CustomerAdapter;
import com.aceevo.examples.fluentbuilderforms.item.TroubleTicketAdapter;
import com.aceevo.examples.fluentbuilderforms.model.Customer;
import com.aceevo.examples.fluentbuilderforms.model.TroubleTicket;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class FluentBuilderActionPanel extends CustomComponent {

	private static final long serialVersionUID = -3549661601588596519L;
	private Button addCustomerButton = new Button("New Customer");
	private Button addTicketButton = new Button("New Trouble Ticket");
	private Form form;
	Button.ClickListener actionPanelListener = new ClickListener() {

		private static final long serialVersionUID = -2713317871453072836L;

		public void buttonClick(ClickEvent event) {
			if (event.getButton().equals(addCustomerButton)) {
				CustomerAdapter customerAdapter = new CustomerAdapter(createNewCustomer());
				form.setData(customerAdapter);
			} else if (event.getButton().equals(addTicketButton)) {
				TroubleTicket troubleTicket = new TroubleTicket();
				TroubleTicketAdapter troubleTicketAdapter = new TroubleTicketAdapter(troubleTicket);
				form.setData(troubleTicketAdapter);
			}
		}
	};

	public FluentBuilderActionPanel(Form form) {
		this.form = form;

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.addComponent(addCustomerButton);
		horizontalLayout.addComponent(addTicketButton);
		horizontalLayout.setSpacing(true);
		horizontalLayout.setMargin(false, false, true, false);

		addCustomerButton.addListener(actionPanelListener);
		addTicketButton.addListener(actionPanelListener);
		setCompositionRoot(horizontalLayout);
	}

	private Customer createNewCustomer() {
		Customer customer = new Customer("Bob", "Smith", new GregorianCalendar(1980, 5, 3)
				.getTime());
		customer.setCustomerType("Silver");
		customer.setGender("Male");
		return customer;

	}
}

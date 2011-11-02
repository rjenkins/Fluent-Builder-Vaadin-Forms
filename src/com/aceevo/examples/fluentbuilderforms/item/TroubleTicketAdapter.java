package com.aceevo.examples.fluentbuilderforms.item;

import java.util.Arrays;

import com.aceevo.examples.fluentbuilderforms.model.TroubleTicket;
import com.vaadin.data.validator.RegexpValidator;

public class TroubleTicketAdapter extends BeanItemAdapter<TroubleTicket> {

	private static final long serialVersionUID = -4966215760997408782L;
	private static final String TICKET_NUMBER = "ticketNumber";
	private static final String CREATED_DATE = "createdDate";
	private static final String STATUS = "status";
	private static final String CONTACT_NAME = "contactName";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String PROBLEM_TYPE = "problemType";
	private static final String DETAIL = "detail";

	private static final String[] visibleFields = new String[] { TICKET_NUMBER, CREATED_DATE,
			STATUS, CONTACT_NAME, PHONE_NUMBER, PROBLEM_TYPE, DETAIL };

	public TroubleTicketAdapter(TroubleTicket troubleTicket) {
		super(troubleTicket, visibleFields);
	}

	@Override
	protected void initializeItemProps() {
		getItemProperty(TICKET_NUMBER).setDisplayName("Ticket Number").setReadOnly(true);
		getItemProperty(CREATED_DATE).setDisplayName("Date Created").setReadOnly(true);
		getItemProperty(STATUS).setDisplayName("Status").setOptions(
				Arrays.asList(new Object[] { "Open", "Resolved" })).setRadio(true);
		getItemProperty(CONTACT_NAME).setDisplayName("Contact Name");
		getItemProperty(PHONE_NUMBER).setDisplayName("Phone Number").addValidator(
				new RegexpValidator("(\\d{3}-)?\\d{3}-\\d{4}",
						"Phone Number must follow the form xxx-xxx-xxxx"));
		getItemProperty(PROBLEM_TYPE).setDisplayName("Problem Type").setOptions(
				Arrays.asList(new Object[] { "System Failure", "RMA", "Performance Problems",
						"General Question" }));
		getItemProperty(DETAIL).setDisplayName("Additional Information").setRichText(true)
				.setWidth(500);
	}
}

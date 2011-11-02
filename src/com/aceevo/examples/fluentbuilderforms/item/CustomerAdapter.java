package com.aceevo.examples.fluentbuilderforms.item;

import java.util.Arrays;

import com.aceevo.examples.fluentbuilderforms.model.Customer;

public class CustomerAdapter extends BeanItemAdapter<Customer> {

	private static final long serialVersionUID = -6237703225578104195L;
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String DOB = "dob";
	private static final String GENDER = "gender";
	private static final String CUSTOMER_TYPE = "customerType";
	private static final String ADDITIONAL_DATA = "additionalData";

	private static final String[] visibleFields = new String[] { FIRST_NAME, LAST_NAME, DOB,
			GENDER, CUSTOMER_TYPE, ADDITIONAL_DATA };

	public CustomerAdapter(Customer customer) {
		super(customer, visibleFields);
	}

	@Override
	protected void initializeItemProps() {
		getItemProperty(FIRST_NAME).setDisplayName("First Name").setRequired(true);
		getItemProperty(LAST_NAME).setDisplayName("Last Name").setRequired(true);
		getItemProperty(DOB).setDisplayName("Date of Birth");
		getItemProperty(GENDER).setOptions(Arrays.asList(new Object[] { "Male", "Female" }))
				.setDisplayName("Gender").setRadio(true);

		getItemProperty(CUSTOMER_TYPE).setOptions(
				Arrays.asList(new Object[] { "Platinum", "Gold", "Silver" })).setDisplayName(
				"Customer Status");
		getItemProperty(ADDITIONAL_DATA).setDisplayName("Additional Information").setRichText(true)
				.setWidth(500);

	}
}

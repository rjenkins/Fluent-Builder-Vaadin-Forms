package com.aceevo.examples.fluentbuilderforms.item;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aceevo.examples.fluentbuilderforms.model.PersistentObject;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

public class BeanItemAdapter<T extends PersistentObject> extends BeanItem<T> implements
		FormFieldFactory {

	private static final long serialVersionUID = 677532970926992036L;
	private Map<Property, BuildableObjectProperty> propertyCache;

	public BeanItemAdapter(T bean) {
		super(bean);
		initializeItemProps();
	}

	public BeanItemAdapter(T bean, Collection<?> propertyIds) {
		super(bean, propertyIds);
		initializeItemProps();
	}

	public BeanItemAdapter(T bean, String[] propertyIds) {
		super(bean, propertyIds);
		initializeItemProps();
	}

	protected void initializeItemProps() {

	}

	@Override
	public BuildableObjectProperty getItemProperty(Object id) {
		Property property = super.getItemProperty(id);

		if (propertyCache == null)
			propertyCache = new HashMap<Property, BuildableObjectProperty>();

		BuildableObjectProperty buildableObjectProperty = propertyCache.get(property);
		if (buildableObjectProperty == null) {
			buildableObjectProperty = new BuildableObjectProperty(property);
			propertyCache.put(property, buildableObjectProperty);
		}
		return buildableObjectProperty;
	}

	@Override
	public boolean addItemProperty(Object id, Property property) {
		boolean addPropBool = super.addItemProperty(id, property);
		if (addPropBool == true) {

			if (propertyCache == null)
				propertyCache = new HashMap<Property, BuildableObjectProperty>();

			BuildableObjectProperty buildableObjectProperty = propertyCache.get(property);
			if (buildableObjectProperty == null) {
				buildableObjectProperty = new BuildableObjectProperty(property);
				propertyCache.put(property, buildableObjectProperty);
			}
		}

		return addPropBool;

	}

	@Override
	public boolean removeItemProperty(Object id) {
		Property property = super.getItemProperty(id);
		propertyCache.remove(property);
		return super.removeItemProperty(id);
	}

	public Field createField(Item item, Object propertyId, Component uiContext) {
		BuildableObjectProperty property = (BuildableObjectProperty) item
				.getItemProperty(propertyId);

		Field field = null;

		if (property.getOptions().size() > 0) {
			if (property.isRadio() == false) {
				ComboBox comboBox = new ComboBox();
				comboBox.setNullSelectionAllowed(false);
				comboBox.setNewItemsAllowed(false);
				for (Object option : property.getOptions())
					comboBox.addItem(option);
				comboBox.setImmediate(true);
				field = comboBox;
			} else {
				OptionGroup optionGroup = new OptionGroup();
				for (Object option : property.getOptions())
					optionGroup.addItem(option);

				field = optionGroup;
			}
		} else {

			if (property.getType() == Date.class) {
				PopupDateField dateField = new PopupDateField();
				dateField.setResolution(PopupDateField.RESOLUTION_DAY);
				field = dateField;
			} else if (property.isRichText()) {
				RichTextArea richTextArea = new RichTextArea();
				field = richTextArea;
			} else {
				TextField textField = new TextField();
				field = textField;
			}
		}

		if (field != null) {
			field.setCaption(property.getDisplayName());
			field.setWidth(property.getWidth(), Sizeable.UNITS_PIXELS);
			field.setRequired(property.isRequired());
			for (Validator validator : property.getValidators())
				field.addValidator(validator);
		}
		return field;
	}
}

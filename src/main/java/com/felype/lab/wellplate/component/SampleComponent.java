package com.felype.lab.wellplate.component;

import com.felype.lab.wellplate.entity.Sample;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.themes.ValoTheme;

public class SampleComponent extends CustomField<Sample> {

	private Button button = new Button();

	private Sample value;

	public SampleComponent() {
		this(null);

		setSample(null);

		setWidth(50, Unit.PIXELS);
		setHeight(40, Unit.PIXELS);
	}

	public SampleComponent(Sample sample) {
		setValue(sample);
	}

	public boolean isEmpty() {
		return value == null;
	}

	@Override
	public Sample getValue() {
		return value;
	}

	@Override
	protected Component initContent() {

		return button;
	}

	@Override
	protected void doSetValue(Sample value) {
		this.value = value;

		setSample(value);
	}

	private void setSample(Sample sample) {
		button.setStyleName(ValoTheme.BUTTON_LINK);
		addStyleName("sample-component");

		if (sample != null) {
			button.setDescription("ID: " + sample.getId() + "\n" + "Volume: " + sample.getVolume());
			button.setIcon(VaadinIcons.DOT_CIRCLE);
		} else {
			button.setDescription("Empty");
			button.setIcon(VaadinIcons.CIRCLE_THIN);
		}
	}

	public Button getButton() {
		return button;
	}

}

package com.felype.lab.wellplate;

import javax.servlet.annotation.WebServlet;

import com.felype.lab.wellplate.component.PlateComponent;
import com.felype.lab.wellplate.service.PersistenceService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class MyUI extends UI {

	private PersistenceService persistenceService = new PersistenceService();

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		// The so called 96-well plate.
		PlateComponent plateComponent = new PlateComponent();
		persistenceService.getPlate(111).ifPresent(plateComponent::setValue);

		plateComponent.addPlateComponentListener(sample -> {
			if (sample != null) {
				Notification.show(
						sample + " changed and should be updated in the database through the PersistenceService now.");
			}
		});

		Label instructions = new Label("Click source well and destination well next to move samples.");

		setContent(new VerticalLayout(plateComponent, instructions));
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}

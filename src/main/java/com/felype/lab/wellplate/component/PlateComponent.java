package com.felype.lab.wellplate.component;

import java.util.HashSet;
import java.util.Set;

import com.felype.lab.wellplate.entity.Plate;
import com.felype.lab.wellplate.entity.Sample;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class PlateComponent extends CustomField<Plate> {

	public interface PlateComponentListener {
		void onSampleChanged(Sample sample);
	}

	private Plate value;

	private GridLayout gridLayout;

	private CssLayout content = new CssLayout();

	private SampleComponent selectedSample;

	private Set<PlateComponentListener> listeners = new HashSet<>();

	@Override
	protected Component initContent() {
		return content;
	}

	@Override
	public Plate getValue() {
		return value;
	}

	@Override
	protected void doSetValue(Plate value) {
		this.value = value;

		content.removeAllComponents();

		gridLayout = new GridLayout(value.getColumns() + 1, value.getRows() + 1);

		for (int i = 0; i < value.getRows(); i++) {
			Label labelRow = createLabelRowColumn(String.valueOf((char) ('A' + i)));

			gridLayout.addComponent(labelRow, 0, i + 1);
			gridLayout.setComponentAlignment(labelRow, Alignment.MIDDLE_CENTER);
		}

		for (int i = 0; i < value.getColumns(); i++) {
			Label labelColumn = createLabelRowColumn(String.valueOf(i + 1));

			gridLayout.addComponent(labelColumn, i + 1, 0);
			gridLayout.setComponentAlignment(labelColumn, Alignment.MIDDLE_CENTER);
		}

		for (int row = 0; row < value.getRows(); row++) {
			for (int column = 0; column < value.getColumns(); column++) {
				SampleComponent sampleComponent = new SampleComponent();

				final int rowPosition = row;
				final int columnPosition = column;

				sampleComponent.getButton().addClickListener(event -> {
					if (selectedSample == null) {
						// If sample is empty, just ignore it. Empty samples
						// can't be moved.
						if (!sampleComponent.isEmpty()) {
							selectedSample = sampleComponent;
						}
					} else {
						// Sample can't be moved to filled well.
						if (sampleComponent.isEmpty()) {
							Sample sampleToMove = selectedSample.getValue();
							sampleToMove.setRow((char) (rowPosition + 'A'));
							sampleToMove.setColumn(columnPosition + 1);

							sampleComponent.setValue(sampleToMove);

							selectedSample.setValue(null);
							selectedSample = null;
						}
					}
				});

				gridLayout.addComponent(sampleComponent, column + 1, row + 1);
			}
		}

		value.getSamples().forEach(sample -> {
			SampleComponent sampleComponent = (SampleComponent) gridLayout.getComponent(sample.getColumn(),
					sample.getRow() - 'A' + 1);
			sampleComponent.setValue(sample);
		});

		gridLayout.iterator().forEachRemaining(component -> {
			if (SampleComponent.class.isInstance(component)) {
				addChangeListener(SampleComponent.class.cast(component));
			}
		});

		content.addComponent(gridLayout);
	}

	private void addChangeListener(SampleComponent sampleComponent) {
		sampleComponent.addValueChangeListener(value -> {
			listeners.forEach(listener -> {
				listener.onSampleChanged(value.getValue());
			});
		});
	}

	private Label createLabelRowColumn(String value) {
		Label label = new Label(value);
		label.addStyleName("column-row-caption");

		return label;
	}

	public void addPlateComponentListener(PlateComponentListener plateComponentListener) {
		listeners.add(plateComponentListener);
	}

	public void removePlateComponentListener(PlateComponentListener plateComponentListener) {
		listeners.remove(plateComponentListener);
	}

}

package com.felype.lab.wellplate.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.felype.lab.wellplate.entity.Plate;
import com.felype.lab.wellplate.entity.Sample;

public class PersistenceService {

	public Optional<Plate> getPlate(int plateId) {
		// Assume the plate comes from some REST service, database, whatever...
		List<Sample> samples = new ArrayList<>();

		Sample sample = new Sample(1, 'C', 4, 0.23);
		samples.add(sample);

		Sample sample2 = new Sample(2, 'A', 5, 0.12);
		samples.add(sample2);

		Sample sample3 = new Sample(3, 'B', 3, 0.23);
		samples.add(sample3);

		Sample sample4 = new Sample(4, 'C', 10, 0.93);
		samples.add(sample4);

		Sample sample5 = new Sample(5, 'D', 1, 0.23);
		samples.add(sample5);

		Sample sample6 = new Sample(6, 'D', 9, 0.23);
		samples.add(sample6);

		Sample sample7 = new Sample(7, 'E', 3, 0.23);
		samples.add(sample7);

		Sample sample8 = new Sample(8, 'E', 5, 0.23);
		samples.add(sample8);

		Sample sample9 = new Sample(9, 'F', 12, 0.23);
		samples.add(sample9);

		Sample sample10 = new Sample(10, 'F', 2, 0.23);
		samples.add(sample10);

		Sample sample11 = new Sample(11, 'G', 6, 0.23);
		samples.add(sample11);

		Sample sample12 = new Sample(12, 'H', 8, 0.23);
		samples.add(sample12);

		Collections.sort(samples, new Comparator<Sample>() {

			@Override
			public int compare(Sample sample, Sample otherSample) {
				return Integer.compare(getPosition(sample), getPosition(otherSample));
			}

			private int getPosition(Sample sample) {
				return (sample.getRow() - 'A') * 12 + sample.getColumn();
			}
		});

		Plate result = new Plate(plateId, samples, 8, 12);
		return Optional.of(result);
	}

}

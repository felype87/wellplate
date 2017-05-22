package com.felype.lab.wellplate.entity;

import java.util.List;

public class Plate {

	private long id;

	private List<Sample> samples;

	private int rows;
	private int columns;

	public Plate(long id, List<Sample> samples, int rows, int columns) {
		this.id = id;
		this.samples = samples;
		this.rows = rows;
		this.columns = columns;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Sample> getSamples() {
		return samples;
	}

	public void setSamples(List<Sample> samples) {
		this.samples = samples;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

}

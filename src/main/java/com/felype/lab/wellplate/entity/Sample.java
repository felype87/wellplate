package com.felype.lab.wellplate.entity;

public class Sample {

	private long id;

	private char row;

	private int column;

	private double volume;

	public Sample(long id, char row, int column, double volume) {
		this.id = id;
		this.row = row;
		this.column = column;
		this.volume = volume;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public char getRow() {
		return row;
	}

	public void setRow(char row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Sample [id=" + id + ", row=" + row + ", column=" + column + ", volume=" + volume + "]";
	}

}

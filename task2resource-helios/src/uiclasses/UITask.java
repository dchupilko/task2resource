package uiclasses;

import java.util.Date;
import java.util.GregorianCalendar;

/*
 * UI->BL
 * Use case: create new task
 * BL->UI
 * Use case: edit task
 * Use case: show all tasks
 */
public class UITask {
	protected String name;
	protected int capacity;
	protected Date fromDate;
	protected Date toDate;
	protected int lengthInMinutes;
	protected int[][] period;
	//TODO: description
	
	public UITask(String name, int capacity, Date fromDate,	Date toDate, int lengthInMinutes, int[][] period) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.lengthInMinutes = lengthInMinutes;
		this.period = period;
	}
	
	public UITask (String name, int capacity, Date fromDate, Date toDate)
	{
		//TODO: come to agreement with UI
		this(name, capacity, fromDate, toDate, 0, null);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getLengthInMinutes() {
		return lengthInMinutes;
	}
	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}
	public int[][] getPeriod() {
		return period;
	}
	public void setPeriod(int[][] period) {
		this.period = period;
	}
	
	public String toString() {
		return this.name + "; " + this.capacity;
	}
}

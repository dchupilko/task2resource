package uiclasses;

import java.util.GregorianCalendar;

/*
 * BL->UI
 * Use case: create new task
 * Use case: show task
 * Use case: edit task
 */
public class UIDates {
	protected GregorianCalendar startDate;
	protected GregorianCalendar finishDate;

	public UIDates(GregorianCalendar startDate, GregorianCalendar finishDate) {
		this.startDate = startDate;
		this.finishDate = finishDate;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}
	public GregorianCalendar getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(GregorianCalendar finishDate) {
		this.finishDate = finishDate;
	}
}

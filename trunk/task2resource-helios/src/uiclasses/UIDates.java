package uiclasses;

import java.util.GregorianCalendar;

public class UIDates {
	protected GregorianCalendar startDate;
	protected GregorianCalendar finishDate;
	
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

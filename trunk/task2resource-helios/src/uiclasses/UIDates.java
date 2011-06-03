package uiclasses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * BL->UI
 * Use case: create new task
 * Use case: show task
 * Use case: edit task
 */
public class UIDates {
	protected Date startDate;
	protected Date finishDate;

	public UIDates(Date startDate, Date finishDate) {
		this.startDate = startDate;
		this.finishDate = finishDate;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	@Override
	public String toString() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    String strStartDate=sdf.format(startDate);
	    String strFinishDate=sdf.format(finishDate);
	    
		return "Dates [startDate=" + strStartDate + ", finishDate=" + strFinishDate
				+ "]";
	}
}

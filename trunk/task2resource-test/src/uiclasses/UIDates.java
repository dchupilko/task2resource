package uiclasses;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/*
 * BL->UI
 * Use case: create new task
 * Use case: show task
 * Use case: edit task
 */
public class UIDates {
	protected GregorianCalendar startDate = new GregorianCalendar();
	protected GregorianCalendar finishDate = new GregorianCalendar();

	public UIDates(GregorianCalendar startDate, GregorianCalendar finishDate) {
		this.startDate.setTime(startDate.getTime());
		this.finishDate.setTime(finishDate.getTime());
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate.setTime(startDate.getTime());
	}
	public GregorianCalendar getFinishDate() {
		GregorianCalendar temp = new GregorianCalendar();
		temp.setTime(finishDate.getTime());
		return temp;
		//return this.finishDate;
	}
	public void setFinishDate(GregorianCalendar finishDate) {
		this.finishDate.setTime(finishDate.getTime());
	}

	@Override
	public String toString() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    String strStartDate=sdf.format(startDate.getTime());
	    String strFinishDate=sdf.format(finishDate.getTime());	    
		return "Dates [startDate=" + strStartDate + ", finishDate=" + strFinishDate
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((finishDate == null) ? 0 : finishDate.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UIDates other = (UIDates) obj;
		if (finishDate == null) {
			if (other.finishDate != null)
				return false;
		} else if (!finishDate.equals(other.finishDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	
	
	
}

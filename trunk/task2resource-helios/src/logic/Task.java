package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task {
    protected String name;
    protected int capacity;
    protected int oid;
    protected ArrayList<Dates> dates;
    protected ArrayList<Resource> resources;
    
    /*
     * тут будем хранить коллекцию начала и конца события включая
     * дату и время. Коллекции синхронны
     */
    private ArrayList<GregorianCalendar> eventStarts = null;
    private ArrayList<GregorianCalendar> eventEnds = null;
    
    /*
     * Тут будем хранить диапазон периодичности события (только дата)
     * Если событие единоразовое, то поля совпадают
     */
    private GregorianCalendar fromDate = null;
    private GregorianCalendar toDate = null;
    
    private int lengthInMinutes = 0;
    private int[][] period = null;//Переменная задает время и день события
    
    public Task() {}
    
    /**
     * 
     * @param name
     * @param capacity
     * Любое событие обязано иметь:
     * @param fromDate - дату начала
     * @param toDate - дату конца (если событие повторяется)
     * @param length - длительность в минутах
     * @param period - периодичность в виде день-часы-минуты
     * Иными словами, иметь такой вид
     *   {1,11,00}//Воскресенье, 11-00
     *   {2,16,15}//Понедельник, 16-15
     *   {3,13,00}//Вторник, 13-00
     * Для единичного события массив будет иметь одну строку.
     */
    public Task(String name, int capacity, GregorianCalendar fromDate, GregorianCalendar toDate, int length, int[][]period) {
        
        this.name = name;
        this.capacity = capacity;
        
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.lengthInMinutes = length;
        this.period = period;
        
        
        calculateDates(this.fromDate, this.toDate, this.lengthInMinutes, this.period);
        
    }
    
    /*
     * Программа сделает хотя бы один проход (если событие единоразовое)
     */
    private void calculateDates(GregorianCalendar fromDate, GregorianCalendar toDate, int length, int[][] period){
        GregorianCalendar tmpDate               = fromDate;
        GregorianCalendar templateDateForStart  = null;
        GregorianCalendar templateDateForFinish = null;
        do {
            for (int i = 0; i < period.length; i++) {
                if (tmpDate.get(Calendar.DAY_OF_WEEK) == period[i][0]) {
                    templateDateForStart = new GregorianCalendar(tmpDate.get(Calendar.YEAR), 
                                                                 tmpDate.get(Calendar.MONTH), 
                                                                 tmpDate.get(Calendar.DAY_OF_MONTH), 
                                                                 period[i][1], 
                                                                 period[i][2]);
                    eventStarts.add(templateDateForStart);
                    
                    templateDateForFinish = new GregorianCalendar(tmpDate.get(Calendar.YEAR), 
                                                                  tmpDate.get(Calendar.MONTH), 
                                                                  tmpDate.get(Calendar.DAY_OF_MONTH), 
                                                                  period[i][1] + length/60, 
                                                                  period[i][1] + length%60);
                    eventEnds.add(templateDateForFinish); 
                }
            }
            tmpDate.add(Calendar.DATE, 1);
        } while (!tmpDate.equals(toDate));
    }
    
    public String getName () {
        return this.name;
    }
    
    public int getCapacity () {
        return this.capacity;
    }
    
    public int getOid () {
        return this.oid;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public void setCapacity (int capacity) {
        this.capacity = capacity;
    }
    
    public void setOid (int oid) {
        this.oid = oid;
    }
}


package ec.edu.espe.ecutravel.aerolinea.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zzynx
 */
public class RandomDateGenerator {

    private Date dMin = null;
    private Date dMax = null;

    /**
     * Creates a new instance of RandomDateGenerator
     */
    public RandomDateGenerator() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1); // today minus one year
        Date dMin = cal.getTime();
        System.out.println("Date min:" + dMin);
        cal.add(Calendar.MONTH, 2); // today plus one year
        Date dMax = cal.getTime();
        System.out.println("Date max:" + dMax);
        this.dMin= dMin;
        this.dMax= dMax;
    }

    public RandomDateGenerator(Date min, Date max) {
        dMin = min;
        dMax = max;
    }

    public Date generate() {
        long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
        GregorianCalendar s = new GregorianCalendar();
        s.setTimeInMillis(dMin.getTime());
        GregorianCalendar e = new GregorianCalendar();
        e.setTimeInMillis(dMax.getTime());

        // Get difference in milliseconds
        long endL = e.getTimeInMillis() + e.getTimeZone().getOffset(e.getTimeInMillis());
        long startL = s.getTimeInMillis() + s.getTimeZone().getOffset(s.getTimeInMillis());
        long dayDiff = (endL - startL) / MILLIS_PER_DAY;

        Calendar cal = Calendar.getInstance();
        cal.setTime(dMin);
        cal.add(Calendar.DATE, new Random().nextInt((int) dayDiff));
        return cal.getTime();
    }

    public Date retrieveRandomDate2() {

        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String str_date1 = "01-July-07 00:00:00";
        String str_date2 = "30-August-07 23:59:59";
        Long value2 = new Long("0");
        Long value1 = new Long("0");
        try {
            cal.setTime(formatter.parse(str_date1));

            value1 = cal.getTimeInMillis();

            cal.setTime(formatter.parse(str_date2));
            value2 = cal.getTimeInMillis();
        } catch (ParseException ex) {
            Logger.getLogger(RandomDateGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        long value3 = (long) (value1 + Math.random() * (value2 - value1));
        cal.setTimeInMillis(value3);
        System.out.println(formatter.format(cal.getTime()));

        return cal.getTime();
    }
//    public Date retrieveFecha() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, -1); // today minus one year
//        Date dMin = cal.getTime();
//        System.out.println("Date min:" + dMin);
//        cal.add(Calendar.MONTH, 2); // today plus one year
//        Date dMax = cal.getTime();
//        System.out.println("Date max:" + dMax);
//
//        RandomDateGenerator rnd = new RandomDateGenerator(dMin, dMax);
////        for (int i = 1; i <= 10; i++) {
////            System.out.println("Date = " + rnd.generate());
////        }
////        System.out.println("");
//        return rnd.generate();
//    }
    // =========
    // Test it:
    // =========

    public static void main(String args[]) {
//        try {
            //        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, -1); // today minus one year
//        Date dMin = cal.getTime();
//        System.out.println("Date min:" + dMin);
//        cal.add(Calendar.MONTH, 2); // today plus one year
//        Date dMax = cal.getTime();
//        System.out.println("Date max:" + dMax);
//
//        RandomDateGenerator rnd = new RandomDateGenerator(dMin, dMax);
//        for (int i = 1; i <= 10; i++) {
//            System.out.println("Date = " + rnd.generate());
//        }
//        System.out.println("");

//            DateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
//            Calendar cal = Calendar.getInstance();
//            String str_date1 = "17-June-07 02:10:15";
//            String str_date2 = "27-June-07 02:10:20";
//
//            cal.setTime(formatter.parse(str_date1));
//            Long value1 = cal.getTimeInMillis();
//
//            cal.setTime(formatter.parse(str_date2));
//            Long value2 = cal.getTimeInMillis();
//
//            long value3 = (long) (value1 + Math.random() * (value2 - value1));
//            cal.setTimeInMillis(value3);
//            System.out.println(formatter.format(cal.getTime()));
//        } catch (ParseException ex) {
//            Logger.getLogger(RandomDateGenerator.class.getName()).log(Level.SEVERE, null, ex);
//        }
        }
    }

//import javax.xml.crypto.Data;
import java.time.LocalDateTime;

public class Dates {
    private static int currentHour;
    private static int startHour;
    private static int startMinute;
    private static String curentDay;
    private static int gameMinute;
    private int timing=5000;
//    private Data curentTime;
    private DataIO dataIO=null;
    private int countWork=3;
    private int countVih=4;
    private int count;


    public DataIO getDataIO() {
        return dataIO;
    }

    public static void main (String [] args){
        curentDay = LocalDateTime.now ( ).getDayOfWeek().toString ();
        startHour= LocalDateTime.now ( ).getHour();
        startMinute= LocalDateTime.now ( ).getMinute();
        System.out.println(curentDay+" "+startHour+" "+startMinute);
        DataIO dataIO=getDataio();
        if (dataIO.getStartDate()==null) {
            dataIO.setStartDate(curentDay);
            dataIO.setStartHour(startHour);
            dataIO.setStartMinute(startMinute);
            dataIO.setGameMinute(gameMinute);
            dataIO.setNonce(curentDay+" "+startHour+" "+startMinute+ " "+gameMinute);
        }
        else if (!curentDay.equals(dataIO.getStartDate())) dataIO.setNonce(curentDay+" "+startHour+" "+startMinute);
        else if (curentDay.equals(dataIO.getStartDate())) {

        }
        System.out.println(dataIO.getStartHour());
        //DataIO dataIO=new DataIO(curentDay,startHour,startMinute);
    /*    dataIO.setStartDate(curentDay);
        dataIO.setStartHour(startHour);
        dataIO.setStartMinute(startMinute);
        System.out.println(dataIO.getStartHour());
        System.out.println(LocalDateTime.now());
    */}

    public String getCurentDay() {

        return curentDay;
    }

    public void setCurentDay(String curentDay) {
        this.curentDay = curentDay;
    }

    public static DataIO getDataio() {
        DataIO dataIO=new DataIO();
        return dataIO;
    }
    public void gameNow(){
        try {
            Thread.sleep(timing);



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class Dates {
    private static int currentHour;
    private static int currentMinute;
    private static String curentDay;
    private static int gameMinute;
    private int timing=5000;
//    private Data curentTime;
    private DataIO dataIO=null;

    private static int countHour;
    private static int startHour;
    private static int endHour;
    private static int maxMinGame;

    private int count;

    public static void main (String [] args){
        curentDay = LocalDateTime.now ( ).getDayOfWeek().toString ();
        currentHour= LocalDateTime.now ( ).getHour();
        currentMinute= LocalDateTime.now ( ).getMinute();
        readConfig();
        System.out.println(curentDay+" "+currentHour+" "+currentMinute);
        DataIO dataIO=getDataio();
        if (dataIO==null) {
            dataIO.setStartDate(curentDay);
            dataIO.setStartHour(currentHour);
            dataIO.setStartMinute(currentMinute);
            dataIO.setGameMinute(gameMinute);
            dataIO.setGameMinute(0);
            dataIO.setNonce(curentDay+" "+currentHour+" "+currentMinute+ " "+gameMinute);
        }
        else if (!curentDay.equals(dataIO.getStartDate())) dataIO.setNonce(curentDay+" "+currentHour+" "+currentMinute);
        else if (curentDay.equals(dataIO.getStartDate())) {
          //  if (currentHour>)
        }
        System.out.println(dataIO.getStartHour());
        //DataIO dataIO=new DataIO(curentDay,startHour,startMinute);
    /*    dataIO.setStartDate(curentDay);
        dataIO.setStartHour(startHour);
        dataIO.setStartMinute(startMinute);
        System.out.println(dataIO.getStartHour());
        System.out.println(LocalDateTime.now());
    */}

    public String getCurentDay() { return curentDay; }
    public void setCurentDay(String curentDay) { this.curentDay = curentDay; }

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
    public static void goShutdown(){}
    public static void readConfig(){
        File timeIO = new File("C:\\Users\\123\\IdeaProjects\\GoSleep\\src\\config.txt");
        String config="";
        try {
            BufferedReader re = new BufferedReader(new FileReader(timeIO));
            config=String.valueOf(re.readLine());
            re.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data[]=config.split(" ");
        if (curentDay.equals("SUNDAY")||curentDay.equals("SATURDAY"))countHour=Integer.parseInt(data[1]);
        else countHour=Integer.parseInt(data[0]);
    }
}

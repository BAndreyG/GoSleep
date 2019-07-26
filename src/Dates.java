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
    private static int timing=5000;
    private static DataIO dataIO=null;

    private static int countHour;
    private static int startHour;
    private static int endHour;
    private static int maxLongGame=120;

    private int count;

    public static void main (String [] args){
        curentDay = LocalDateTime.now ( ).getDayOfWeek().toString ();
        currentHour= LocalDateTime.now ( ).getHour();
        currentMinute= LocalDateTime.now ( ).getMinute();
        //readConfig();
        System.out.println(curentDay+" "+currentHour+" "+currentMinute);
        DataIO dataIO=new DataIO();
        if (dataIO==null) {setCurent();}
        else if (!curentDay.equals(dataIO.getStartDate())) setCurent();
            if (currentHour>dataIO.getGameEnd())goShutdown();
            if (gameMinute>dataIO.getMaxMinGameDay())goShutdown();
            if ((currentHour*60+currentMinute)-(dataIO.getStartHour()*60+dataIO.getStartMinute())<60) goShutdown();
            gameNow();

        System.out.println(dataIO.getStartHour());
        }

    public static void setCurent() {
        dataIO.setStartDate(curentDay);
        dataIO.setStartHour(currentHour);
        dataIO.setStartMinute(currentMinute);
        dataIO.setGameMinute(gameMinute);/// ???
        dataIO.setGameMinute(0); /// ???
        dataIO.setNonce(curentDay+" "+currentHour+" "+currentMinute+ " "+gameMinute); }
    public void setCurentDay(String curentDay) { this.curentDay = curentDay; }

    public static DataIO getDataio() {
        DataIO dataIO=new DataIO();
        return dataIO;
    }
    public static void gameNow(){
        try {
            Thread.sleep(timing);
            gameMinute+=timing/1000;
            if (gameMinute>dataIO.getMaxMinGameDay())goShutdown();
            if (LocalDateTime.now ( ).getHour()>dataIO.getGameEnd())goShutdown();

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

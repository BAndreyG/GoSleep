//import javax.xml.crypto.Data;
import com.sun.javaws.exceptions.ExitException;

import java.io.*;
import java.time.LocalDateTime;

public class Dates {
    private static int currentHour;
    private static int currentMinute;
    private static String curentDay;
    private static int gameMinute;
    private static int timing=5000;
    private static DataIO dataIO;

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
        dataIO=new DataIO();
        gameMinute=dataIO.getGameMinute();
        if (dataIO==null) {setCurent();}
        else if (!curentDay.equals(dataIO.getStartDate())) setCurent();
            if (currentHour>dataIO.getGameEnd())goShutdown("begin night");
            if (dataIO.getGameMinute()>=dataIO.getMaxMinGameDay())goShutdown("full game time");
            if (((currentHour*60+currentMinute)-(dataIO.getStartHour()*60+dataIO.getStartMinute()))<60) goShutdown("period relax <60");
            gameNow();
        }

    public static void setCurent() {
        gameMinute=0;
        //Dates.dataIO.setCurent(curentDay,currentHour,currentMinute,gameMinute);
        dataIO.setCurent(curentDay,currentHour,currentMinute,gameMinute);
        //dataIO.setNonce(curentDay+" "+currentHour+" "+currentMinute+ " "+gameMinute);
    }
    public void setCurentDay(String curentDay) { this.curentDay = curentDay; }

    public static DataIO getDataio() {
        DataIO dataIO=new DataIO();
        return dataIO;
    }
    public static void gameNow(){
        try {
            while (true) {
                Thread.sleep(timing*60);//*60
                gameMinute += timing / 1000;
                dataIO.setNonce(curentDay+" "+currentHour+" "+currentMinute+" "+gameMinute);
                if (gameMinute > dataIO.getMaxMinGameDay()) goShutdown("more 180");
                if (LocalDateTime.now().getHour() > dataIO.getGameEnd()) goShutdown("after 20");
                int q=LocalDateTime.now().getHour()*60+LocalDateTime.now().getMinute();
                int w=dataIO.getStartHour()*60+dataIO.getStartMinute();
                int e=(currentHour*60+currentMinute);

                if ((q-w)>120){System.out.println(q+" q "+w+" w "+e +" e ");
                    goShutdown("more 120 min__");
                    System.exit(0);}
                if (((currentHour*60+currentMinute)-(dataIO.getStartHour()*60+dataIO.getStartMinute()))>120)
                {goShutdown("more 120 min");}
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void goShutdown(String text){
        System.out.println(LocalDateTime.now().getMinute()+"   / gameMinute="+gameMinute);

        System.out.println(text);
        String command = "cmd /c start shutdown /s /t 999"; //  /f- немедленное отключение
        Process child = null;
        try {
            child = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream out = child.getOutputStream();

    }
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

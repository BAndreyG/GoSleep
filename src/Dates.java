//import javax.xml.crypto.Data;

import java.time.LocalDateTime;

class Dates {
    private int currentHour;
    private int currentMinute;
    private String currentDay;
    private int gameMinute;
    private DataIO dataIO;

    Dates() {
        currentDay = LocalDateTime.now().getDayOfWeek().toString();
        currentHour = LocalDateTime.now().getHour();
        currentMinute = LocalDateTime.now().getMinute();
        dataIO = new DataIO();
        gameMinute = dataIO.getGameMinute();
    }

    void gameNow() {
        try {
            while (true) {
                int min = 1;
                Thread.sleep(min * 60 * 1000);// sleep min minute
                gameMinute += min;
                dataIO.setNonce(currentDay + " " + currentHour + " " + currentMinute + " " + gameMinute);
                if (gameMinute > dataIO.getMaxMinGameDay()) goShutdown("more 180");
                if (LocalDateTime.now().getHour() > dataIO.getGameEnd()) goShutdown("after 20");
                int q = LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
                int w = dataIO.getStartHour() * 60 + dataIO.getStartMinute();
                int e = (currentHour * 60 + currentMinute);

                if ((LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute() - dataIO.getStartHour() * 60 + dataIO.getStartMinute()) > 120) {
                    System.out.println(q + " q " + w + " w " + e + " e ");
                    goShutdown("more 120 min__");
                }
                if (((currentHour * 60 + currentMinute) - (dataIO.getStartHour() * 60 + dataIO.getStartMinute())) > 120) {
                    goShutdown("more 120 min");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void goShutdown(String text) {
        System.out.println(LocalDateTime.now().getMinute() + "   / gameMinute=" + gameMinute);

        System.out.println(text);
       /* String command = "cmd /c"; // start cmd.exe
        Process child = null;
        try {
            child = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream out = child.getOutputStream();
       */ // System.exit(0);
    }

    DataIO getDataIO() {
        return dataIO;
    }

    void setCurrent() {
        gameMinute = 0;
        //Dates.dataIO.setCurent(curentDay,currentHour,currentMinute,gameMinute);
        dataIO.setCurrent(currentDay, currentHour, currentMinute, gameMinute);
        //dataIO.setNonce(curentDay+" "+currentHour+" "+currentMinute+ " "+gameMinute);
    }

    int getCurrentHour() {
        return currentHour;
    }

    int getCurrentMinute() {
        return currentMinute;
    }

    String getCurrentDay() {
        return currentDay;
    }
}

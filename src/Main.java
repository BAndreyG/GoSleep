public class Main {
    public static void main(String[] args) {
        Dates dates = new Dates();

        int currentHour = dates.getCurrentHour();
        int currentMinute = dates.getCurrentMinute();
        String currentDay = dates.getCurrentDay();
        System.out.println(currentDay + " " + currentHour + " " + currentMinute);

        DataIO dataIO = dates.getDataIO();
        if (!currentDay.equals(dataIO.getStartDate())) {
            dates.setCurrent();
        }
        if (currentHour > dataIO.getGameEnd()) {
            dates.goShutdown("begin night");
        }
        if (dataIO.getGameMinute() >= dataIO.getMaxMinGameDay()) {
            dates.goShutdown("full game time");
        }
        if (((currentHour * 60 + currentMinute) - (dataIO.getStartHour() * 60 + dataIO.getStartMinute())) < 60) {
            dates.goShutdown("period relax <60");
        }
        dates.gameNow();
    }
}

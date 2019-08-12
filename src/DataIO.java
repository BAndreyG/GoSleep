public class DataIO {
    private String startDate;
    private int startHour;
    private int startMinute;
    private int gameMinute;
    private TimeIO timeIO;
    private Config config;

    DataIO() {
        timeIO = new TimeIO();

        String nonce = timeIO.read();
        if (nonce == null) return;
        if (nonce.equals("-1")) return;

        String[] s = nonce.split(" ");
        startDate = s[0];
        startHour = Integer.parseInt(s[1]);
        startMinute = Integer.parseInt(s[2]);
        gameMinute = Integer.parseInt(s[3]);
        config = new Config(startDate);
    }

    int getGameMinute() {
        return gameMinute;
    }

    private void setGameMinute(int gameMinute) {
        this.gameMinute = gameMinute;
    }


    int getMaxMinGameDay() {
        return config.getMaxMinGameDay();
    }

    int getGameEnd() {
        return config.getGameEnd();
    }

    void setCurrent(String currentDay, int currentHour, int currentMinute, int gameMinute) {
        setStartDate(currentDay);
        setStartHour(currentHour);
        setStartMinute(currentMinute);
        setGameMinute(gameMinute);
        timeIO.write(currentDay + " " + currentHour + " " + currentMinute + " " + gameMinute);
    }

    String getStartDate() {
        return startDate;
    }

    private void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    int getStartHour() {
        return startHour;
    }

    private void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    int getStartMinute() {
        return startMinute;
    }

    private void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public String getNonce() {
        return timeIO.read();
    }

    void setNonce(String nonce) {
        timeIO.write(nonce);
    }
}

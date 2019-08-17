import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Config {
    private File config = new File("config.txt");
    private int maxMinGameDay;
    private int gameEnd;

    Config(String startDate) {
        String conf = read();
        String[] configParts = conf.split(" ");
        maxMinGameDay = Integer.parseInt(configParts[0]);
        if (startDate.equals("SUNDAY") || startDate.equals("SATURDAY")) {
            maxMinGameDay += 60;
        }
        gameEnd = Integer.parseInt(configParts[1]);

    }

    private String read() {
        try (BufferedReader re = new BufferedReader(new FileReader(config))) {
            return String.valueOf(re.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "180 20";
    }

    int getMaxMinGameDay() {
        return maxMinGameDay;
    }

    int getGameEnd() {
        return gameEnd;
    }
}

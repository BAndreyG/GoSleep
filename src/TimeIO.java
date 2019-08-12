import java.io.*;

class TimeIO {
    private File timeIO = new File("timeIO.txt");

    String read() {
        try (BufferedReader re = new BufferedReader(new FileReader(timeIO))) {
            return String.valueOf(re.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    void write(String nonce) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(timeIO))) {
            ///проверить насколько текущие данные записывает!!!
            writer.write(nonce);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

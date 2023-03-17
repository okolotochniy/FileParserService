import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileWatcher fileWatcher = new FileWatcher();
        fileWatcher.run();
    }
}

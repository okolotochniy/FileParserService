import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
    public void parse(String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                PostgresWriter postgresWriter = new PostgresWriter();
                postgresWriter.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

import java.io.IOException;
import java.nio.file.*;

public class FileWatcher {

    public void run() throws IOException, InterruptedException {
        String filePath = "./file.txt";
        Path path = Paths.get(filePath);

        // Создаем WatchService и регистрируем его для отслеживания изменений в файле
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Отслеживание изменений в файле " + filePath);

        while (true) {
            WatchKey key = watchService.take(); // блокирующий вызов, ожидание изменений
            for (WatchEvent<?> event : key.pollEvents()) {
                // Получаем тип события и имя файла, связанного с событием
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }
                WatchEvent<Path> watchEvent = (WatchEvent<Path>) event;
                Path fileName = watchEvent.context();

                // Если изменения были в файле, который мы отслеживаем, то выводим сообщение об изменении
                if (fileName.toString().equals(path.getFileName().toString())) {
                    System.out.println("Файл " + filePath + " был изменен");
                    FileParser fileParser = new FileParser();
                    fileParser.parse(filePath);
                }
            }
            key.reset();
        }
    }
}

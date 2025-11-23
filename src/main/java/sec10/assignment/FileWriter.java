package sec10.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter {
    private final Path filePath;
    private BufferedWriter writer;

    private FileWriter(Path filePath) {
        this.filePath = filePath;
    }

    private void createFile() {
        try {
            this.writer = Files.newBufferedWriter(this.filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeFile() {
        try {
            this.writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(String content) {
        try {
            this.writer.write(content);
            this.writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<Void> writeToFile(Flux<String> dataStream, Path filePath) {
        var fileWriter = new FileWriter(filePath);
        return dataStream
                .doOnNext(fileWriter::writeToFile)
                .doFirst(fileWriter::createFile)
                .doOnComplete(fileWriter::closeFile)
                .then();
    }

}


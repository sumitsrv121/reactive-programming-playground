package sec04.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileReaderServiceFluxGenerateImpl implements FileReaderService {
    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceFluxGenerateImpl.class);

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                () -> openFile(path),
                this::readLine,
                this::closeFile
        );
    }

    private void closeFile(BufferedReader bufferedReader) {
        if (Objects.nonNull(bufferedReader)) {
            try {
                bufferedReader.close();
                log.info("File is closed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private BufferedReader readLine(BufferedReader bufferedReader, SynchronousSink<String> synchronousSink) {
        try {
            String line = bufferedReader.readLine();
            if (Objects.isNull(line)) {
                synchronousSink.complete();
            } else {
                log.info("Reading line: {}", line);
                synchronousSink.next(line);
            }
        } catch (IOException e) {
            log.info("Error in reading line {}", e.getMessage(), e);
            synchronousSink.error(new RuntimeException(e));
        }
        return bufferedReader;
    }

    private BufferedReader openFile(Path path) throws IOException {
        return Files.newBufferedReader(path);
    }
}

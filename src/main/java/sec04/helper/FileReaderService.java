package sec04.helper;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface FileReaderService {
    Flux<String> read(Path path);
}

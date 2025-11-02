package sec04.helper;

import common.Util;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderServiceImpl implements
        FileReaderService {
    @Override
    public Flux<String> read(Path path) {
        return Flux.create(fluxSink -> {
            fluxSink.onRequest(request -> {
                ;
                try (var bufferedReader = Files.newBufferedReader(path)) {
                    String line;
                    while (!fluxSink.isCancelled() && ((line = bufferedReader.readLine()) != null)) {
                        Util.sleep(1);
                        fluxSink.next(line);
                    }
                    if (!fluxSink.isCancelled()) {
                        fluxSink.complete();
                    }
                } catch (IOException e) {
                    fluxSink.error(new RuntimeException(e.getMessage()));
                }
            });
        });
    }
}

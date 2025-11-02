package sec04;

import common.Util;
import sec04.helper.FileReaderService;
import sec04.helper.FileReaderServiceFluxGenerateImpl;

import java.nio.file.Path;

public class Lec10Assignment {
    private static final Path path = Path.of("src/main/resources/sec04");
    public static void main(String[] args) {
        FileReaderService readerService = new FileReaderServiceFluxGenerateImpl();

        readerService.read(path.resolve("test"))
                .take(6)
                .subscribe(Util.subscriber());
    }
}

package sec02;

import common.Util;
import sec02.assignment.FileService;
import sec02.assignment.FileServiceImpl;

public class Lec11Assignment {
    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();

        fileService.read("test")
                .subscribe(Util.subscriber());

        fileService.write("test", Util.faker().ancient().god())
                .subscribe(Util.subscriber());

        fileService.read("test")
                .subscribe(Util.subscriber());
        fileService.delete("test")
                .subscribe(Util.subscriber());
    }
}

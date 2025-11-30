package sec12;

import common.Util;
import sec12.assignment.SlackMember;
import sec12.assignment.SlackRoom;

public class Lec08Assignment {
    public static void main(String[] args) {
        var room = new SlackRoom("reactor");

        var sam = new SlackMember("sam");
        var jake = new SlackMember("jake");
        var mike = new SlackMember("mike");

        room.addMember(sam);
        room.addMember(jake);

        sam.says("Hi all....");

        Util.sleep(4);

        jake.says("Hey!..");
        sam.says("I simply wanted to say Hi....");

        Util.sleep(4);

        room.addMember(mike);
        mike.says("Hey guys....glad to be here....");
    }
}

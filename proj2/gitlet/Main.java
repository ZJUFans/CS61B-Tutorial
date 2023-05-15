package gitlet;

import java.io.IOException;

import static gitlet.Repository.*;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
//        args = new String[]{"log"};
        // TODO: what if args is empty?
        if (args.length == 0) {
            throw Utils.error("Must have at least one argument");
        }

        String firstArg = args[0];
        switch (firstArg) {
            case "init":
                // TODO: handle the `init` command

            case "add":
                // TODO: handle the `add [filename]` command

                // TODO: FILL THE REST IN
        }
    }

}

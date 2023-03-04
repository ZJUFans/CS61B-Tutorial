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
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                validateNumArgs("init", args, 1);
                try {
                    init();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                validateNumArgs("add", args, 2);
                try {
                    add(args[1]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            // TODO: FILL THE REST IN
            case "commit":
                validateNumArgs("commit", args,2);
                try {
                    commit(args[1]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "rm":
                validateNumArgs("rm", args, 2);
                try {
                    rm(args[1]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "log":
                validateNumArgs("log", args, 1);
                log();
                break;
            case "checkout":
                if (args.length == 2) {
                    
                } else if (args.length == 3) {
                    if (!args[1].equals("--")) {
                        throw Utils.error("Invalid number of arguments for: checkout");
                    }
                    try {
                        checkoutFile(args[2]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (args.length == 4) {
                    if (!args[2].equals("--")) {
                        throw Utils.error("Invalid number of arguments for: checkout");
                    }
                    try {
                        checkoutSpecificFile(args[1], args[3]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    throw Utils.error("Invalid number of arguments for: checkout");
                }
                break;
            case "global-log":
                validateNumArgs("global-log", args, 1);
                globalLog();
                break;
            case "find":
                validateNumArgs("find", args, 2);
                find(args[1]);
                break;
            case "status":
                validateNumArgs("status", args, 1);
                status();
                break;
            default:
                throw Utils.error("Unknown command: ", args[0]);
        }
    }

    private static void validateNumArgs(String cmd, String[] args, int i) {
        if (args.length != i) {
            throw Utils.error("Invalid number of arguments for: ", cmd);
        }
    }
}

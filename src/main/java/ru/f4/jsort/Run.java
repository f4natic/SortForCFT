package ru.f4.jsort;

import ru.f4.jsort.controllers.FileController;
import ru.f4.jsort.model.InputData;

public class Run {
    private InputData inputData;
    private String[] args;

    public Run(String[] args) {
        this.args = args;
        initCondition();
        new FileController(inputData).run();
    }

    public static void main(String[] args) {
        new Run(args);
    }

    private void initCondition() {
        String order = null;
        String type = null;
        String out = null;
        String[] input = null;

        if(args.length == 0 || args.length < 3 || (args.length == 3 && (args[0].equals("-a") || args[0].equals("-d")))) {
            System.out.println("Wrong conditions or nothing sort!");
            System.exit(0);
        }

        if(args[0].equals("-i")) {
            order = "ascending";
            type = "integer";
            out = args[1];
            input = new String[args.length - 2];
            System.arraycopy(args, 2, input, 0 , input.length);
        }

        if(args[0].equals("-s")) {
            order = "ascending";
            type = "string";
            out = args[1];
            input = new String[args.length - 2];
            System.arraycopy(args, 2, input, 0 , input.length);
        }

        if(args[0].equals("-a")) {
            order = "ascending";
            if(args[1].equals("-i")) {
                type = "integer";
            }else {
                type = "string";
            }
            out = args[2];
            input = new String[args.length - 3];
            System.arraycopy(args, 3, input, 0 , input.length);
        }

        if(args[0].equals("-d")) {
            order = "descending";
            if(args[1].equals("-i")) {
                type = "integer";
            }else {
                type = "string";
            }
            out = args[2];
            input = new String[args.length - 3];
            System.arraycopy(args, 3, input, 0 , input.length);
        }
        inputData = new InputData(order, type, out, input);
    }
}

package org.example;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    private double width, height, cReal, cImaginary;
    private String path;

    public static void main(String[] args) {
        Main main = new Main();
        main.getParams(args);
        JuliaFractal juliaFractal = new JuliaFractal(main.width, main.height, main.cReal, main.cImaginary, main.path);
        try {
            juliaFractal.calcJuliaFractal();
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void getParams(@NotNull String[] args) {
        for (int i=0; i<args.length;){
            switch (args[i]) {
                case "-d" -> {
                    width = Double.parseDouble(args[i + 1].split(";")[0]);
                    height = Double.parseDouble(args[i + 1].split(";")[1]);
                    i = i + 2;
                }
                case "-c" -> {
                    cReal = Double.parseDouble(args[i + 1].split(";")[0]);
                    cImaginary = Double.parseDouble(args[i + 1].split(";")[1]);
                    i = i + 2;
                }
                case "-o" -> {
                    path = args[i + 1];
                    i = i + 2;
                }
                default -> i++;
            }
        }
    }
}
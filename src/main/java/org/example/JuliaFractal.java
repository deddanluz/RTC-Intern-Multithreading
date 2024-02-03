package org.example;

import me.tongfei.progressbar.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class JuliaFractal {
    private final ComplexNumber constant;
    private ImageJulia imageJulia;
    private final String path;

    public JuliaFractal(double width, double height, double cReal, double cImaginary, String path){
        try {
            imageJulia = new ImageJulia(width, height);
        }catch (IllegalArgumentException e){
            imageJulia = new ImageJulia();
        }
        constant = new ComplexNumber(cReal, cImaginary);
        this.path=path;
    }

    public void calcJuliaFractal() throws IOException, ExecutionException, InterruptedException {
        /*пул потоков - по количеству потоков процессора*/
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        /*время начала выполнения задач*/
        long startTime = System.nanoTime();
        for (int x = 0; x < ImageJulia.getWIDTH(); x++) {
            executor.execute(new ThreadJulia(x, constant, imageJulia));
        }
        /*задачи закончились, больше не принимаем*/
        executor.shutdown();
        /*ожидаем, пока все запущенные задачи выполнятся*/
        /*иначе артефакты*/
        try (ProgressBar pb = new ProgressBar("Ожидается завершение выполнения задач", executor.getTaskCount())) {
            while(!executor.isTerminated()){
                pb.stepTo(executor.getCompletedTaskCount());
            }
        }
        /*время окончания выполнения задач*/
        long endTime = System.nanoTime();
        System.out.println("Время вычисления: "+(endTime - startTime)*0.000000001+" секунды");
        /*пишем из буфера в файл*/
        try {
            imageJulia.write(new File(path));
        }catch (NullPointerException e){
            imageJulia.write(new File("Julia.png"));
        }
        /*учитываем время записи в файл*/
        endTime = System.nanoTime();
        System.out.println("Время вычисления+записи: "+(endTime - startTime)*0.000000001+" секунды");
    }
}

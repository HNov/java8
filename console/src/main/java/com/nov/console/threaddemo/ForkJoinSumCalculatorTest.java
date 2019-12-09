package com.nov.console.threaddemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculatorTest {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(0, 100000000).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long now = System.currentTimeMillis();
        System.out.println(new ForkJoinPool().invoke(task));
        System.out.println("耗时:" + (System.currentTimeMillis() - now));
    }

}


class ForkJoinSumCalculator extends RecursiveTask<Long> {
    /**
     *
     */
    private static final long serialVersionUID = -4008111329500774405L;

    private final long[] numbers;
    private final int start;
    private final int end;

    private static final long LIMIT = 10000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= LIMIT) {
            return computeSum();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);

        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();

        return rightResult + leftResult;
    }

    private Long computeSum() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];

        }
        return sum;
    }

}
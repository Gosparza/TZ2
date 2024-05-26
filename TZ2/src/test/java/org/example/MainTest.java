package org.example;

import org.junit.jupiter.api.*;


import java.io.File;
import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;

public class MainTest{

    @Tag("WORK")

    @Nested
    class CorrectWorkTests{

        ArrayList<BigInteger> v;

        @BeforeEach
        void makeStartList(){
            v = new ArrayList<>();
            v.add(new BigInteger("1"));
            v.add(new BigInteger("2"));
            v.add(new BigInteger("3"));
            v.add(new BigInteger("4"));
        }

        @Tag("MIN")
        @Test
        void testMinWork(){
            Assertions.assertEquals(new BigInteger("1"), Main.min(v), "min is not correct (test 1)");

            for(int i = 0; i < v.size(); i++){
                int k = (i + 1) * (int) Math.pow(-1, i);
                v.set(i, BigInteger.valueOf(k));
            }

            Assertions.assertEquals(new BigInteger("-4"), Main.min(v), "min is not correct (test 2)");

            System.out.println("min is correct");
        }

        @Tag("MAX")
        @Test
        void testMaxWork(){
            Assertions.assertEquals(new BigInteger("4"), Main.max(v), "max is not correct (test 1)");

            for(int i = 0; i < v.size(); i++){
                int k = (i + 1) * (-1);
                v.set(i, BigInteger.valueOf(k));
            }

            Assertions.assertEquals(new BigInteger("-1"), Main.max(v), "max is not correct (test 2)");

            System.out.println("max is correct");
        }

        @Tag("SUM")
        @Test
        void testSumWork(){
            Assertions.assertEquals(new BigInteger("10"), Main.sum(v), "sum is not correct (test 1)");

            for(int i = 0; i < v.size(); i++){
                int k = (int)  Math.pow((-1), (i + 1));
                v.set(i, BigInteger.valueOf(k));
            }

            Assertions.assertEquals(new BigInteger("0"), Main.sum(v), "sum is not correct (test 2)");

            System.out.println("sum is correct");
        }

        @Tag("MULT")
        @Test
        void testMultWork(){
            Assertions.assertEquals(new BigInteger("24"), Main.mult(v), "mult is not correct (test 1)");

            for(int i = 0; i < v.size(); i++){
                int k = (int)  Math.pow(i, 2);
                v.set(i, BigInteger.valueOf(k));
            }

            Assertions.assertEquals(new BigInteger("0"), Main.mult(v), "mult is not correct (test 2)");

            System.out.println("mult is correct");
        }
    }




    @Tag("TIME") // Это часть не совсем тесты, но их было удобно представить как тесты

    @Nested
    class TimeWorkTests{

        ArrayList<BigInteger> v;
        BigInteger k;
        long t1;
        long t2;
        int i;

        @BeforeEach
        void makeStartValues(){
            v = new ArrayList<>();
            i = 1;
        }

        @Tag("MIN_TIME")
        @Test
        void testMinTime() {
            while(i <= 1000000){
                while(v.size() <= i){
                    v.add(BigInteger.valueOf(i));
                }

                t1 = System.nanoTime();
                k = Main.min(v);
                t2 = System.nanoTime();

                System.out.println("Time of min with " + i + " objects: " + (t2 - t1) + "ns");

                i *= 10;
            }
        }

        @Tag("MAX_TIME")
        @Test
        void testMaxTime() {
            while(i <= 1000000){
                while(v.size() <= i){
                    v.add(BigInteger.valueOf(i));
                }

                t1 = System.nanoTime();
                k = Main.max(v);
                t2 = System.nanoTime();

                System.out.println("Time of max with " + i + " objects: " + (t2 - t1) + "ns");

                i *= 10;
            }
        }

        @Tag("SUM_TIME")
        @Test
        void testSumTime() {
            while(i <= 1000000){
                while(v.size() <= i){
                    v.add(BigInteger.valueOf(i));
                }

                t1 = System.nanoTime();
                k = Main.sum(v);
                t2 = System.nanoTime();

                System.out.println("Time of sum with " + i + " objects: " + (t2 - t1) + "ns");

                i *= 10;
            }
        }

        @Tag("MULT_TIME")
        @Test
        void testMultTime() {
            while(i <= 1000000){
                while(v.size() <= i){
                    v.add(BigInteger.valueOf(i % 10));
                }

                t1 = System.nanoTime();
                k = Main.mult(v);
                t2 = System.nanoTime();

                System.out.println("Time of mult with " + i + " objects (mod 10) : " + (t2 - t1) + "ns");

                i *= 10;
            }

            // Следующая часть кода работает долговато в силу большого количества вычислений))
            for(i = 1; i < 5; i++){
                for(int j = 0; j < 1000000; j++){
                    v.set(j, BigInteger.valueOf(i % (10 + i)));
                }

                t1 = System.nanoTime();
                k = Main.mult(v);
                t2 = System.nanoTime();

                System.out.println("Time of mult with " + 1000000 + " objects (mod " + (10 + i) + ") : " + (t2 - t1) + "ns");
            }
        }

    }

    @Tag("ADD")

    @Nested
    class AddTests{

        ArrayList<BigInteger> v;

        @Tag("EXC")
        @Test
        void testException(){
            Assertions.assertThrows(Exception.class, () -> {
                v = Main.read_file("");
            });

            System.out.println("It trows exception. That is good.");
        }

        @Tag("TL")
        @Test
        void testTimeLim() throws Exception{
            String path = new File("").getAbsolutePath();
            path += "/src/test/java/org/example/1.txt";
            v = Main.read_file(path);

            Assertions.assertTimeout(Duration.ofNanos(1000000), () -> {
                BigInteger m = Main.min(v);
            });

            System.out.println("It work in normal time.");
        }

        //Следующий метод использовался для создания графика, но закомментирован за ненадобностью в основных тестах
//        @Tag("GRAPH")
//        @Test
//        void testgraph(){
//            v = new ArrayList<>();
//            v.add(new BigInteger("1"));
//            BigInteger k;
//            long t1;
//            long t2;

//            for(int i = 0; i <= 1000000; i+=10000){
//                while(v.size() <= i){
//                    v.add(BigInteger.valueOf(i + 1));
//                }

//                t1 = System.nanoTime();
//                k = Main.min(v);
//                t2 = System.nanoTime();

//                System.out.println(i);
//                System.out.println(t2 - t1);
//            }
//        }
    }
}

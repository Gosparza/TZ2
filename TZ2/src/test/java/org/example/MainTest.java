package org.example;

import org.junit.jupiter.api.*;


import java.io.File;
import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;

public class MainTest{

    @Tag("WORK")
    //Класс тестов проверки корректности работы вычисляющих функций
    @Nested
    class CorrectWorkTests{

        ArrayList<BigInteger> v;
        //Начальная инициализация массива для всех тестов класса
        @BeforeEach
        void makeStartList(){
            v = new ArrayList<>();
            v.add(new BigInteger("1"));
            v.add(new BigInteger("2"));
            v.add(new BigInteger("3"));
            v.add(new BigInteger("4"));
        }

        //Проверка работы функции нахождения минимума
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

        //Проверка работы функции нахождения максимума
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

        //Проверка работы функции нахождения суммы
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

        //Проверка работы функции нахождения произведения
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

    

    @Tag("ADD")
    //Дополнительные тетсты
    @Nested
    //Класс дополнительных тестов
    class AddTests{

        //Объявление массива
        ArrayList<BigInteger> v;

        //Тест на выброс исключения при отсутствии файла
        @Tag("EXC")
        @Test
        void testException(){
            Assertions.assertThrows(Exception.class, () -> {
                v = Main.read_file("");
            });

            System.out.println("It trows exception. That is good.");
        }

        //Тест на ограничение времени выполнения
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
    }
}

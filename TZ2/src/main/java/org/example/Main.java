package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

public class Main
{
    //Функция вывода результатов операций с числами из введенного файла
    public static void main( String[] args ) throws Exception
    {
        System.out.println("Укажите путь до файла: ");
        Scanner input = new Scanner(System.in);
        String path = input.nextLine();
        input.close();

        ArrayList<BigInteger> vect = Main.read_file(path);

        System.out.println("min: " + Main.min(vect));
        System.out.println("max: " + Main.max(vect));
        System.out.println("sum: " + Main.sum(vect));
        System.out.println("mult: " + Main.mult(vect));
    }

    //Функция чтения чисел из файла и запись их в массив
    static ArrayList<BigInteger> read_file(String path) throws Exception{
        BufferedReader f_scan = new BufferedReader(new FileReader(path));
        String i = f_scan.readLine();
        ArrayList<BigInteger> vect = new ArrayList<>();
        String s = "";

        for(String j : i.split(" ")){
            vect.add(new BigInteger(j));
        }
        
        f_scan.close();
        return vect;
    }

    //Нахождение минимума массива
    static BigInteger min(ArrayList<BigInteger> vect){
        BigInteger m = vect.get(0);
        for(BigInteger i: vect){
            if(i.compareTo(m) == -1){
                m = i;
            }
        }
        return m;
    }

    //Нахождение максимума массива
    static BigInteger max(ArrayList<BigInteger> vect){
        BigInteger m = vect.get(0);
        for(BigInteger i: vect){
            if(i.compareTo(m) == 1){
                m = i;
            }
        }
        return m;
    }

    //Нахождение суммы чисел массива
    static BigInteger sum(ArrayList<BigInteger> vect){
        BigInteger s = BigInteger.ZERO;
        for(BigInteger i: vect) {
            s = s.add(i);
        }
        return s;
    }

    //Нахождение произведения чисел массива
    static BigInteger mult(ArrayList<BigInteger> vect){
        BigInteger s = BigInteger.ONE;
        for(BigInteger i: vect) {
            s = s.multiply(i);
        }
        return s;
    }

    static void timeMeasurement(){
        ArrayList<BigInteger> v = new ArrayList<>();
        BigInteger k;
        long t1;
        long t2;
        int i = 1;
        while(i <= 1000000) {
            while (v.size() <= i) {
                v.add(BigInteger.valueOf(i));
            }

            t1 = System.nanoTime();
            k = Main.min(v);
            t2 = System.nanoTime();

            System.out.println("Time of min with " + i + " objects: " + (t2 - t1) + "ns");

            i *= 10;
        }
        v = new ArrayList<>();
        i = 1;
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
        v = new ArrayList<>();
        i = 1;
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
        v = new ArrayList<>();
        i = 1;
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

    static void graph(){
        ArrayList<BigInteger> v = new ArrayList<>();
        v.add(new BigInteger("1"));
        BigInteger k;
        long t1;
        long t2;

        for(int i = 0; i <= 1000000; i+=10000){
            while(v.size() <= i){
                v.add(BigInteger.valueOf(i + 1));
            }

            t1 = System.nanoTime();
            k = Main.min(v);
            t2 = System.nanoTime();

            System.out.println(i);
            System.out.println(t2 - t1);
        }
    }
}

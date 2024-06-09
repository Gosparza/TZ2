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
        int i = f_scan.read();
        ArrayList<BigInteger> vect = new ArrayList<>();
        String s = "";
        while (i != -1) {
            char c = (char) i;
            if(Character.isDigit(c) c == '-') {
                s = s + c;
            }else{
                vect.add(new BigInteger(s));
                s = "";
            }
            i = f_scan.read();
        }
        if(s != ""){
            vect.add(new BigInteger(s));
            s = "";
        }

        f_scan.close();
        return vect;
}

    //Нахождение минимума массива
    static BigInteger min(ArrayList<BigInteger> vect){
        BigInteger m = BigInteger.ZERO;
        int k = 0;
        for(BigInteger i: vect){
            if(k == 0){
                m = i;
                k = 1;
            }
            m = m.min(i);
        }
        return m;
    }

    //Нахождение максимума массива
    static BigInteger max(ArrayList<BigInteger> vect){
        BigInteger m = BigInteger.ZERO;
        int k = 0;
        for(BigInteger i: vect){
            if(k == 0){
                m = i;
                k = 1;
            }
            m = m.max(i);
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
}

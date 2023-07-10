/**
 * 1. Написать функцию, создающую резервную копию всех файлов
 * в директории (без поддиректорий) во вновь созданную папку ./backup
 * <p>
 * 2. Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3], и представляют собой, например,
 * состояния ячеек поля для игры в крестики-нолики, где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле
 * с ноликом, 3 – резервное значение. Такое предположение позволит хранить в одном числе типа int всё поле 3х3.
 * Записать в файл 9 значений так, чтобы они заняли три байта.
 */
package Main;

import Classes.Cipher;
import Classes.FileCopy;

import java.io.*;
import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {

        //Решение 1 задачи

        try {
            FileCopy.makeBackUp("./src/main/java");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //Решение 2 задачи

        //Создаем поле

        byte[][] field = {{0, 1, 2}, {3, 2, 1}, {0, 1, 3}};

        // Шифруем и записываем в файл

        try (FileOutputStream fos = new FileOutputStream("./src/main/java/key.txt")) {
            byte[] buffer = Cipher.encrypt(field);
            fos.write(buffer, 0, buffer.length);
            System.out.println("Файл записан и имеет длину " + buffer.length + " байта");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // Читаем с файла ключ

        byte[] keyForDecrypted = new byte[3];
        try (FileInputStream fin = new FileInputStream("./src/main/java/key.txt")) {
            int i;
            int index = 0;
            while ((i = fin.read()) != -1) {
                if (i != 0) {
                    keyForDecrypted[index] = (byte) i;
                    index++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        //Вывод дешифрованной записи из файла
        System.out.println("Дешифрованное поле из файла");
        System.out.println(Arrays.deepToString(Cipher.decrypt(keyForDecrypted)));
    }
}
/*
Есть входной файл с набором слов, написанных через пробел
Необходимо:
+ Прочитать слова из файла.
Отсортировать в алфавитном порядке.
Посчитать сколько раз каждое слово встречается в файле.
Вывести статистику на консоль
Найти слово с максимальным количеством повторений. Вывести на консоль это слово и сколько раз оно встречается в файле
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] text = new String[0];
        try {
            String str = null;
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));
            while ((str = br.readLine()) != null) {
                //получаем новые слова
                String[] newWords = str.split(" ");
                //создаем расширенный массив
                String[] result = new String[text.length + newWords.length];
                //копируем элементы в массив
                System.arraycopy(text, 0, result, 0, text.length);
                System.arraycopy(newWords, 0, result, text.length, newWords.length);
                //присваиваем результирующий массив текущему
                text = result;
            }
            br.close();
        } catch (Exception exc) {
            System.out.println("IO error!" + exc);
        }
        Arrays.sort(text);
        for (int i = 0; i< text.length; i++){
            System.out.println(text[i]);
        }
        System.out.println("\n");

        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String word : text) {
            if (!wordCount.containsKey(word)) {
                wordCount.put(word, 0);
            }
            wordCount.put(word, wordCount.get(word) + 1);
        }
        for (String word : wordCount.keySet()) {
            System.out.println(word + " " + wordCount.get(word));
        }
        System.out.println("\n");

        String maxWord = "", word = "";
        int maxCount = 0, count = 1;
        for (String s : text) {
            if (s.equals(word)) {
                count++;
            } else {
                if (count > maxCount) {
                    maxCount = count;
                    maxWord = word;
                }
                word = s;
                count = 1;
            }
        }
        if (count > maxCount) {
            maxCount = count;
            maxWord = word;
        }
        System.out.println("Слово с максимальным количеством повторений " + "'" + maxWord + "'" + " встречается " + maxCount + " раз");
    }
}
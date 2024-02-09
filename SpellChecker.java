
public class SpellChecker {


    public static void main(String[] args) {
        String word = args[0];
        int threshold = Integer.parseInt(args[1]);
        String[] dictionary = readDictionary("dictionary.txt");
        String correction = spellChecker(word, threshold, dictionary);
        System.out.println(correction);
    }

    public static String tail(String str) {
        return null;
    }

    public static int levenshtein(String word1, String word2) {
        // Convert the words to lowercase for case-insensitivity
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        int m = word1.length();
        int n = word2.length();

        // Base cases
        if (m == 0) {
            return n;
        }

        if (n == 0) {
            return m;
        }

        // If the last characters are the same, no operation is needed
        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            return levenshtein(word1.substring(0, m - 1), word2.substring(0, n - 1));
        }

        // Calculate the minimum of insert, delete, and replace operations
        int insert = levenshtein(word1, word2.substring(0, n - 1));
        int delete = levenshtein(word1.substring(0, m - 1), word2);
        int replace = levenshtein(word1.substring(0, m - 1), word2.substring(0, n - 1));

        return 1 + Math.min(Math.min(insert, delete), replace);
    }


    public static String[] readDictionary(String fileName) {
        String[] dictionary = new String[3000];
        int count = 0;

        In in = new In(fileName);
        while (!in.isEmpty()) {
            dictionary[count] = in.readString();
            count++;
        }

        // Your code here

        return dictionary;
    }

    public static String spellChecker(String word, int threshold, String[] dictionary) {
        String closestWord = word;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < dictionary.length; i++) {
            int distance = levenshtein(word, dictionary[i]);

            if (distance < minDistance) {
                minDistance = distance;
                closestWord = dictionary[i];
            }
        }

        if (minDistance > threshold) {
            return word;
        } else {
            return closestWord;
        }

    }
}






public class HashTagTokenizer {

    public static void main(String[] args) {

        String hashTag = args[0];
        String[] dictionary = readDictionary("dictionary.txt");
        breakHashTag(hashTag, dictionary);
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

    public static boolean existInDictionary(String word, String[] dictionary) {
        return existInDictionary(word, dictionary, 0);
    }

    public static boolean existInDictionary(String word, String[] dictionary, int index) {
        { // Base case: If the index reaches the end of the dictionary, the word is not found.
            if (index == dictionary.length) {
                return false;
            }

            // Check if the current word at the given index is equal to the target word.
            if (dictionary[index].equals(word)) {
                return true;
            }

            // Recursively search for the word in the rest of the dictionary with an incremented index.
            return existInDictionary(word, dictionary, index + 1);
        }

    }

    public static void breakHashTag(String hashtag, String[] dictionary) {
        // Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
        hashtag = hashtag.toLowerCase();

        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
            String substring = hashtag.substring(0, i);

            if (existInDictionary(substring, dictionary)) {
                System.out.println(substring);
                // Recursively process the remaining part of the hashtag
                breakHashTag(hashtag.substring(i), dictionary);
            }
            // Continue the loop for the next substring
        }
    }
}


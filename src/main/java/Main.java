import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The main class of this Lab.
 *
 * @author rjnagel
 *
 */
public class Main {

    /**
     * Read the contents at a URL, and return those contents.
     *
     * @param url the URL to fetch
     * @return the contents at that URL, or "" if an error occurs
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    /**
     * The main method that is the entry point to this program.
     *
     * @param args the command-line arguments
     */
    public static void main(final String[] args) {
        String s = urlToString("http://erdani.com/tdpl/hamlet.txt");
        Map<String, Integer> wordFrequencies = new HashMap<String, Integer>();
        int lastIndex = 0;
        while (lastIndex >= 0 && lastIndex < s.length()) {
            int nextIndex = s.indexOf(' ', lastIndex);
            if (nextIndex < 0) {
                nextIndex = s.length() - 1;
            }
            String word = s.substring(lastIndex, nextIndex);
            if (!wordFrequencies.containsKey(word)) {
                wordFrequencies.put(word, 1);
            } else {
                wordFrequencies.put(word, wordFrequencies.get(word) + 1);
            }
            // Add 1 so that the loop does not repeatedly find the first
            // ' ' in the file.
            lastIndex = nextIndex + 1;
        }
        for (Map.Entry<String, Integer> pair : wordFrequencies.entrySet()) {
            System.out.println(pair.getValue() + " - " + pair.getKey());
        }
    }

}

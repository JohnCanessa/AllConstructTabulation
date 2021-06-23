import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * 
 */
public class AllConstructTabulation {


    /**
     * Write a function `allConstruct(target, wordBank)` that accepts a 
     * target string and ann array of strings.
     * 
     * The function should return a 2D array containing all of the ways
     * that the `target` can be constructed by concatenating elements of
     * the `wordBank` array.
     * 
     * You may reuse elements of the `wordBank` as many times as needed.
     * 
     * m = target.length
     * n = wordBank.length
     * 
     * Time: O(n^m)  Space: O(n^m)
     */
    static String[][] allConstruct(String target, String[] wordBank) {

        // **** sanity check(s) [[]]****
        if (target.length() == 0) {
            String[][] ans = new String[1][1];
            ans[0] = new String[] {""};
            return ans;
        }

        // **** create and initialize table ****
        ArrayList<ArrayList<ArrayList<String>>> table = new ArrayList<ArrayList<ArrayList<String>>>();
        for (int i = 0; i < target.length() + 1; i++)
            table.add(i, new ArrayList<ArrayList<String>>());
        ArrayList<ArrayList<String>> lol = table.get(0);
        lol.add(new ArrayList<String>());

        // ???? ????
        System.out.println("<<<  table: " + table.toString() + " size: " + table.size());

        // **** iterate through the table ****
        for (int i = 0; i < table.size(); i++) {

            // **** get to the current list of lists ****
            lol = table.get(i);

            // **** if blank entry (skip) ****
            if (lol.size() == 0)
                continue;

            // **** iterate through the word bank ****
            for (int j = 0; j < wordBank.length; j++) {

                // **** for ease of use ****
                String word = wordBank[j];

                // **** generate index ****
                int ndx = i + word.length();

                // **** if we can NOT extract prefix from target (skip) ****
                if (ndx > target.length())
                    continue;

                // **** extract prefix from the target ****
                String prefix = target.substring(i, ndx);

                // **** if word and prefix do NOT match (skip) ****
                if (!word.equals(prefix))
                    continue;

                // **** source list of lists ****
                ArrayList<ArrayList<String>> src = table.get(i);

                // **** destination list of lists ****
                ArrayList<ArrayList<String>> dst = table.get(ndx);

                // **** copy source list(s) to this table entry ****
                for (int k = 0; k < src.size(); k++) {

                    // **** source list to copy ****
                    ArrayList<String> srcLst = src.get(k);

                    // **** destination list ****
                    ArrayList<String> dstLst = new ArrayList<String>();

                    // **** add source to destination list ****
                    dstLst.addAll(srcLst);

                    // **** append word to destination list ****
                    dstLst.add(word);

                    // **** add destination list to destinaltion list of lists ****
                    dst.add(dstLst);
                }

                // ???? ????
                System.out.println("<<<  table: " + table.toString() + " size: " + table.size());
            }
        }

        // **** get last list of lists in table ****
        ArrayList<ArrayList<String>> ansLst = table.get(target.length());

        // **** 2D array to hold answer ****
        String[][] ans = new String[ansLst.size()][];

        // **** traverse the list of lists ****
        for (int i = 0; i < ansLst.size(); i++) {

            // **** get this array list ****
            ArrayList<String> al = ansLst.get(i);

            // **** generate array from array list ****
            String[] arr = al.toArray(String[]::new);

            // **** insert array into 2D array ****
            ans[i] = arr;
        }

        // **** return 2D array ****
        return ans;
    }


    /**
     * Test scaffold. 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read target string ****
        String target = br.readLine().trim();

        // **** read word bank array ****
        String[] wordBank = br.readLine().trim().split(",");

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< target ==>" + target + "<== length: " + target.length());
        System.out.println("main <<< wordBank: " + Arrays.toString(wordBank));

        // **** call function of interest ****
        String[][] ans = allConstruct(target, wordBank);

        // **** display 2D array ****
        if (ans == null)
            System.out.println("main <<< ans: []");
        else {
            System.out.println("main <<< ans:\n[");
            for (int i = 0; i < ans.length; i++)
                System.out.println(Arrays.toString(ans[i]));
            System.out.println("]");
        }
    }
}
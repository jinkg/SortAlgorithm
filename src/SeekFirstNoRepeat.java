/**
 * YaLin
 * 2016/12/15.
 */
public class SeekFirstNoRepeat {
    public static void main(String[] args) {
        String str = "abbecadedhifvbdnkwixzhvfdlkwer9xvlthrtc";

        char first = firstNoRepeat(str);
        System.out.println("The first no repeat char is: " + first);
    }

    /**
     * search the first char in chars, which is not repeat
     *
     * @param chars chars for search
     * @return the result, or -1 with no find
     */
    public static char firstNoRepeat(String chars) {
        int[] counts = new int[128];

        int i = 1;
        for (char c : chars.toCharArray()) {
            if (counts[c] > 0) {
                counts[c] = -1;
            } else if (counts[c] == 0) {
                counts[c] = i;
            }
            i++;
        }

        int minIndex = -1;
        for (int j = 0; j < counts.length; j++) {
            if (counts[j] > 0 &&
                    (minIndex < 0 || counts[minIndex] <= 0 || counts[j] < counts[minIndex])) {
                minIndex = j;
            }
        }
        return (char) minIndex;
    }
}

package util;

public class AlgorithmUtil {

    /**
     *
     * @param message 输入（要查找）的字符串
     * @param p 原字符串
     * @return
     */
    public static boolean KMP(String message, String p) {
        int m = message.length();
        int[] ne = new int[m];
        ne[0] = -1;
        char[] k = message.toCharArray();
        for (int i = 1, j = -1; i < m; i++) { //求next数组
            while (j >= 0 && k[j + 1] != k[i]) j = ne[j];
            if (k[j + 1] == k[i]) j++;
            ne[i] = j;
        }
        int n = p.length();
        char[] s = new char[n + 1];
        for (int i = 0; i < n; i++) {
            s[i] = p.charAt(i);
        }
        for (int i = 0, j = -1; i < n; i++) {
            while (j != -1 && s[i] != k[j + 1]) j = ne[j];
            if (s[i] == k[j + 1]) j++;
            if (j == m - 1) {
                return true;
            }
        }
        return false;
    }
}
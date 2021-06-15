package util;

import pojo.FriendRequest;
import pojo.PairingRequest;
import pojo.User;

import java.util.List;
import java.util.PriorityQueue;

public class AlgorithmUtil {

    /**
     * @param message 输入（要查找）的字符串
     * @param p       原字符串
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

    //快速排序

    public static void quickSort(List<User> userList) {
        User[] users = userList.toArray(new User[0]);
        quickSort(users);
        userList.clear();
        for (User i : users) {
            userList.add(i);
        }
    }

    private static void quickSort(Comparable[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    // arr[l..r]排好序
    private static void quickSort(Comparable[] arr, int L, int R) {
        if (L < R) {
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);        // < 区
            quickSort(arr, p[1] + 1, R);        // > 区
        }
    }

    // 这是一个处理arr[l..r]的函数
    // 默认以arr[r]做划分
    // 返回等于区域(左边界，右边界), 所以返回一个长度为2的数组res, res[0] res[1]
    private static int[] partition(Comparable[] arr, int L, int R) {
        int less = L - 1;                        // <区右边界
        int more = R;                        // >区左边界
        while (L < more) {                    //  L表示当前数的位置
            if (arr[L].compareTo(arr[R]) < 0) {                // 当前数 < 划分值
                swap(arr, ++less, L++);
            } else if (arr[L].compareTo(arr[R]) > 0) {            // 当前数 > 划分值
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //归并排序

    public static void mergeSort(List<PairingRequest> pairingRequestList) {
        PairingRequest[] pairingRequests = pairingRequestList.toArray(new PairingRequest[0]);
        mergeSort(pairingRequests);
        pairingRequestList.clear();
        for (PairingRequest i : pairingRequests) {
            pairingRequestList.add(i);
        }
    }

    private static void mergeSort(Comparable[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(Comparable[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(Comparable[] arr, int L, int M, int R) {
        Comparable[] help = new Comparable[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1].compareTo(arr[p2]) <= 0 ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static int binarySearch(List<? extends Comparable> list, Comparable value) {  //二分查找
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int middle = (high + low) / 2;
            if (list.get(middle).compareTo(value) == 0) {
                return middle;
            } else if (list.get(middle).compareTo(value) < 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    public static int bisectionInsert(List<? extends Comparable> list, Comparable value) {   //二分查找最小下标
        int middle = 0;
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            middle = (high + low) / 2;
            if (list.get(middle).compareTo(value) < 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        if (middle == high) {
            return list.size();
        }
        return low;
    }

    //堆排序

    public static void heapSort(List<FriendRequest> friendRequestList) {
        FriendRequest[] friendRequests = friendRequestList.toArray(new FriendRequest[0]);
        heapSort(friendRequests);
        friendRequestList.clear();
        for (FriendRequest i : friendRequests) {
            friendRequestList.add(i);
        }
    }

    private static void heapSort(Comparable[] arr) {
        PriorityQueue<Comparable> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.offer(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.poll();
        }
    }
}
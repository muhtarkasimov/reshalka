package com.example.tesseract.service;

import net.sourceforge.tess4j.TesseractException;

import java.util.*;
import java.util.stream.Collectors;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Logic {

    public static void main(String[] args) throws TesseractException {
//        int[][] matrix = new int[][]{{1, 2, 3, 4}, {3, 1, 2, 4}};
//        System.out.println(matrix.length);
//        System.out.println(matrix[0].length);

        System.out.println(longestPalindrome("aacabdkacaa"));
    }


    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);

        ListNode node = head;
        int result = set.size();
        while (node.next != null) {
            if (set.contains(node.val) && set.contains(node.next.val)) result--;
            node = node.next;
        }

        return result;
    }

    public static String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            for (int j = 0; j < sb.length() - i - 1; j++) {
                StringBuilder subs = new StringBuilder(sb.substring(i, sb.length() - j));
                String x = subs.reverse().toString();
                if (x.equals(subs.reverse().toString())) {
                    return subs.toString();
                }
            }
        }
        return s.substring(0, 1);
    }


    public int[] rearrangeArray(int[] nums) {
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        for (int i = 0; i != nums.length; i++) {
            if (nums[i] > 0) positives.add(nums[i]);
            else negatives.add(nums[i]);
        }
        List<Integer> arr = new ArrayList<>();
        for (int i = 0, j = 1; i != positives.size(); i++, j++) {
            arr.add(positives.get(i));
            arr.add(negatives.get(i));
        }
        int[] arr2 = new int[arr.size()];
        for (int i = 0; i != arr.size(); ++i)
            arr2[i] = arr.get(i);
        return arr2;
    }

//    public static void zadacha() {
//        public ListNode swapPairs (ListNode head){
//            ListNode prevNode = new ListNode(0);
//            prevNode.next = head;
//            ListNode newHead = prevNode;
//            while (prevNode.next != null && prevNode.next.next != null) {
//                ListNode node1 = prevNode.next;
//                ListNode node2 = node1.next;
//                ListNode nextNode = node2.next;
//                prevNode.next = node2;
//                node2.next = node1;
//                node1.next = nextNode;
//                prevNode = node1;
//            }
//            return newHead.next;
//        }
//    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> lastArr = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> arr = new ArrayList<>();
            if (i < 3) {
                if (i == 1) {
                    arr.set(0, 1);
                } else if (i == 2) ;
            }
            arr.set(i, 1);
            arr.set(arr.size() - 1, 1);

            for (int j = 1; j < arr.size() - 2; j++) {
                arr.set(i, lastArr.get(i - 1) + lastArr.get(i));
            }
            lastArr = arr;
            lists.add(arr);
        }
        return lists;
    }

    public static boolean hasCycle1(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode list = head;
        while (list != null) {
            if (!map.containsKey(list)) {
                map.put(list, 1);
            } else {
                int counter = map.get(list);
                map.put(list, ++counter);
            }
            if (map.get(list) == 2)
                return true;
            list = list.next;
        }
        return false;

    }

//    public static List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> list = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 1 + i; j < nums.length - 1; j++) {
//                for (int k = 2 + i + j; k < nums.length - 2; k++) {
//                    if (i != j && j != k) {
//                        if (nums[i] + nums[j] + nums[k] == 0) {
//                            ArrayList<Integer> integers = new ArrayList<>();
//                            integers.
//                            list.add(new ArrayList<Integer>(){{add(nums[i]);add(nums[j]);add(nums[k])}});
//                        }
//                    }
//
//                }
//            }
//        }
//    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        int match = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] != match) return false;
        }
        return true;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m, j = 0; i < m + n && j < n; i++, j++) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
        for (int i : nums1) {
            System.out.print(i + ", ");
        }
    }

    public static int maxArea(int[] height) {
        for (int i = 0; i < height.length; i++) {
            System.out.print(height[i] + ", ");
        }
        System.out.println();
        int[] sorted = height.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < height.length; i++) {
            System.out.print(sorted[i] + ", ");
        }
        System.out.println();
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < sorted.length; i++) {
            if (height[i] == sorted[sorted.length - 1]) {
                index1 = i;
            }
        }
        for (int i = 0; i < sorted.length; i++) {
            if (height[i] == sorted[sorted.length - 2]) {
                index2 = i;
            }
        }

        System.out.println("index1 = " + index1);
        System.out.println("index2 = " + index2);
        int len = (index1 > index2) ? index1 - index2 : index2 - index1;
        return sorted[sorted.length - 2] * (len);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap();

        for (String s : strs) {
            char[] charr = s.toCharArray();
            Arrays.sort(charr);
            String sorted = String.copyValueOf(charr);
            if (map.get(sorted) != null) {
                map.get(sorted).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(sorted, list);
            }
        }
        return new ArrayList<>(map.values());
    }


    public static boolean hasCycle(ListNode head) {
        List<Integer> list = new ArrayList<>();
        return addValAndReturnBool(head, list);
    }

    static boolean addValAndReturnBool(ListNode node, List<Integer> list) {
        if (node == null) {
            return false;
        } else if (list.contains(node.val)) {
            return true;
        } else if (node.next == null) {
            return false;
        }
        list.add(node.val);
        return addValAndReturnBool(node.next, list);
    }

    public static int singleNumber(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println("list = " + list);
        System.out.println("nums = " + nums);
        for (int num : nums) {
            boolean b1 = list.remove((Object) num);
            boolean b2 = list.remove((Object) num);
            if (b1 ^ b2) {
                return num;
            }
        }
        return 0;
    }

    public static int[] plusOne(int[] d) {
        boolean plus = true;
        for (byte i = (byte) (d.length - 1); i >= 0; i--) {
            if (plus) {
                d[i]++;
                System.out.println(d[i]);
                if (d[i] > 9) {
                    plus = true;
                    d[i] = 0;
                    if (i == 1) {
                        System.out.println("here");
                        int[] arr = new int[d.length + 1];
                        arr[0] = 1;
                        System.arraycopy(d, 0, arr, 1, arr.length - 1);
                        return arr;
                    }
                } else {
                    plus = false;
                }
            }
        }
        return d;
    }

}

import java.util.ArrayList;
import java.util.TreeSet;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i : nums1) {
            treeSet.add(i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (treeSet.contains(i)) {
                treeSet.remove(i);
                list.add(i);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
public class Solution2 {
    /**
     * LeetCode中的第203
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null){
            if (prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = prev.next.next;
                delNode.next = null;
            }else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {
        int[] nums = {1 , 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode res = new Solution2().removeElements(head,6);
        System.out.println(res);
    }
}

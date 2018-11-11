public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {
        //递归的基本思路,1:先展示出递归问题最小的情况的结果
        if (head == null){
            return null;
        }
        //对后面的链表进行删除操作
        ListNode res =  removeElements(head.next,val);
        //如果当前节点需要被删除
        if (head.val == val){
            //返回下一个节点递归的结果
            return res;
        }else {
            //设置当前节点的下一个节点
            head.next = res;
            //返回当前将节点
            return head;
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode res = new Solution3().removeElements(head, 6);
        System.out.println(res);
    }
}

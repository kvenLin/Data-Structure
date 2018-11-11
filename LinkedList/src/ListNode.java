public class ListNode{
    int val;
    ListNode next;
    public ListNode(int x){
        val = x;
    }

    /**
     * 链表节点的构造函数
     * 使用arr为参数,创建一个链表
     * 当前的ListNode为链表头节点
     * @param arr
     */
    public ListNode(int[] arr){
        if (arr == null || arr.length == 0){
            throw new IllegalArgumentException("数组为空,不能生成链表");
        }
        ListNode cur = this;
        for (int i = 0; i < arr.length; i ++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null){
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}

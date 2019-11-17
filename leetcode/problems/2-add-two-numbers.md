- 单向链表进行遍历时，赋值给一个新的对象保存链表的指针，遍历操作完返回新对象
- 考虑极端情况
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode result = l3;
        int add = 0;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            ListNode l4 = new ListNode((a + b + add) % 10);
            add = (a + b + add) / 10;
            l3.next = l4;
            l3 = l3.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (add > 0) {
            l3.next = new ListNode(add % 10);
            l3 = l3.next;
        }
        return result.next;
    }
}
```
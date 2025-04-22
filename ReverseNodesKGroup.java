// In this approach, iterating over the linkedlist and wherever count mod k == 0, taking that group and passing to reverse function.
// This function will reverse the group and return the begin of next group. Begin is one node prior to group and end is one node after
// last node in group. We need begin and end because we also need to change the pointers of what was previous to group and what was
// after group.

// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Base case
        if (head == null || k == 0) {
            return head;
        }
        // Take the dummy node so that we can have begin for first group also
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // Begin will be dummy node for the first group
        ListNode begin = dummy;
        ListNode end = new ListNode();
        int count = 0;
        // Loop over LL
        while (head != null) {
            // Do a count++
            count++;
            // Whenever we find a group of size k
            if (count % k == 0) {
                // Take the end node as the node after the last node in group
                end = head.next;
                // Send this begin and end to the reverse function which will reverse the nodes
                // in group between begin and end, then it will return the begin of next group
                begin = reverse(begin, end);
                // Reset the head to begin, if 1->2->3 and head was at 3, after reverse 3-2-1,
                // we want head at 1
                head = begin;
            }
            // If not size k, just move
            head = head.next;
        }
        // dummy.next will have the head of reversed LL
        return dummy.next;
    }

    private ListNode reverse(ListNode begin, ListNode end) {
        // Here we store the first node of group in first
        ListNode first = begin.next;
        // Take standard pointers for the reverse a LL operation - prev,curr,fast
        ListNode prev = null;
        ListNode curr = begin.next;
        ListNode fast = curr.next;
        // Loop till fast has not reach end
        while (fast != end) {
            // Make the next pointer of curr to prev
            curr.next = prev;
            // Move ahead all pointers by one
            prev = curr;
            curr = fast;
            fast = fast.next;
        }
        // At last, the next pointer of last node will be remaining, so do that
        curr.next = prev;
        // If (-1)-1-2-3-4 and begin=-1 end=4 curr=3 first=1, after reverse we need
        // (-1)-3-2-1-4
        begin.next = curr;
        first.next = end;
        return first;
    }
}
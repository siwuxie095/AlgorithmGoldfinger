package com.siwuxie095.algorithm.chapter1st.example30th;

/**
 * @author Jiajing Li
 * @date 2021-09-10 21:10:09
 */
public class Main {

    /**
     * 双指针技巧总结
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 141.环形链表（简单）
     * 142.环形链表II（简单）
     * 167.两数之和 II - 输入有序数组（中等）
     * 344.反转字符串（简单）
     * 19.删除链表倒数第 N 个元素（中等）
     * 876. 链表的中间结点
     *
     * 这里把双指针技巧再分为两类，一类是「快慢指针」，一类是「左右指针」。前者解决主要解决链表中的问题，比如
     * 典型的判定链表中是否包含环；后者主要解决数组（或者字符串）中的问题，比如二分查找。
     *
     *
     *
     * 一、快慢指针的常见算法
     *
     * 快慢指针一般都初始化指向链表的头结点 head，前进时快指针 fast 在前，慢指针 slow 在后，巧妙解决一些链
     * 表中的问题。
     *
     *
     * 1、判定链表中是否含有环
     *
     * 这属于链表最基本的操作了，学习数据结构应该对这个算法思想都不陌生。
     *
     * 单链表的特点是每个节点只知道下一个节点，所以一个指针的话无法判断链表中是否含有环的。
     *
     * 如果链表中不含环，那么这个指针最终会遇到空指针 null 表示链表到头了，这还好说，可以判断该链表不含环：
     *
     * boolean hasCycle(ListNode head) {
     *     while (head != null)
     *         head = head.next;
     *     return false;
     * }
     *
     * 但是如果链表中含有环，那么这个指针就会陷入死循环，因为环形数组中没有 null 指针作为尾部节点。
     *
     * 经典解法就是用两个指针，一个跑得快，一个跑得慢。如果不含有环，跑得快的那个指针最终会遇到 null，说明链
     * 表不含环；如果含有环，快指针最终会超慢指针一圈，和慢指针相遇，说明链表含有环。
     *
     * 力扣第 141 题就是这个问题，解法代码如下：
     *
     * boolean hasCycle(ListNode head) {
     *     ListNode fast, slow;
     *     fast = slow = head;
     *     while (fast != null && fast.next != null) {
     *         fast = fast.next.next;
     *         slow = slow.next;
     *
     *         if (fast == slow) return true;
     *     }
     *     return false;
     * }
     *
     *
     * 2、已知链表中含有环，返回这个环的起始位置
     *
     * 这是力扣第 142 题，其实一点都不困难，有点类似脑筋急转弯，先直接看代码：
     *
     * ListNode detectCycle(ListNode head) {
     *     ListNode fast, slow;
     *     fast = slow = head;
     *     while (fast != null && fast.next != null) {
     *         fast = fast.next.next;
     *         slow = slow.next;
     *         if (fast == slow) break;
     *     }
     *     // 上面的代码类似 hasCycle 函数
     *     if (fast == null || fast.next == null) {
     *         // fast 遇到空指针说明没有环
     *         return null;
     *     }
     *
     *     slow = head;
     *     while (slow != fast) {
     *         fast = fast.next;
     *         slow = slow.next;
     *     }
     *     return slow;
     * }
     *
     * 可以看到，当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点
     * 位置就是环开始的位置。这是为什么呢？
     *
     * 第一次相遇时，假设慢指针 slow 走了 k 步，那么快指针 fast 一定走了 2k 步。
     *
     * fast 一定比 slow 多走了 k 步，这多走的 k 步其实就是 fast 指针在环里转圈圈，所以 k 的值就是环长度的
     * 「整数倍」。
     *
     * 设相遇点距环的起点的距离为 m，那么环的起点距头结点 head 的距离为 k - m，也就是说如果从 head 前进
     * k - m 步就能到达环起点。
     *
     * 巧的是，如果从相遇点继续前进 k - m 步，也恰好到达环起点。你甭管 fast 在环里到底转了几圈，反正走 k 步
     * 可以到相遇点，那走 k - m 步一定就是走到环起点了。
     *
     * 所以，只要我们把快慢指针中的任一个重新指向 head，然后两个指针同速前进，k - m 步后就会相遇，相遇之处
     * 就是环的起点了。
     *
     *
     * 3、寻找链表的中点
     *
     * 类似上面的思路，还可以让快指针一次前进两步，慢指针一次前进一步，当快指针到达链表尽头时，慢指针就处于
     * 链表的中间位置。
     *
     * 力扣第 876 题就是找链表中点的题目，解法代码如下：
     *
     * ListNode middleNode(ListNode head) {
     *     ListNode fast, slow;
     *     fast = slow = head;
     *     while (fast != null && fast.next != null) {
     *         fast = fast.next.next;
     *         slow = slow.next;
     *     }
     *     // slow 就在中间位置
     *     return slow;
     * }
     *
     * 当链表的长度是奇数时，slow 恰巧停在中点位置；如果长度是偶数，slow 最终的位置是中间偏右。
     *
     * 寻找链表中点的一个重要作用是对链表进行归并排序。
     *
     * 回想数组的归并排序：求中点索引递归地把数组二分，最后合并两个有序数组。对于链表，合并两个有序链表是很
     * 简单的，难点就在于二分。
     *
     * 但是现在你学会了找到链表的中点，就能实现链表的二分了。
     *
     *
     * 4、寻找链表的倒数第 n 个元素
     *
     * 这是力扣第 19 题「删除链表的倒数第 n 个元素」，先看下题目：
     *
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * 这里的思路还是使用快慢指针，让快指针先走 n 步，然后快慢指针开始同速前进。这样当快指针走到链表末尾
     * null 时，慢指针所在的位置就是倒数第 n 个链表节点（n 不会超过链表长度）。
     *
     * 解法比较简单，直接看代码吧：
     *
     * ListNode removeNthFromEnd(ListNode head, int n) {
     *     ListNode fast, slow;
     *     fast = slow = head;
     *     // 快指针先前进 n 步
     *     while (n-- > 0) {
     *         fast = fast.next;
     *     }
     *     if (fast == null) {
     *         // 如果此时快指针走到头了，
     *         // 说明倒数第 n 个节点就是第一个节点
     *         return head.next;
     *     }
     *     // 让慢指针和快指针同步向前
     *     while (fast != null && fast.next != null) {
     *         fast = fast.next;
     *         slow = slow.next;
     *     }
     *     // slow.next 就是倒数第 n 个节点，删除它
     *     slow.next = slow.next.next;
     *     return head;
     * }
     *
     *
     *
     * 二、左右指针的常用算法
     *
     * 左右指针在数组中实际是指两个索引值，一般初始化为 left = 0, right = nums.length - 1 。
     *
     *
     * 1、二分查找
     *
     * 这里只写最简单的二分算法，旨在突出它的双指针特性：
     *
     * int binarySearch(int[] nums, int target) {
     *     int left = 0;
     *     int right = nums.length - 1;
     *     while(left <= right) {
     *         int mid = (right + left) / 2;
     *         if(nums[mid] == target)
     *             return mid;
     *         else if (nums[mid] < target)
     *             left = mid + 1;
     *         else if (nums[mid] > target)
     *             right = mid - 1;
     *     }
     *     return -1;
     * }
     *
     *
     * 2、两数之和
     *
     * 直接看力扣第 167 题「两数之和 II」吧：
     *
     * 给定一个已按照 非递减顺序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数
     *  target 。
     *
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案
     * 数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     *
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     *
     *
     * 只要数组有序，就应该想到双指针技巧。这道题的解法有点类似二分查找，通过调节 left 和 right 可以调整
     * sum 的大小：
     *
     * int[] twoSum(int[] nums, int target) {
     *     int left = 0, right = nums.length - 1;
     *     while (left < right) {
     *         int sum = nums[left] + nums[right];
     *         if (sum == target) {
     *             // 题目要求的索引是从 1 开始的
     *             return new int[]{left + 1, right + 1};
     *         } else if (sum < target) {
     *             left++; // 让 sum 大一点
     *         } else if (sum > target) {
     *             right--; // 让 sum 小一点
     *         }
     *     }
     *     return new int[]{-1, -1};
     * }
     *
     *
     * 3、反转数组
     *
     * 一般编程语言都会提供 reverse 函数，其实非常简单，力扣第 344 题是类似的需求，让你反转一个 char[]
     * 类型的字符数组，直接看代码吧：
     *
     * void reverseString(char[] arr) {
     *     int left = 0;
     *     int right = arr.length - 1;
     *     while (left < right) {
     *         // 交换 arr[left] 和 arr[right]
     *         char temp = arr[left];
     *         arr[left] = arr[right];
     *         arr[right] = temp;
     *         left++; right--;
     *     }
     * }
     *
     *
     * 4、滑动窗口算法
     *
     * 这也许是双指针技巧的最高境界了，如果掌握了此算法，可以解决一大类子字符串匹配的问题，不过「滑动窗口」
     * 稍微比上述的这些算法复杂些。
     *
     * 不过这类算法是有框架模板的，而且之前「滑动窗口解题套路框架」就讲解了「滑动窗口」算法模板，帮大家秒杀
     * 几道子串匹配的问题，如果没有看过，建议去看看。
     */
    public static void main(String[] args) {

    }

}

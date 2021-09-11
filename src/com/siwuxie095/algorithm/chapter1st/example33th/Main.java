package com.siwuxie095.algorithm.chapter1st.example33th;

/**
 * @author Jiajing Li
 * @date 2021-09-11 21:48:49
 */
public class Main {

    /**
     * 原地修改数组
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 26.删除排序数组中的重复项（简单）
     * 83.删除排序链表中的重复元素（简单）
     * 27.移除元素（简单）
     * 283.移动零（简单）
     *
     * 对于数组来说，在尾部插入、删除元素是比较高效的，时间复杂度是 O(1)，但是如果在中间或者开头
     * 插入、删除元素，就会涉及数据的搬移，时间复杂度为 O(N)，效率较低。
     *
     * 所以就有一种技巧，把待删除元素交换到最后一个，然后再删除，就可以避免数据搬移。
     *
     * 下面来讲一讲如何在原地修改数组，避免数据的搬移。
     *
     *
     *
     * 有序数组/链表去重
     *
     * 先讲讲如何对一个有序数组去重，先看下力扣第 26 题：
     *
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     *
     *
     * 函数签名如下：
     *
     * int removeDuplicates(int[] nums);
     *
     * 显然，由于数组已经排序，所以重复的元素一定连在一起，找出它们并不难，但如果毎找到一个重复元素就立即删除
     * 它，就是在数组中间进行删除操作，整个时间复杂度是会达到 O(N^2)。
     *
     * 简单解释一下什么是原地修改：
     *
     * 如果不是原地修改的话，直接 new 一个 int[] 数组，把去重之后的元素放进这个新数组中，然后返回这个新数组
     * 即可。
     *
     * 但是原地删除，不允许 new 新数组，只能在原数组上操作，然后返回一个长度，这样就可以通过返回的长度和原始
     * 数组得到去重后的元素有哪些了。
     *
     * 这种需求在数组相关的算法题中时非常常见的，通用解法就是双指针技巧中的快慢指针技巧。
     *
     * 让慢指针 slow 走在后面，快指针 fast 走在前面探路，找到一个不重复的元素就告诉 slow 并让 slow 前进
     * 一步。这样当 fast 指针遍历完整个数组 nums 后，nums[0..slow] 就是不重复元素。
     *
     * int removeDuplicates(int[] nums) {
     *     if (nums.length == 0) {
     *         return 0;
     *     }
     *     int slow = 0, fast = 0;
     *     while (fast < nums.length) {
     *         if (nums[fast] != nums[slow]) {
     *             slow++;
     *             // 维护 nums[0..slow] 无重复
     *             nums[slow] = nums[fast];
     *         }
     *         fast++;
     *     }
     *     // 数组长度为索引 + 1
     *     return slow + 1;
     * }
     *
     * 再简单扩展一下，如果给你一个有序链表，如何去重呢？这是力扣第 83 题，其实和数组去重是一模一样的，唯一的
     * 区别是把数组赋值操作变成操作指针而已：
     *
     * ListNode deleteDuplicates(ListNode head) {
     *     if (head == null) return null;
     *     ListNode slow = head, fast = head;
     *     while (fast != null) {
     *         if (fast.val != slow.val) {
     *             // nums[slow] = nums[fast];
     *             slow.next = fast;
     *             // slow++;
     *             slow = slow.next;
     *         }
     *         // fast++
     *         fast = fast.next;
     *     }
     *     // 断开与后面重复元素的连接
     *     slow.next = null;
     *     return head;
     * }
     *
     *
     *
     * 移除元素
     *
     * 这是力扣第 27 题，看下题目：
     *
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     *
     *
     * 函数签名如下：
     *
     * int removeElement(int[] nums, int val);
     *
     * 题目要求把 nums 中所有值为 val 的元素原地删除，依然需要使用双指针技巧中的快慢指针：
     *
     * 如果 fast 遇到需要去除的元素，则直接跳过，否则就告诉 slow 指针，并让 slow 前进一步。
     *
     * 这和前面说到的数组去重问题解法思路是完全一样的，直接看代码：
     *
     * int removeElement(int[] nums, int val) {
     *     int fast = 0, slow = 0;
     *     while (fast < nums.length) {
     *         if (nums[fast] != val) {
     *             nums[slow] = nums[fast];
     *             slow++;
     *         }
     *         fast++;
     *     }
     *     return slow;
     * }
     *
     * 注意这里和有序数组去重的解法有一个重要不同，这里是先给 nums[slow] 赋值然后再给 slow++，这样可以保证
     * nums[0..slow-1] 是不包含值为 val 的元素的，最后的结果数组长度就是 slow。
     *
     *
     *
     * 移动零
     *
     * 这是力扣第 283 题，题目如下：
     *
     * 给你输入一个数组 nums，请你原地修改，将数组中的所有值为 0 的元素移到数组末尾。
     *
     * 函数签名如下：
     *
     * void moveZeroes(int[] nums);
     *
     * 比如说给你输入 nums = [0,1,4,0,2]，你的算法没有返回值，但是会把 nums 数组原地修改成 [1,4,2,0,0]。
     *
     * 结合之前说到的几个题目，你是否有已经有了答案呢？
     *
     * 题目让将所有 0 移到最后，其实就相当于移除 nums 中的所有 0，然后再把后面的元素都赋值为 0 即可。
     *
     * 所以可以复用上一题的 removeElement 函数：
     *
     * void moveZeroes(int[] nums) {
     *     // 去除 nums 中的所有 0
     *     // 返回去除 0 之后的数组长度
     *     int p = removeElement(nums, 0);
     *     // 将 p 之后的所有元素赋值为 0
     *     for (; p < nums.length; p++) {
     *         nums[p] = 0;
     *     }
     * }
     *
     * // 见上文代码实现
     * int removeElement(int[] nums, int val);
     *
     * 至此，四道「原地修改」的算法问题就讲完了，其实核心还是快慢指针技巧，你学会了吗？
     */
    public static void main(String[] args) {

    }

}

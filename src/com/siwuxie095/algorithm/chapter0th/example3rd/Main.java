package com.siwuxie095.algorithm.chapter0th.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-08-16 21:41:39
 */
public class Main {

    /**
     * ⼆、数据结构的基本操作
     *
     * 对于任何数据结构，其基本操作⽆⾮遍历 + 访问，再具体⼀点就是：增删查改。
     *
     *
     * 数据结构种类很多，但它们存在的⽬的都是在不同的应⽤场景，尽可能⾼效地增删查改。话说这不就是数据结构的使命么？
     *
     * 如何遍历 + 访问？这里仍然从最⾼层来看，各种数据结构的遍历 + 访问⽆⾮两种形式：线性的和⾮线性的。
     *
     * 线性就是 for/while 迭代为代表，⾮线性就是递归为代表。再具体⼀步，⽆⾮以下三种框架。
     *
     * （1）数组遍历框架，典型的线性迭代结构：
     *
     * void traverse(int[] arr) {
     *     for (int i = 0; i < arr.length; i++) {
     *          // 迭代访问 arr[i]
     *     }
     * }
     *
     * （2）链表遍历框架，兼具迭代和递归结构：
     *
     * // 基本的单链表节点
     * class ListNode {
     *     int val;
     *     ListNode next;
     * }
     *
     * void traverse(ListNode head) {
     *     for (ListNode p = head; p != null; p = p.next) {
     *          // 迭代访问 p.val
     *     }
     * }
     *
     * void traverse(ListNode head) {
     *     // 递归访问 head.val
     *     traverse(head.next)
     * }
     *
     *
     *
     *
     *
     *
     * （3）⼆叉树遍历框架，典型的⾮线性递归遍历结构：
     *
     * // 基本的⼆叉树节点
     * class TreeNode {
     *    int val;
     *    TreeNode left, right;
     * }
     *
     * void traverse(TreeNode root) {
     *     traverse(root.left)
     *     traverse(root.right)
     * }
     *
     * 你看⼆叉树的递归遍历⽅式和链表的递归遍历⽅式，相似不？再看看⼆叉树结构和单链表结构，相似不？如果再多⼏条叉，
     * N 叉树你会不会遍历？
     *
     * ⼆叉树框架可以扩展为 N 叉树的遍历框架：
     *
     * // 基本的 N 叉树节点
     * class TreeNode {
     *     int val;
     *     TreeNode[] children;
     * }
     *
     * void traverse(TreeNode root) {
     *     for (TreeNode child : root.children)
     *          traverse(child)
     * }
     *
     * N 叉树的遍历⼜可以扩展为图的遍历，因为图就是好⼏ N 叉棵树的结合体。你说图是可能出现环的？这个很好办，⽤个
     * 布尔数组 visited 做标记就⾏了，这⾥就不写代码了。
     *
     * 所谓框架，就是套路。不管增删查改，这些代码都是永远⽆法脱离的结构，你可以把这个结构作为⼤纲，根据具体问题在
     * 框架上添加代码就⾏了，后续会具体举例。
     */
    public static void main(String[] args) {

    }

}

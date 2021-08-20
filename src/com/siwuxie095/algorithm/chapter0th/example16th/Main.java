package com.siwuxie095.algorithm.chapter0th.example16th;

/**
 * @author Jiajing Li
 * @date 2021-08-20 21:31:42
 */
public class Main {

    /**
     * ⼆、⼆叉树的最⼩⾼度
     *
     * 先来个简单的问题实践⼀下 BFS 框架吧，判断⼀棵⼆叉树的最⼩⾼度，这也是 LeetCode 第 111 题，
     * 看⼀下题⽬：
     *
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点。
     *
     * 怎么套到 BFS 的框架⾥呢？⾸先明确⼀下起点 start 和终点 target 是什么，怎么判断到达了终点？
     *
     * 显然起点就是 root 根节点，终点就是最靠近根节点的那个「叶⼦节点」嘛，叶⼦节点就是两个⼦节点
     * 都是 null 的节点：
     *
     * if (cur.left == null && cur.right == null)
     *     // 到达叶⼦节点
     *
     * 那么，按照之前定义的框架稍加改造来写解法即可：
     *
     * int minDepth(TreeNode root) {
     *     if (root == null) return 0;
     *     Queue<TreeNode> q = new LinkedList<>();
     *     q.offer(root);
     *     // root 本身就是一层，depth 初始化为 1
     *     int depth = 1;
     *
     *     while (!q.isEmpty()) {
     *         int sz = q.size();
     *         // 将当前队列中的所有节点向四周扩散
     *         for (int i = 0; i < sz; i++) {
     *             TreeNode cur = q.poll();
     *             // 判断是否到达终点
     *             if (cur.left == null && cur.right == null)
     *                 return depth;
     *             // 将 cur 的相邻节点加入队列
     *             if (cur.left != null)
     *                 q.offer(cur.left);
     *             if (cur.right != null)
     *                 q.offer(cur.right);
     *         }
     *         // 这里增加步数
     *         depth++;
     *     }
     *     return depth;
     * }
     *
     * ⼆叉树是很简单的数据结构，上述代码应该不难理解，其实其他复杂问题都是这个框架的变形，再探讨
     * 复杂问题之前，先解答两个问题：
     *
     * 1、为什么 BFS 可以找到最短距离，DFS 不⾏吗？
     *
     * ⾸先，你看 BFS 的逻辑， depth 每增加⼀次，队列中的所有节点都向前迈⼀步，这保证了第⼀次到
     * 达终点的时候，⾛的步数是最少的。
     *
     * DFS 不能找最短路径吗？其实也是可以的，但是时间复杂度相对⾼很多。你想啊，DFS 实际上是靠递
     * 归的堆栈记录⾛过的路径，你要找到最短路径，肯定得把⼆叉树中所有树杈都探索完才能对⽐出最短的
     * 路径有多⻓对不对？⽽ BFS 借助队列做到⼀次⼀步「⻬头并进」，是可以在不遍历完整棵树的条件下
     * 找到最短距离的。
     *
     * 形象点说，DFS 是线，BFS 是⾯；DFS 是单打独⽃，BFS 是集体⾏动。这个应该⽐较容易理解吧。
     *
     *
     * 2、既然 BFS 那么好，为啥 DFS 还要存在？
     *
     * BFS 可以找到最短距离，但是空间复杂度⾼，⽽ DFS 的空间复杂度较低。
     *
     * 还是拿刚才处理⼆叉树问题的例⼦，假设给你的这个⼆叉树是满⼆叉树，节点数为 N，对于 DFS 算法
     * 来说，空间复杂度⽆⾮就是递归堆栈，最坏情况下顶多就是树的⾼度，也就是 O(logN)。
     *
     * 但是你想想 BFS 算法，队列中每次都会储存着⼆叉树⼀层的节点，这样的话最坏情况下空间复杂度应
     * 该是树的最底层节点的数量，也就是 N/2，⽤ Big O 表⽰的话也就是 O(N)。
     *
     * 由此观之，BFS 还是有代价的，⼀般来说在找最短路径的时候使⽤ BFS，其他时候还是 DFS 使⽤得多
     * ⼀些（主要是递归代码好写）。
     *
     * 好了，现在你对 BFS 了解得⾜够多了，后续将来⼀道难⼀点的题⽬，深化⼀下对框架的理解。
     */
    public static void main(String[] args) {

    }

}

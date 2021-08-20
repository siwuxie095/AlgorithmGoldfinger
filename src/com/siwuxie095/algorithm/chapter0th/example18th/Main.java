package com.siwuxie095.algorithm.chapter0th.example18th;

/**
 * @author Jiajing Li
 * @date 2021-08-20 22:30:11
 */
public class Main {

    /**
     * 四、双向 BFS 优化
     *
     * 你以为到这⾥ BFS 算法就结束了？恰恰相反。BFS 算法还有⼀种稍微⾼级⼀点的优化思路：
     * 双向 BFS，可以进⼀步提⾼算法的效率。
     *
     * 篇幅所限，这⾥就提⼀下区别：传统的 BFS 框架就是从起点开始向四周扩散，遇到终点时
     * 停⽌；⽽双向 BFS 则是从起点和终点同时开始扩散，当两边有交集的时候停⽌。
     *
     * 为什么这样能够能够提升效率呢？其实从 Big O 表⽰法分析算法复杂度的话，它俩的最坏
     * 复杂度都是 O(N) ，但是实际上双向 BFS 确实会快⼀些。
     *
     *
     * 以树形结构为例，如果起点在顶部，终点在最底部，按照传统 BFS 算法的策略，会把整棵
     * 树的节点都搜索⼀遍，最后找到 target；⽽双向 BFS 其实只遍历了半棵树就出现了交集，
     * 也就是找到了最短距离。
     *
     * 从这个例⼦可以直观地感受到，双向 BFS 是要⽐传统 BFS ⾼效的。
     *
     * 不过，双向 BFS 也有局限，因为你必须知道终点在哪⾥。⽐如之前讨论的⼆叉树最⼩⾼度
     * 的问题，你⼀开始根本就不知道终点在哪⾥，也就⽆法使⽤双向 BFS；但是后来的密码锁
     * 的问题，是可以使⽤双向 BFS 算法来提⾼效率的，代码稍加修改即可：
     *
     * int openLock(String[] deadends, String target) {
     *     Set<String> deads = new HashSet<>();
     *     for (String s : deadends) deads.add(s);
     *     // 用集合不用队列，可以快速判断元素是否存在
     *     Set<String> q1 = new HashSet<>();
     *     Set<String> q2 = new HashSet<>();
     *     Set<String> visited = new HashSet<>();
     *     // 初始化起点和终点
     *     q1.add("0000");
     *     q2.add(target);
     *     int step = 0;
     *
     *     while (!q1.isEmpty() && !q2.isEmpty()) {
     *         // 哈希集合在遍历的过程中不能修改，
     *         // 用 temp 存储 q1 的扩散结果
     *         Set<String> temp = new HashSet<>();
     *
     *         // 将 q1 中的所有节点向周围扩散
     *         for (String cur : q1) {
     *             // 判断是否到达终点
     *             if (deads.contains(cur))
     *                 continue;
     *             if (q2.contains(cur))
     *                 return step;
     *             visited.add(cur);
     *
     *             // 将一个节点的未遍历相邻节点加入集合
     *             for (int j = 0; j < 4; j++) {
     *                 String up = plusOne(cur, j);
     *                 if (!visited.contains(up))
     *                     temp.add(up);
     *                 String down = minusOne(cur, j);
     *                 if (!visited.contains(down))
     *                     temp.add(down);
     *             }
     *         }
     *         // 在这里增加步数
     *         step++;
     *         // temp 相当于 q1
     *         // 这里交换 q1 q2，下一轮 while 就是扩散 q2
     *         q1 = q2;
     *         q2 = temp;
     *     }
     *     return -1;
     * }
     *
     * 双向 BFS 还是遵循 BFS 算法框架的，只是不再使⽤队列，⽽是使⽤ HashSet ⽅便快速
     * 判断两个集合是否有交集。
     *
     * 另外的⼀个技巧点就是 while 循环的最后交换 q1 和 q2 的内容，所以只 要默认扩散
     * q1 就相当于轮流扩散 q1 和 q2。
     *
     * 其实双向 BFS 还有⼀个优化，就是在 while 循环开始时做⼀个判断：
     *
     * // ...
     * while (!q1.isEmpty() && !q2.isEmpty()) {
     *     if (q1.size() > q2.size()) {
     *         // 交换 q1 和 q2
     *         temp = q1;
     *         q1 = q2;
     *         q2 = temp;
     *     }
     *     // ...
     * }
     *
     * 为什么这是⼀个优化呢？
     *
     * 因为按照 BFS 的逻辑，队列（集合）中的元素越多，扩散之后新的队列（集合）中的元素
     * 就越多；在双向 BFS 算法中，如果每次都选择⼀个较⼩的集合进⾏扩散，那么占⽤的空间
     * 增⻓速度就会慢⼀些，效率就会⾼⼀些。
     *
     * 不过话说回来，⽆论传统 BFS 还是双向 BFS，⽆论做不做优化，从 Big O 衡量标准来看，
     * 时间复杂度都是⼀样的，只能说双向 BFS 是⼀种 trick，算法运⾏的速度会相对快⼀点，
     * 掌握不掌握其实都⽆所谓。最关键的是把 BFS 通⽤框架记下来，反正所有 BFS 算法都可以
     * ⽤它套出解法。
     */
    public static void main(String[] args) {

    }

}

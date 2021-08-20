package com.siwuxie095.algorithm.chapter0th.example15th;

/**
 * @author Jiajing Li
 * @date 2021-08-20 21:20:07
 */
public class Main {

    /**
     * ⼀、BFS 算法框架
     *
     * 要说框架的话，先举例⼀下 BFS 出现的常⻅场景，问题的本质就是让你在⼀幅「图」中找到从起点 start
     * 到终点 target 的最近距离，这个例⼦听起来很枯燥，但是 BFS 算法问题其实都是在⼲这个事⼉，把枯燥
     * 的本质搞清楚了，再去欣赏各种问题的包装才能胸有成⽵嘛。
     *
     * 这个⼴义的描述可以有各种变体，⽐如⾛迷宫，有的格⼦是围墙不能⾛，从起点到终点的最短距离是多少？
     * 如果这个迷宫带「传送门」可以瞬间传送呢？
     *
     * 再⽐如说两个单词，要求你通过某些替换，把其中⼀个变成另⼀个，每次只能替换⼀个字符，最少要替换⼏次？
     *
     * 再⽐如说连连看游戏，两个⽅块消除的条件不仅仅是图案相同，还得保证两个⽅块之间的最短连线不能多于两
     * 个拐点。你玩连连看，点击两个坐标，游戏是如何判断它俩的最短连线有⼏个拐点的？
     *
     * 再⽐如……
     *
     * 净整些花⾥胡哨的，这些问题都没啥奇技淫巧，本质上就是⼀幅「图」，让你从⼀个起点，⾛到终点，问最短
     * 路径。这就是 BFS 的本质，框架搞清楚了直接默写就好。
     *
     * 记住下⾯这个框架就 OK 了：
     *
     * // 计算从起点 start 到终点 target 的最近距离
     * int BFS(Node start, Node target) {
     *     Queue<Node> q; // 核心数据结构
     *     Set<Node> visited; // 避免走回头路
     *
     *     q.offer(start); // 将起点加入队列
     *     visited.add(start);
     *     int step = 0; // 记录扩散的步数
     *
     *     while (q not empty) {
     *         int sz = q.size();
     *         // 将当前队列中的所有节点向四周扩散
     *         for (int i = 0; i < sz; i++) {
     *             Node cur = q.poll();
     *             // 划重点：这里判断是否到达终点
     *             if (cur is target)
     *                 return step;
     *             // 将 cur 的相邻节点加入队列
     *             for (Node x : cur.adj())
     *                 if (x not in visited) {
     *                     q.offer(x);
     *                     visited.add(x);
     *                 }
     *         }
     *         // 划重点：更新步数在这里
     *         step++;
     *     }
     * }
     *
     * 队列 q 就不说了，BFS 的核⼼数据结构；cur.adj() 泛指 cur 相邻的节点，⽐如说⼆维数组中，cur
     * 上下左右四⾯的位置就是相邻节点； visited 的主要作⽤是防⽌⾛回头路，⼤部分时候都是必须的，但
     * 是像⼀般的⼆叉树结构，没有⼦节点到⽗节点的指针，不会⾛回头路就不需要 visited 。
     */
    public static void main(String[] args) {

    }

}

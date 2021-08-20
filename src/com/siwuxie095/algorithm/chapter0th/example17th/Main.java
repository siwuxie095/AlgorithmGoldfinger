package com.siwuxie095.algorithm.chapter0th.example17th;

/**
 * @author Jiajing Li
 * @date 2021-08-20 21:50:07
 */
public class Main {

    /**
     * 三、解开密码锁的最少次数
     *
     * 这道 LeetCode 题⽬是第 752 题，⽐较有意思，如下：
     *
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'。
     * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9'。每次旋转都只能旋转一个拨轮的一位数字。
     *
     * 锁的初始数字为 '0000'，一个代表四个拨轮的数字的字符串。
     *
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     *
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1。
     *
     *
     * 上面描述的就是⽣活中常⻅的那种密码锁，如果没有任何约束，最少的拨动次数很好算，就像平时开密码锁那样直奔密码拨就⾏了。
     *
     * 但现在的难点就在于，不能出现 deadends，应该如何计算出最少的转动次数呢？
     *
     * 第⼀步，不管所有的限制条件，不管 deadends 和 target 的限制，就思考⼀个问题：如果让你设计⼀个算法，穷举所有可能
     * 的密码组合，你怎么做？
     *
     * 穷举呗，再简单⼀点，如果你只转⼀下锁，有⼏种可能？总共有 4 个位置，每个位置可以向上转，也可以向下转，也就是有 8
     * 种可能对吧。
     *
     * ⽐如说从 "0000" 开始，转⼀次，可以穷举出 "1000", "9000", "0100", "0900"... 共 8 种密码。然后，再以这 8
     * 种密码作为基础，对每个密码再转⼀下，穷举出所有可能...
     *
     * 仔细想想，这就可以抽象成⼀幅图，每个节点有 8 个相邻的节点，⼜让你求最短距离，这不就是典型的 BFS 嘛，框架就可以
     * 派上⽤场了，先写出⼀个「简陋」的 BFS 框架代码再说别的：
     *
     * // 将 s[j] 向上拨动⼀次
     * String plusOne(String s, int j) {
     *     char[] ch = s.toCharArray();
     *     if (ch[j] == '9')
     *         ch[j] = '0';
     *     else
     *         ch[j] += 1;
     *     return new String(ch);
     * }
     *
     * // 将 s[i] 向下拨动⼀次
     * String minusOne(String s, int j) {
     *     char[] ch = s.toCharArray();
     *     if (ch[j] == '0')
     *         ch[j] = '9';
     *     else
     *         ch[j] -= 1;
     *     return new String(ch);
     * }
     *
     * // BFS 框架，打印出所有可能的密码
     * void BFS(String target) {
     *      Queue<String> q = new LinkedList<>();
     *      q.offer("0000");
     *
     *      while (!q.isEmpty()) {
     *          int sz = q.size();
     *          // 将当前队列中的所有节点向周围扩散
     *          for (int i = 0; i < sz; i++) {
     *              String cur = q.poll();
     *              // 判断是否到达终点
     *              System.out.println(cur);
     *
     *              // 将⼀个节点的相邻节点加⼊队列
     *              for (int j = 0; j < 4; j++) {
     *                  String up = plusOne(cur, j);
     *                  String down = minusOne(cur, j);
     *                  q.offer(up);
     *                  q.offer(down);
     *              }
     *          }
     *          // 在这⾥增加步数
     *      }
     *      return;
     * }
     *
     * PS：这段代码当然有很多问题，但是做算法题肯定不是⼀蹴⽽就的，⽽是从简陋到完美的。不要完美主义，要慢慢来。
     *
     * 这段 BFS 代码已经能够穷举所有可能的密码组合了，但是显然不能完成题⽬，有如下问题需要解决：
     *
     * 1、会⾛回头路。⽐如说从 "0000" 拨到 "1000" ，但是等从队列拿出 "1000" 时，还会拨出⼀个 "0000"，
     * 这样的话会产⽣死循环。
     *
     * 2、没有终⽌条件，按照题⽬要求，这里找到 target 就应该结束并返回拨动的次数。
     *
     * 3、没有对 deadends 的处理，按道理这些「死亡密码」是不能出现的，也就是说你遇到这些密码的时候需要跳过。
     *
     * 如果你能够看懂上⾯那段代码，真得给你⿎掌，只要按照 BFS 框架在对应的位置稍作修改即可修复这些问题：
     *
     * int openLock(String[] deadends, String target) {
     *     // 记录需要跳过的死亡密码
     *     Set<String> deads = new HashSet<>();
     *     for (String s : deadends) deads.add(s);
     *     // 记录已经穷举过的密码，防止走回头路
     *     Set<String> visited = new HashSet<>();
     *     Queue<String> q = new LinkedList<>();
     *     // 从起点开始启动广度优先搜索
     *     int step = 0;
     *     q.offer("0000");
     *     visited.add("0000");
     *
     *     while (!q.isEmpty()) {
     *         int sz = q.size();
     *         // 将当前队列中的所有节点向周围扩散
     *         for (int i = 0; i < sz; i++) {
     *             String cur = q.poll();
     *
     *             // 判断密码是否合法，是否到达终点
     *             if (deads.contains(cur))
     *                 continue;
     *             if (cur.equals(target))
     *                 return step;
     *
     *             // 将一个节点的未遍历相邻节点加入队列
     *             for (int j = 0; j < 4; j++) {
     *                 String up = plusOne(cur, j);
     *                 if (!visited.contains(up)) {
     *                     q.offer(up);
     *                     visited.add(up);
     *                 }
     *                 String down = minusOne(cur, j);
     *                 if (!visited.contains(down)) {
     *                     q.offer(down);
     *                     visited.add(down);
     *                 }
     *             }
     *         }
     *         // 在这里增加步数
     *         step++;
     *     }
     *     // 如果穷举完都没找到目标密码，那就是找不到了
     *     return -1;
     * }
     *
     * ⾄此，就解决这道题⽬了。有⼀个⽐较⼩的优化：可以不需要 dead 这个哈希集合，可以直接将这些元素初始化到
     * visited 集合中，效果是⼀样的，可能更加优雅⼀些。
     */
    public static void main(String[] args) {

    }

}

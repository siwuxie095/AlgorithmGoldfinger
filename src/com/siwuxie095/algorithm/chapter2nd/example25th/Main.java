package com.siwuxie095.algorithm.chapter2nd.example25th;

/**
 * @author Jiajing Li
 * @date 2021-09-25 16:39:41
 */
public class Main {

    /**
     * 经典动态规划：戳气球
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 312.戳气球（困难）
     *
     * 这里要聊的这道题「Burst Balloon」和之前「经典动态规划：高楼扔鸡蛋问题」分析过的高楼扔鸡蛋问题类似，
     * 知名度很高，但难度确实也很大。
     *
     * 它是 LeetCode 第 312 题，题目如下：
     *
     * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     *
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1]
     * 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组
     * 的边界，那么就当它是一个数字为 1 的气球。
     *
     * 求所能获得硬币的最大数量。
     *
     * 示例 1：
     * 输入：nums = [3,1,5,8]
     * 输出：167
     * 解释：
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     *
     *
     * 首先必须要说明，这个题目的状态转移方程真的比较巧妙，所以说如果你看了题目之后完全没有思路恰恰是正常的。
     * 虽然最优答案不容易想出来，但基本的思路分析是应该力求做到的。所以这里会先分析一下常规思路，然后再引入
     * 动态规划解法。
     *
     *
     *
     * 一、回溯思路
     *
     * 先来顺一下解决这种问题的套路：
     *
     * 之前多次强调过，很显然只要涉及求最值，没有任何奇技淫巧，一定是穷举所有可能的结果，然后对比得出最值。
     *
     * 所以说，只要遇到求最值的算法问题，首先要思考的就是：如何穷举出所有可能的结果？
     *
     * 穷举主要有两种算法，就是回溯算法和动态规划，前者就是暴力穷举，而后者是根据状态转移方程推导「状态」。
     *
     * 如何将这里的扎气球问题转化成回溯算法呢？这个应该不难想到的，其实就是想穷举戳气球的顺序，不同的戳气球
     * 顺序可能得到不同的分数，需要把所有可能的分数中最高的那个找出来，对吧。
     *
     * 那么，这不就是一个「全排列」问题嘛，「回溯算法解题套路框架」中有全排列算法的详解和代码，其实只要稍微
     * 改一下逻辑即可，伪码思路如下：
     *
     * int res = Integer.MIN_VALUE;
     * // 输入一组气球，返回戳破它们获得的最大分数
     * int maxCoins(int[] nums) {
     *     backtrack(nums, 0);
     *     return res;
     * }
     *
     * // 回溯算法的伪码解法
     * void backtrack(int[] nums, int socre) {
     *     if (nums 为空) {
     *         res = max(res, score);
     *         return;
     *     }
     *     for (int i = 0; i < nums.length; i++) {
     *         int point = nums[i-1] * nums[i] * nums[i+1];
     *         int temp = nums[i];
     *         // 做选择
     *         在 nums 中删除元素 nums[i]
     *         // 递归回溯
     *         backtrack(nums, score + point);
     *         // 撤销选择
     *         将 temp 还原到 nums[i]
     *     }
     * }
     *
     * 回溯算法就是这么简单粗暴，但是相应的，算法的效率非常低。这个解法等同于全排列，所以时间复杂度是阶乘级
     * 别，非常高，题目说了 nums 的大小 n 最多为 500，所以回溯算法肯定是不能通过所有测试用例的。
     *
     *
     *
     * 二、动态规划思路
     *
     * 这个动态规划问题和之前的动态规划系列相比有什么特别之处？为什么它比较难呢？
     *
     * 原因在于，这个问题中每戳破一个气球 nums[i]，得到的分数和该气球相邻的气球 nums[i-1] 和 nums[i+1]
     * 是有相关性的。
     *
     * 运用动态规划算法的一个重要条件：子问题必须独立。所以对于这个戳气球问题，如果想用动态规划，必须巧妙地
     * 定义 dp 数组的含义，避免子问题产生相关性，才能推出合理的状态转移方程。
     *
     * 如何定义 dp 数组呢，这里需要对问题进行一个简单地转化。题目说可以认为 nums[-1] = nums[n] = 1，那
     * 么先直接把这两个边界加进去，形成一个新的数组 points：
     *
     * int maxCoins(int[] nums) {
     *     int n = nums.length;
     *     // 两端加入两个虚拟气球
     *     int[] points = new int[n + 2];
     *     points[0] = points[n + 1] = 1;
     *     for (int i = 1; i <= n; i++) {
     *         points[i] = nums[i - 1];
     *     }
     *     // ...
     * }
     *
     * 现在气球的索引变成了从 1 到 n，points[0] 和 points[n+1] 可以认为是两个「虚拟气球」。
     *
     * 那么可以改变问题：在一排气球 points 中，请你戳破气球 0 和气球 n+1 之间的所有气球（不包括 0 和
     * n+1），使得最终只剩下气球 0 和气球 n+1 两个气球，最多能够得到多少分？
     *
     * 现在可以定义 dp 数组的含义：
     *
     * dp[i][j] = x 表示，戳破气球 i 和气球 j 之间（开区间，不包括 i 和 j）的所有气球，可以获得的最高
     * 分数为 x。
     *
     * 那么根据这个定义，题目要求的结果就是 dp[0][n+1] 的值，而 base case 就是 dp[i][j] = 0，其中
     * 0 <= i <= n+1, j <= i+1，因为这种情况下，开区间 (i, j) 中间根本没有气球可以戳。
     *
     * // base case 已经都被初始化为 0
     * int[][] dp = new int[n + 2][n + 2];
     *
     * 现在要根据这个 dp 数组来推导状态转移方程了，根据之前的套路，所谓的推导「状态转移方程」，实际上就是在
     * 思考怎么「做选择」，也就是这道题目最有技巧的部分：
     *
     * 不就是想求戳破气球 i 和气球 j 之间的最高分数吗，如果「正向思考」，就只能写出上面的回溯算法；这里需要
     * 「反向思考」，想一想气球 i 和气球 j 之间最后一个被戳破的气球可能是哪一个？
     *
     * 其实气球 i 和气球 j 之间的所有气球都可能是最后被戳破的那一个，不妨假设为 k。回顾动态规划的套路，这里
     * 其实已经找到了「状态」和「选择」：i 和 j 就是两个「状态」，最后戳破的那个气球 k 就是「选择」。
     *
     * 根据刚才对 dp 数组的定义，如果最后一个戳破气球 k，dp[i][j] 的值应该为：
     *
     * dp[i][j] = dp[i][k] + dp[k][j]
     *          + points[i]*points[k]*points[j]
     *
     * 你不是要最后戳破气球 k 吗？那得先把开区间 (i, k) 的气球都戳破，再把开区间 (k, j) 的气球都戳破；最
     * 后剩下的气球 k，相邻的就是气球 i 和气球 j，这时候戳破 k 的话得到的分数就是 points[i]*points[k]*
     * points[j]。
     *
     * 那么戳破开区间 (i, k) 和开区间 (k, j) 的气球最多能得到的分数是多少呢？嘿嘿，就是 dp[i][k] 和
     * dp[k][j]，这恰好就是对 dp 数组的定义嘛！
     *
     * 由于是开区间，dp[i][k] 和 dp[k][j] 不会影响气球 k；而戳破气球 k 时，旁边相邻的就是气球 i 和气球 j
     * 了，最后还会剩下气球 i 和气球 j，这也恰好满足了 dp 数组开区间的定义。
     *
     * 那么，对于一组给定的 i 和 j，只要穷举 i < k < j 的所有气球 k，选择得分最高的作为 dp[i][j] 的值即可，
     * 这也就是状态转移方程：
     *
     * // 最后戳破的气球是哪个？
     * for (int k = i + 1; k < j; k++) {
     *     // 择优做选择，使得 dp[i][j] 最大
     *     dp[i][j] = Math.max(
     *         dp[i][j],
     *         dp[i][k] + dp[k][j] + points[i]*points[j]*points[k]
     *     );
     * }
     *
     * 写出状态转移方程就完成这道题的一大半了，但是还有问题：对于 k 的穷举仅仅是在做「选择」，但是应该如何
     * 穷举「状态」i 和 j 呢？
     *
     * for (int i = ...; ; )
     *     for (int j = ...; ; )
     *         for (int k = i + 1; k < j; k++) {
     *             dp[i][j] = Math.max(
     *                 dp[i][j],
     *                 dp[i][k] + dp[k][j] + points[i]*points[j]*points[k]
     *             );
     * return dp[0][n+1];
     *
     *
     *
     * 三、写出代码
     *
     * 关于「状态」的穷举，最重要的一点就是：状态转移所依赖的状态必须被提前计算出来。
     *
     * 拿这道题举例，dp[i][j] 所依赖的状态是 dp[i][k] 和 dp[k][j]，那么必须保证：在计算 dp[i][j] 时，
     * dp[i][k] 和 dp[k][j] 已经被计算出来了（其中 i < k < j）。
     *
     * 那么应该如何安排 i 和 j 的遍历顺序，来提供上述的保证呢？有这样一个技巧：根据 base case 和最终状态
     * 进行推导。
     *
     * PS：最终状态就是指题目要求的结果，对于这道题目也就是 dp[0][n+1]。
     *
     * 为了达到这个要求，可以有两种遍历方法，要么斜着遍历，要么从下到上从左到右遍历。
     *
     * 斜着遍历有一点难写，所以一般就从下往上遍历，下面看完整代码：
     *
     * int maxCoins(int[] nums) {
     *     int n = nums.length;
     *     // 添加两侧的虚拟气球
     *     int[] points = new int[n + 2];
     *     points[0] = points[n + 1] = 1;
     *     for (int i = 1; i <= n; i++) {
     *         points[i] = nums[i - 1];
     *     }
     *     // base case 已经都被初始化为 0
     *     int[][] dp = new int[n + 2][n + 2];
     *     // 开始状态转移
     *     // i 应该从下往上
     *     for (int i = n; i >= 0; i--) {
     *         // j 应该从左往右
     *         for (int j = i + 1; j < n + 2; j++) {
     *             // 最后戳破的气球是哪个？
     *             for (int k = i + 1; k < j; k++) {
     *                 // 择优做选择
     *                 dp[i][j] = Math.max(
     *                     dp[i][j],
     *                     dp[i][k] + dp[k][j] + points[i]*points[j]*points[k]
     *                 );
     *             }
     *         }
     *     }
     *     return dp[0][n + 1];
     * }
     *
     * 至此，这道题目就完全解决了，十分巧妙，但也不是那么难，对吧？
     *
     * 关键在于 dp 数组的定义，需要避免子问题互相影响，所以反向思考，将 dp[i][j] 的定义设为开区间，
     * 考虑最后戳破的气球是哪一个，以此构建了状态转移方程。
     *
     * 对于如何穷举「状态」，使用了小技巧，通过 base case 和最终状态推导出 i,j 的遍历方向，保证
     * 正确的状态转移。
     */
    public static void main(String[] args) {

    }

}
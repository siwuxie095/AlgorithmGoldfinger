package com.siwuxie095.algorithm.chapter2nd.example13th;

/**
 * @author Jiajing Li
 * @date 2021-09-21 16:10:42
 */
public class Main {

    /**
     * 经典动态规划：完全背包问题
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 518.零钱兑换II（中等）
     *
     * 零钱兑换 2 是另一种典型背包问题的变体，之前已经讲了：
     * （1）「经典动态规划：0-1 背包问题」
     * （2）「经典动态规划：子集背包问题」
     *
     * 这里继续按照背包问题的套路，列举一个背包问题的变形。
     *
     * 下面来看 LeetCode 第 518 题 Coin Change 2，题目如下：
     *
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     *
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     *
     * 假设每一种面额的硬币有无限个。 
     *
     * 题目数据保证结果符合 32 位带符号整数。
     *
     * 示例 1：
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     *
     *
     * 函数签名如下：
     *
     * int change(int amount, int[] coins);
     *
     * 可以把这个问题转化为背包问题的描述形式：
     *
     * 有一个背包，最大容量为 amount，有一系列物品 coins，每个物品的重量为 coins[i]，每个物品的数量无限。
     * 请问有多少种方法，能够把背包恰好装满？
     *
     * 这个问题和之前讲过的两个背包问题，有一个最大的区别就是，每个物品的数量是无限的，这也就是传说中的「完
     * 全背包问题」，没啥高大上的，无非就是状态转移方程有一点变化而已。
     *
     * 下面就以背包问题的描述形式，继续按照流程来分析。
     *
     *
     *
     * 解题思路
     *
     * 第一步要明确两点，「状态」和「选择」。
     *
     * 状态有两个，就是「背包的容量」和「可选择的物品」，选择就是「装进背包」或者「不装进背包」嘛，背包问题
     * 的套路都是这样。
     *
     * 明白了状态和选择，动态规划问题基本上就解决了，只要往这个框架套就完事儿了：
     *
     * for 状态1 in 状态1的所有取值：
     *     for 状态2 in 状态2的所有取值：
     *         for ...
     *             dp[状态1][状态2][...] = 计算(选择1，选择2...)
     *
     *
     * 第二步要明确 dp 数组的定义。
     *
     * 首先看看刚才找到的「状态」，有两个，也就是说需要一个二维 dp 数组。
     *
     * dp[i][j] 的定义如下：
     *
     * 若只使用前 i 个物品（可以重复使用），当背包容量为 j 时，有 dp[i][j] 种方法可以装满背包。
     *
     * 换句话说，翻译回题目的意思就是：
     *
     * 若只使用 coins 中的前 i 个硬币的面值，若想凑出金额 j，有 dp[i][j] 种凑法。
     *
     * 经过以上的定义，可以得到：
     *
     * base case 为 dp[0][..] = 0， dp[..][0] = 1。因为如果不使用任何硬币面值，就无法凑出任何金额；
     * 如果凑出的目标金额为 0，那么 "无为而治" 就是唯一的一种凑法。
     *
     * 最终想得到的答案就是 dp[N][amount]，其中 N 为 coins 数组的大小。
     *
     * 大致的伪码思路如下：
     *
     * int dp[N+1][amount+1]
     * dp[0][..] = 0
     * dp[..][0] = 1
     *
     * for i in [1..N]:
     *     for j in [1..amount]:
     *         把物品 i 装进背包,
     *         不把物品 i 装进背包
     * return dp[N][amount]
     *
     *
     * 第三步，根据「选择」，思考状态转移的逻辑。
     *
     * 注意，这个问题的特殊点在于物品的数量是无限的，所以这里和之前写的 0-1 背包问题有所不同。
     *
     * 如果你不把这第 i 个物品装入背包，也就是说你不使用 coins[i] 这个面值的硬币，那么凑出面额 j 的方法数
     * dp[i][j] 应该等于 dp[i-1][j]，继承之前的结果。
     *
     * 如果你把这第 i 个物品装入了背包，也就是说你使用 coins[i] 这个面值的硬币，那么 dp[i][j] 应该等于
     * dp[i][j-coins[i-1]]。
     *
     * 首先由于 i 是从 1 开始的，所以 coins 的索引是 i-1 时表示第 i 个硬币的面值。
     *
     * dp[i][j-coins[i-1]] 也不难理解，如果你决定使用这个面值的硬币，那么就应该关注如何凑出金额
     * j - coins[i-1]。
     *
     * 比如说，你想用面值为 2 的硬币凑出金额 5，那么如果你知道了凑出金额 3 的方法，再加上一枚面额为 2 的
     * 硬币，不就可以凑出 5 了嘛。
     *
     * 综上就是两种选择，而想求的 dp[i][j] 是「共有多少种凑法」，所以 dp[i][j] 的值应该是以上两种选择的
     * 结果之和：
     *
     * for (int i = 1; i <= n; i++) {
     *     for (int j = 1; j <= amount; j++) {
     *         if (j - coins[i-1] >= 0)
     *             dp[i][j] = dp[i - 1][j]
     *                      + dp[i][j-coins[i-1]];
     * return dp[N][W]
     *
     * PS：有的读者在这里可能会有疑问，不是说可以重复使用硬币吗？那么如果确定「使用第 i 个面值的硬币」，
     * 怎么确定这个面值的硬币被使用了多少枚？简单的一个 dp[i][j-coins[i-1]] 可以包含重复使用第 i
     * 个硬币的情况吗？
     *
     * 对于这个问题，建议你再仔回头细阅读一下对 dp 数组的定义，然后把这个定义代入 dp[i][j-coins[i-1]]
     * 看看：
     *
     * 若只使用前 i 个物品（可以重复使用），当背包容量为 j-coins[i-1] 时，有 dp[i][j-coins[i-1]]
     * 种方法可以装满背包。
     *
     * 看到了吗，dp[i][j-coins[i-1]] 也是允许你使用第 i 个硬币的，所以说已经包含了重复使用硬币的情况，
     * 你一百个放心。
     *
     *
     * 最后一步，把伪码翻译成代码，处理一些边界情况。
     *
     * 这里用 Java 写的代码，把上面的思路完全翻译了一遍，并且处理了一些边界问题：
     *
     * int change(int amount, int[] coins) {
     *     int n = coins.length;
     *     int[][] dp = amount int[n + 1][amount + 1];
     *     // base case
     *     for (int i = 0; i <= n; i++)
     *         dp[i][0] = 1;
     *
     *     for (int i = 1; i <= n; i++) {
     *         for (int j = 1; j <= amount; j++)
     *             if (j - coins[i-1] >= 0)
     *                 dp[i][j] = dp[i - 1][j]
     *                          + dp[i][j - coins[i-1]];
     *             else
     *                 dp[i][j] = dp[i - 1][j];
     *     }
     *     return dp[n][amount];
     * }
     *
     * 而且，通过观察可以发现，dp 数组的转移只和 dp[i][..] 和 dp[i-1][..] 有关，所以可以压缩状态，
     * 进一步降低算法的空间复杂度：
     *
     * int change(int amount, int[] coins) {
     *     int n = coins.length;
     *     int[] dp = new int[amount + 1];
     *     dp[0] = 1; // base case
     *     for (int i = 0; i < n; i++)
     *         for (int j = 1; j <= amount; j++)
     *             if (j - coins[i] >= 0)
     *                 dp[j] = dp[j] + dp[j-coins[i]];
     *
     *     return dp[amount];
     * }
     *
     * 这个解法和之前的思路完全相同，将二维 dp 数组压缩为一维，时间复杂度 O(N*amount)，
     * 空间复杂度 O(amount)。
     *
     * 至此，这道零钱兑换问题也通过背包问题的框架解决了。
     */
    public static void main(String[] args) {

    }

}
package com.siwuxie095.algorithm.chapter0th.example8th;

/**
 * @author Jiajing Li
 * @date 2021-08-17 22:12:57
 */
public class Main {

    /**
     * ⼆、凑零钱问题
     *
     * 先看下题⽬：给你 k 种⾯值的硬币，⾯值分别为 c1, c2 ... ck，每种硬币的数量⽆限，再给⼀个
     * 总⾦额 amount，问你最少需要⼏枚硬币凑出这个⾦额，如果不可能凑出，算法返回 -1。算法的函数
     * 签名如下：
     *
     * // coins 中是可选硬币⾯值，amount 是⽬标⾦额
     * int coinChange(int[] coins, int amount);
     *
     * ⽐如说 k = 3 ，⾯值分别为 1，2，5，总⾦额 amount = 11 。那么最少需要 3 枚硬币凑出，即
     * 11 = 5 + 5 + 1。
     *
     * 你认为计算机应该如何解决这个问题？显然，就是把所有肯能的凑硬币⽅法都穷举出来，然后找找看最
     * 少需要多少枚硬币。
     *
     *
     * 1、暴⼒递归
     *
     * ⾸先，这个问题是动态规划问题，因为它具有「最优⼦结构」的。要符合「最优⼦结构」，⼦问题间必
     * 须互相独⽴。啥叫相互独⽴？你肯定不想看数学证明，下面⽤⼀个直观的例⼦来讲解。
     *
     * ⽐如说，你的原问题是考出最⾼的总成绩，那么你的⼦问题就是要把语⽂考到最⾼，数学考到最⾼……
     * 为了每门课考到最⾼，你要把每门课相应的选择题分数拿到最⾼，填空题分数拿到最⾼…… 当然，最终
     * 就是你每门课都是满分，这就是最⾼的总成绩。
     *
     * 得到了正确的结果：最⾼的总成绩就是总分。因为这个过程符合最优⼦结构，"每门科⽬考到最⾼"
     * 这些⼦问题是互相独⽴，互不⼲扰的。
     *
     * 但是，如果加⼀个条件：你的语⽂成绩和数学成绩会互相制约，此消彼⻓。这样的话，显然你能考到的
     * 最⾼总成绩就达不到总分了，按刚才那个思路就会得到错误的结果。因为⼦问题并不独⽴，语⽂数学成
     * 绩⽆法同时最优，所以最优⼦结构被破坏。
     *
     * 回到凑零钱问题，为什么说它符合最优⼦结构呢？⽐如你想求 amount = 11 时的最少硬币数（原问
     * 题），如果你知道凑出 amount = 10 的最少硬币数（⼦问题），你只需要把⼦问题的答案加⼀（再
     * 选⼀枚⾯值为 1 的硬币）就是原问题的答案，因为硬币的数量是没有限制的，⼦问题之间没有相互制
     * 约，是互相独⽴的。
     *
     * 那么，既然知道了这是个动态规划问题，就要思考如何列出正确的状态转移⽅程？
     *
     * 先确定「状态」，也就是原问题和⼦问题中变化的变量。由于硬币数量⽆限，所以唯⼀的状态就是⽬标
     * ⾦额 amount。
     *
     * 然后确定 dp 函数的定义：当前的⽬标⾦额是 n，⾄少需要 dp(n) 个硬币凑出该⾦额。
     *
     * 然后确定「选择」并择优，也就是对于每个状态，可以做出什么选择改变当前状态。具体到这个问题，
     * ⽆论当的⽬标⾦额是多少，选择就是从⾯额列表 coins 中选择⼀个硬币，然后⽬标⾦额就会减少：
     *
     * # 伪码框架
     * def coinChange(coins: List[int], amount: int):
     *
     *     # 定义：要凑出金额 n，至少要 dp(n) 个硬币
     *     def dp(n):
     *         # 做选择，选择需要硬币最少的那个结果
     *         for coin in coins:
     *             res = min(res, 1 + dp(n - coin))
     *         return res
     *
     *     # 题目要求的最终结果是 dp(amount)
     *     return dp(amount)
     *
     * 最后明确 base case，显然⽬标⾦额为 0 时，所需硬币数量为 0；当⽬标⾦额⼩于 0 时，⽆解，
     * 返回 -1：
     *
     * def coinChange(coins: List[int], amount: int):
     *
     *     def dp(n):
     *         # base case
     *         if n == 0: return 0
     *         if n < 0: return -1
     *         # 求最小值，所以初始化为正无穷
     *         res = float('INF')
     *         for coin in coins:
     *             subproblem = dp(n - coin)
     *             # 子问题无解，跳过
     *             if subproblem == -1: continue
     *             res = min(res, 1 + subproblem)
     *
     *         return res if res != float('INF') else -1
     *
     *     return dp(amount)
     *
     * ⾄此，状态转移⽅程其实已经完成了，以上算法已经是暴⼒解法了，以上代码的数学形式就是状态转移⽅程：
     *
     * n = 0 时，dp(n) = 0
     * n < 0 时，dp(n) = -1
     * n > 0 时，dp(n) = min{dp(n - coin) + 1 | coin}
     *
     * ⾄此，这个问题其实就解决了，只不过需要消除⼀下重叠⼦问题，⽐如 amount = 11, coins = {1,2,5}
     * 时，可以画出递归树看看。
     *
     * 时间复杂度分析：⼦问题总数 x 每个⼦问题的时间。
     *
     * ⼦问题总数为递归树节点个数，这个⽐较难看出来，是 O(n^k)，总之是指数级别的。每个⼦问题中含有⼀个
     * for 循环，复杂度为 O(k)。所以总时间复杂度为 O(k * n^k)，指数级别。
     *
     *
     * 2、带备忘录的递归
     *
     * 只需要稍加修改，就可以通过备忘录消除⼦问题：
     *
     * def coinChange(coins: List[int], amount: int):
     *     # 备忘录
     *     memo = dict()
     *     def dp(n):
     *         # 查备忘录，避免重复计算
     *         if n in memo: return memo[n]
     *         # base case
     *         if n == 0: return 0
     *         if n < 0: return -1
     *         res = float('INF')
     *         for coin in coins:
     *             subproblem = dp(n - coin)
     *             if subproblem == -1: continue
     *             res = min(res, 1 + subproblem)
     *
     *         # 记入备忘录
     *         memo[n] = res if res != float('INF') else -1
     *         return memo[n]
     *
     *     return dp(amount)
     *
     * 很显然，「备忘录」⼤⼤减⼩了⼦问题数⽬，完全消除了⼦问题的冗余，所以⼦问题总数不会超过⾦额数 n，
     * 即⼦问题数⽬为 O(n)。处理⼀个⼦问题的时间不变，仍是 O(k)，所以总的时间复杂度是 O(kn)。
     *
     *
     * 3、dp 数组的迭代解法
     *
     * 当然，也可以⾃底向上使⽤ dp table 来消除重叠⼦问题， dp 数组的定义和刚才 dp 函数类似，定义也是
     * ⼀样的：
     *
     * dp[i] = x 表⽰，当⽬标⾦额为 i 时，⾄少需要 x 枚硬币。
     *
     * int coinChange(vector<int>& coins, int amount) {
     *     // 数组大小为 amount + 1，初始值也为 amount + 1
     *     vector<int> dp(amount + 1, amount + 1);
     *     // base case
     *     dp[0] = 0;
     *     // 外层 for 循环在遍历所有状态的所有取值
     *     for (int i = 0; i < dp.size(); i++) {
     *         // 内层 for 循环在求所有选择的最小值
     *         for (int coin : coins) {
     *             // 子问题无解，跳过
     *             if (i - coin < 0) continue;
     *             dp[i] = min(dp[i], 1 + dp[i - coin]);
     *         }
     *     }
     *     return (dp[amount] == amount + 1) ? -1 : dp[amount];
     * }
     *
     * PS：为啥 dp 数组初始化为 amount + 1 呢，因为凑成 amount ⾦额的硬币数最多只可能等于 amount
     * （全⽤ 1 元⾯值的硬币），所以初始化为 amount + 1 就相当于初始化为正⽆穷，便于后续取最⼩值。
     */
    public static void main(String[] args) {

    }

}

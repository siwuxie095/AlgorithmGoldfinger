package com.siwuxie095.algorithm.chapter2nd.example19th;

/**
 * @author Jiajing Li
 * @date 2021-09-23 21:41:17
 */
public class Main {

    /**
     * 动态规划之地下城游戏
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 174.地下城游戏（困难）
     *
     * 「魔塔」是一款经典的地牢类游戏，碰怪物要掉血，吃血瓶能加血，你要收集钥匙，一层一层上楼，最后救出美丽的公主。
     * 现在手机上仍然可以玩这个游戏。
     *
     * 力扣第 174 题是一道类似的题目，下面简单描述一下：
     *
     * 输入一个存储着整数的二维数组 grid，如果 grid[i][j] > 0，说明这个格子装着血瓶，经过它可以增加对应的生命
     * 值；如果 grid[i][j] == 0，则这是一个空格子，经过它不会发生任何事情；如果 grid[i][j] < 0，说明这个格
     * 子有怪物，经过它会损失对应的生命值。
     *
     * 现在你是一名骑士，将会出现在最上角，公主被困在最右下角，你只能向右和向下移动，请问你初始至少需要多少生命值
     * 才能成功救出公主？
     *
     * 换句话说，就是问你至少需要多少初始生命值，能够让骑士从最左上角移动到最右下角，且任何时候生命值都要大于 0。
     *
     * 函数签名如下：
     *
     * int calculateMinimumHP(int[][] grid);
     *
     * 比如题目举的例子，输入如下一个二维数组 grid，用 K 表示骑士，用 P 表示公主：
     *
     * -2(K)  -3   3
     * -5     -10  1
     * 10     30   -5(P)
     *
     * 算法应该返回 7，也就是说骑士的初始生命值至少为 7 时才能成功救出公主。
     *
     * 之前的「最小路径和」写过类似的问题，问你从左上角到右下角的最小路径和是多少。
     *
     * 做算法题一定要尝试举一反三，感觉这道题和最小路径和有点关系对吧？
     *
     * 想要最小化骑士的初始生命值，是不是意味着要最大化骑士行进路线上的血瓶？是不是相当于求「最大路径和」？是不是
     * 可以直接套用计算「最小路径和」的思路？
     *
     * 但是稍加思考，发现这个推论并不成立，吃到最多的血瓶，并不一定就能获得最小的初始生命值。
     *
     * 所以，关键不在于吃最多的血瓶，而是在于如何损失最少的生命值。
     *
     * 这类求最值的问题，肯定要借助动态规划技巧，要合理设计 dp 数组/函数的定义。类比最小路径和问题，dp 函数签名
     * 肯定长这样：
     *
     * int dp(int[][] grid, int i, int j);
     *
     * 但是这道题对 dp 函数的定义比较有意思，按照常理，这个 dp 函数的定义应该是：
     *
     * 从左上角（grid[0][0]）走到 grid[i][j] 至少需要 dp(grid, i, j) 的生命值。
     *
     * 这样定义的话，base case 就是 i, j 都等于 0 的时候，可以这样写代码：
     *
     * int calculateMinimumHP(int[][] grid) {
     *     int m = grid.length;
     *     int n = grid[0].length;
     *     // 我们想计算左上角到右下角所需的最小生命值
     *     return dp(grid, m - 1, n - 1);
     * }
     *
     * int dp(int[][] grid, int i, int j) {
     *     // base case
     *     if (i == 0 && j == 0) {
     *         // 保证骑士落地不死就行了
     *         return gird[i][j] > 0 ? 1 : -grid[i][j] + 1;
     *     }
     *     ...
     * }
     *
     * PS：为了简洁，之后 dp(grid, i, j) 就简写为 dp(i, j)，大家理解就好。
     *
     * 接下来需要找状态转移了，还记得如何找状态转移方程吗？这样定义 dp 函数能否正确进行状态转移呢？
     *
     * 这里希望 dp(i, j) 能够通过 dp(i-1, j) 和 dp(i, j-1) 推导出来，这样就能不断逼近 base case，也就能够
     * 正确进行状态转移。
     *
     * 具体来说，「到达 A 的最小生命值」应该能够由「到达 B 的最小生命值」和「到达 C 的最小生命值」推导出来：
     *
     * ?    ?     ?
     * ?    ?     ?(B)
     * ?    ?(C)  ?(A)
     *
     *
     * 但问题是，能推出来么？实际上是不能的。
     *
     * 因为按照 dp 函数的定义，你只知道「能够从左上角到达 B 的最小生命值」，但并不知道「到达 B 时的生命值」。
     *
     * 所以说，之前对 dp 数组的定义是错误的，信息量不足，算法无法做出正确的状态转移。
     *
     * 正确的做法需要反向思考，依然是如下的 dp 函数：
     *
     * int dp(int[][] grid, int i, int j);
     *
     * 但是要修改 dp 函数的定义：
     *
     * 从 grid[i][j] 到达终点（右下角）所需的最少生命值是 dp(grid, i, j)。
     *
     * 那么可以这样写代码：
     *
     * int calculateMinimumHP(int[][] grid) {
     *     // 想计算左上角到右下角所需的最小生命值
     *     return dp(grid, 0, 0);
     * }
     *
     * int dp(int[][] grid, int i, int j) {
     *     int m = grid.length;
     *     int n = grid[0].length;
     *     // base case
     *     if (i == m - 1 && j == n - 1) {
     *         return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
     *     }
     *     ...
     * }
     *
     * 根据新的 dp 函数定义和 base case，想求 dp(0, 0)，那就应该试图通过 dp(i, j+1) 和 dp(i+1, j) 推导出
     * dp(i, j)，这样才能不断逼近 base case，正确进行状态转移。
     *
     * 综上，状态转移方程已经推出来了：
     *
     * int res = min(
     *     dp(i + 1, j),
     *     dp(i, j + 1)
     * ) - grid[i][j];
     *
     * dp(i, j) = res <= 0 ? 1 : res;
     *
     * 根据这个核心逻辑，加一个备忘录消除重叠子问题，就可以直接写出最终的代码了：
     *
     * // 主函数
     * int calculateMinimumHP(int[][] grid) {
     *     int m = grid.length;
     *     int n = grid[0].length;
     *     // 备忘录中都初始化为 -1
     *     memo = new int[m][n];
     *     for (int[] row : memo) {
     *         Arrays.fill(row, -1);
     *     }
     *
     *     return dp(grid, 0, 0);
     * }
     *
     * // 备忘录，消除重叠子问题
     * int[][] memo;
     *
     * // 定义：从 (i, j) 到达右下角，需要的初始血量至少是多少
     * int dp(int[][] grid, int i, int j) {
     *     int m = grid.length;
     *     int n = grid[0].length;
     *     // base case
     *     if (i == m - 1 && j == n - 1) {
     *         return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
     *     }
     *     if (i == m || j == n) {
     *         return Integer.MAX_VALUE;
     *     }
     *     // 避免重复计算
     *     if (memo[i][j] != -1) {
     *         return memo[i][j];
     *     }
     *     // 状态转移逻辑
     *     int res = Math.min(
     *             dp(grid, i, j + 1),
     *             dp(grid, i + 1, j)
     *         ) - grid[i][j];
     *     // 骑士的生命值至少为 1
     *     memo[i][j] = res <= 0 ? 1 : res;
     *
     *     return memo[i][j];
     * }
     *
     * 这就是自顶向下带备忘录的动态规划解法，参考「动态规划解题套路框架」很容易就可以改写成 dp 数组的迭代解法，
     * 这里就不写了，读者可以尝试自己写一写。
     *
     * 这道题的核心是定义 dp 函数，找到正确的状态转移方程，从而计算出正确的答案。
     */
    public static void main(String[] args) {

    }

}

package com.siwuxie095.algorithm.chapter2nd.example20th;

/**
 * @author Jiajing Li
 * @date 2021-09-23 21:57:40
 */
public class Main {

    /**
     * 动态规划之自由之路
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 514.自由之路（困难）
     *
     * 这里要讲的是力扣第 514 题「自由之路」，题目给你输入一个字符串 ring 代表圆盘上的字符（指针位置在
     * 12 点钟方向，初始指向 ring[0]），再输入一个字符串 key 代表你需要拨动圆盘输入的字符串，你的算法
     * 需要返回输入这个 key 至少进行多少次操作（拨动一格圆盘和按下圆盘中间的按钮都算是一次操作）。
     *
     * 函数签名如下：
     *
     * int findRotateSteps(string ring, string key);
     *
     * 比如题目举的例子，输入 ring = "godding", key = "gd"。
     *
     * 需要输入 key = "gd"，算法返回 4。
     *
     * 因为现在指针指的字母就是字母 "g"，所以可以直接按下中间的按钮，然后再将圆盘逆时针拨动两格，让指针
     * 指向字母 "d"，然后再按一次中间的按钮。
     *
     * 上述过程，按了两次按钮，拨了两格转盘，总共操作了 4 次，是最少的操作次数，所以算法应该返回 4。
     *
     * 这里可以首先给题目做一个等价，转动圆盘是不是就等于拨动指针？
     *
     * 原题可以转化为：圆盘固定，可以拨动指针；现在需要拨动指针并按下按钮，以最少的操作次数输入 key 对应
     * 的字符串。
     *
     * 那么，这个问题如何使用动态规划的技巧解决呢？或者说，这道题的「状态」和「选择」是什么呢？
     *
     * 「状态」就是「当前需要输入的字符」和「当前圆盘指针的位置」。
     *
     * 再具体点，「状态」就是 i 和 j 两个变量。可以用 i 表示当前圆盘上指针指向的字符（也就是 ring[i]）；
     * 用 j 表示需要输入的字符（也就是 key[j]）。
     *
     * 这样可以写这样一个 dp 函数：
     *
     * int dp(string& ring, int i, string& key, int j);
     *
     * 这个 dp 函数的定义如下：
     *
     * 当圆盘指针指向 ring[i] 时，输入字符串 key[j..] 至少需要 dp(ring, i, key, j) 次操作。
     *
     * 根据这个定义，题目其实就是想计算 dp(ring, 0, key, 0) 的值，而且可以把 dp 函数的 base case 写
     * 出来：
     *
     * int dp(string& ring, int i, string& key, int j) {
     *     // base case，完成输入
     *     if (j == key.size()) return 0;
     *     // ...
     * }
     *
     * 接下来，思考一下如何根据状态做选择，如何进行状态转移？
     *
     * 「选择」就是「如何拨动指针得到待输入的字符」。
     *
     * 再具体点就是，对于现在想输入的字符 key[j]，可以如何拨动圆盘，得到这个字符？
     *
     * 比如说输入 ring = "gdonidg"，假设想输入的字符 key[j] = "d"，圆盘中有两个字母 "d"，而且可以
     * 顺时针也可以逆时针拨动指针，所以总共有四种「选择」输入字符 "d"，需要选择操作次数最少的那个拨法。
     *
     * 大致的代码逻辑如下：
     *
     * int dp(string& ring, int i, string& key, int j) {
     *     // base case 完成输入
     *     if (j == key.size()) return 0;
     *
     *     // 做选择
     *     int res = INT_MAX;
     *     for (int k : [字符 key[j] 在 ring 中的所有索引]) {
     *         res = min(
     *             把 i 顺时针转到 k 的代价,
     *             把 i 逆时针转到 k 的代价
     *         );
     *     }
     *
     *     return res;
     * }
     *
     * 至于到底是顺时针还是逆时针，其实非常好判断，怎么近就怎么来；但是对于圆盘中的两个字符 "d"，还能是
     * 怎么近怎么来吗？
     *
     * 不能，因为这和 key[i] 之后需要输入的字符有关，还是上面的例子：
     *
     * 如果输入的是 key = "di"，那么即便右边的 "d" 离得近，也应该去左边的 "d"，因为左边的 "d" 旁边就
     * 是 "i"，「整体」的操作数最少。
     *
     * 那么，应该如何判断呢？其实就是穷举，递归调用 dp 函数，把两种选择的「整体」代价算出来，然后再做比
     * 较就行了。
     *
     * 讲到这就差不多了，直接看代码吧：
     *
     * // 字符 -> 索引列表
     * unordered_map<char, vector<int>> charToIndex;
     * // 备忘录
     * vector<vector<int>> memo;
     *
     * // 主函数
     * int findRotateSteps(string ring, string key) {
     *     int m = ring.size();
     *     int n = key.size();
     *     // 备忘录全部初始化为 0
     *     memo.resize(m, vector<int>(n, 0));
     *     // 记录圆环上字符到索引的映射
     *     for (int i = 0; i < ring.size(); i++) {
     *         charToIndex[ring[i]].push_back(i);
     *     }
     *     // 圆盘指针最初指向 12 点钟方向，
     *     // 从第一个字符开始输入 key
     *     return dp(ring, 0, key, 0);
     * }
     *
     * // 计算圆盘指针在 ring[i]，输入 key[j..] 的最少操作数
     * int dp(string& ring, int i, string& key, int j) {
     *     // base case 完成输入
     *     if (j == key.size()) return 0;
     *     // 查找备忘录，避免重叠子问题
     *     if (memo[i][j] != 0) return memo[i][j];
     *
     *     int n = ring.size();
     *     // 做选择
     *     int res = INT_MAX;
     *     // ring 上可能有多个字符 key[j]
     *     for (int k : charToIndex[key[j]]) {
     *         // 拨动指针的次数
     *         int delta = abs(k - i);
     *         // 选择顺时针还是逆时针
     *         delta = min(delta, n - delta);
     *         // 将指针拨到 ring[k]，继续输入 key[j+1..]
     *         int subProblem = dp(ring, k, key, j + 1);
     *         // 选择「整体」操作次数最少的
     *         res = min(res, 1 + delta + subProblem);
     *         // PS：加一是因为按动按钮也是一次操作
     *     }
     *     // 将结果存入备忘录
     *     memo[i][j] = res;
     *     return res;
     * }
     *
     * 这段代码是 C++ 写的，因为觉得涉及字符串的算法 C++ 更方便一些，这里说一些语言相关的细节问题：
     *
     * 1、unordered_map 就是哈希表，当访问不存在的键时，会自动创建对应的值，所以可以直接 push_back
     * 而不用担心空指针错误。
     *
     * 2、min 函数的参数都是 int 型，所以必须先用一个 int 型变量 n 存储 ring.size()，然后调用
     * min(delta, n - delta)，否则会报错。
     *
     * 至此，这道题就解决了。
     */
    public static void main(String[] args) {

    }

}

package com.siwuxie095.algorithm.chapter0th.example12th;

/**
 * @author Jiajing Li
 * @date 2021-08-18 22:06:19
 */
public class Main {

    /**
     * ⼆、N 皇后问题
     *
     * 这个问题很经典了，简单解释⼀下：给你⼀个 N×N 的棋盘，让你放置 N 个皇后，使得它们不能互相攻击。
     *
     * PS：皇后可以攻击同⼀⾏、同⼀列、左上左下右上右下四个⽅向的任意单位。
     *
     * 这个问题本质上跟全排列问题差不多，决策树的每⼀层表⽰棋盘上的每⼀⾏；每个节点可以做出的选择是，
     * 在该⾏的任意⼀列放置⼀个皇后。
     *
     * 直接套⽤框架：
     *
     * vector<vector<string>> res;
     *
     * // 输入棋盘边长 n，返回所有合法的放置
     * vector<vector<string>> solveNQueens(int n) {
     *     // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
     *     vector<string> board(n, string(n, '.'));
     *     backtrack(board, 0);
     *     return res;
     * }
     *
     * // 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * // 选择列表：第 row 行的所有列都是放置皇后的选择
     * // 结束条件：row 超过 board 的最后一行，说明棋盘放满了
     * void backtrack(vector<string>& board, int row) {
     *     // 触发结束条件
     *     if (row == board.size()) {
     *         res.push_back(board);
     *         return;
     *     }
     *     int n = board[row].size();
     *     for (int col = 0; col < n; col++) {
     *         // 排除不合法选择
     *         if (!isValid(board, row, col))
     *             continue;
     *         // 做选择
     *         board[row][col] = 'Q';
     *         // 进入下一行决策
     *         backtrack(board, row + 1);
     *         // 撤销选择
     *         board[row][col] = '.';
     *     }
     * }
     *
     * 上面这部分主要代码，其实跟全排列问题差不多，isValid 函数的实现也很简单，如下：
     *
     * // 是否可以在 board[row][col] 放置皇后？
     * bool isValid(vector<string>& board, int row, int col) {
     *     int n = board.size();
     *     // 检查列是否有皇后互相冲突
     *     for (int i = 0; i < row; i++) {
     *         if (board[i][col] == 'Q')
     *             return false;
     *     }
     *     // 检查右上方是否有皇后互相冲突
     *     for (int i = row - 1, j = col + 1;
     *             i >= 0 && j < n; i--, j++) {
     *         if (board[i][j] == 'Q')
     *             return false;
     *     }
     *     // 检查左上方是否有皇后互相冲突
     *     for (int i = row - 1, j = col - 1;
     *             i >= 0 && j >= 0; i--, j--) {
     *         if (board[i][j] == 'Q')
     *             return false;
     *     }
     *     return true;
     * }
     *
     * 函数 backtrack 依然像个在决策树上游⾛的指针，通过 row 和 col 就可以表⽰函数遍历到的位置，
     * 通过 isValid 函数可以将不符合条件的情况剪枝。
     *
     * 如果直接给你这么⼀⼤段解法代码，可能是懵逼的。但现在明⽩了回溯算法的框架套路，还有啥难理解的
     * 呢？⽆⾮是改改做选择的⽅式，排除不合法选择的⽅式⽽已，只要框架存于⼼，⾯对的就只剩下⼩问题了。
     *
     * 当 N = 8 时，就是⼋皇后问题，数学⼤佬⾼斯穷尽⼀⽣都没有数清楚⼋皇后问题到底有⼏种可能的放置
     * ⽅法，但是这里的算法只需要⼀秒就可以算出来所有可能的结果。
     *
     * 不过真的不怪⾼斯。这个问题的复杂度确实⾮常⾼，看看最终的决策树，虽然有 isValid 函数剪枝，但
     * 最坏时间复杂度仍然是 O(N^(N+1))，⽽且⽆法优化。如果 N = 10 的时候，计算就已经很耗时了。
     *
     * 有的时候，并不想得到所有合法的答案，只想要⼀个答案，怎么办呢？⽐如解数独的算法，找所有解法的
     * 复杂度太⾼，只要找到⼀种解法就可以。
     *
     * 其实特别简单，只要稍微修改⼀下回溯算法的代码即可：
     *
     * // 函数找到⼀个答案后就返回 true
     * bool backtrack(vector<string>& board, int row) {
     *     // 触发结束条件
     *     if (row == board.size()) {
     *         res.push_back(board);
     *         return true;
     *     }
     *     ...
     *     for (int col = 0; col < n; col++) {
     *         ...
     *         board[row][col] = 'Q';
     *         if (backtrack(board, row + 1))
     *             return true;
     *         board[row][col] = '.';
     *     }
     *     return false;
     * }
     *
     * 只要找到⼀个答案，for 循环的后续递归穷举都会被阻断。也许你可以在 N 皇后问题的代码框架上，
     * 稍加修改，就可以写⼀个解数独的算法。
     */
    public static void main(String[] args) {

    }

}

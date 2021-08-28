package com.siwuxie095.algorithm.chapter1st.example12th;

/**
 * @author Jiajing Li
 * @date 2021-08-28 21:15:58
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 二叉树的序列化
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 297.二叉树的序列化和反序列化（困难）
     *
     * JSON 的运用非常广泛，比如经常将变成语言中的结构体序列化成 JSON 字符串，存入缓存或者通过网络
     * 发送给远端服务，消费者接受 JSON 字符串然后进行反序列化，就可以得到原始数据了。这就是「序列化」
     * 和「反序列化」的目的，以某种固定格式组织字符串，使得数据可以独立于编程语言。
     *
     * 那么假设现在有一棵用 Java 实现的二叉树，想把它序列化字符串，然后用 C++ 读取这棵并还原这棵二
     * 叉树的结构，怎么办？这就需要对二叉树进行「序列化」和「反序列化」了。
     *
     * 这里会用前序、中序、后序遍历的方式来序列化和反序列化二叉树，进一步，还会用迭代式的层级遍历来
     * 解决这个问题。
     *
     * 接下来就用二叉树的遍历框架来给你看看二叉树到底能玩出什么骚操作。
     *
     *
     *
     * 一、题目描述
     *
     * 「二叉树的序列化与反序列化」就是给你输入一棵二叉树的根节点 root，要求你实现如下一个类：
     *
     * public class Codec {
     *
     *     // 把一棵二叉树序列化成字符串
     *     public String serialize(TreeNode root) {}
     *
     *     // 把字符串反序列化成二叉树
     *     public TreeNode deserialize(String data) {}
     * }
     *
     * 可以用 serialize 方法将二叉树序列化成字符串，用 deserialize 方法将序列化的字符串反序列化
     * 成二叉树，至于以什么格式序列化和反序列化，这个完全由你决定。
     *
     * 比如说输入如下这样一棵二叉树：
     *
     *      2
     *    /  \
     *   1    3
     *   \
     *    6
     *
     * serialize 方法也许会把它序列化成字符串 2,1,#,6,3,#,#，其中 # 表示 null 指针，那么把这个
     * 字符串再输入 deserialize 方法，依然可以还原出这棵二叉树。也就是说，这两个方法会成对儿使用，
     * 你只要保证他俩能够自洽就行了。
     *
     * 想象一下，二叉树结该是一个二维平面内的结构，而序列化出来的字符串是一个线性的一维结构。所谓的
     * 序列化不过就是把结构化的数据「打平」，其实就是在考察二叉树的遍历方式。
     *
     * 二叉树的遍历方式有哪些？递归遍历方式有前序遍历，中序遍历，后序遍历；迭代方式一般是层级遍历。
     * 这里就把这些方式都尝试一遍，来实现 serialize 方法和 deserialize 方法。
     *
     *
     *
     * 二、前序遍历解法
     *
     * 前序遍历框架如下：
     *
     * void traverse(TreeNode root) {
     *     if (root == null) return;
     *
     *     // 前序遍历的代码
     *
     *     traverse(root.left);
     *     traverse(root.right);
     * }
     *
     * 真的很简单，在递归遍历两棵子树之前写的代码就是前序遍历代码，那么请你看一看如下伪码：
     *
     * LinkedList<Integer> res;
     * void traverse(TreeNode root) {
     *     if (root == null) {
     *         // 暂且用数字 -1 代表空指针 null
     *         res.addLast(-1);
     *         return;
     *     }
     *
     *     // 前序遍历位置
     *     res.addLast(root.val);
     *
     *     traverse(root.left);
     *     traverse(root.right);
     * }
     *
     * 如上是将二叉树「打平」到了一个列表中，其中 -1 代表 null。
     *
     * 那么，将二叉树打平到一个字符串中也是完全一样的：
     *
     * // 代表分隔符的字符
     * String SEP = ",";
     * // 代表 null 空指针的字符
     * String NULL = "#";
     * // 用于拼接字符串
     * StringBuilder sb = new StringBuilder();
     *
     * // 将二叉树打平为字符串
     * void traverse(TreeNode root, StringBuilder sb) {
     *     if (root == null) {
     *         sb.append(NULL).append(SEP);
     *         return;
     *     }
     *
     *     // 前序遍历位置
     *     sb.append(root.val).append(SEP);
     *
     *     traverse(root.left, sb);
     *     traverse(root.right, sb);
     * }
     *
     * StringBuilder 可以用于高效拼接字符串，所以也可以认为是一个列表，用 , 作为分隔符，用 # 表示
     * 空指针 null。
     *
     * 至此，已经可以写出序列化函数 serialize 的代码了：
     *
     * String SEP = ",";
     * String NULL = "#";
     *
     * // 主函数，将二叉树序列化为字符串
     * String serialize(TreeNode root) {
     *     StringBuilder sb = new StringBuilder();
     *     serialize(root, sb);
     *     return sb.toString();
     * }
     *
     * / 辅助函数，将二叉树存入 StringBuilder
     * void serialize(TreeNode root, StringBuilder sb) {
     *     if (root == null) {
     *         sb.append(NULL).append(SEP);
     *         return;
     *     }
     *
     *     // 前序遍历位置
     *     sb.append(root.val).append(SEP);
     *
     *     serialize(root.left, sb);
     *     serialize(root.right, sb);
     * }
     *
     * 现在，思考一下如何写 deserialize 函数，将字符串反过来构造二叉树。
     *
     * 首先可以把字符串转化成列表：
     *
     * String data = "1,2,#,4,#,#,3,#,#,";
     * String[] nodes = data.split(",");
     *
     * 这样，nodes 列表就是二叉树的前序遍历结果，问题转化为：如何通过二叉树的前序遍历结果还原一棵二叉
     * 树？
     *
     * PS：一般语境下，单单前序遍历结果是不能还原二叉树结构的，因为缺少空指针的信息，至少要得到前、中、
     * 后序遍历中的两种才能还原二叉树。但是这里的 node 列表包含空指针的信息，所以只使用 node 列表就
     * 可以还原二叉树。
     *
     * 根据刚才的分析，nodes 列表就是一棵打平的二叉树。
     *
     * 那么，反序列化过程也是一样，先确定根节点 root，然后遵循前序遍历的规则，递归生成左右子树即可：
     *
     * // 主函数，将字符串反序列化为二叉树结构
     * TreeNode deserialize(String data) {
     *     // 将字符串转化成列表
     *     LinkedList<String> nodes = new LinkedList<>();
     *     for (String s : data.split(SEP)) {
     *         nodes.addLast(s);
     *     }
     *     return deserialize(nodes);
     * }
     *
     * // 辅助函数，通过 nodes 列表构造二叉树
     * TreeNode deserialize(LinkedList<String> nodes) {
     *     if (nodes.isEmpty()) return null;
     *
     *     // 前序遍历位置
     *     // 列表最左侧就是根节点
     *     String first = nodes.removeFirst();
     *     if (first.equals(NULL)) return null;
     *     TreeNode root = new TreeNode(Integer.parseInt(first));
     *
     *     root.left = deserialize(nodes);
     *     root.right = deserialize(nodes);
     *
     *     return root;
     * }
     *
     * 不难发现，根据树的递归性质，nodes 列表的第一个元素就是一棵树的根节点，所以只要将列表的第一个
     * 元素取出作为根节点，剩下的交给递归函数去解决即可。
     *
     *
     *
     * 三、后序遍历解法
     *
     * 二叉树的后续遍历框架：
     *
     * void traverse(TreeNode root) {
     *     if (root == null) return;
     *     traverse(root.left);
     *     traverse(root.right);
     *
     *     // 后序遍历的代码
     * }
     *
     * 明白了前序遍历的解法，后序遍历就比较容易理解了，首先实现 serialize 序列化方法，只需要稍微修改
     * 辅助方法即可：
     *
     * // 辅助函数，将二叉树存入 StringBuilder
     * void serialize(TreeNode root, StringBuilder sb) {
     *     if (root == null) {
     *         sb.append(NULL).append(SEP);
     *         return;
     *     }
     *
     *     serialize(root.left, sb);
     *     serialize(root.right, sb);
     *
     *     // 后序遍历位置
     *     sb.append(root.val).append(SEP);
     * }
     *
     * 这里把对 StringBuilder 的拼接操作放到了后续遍历的位置，后序遍历导致结果的顺序发生变化。
     *
     * 关键的难点在于，如何实现后序遍历的 deserialize 方法呢？是不是也简单地将关键代码放到后序遍历的
     * 位置就行了呢：
     *
     * // 辅助函数，通过 nodes 列表构造二叉树
     * TreeNode deserialize(LinkedList<String> nodes) {
     *     if (nodes.isEmpty()) return null;
     *
     *     root.left = deserialize(nodes);
     *     root.right = deserialize(nodes);
     *
     *     // 后序遍历位置
     *     String first = nodes.removeFirst();
     *     if (first.equals(NULL)) return null;
     *     TreeNode root = new TreeNode(Integer.parseInt(first));
     *
     *     return root;
     * }
     *
     * 没这么简单，显然上述代码是错误的，变量都没声明呢，就开始用了？生搬硬套肯定是行不通的，回想刚才
     * 前序遍历方法中的 deserialize 方法，第一件事情在做什么？
     *
     * deserialize 方法首先寻找 root 节点的值，然后递归计算左右子节点。那么这里也应该顺着这个基本
     * 思路走，后续遍历中，root 节点的值能不能找到？
     *
     * 可见，root 的值是列表的最后一个元素。应该从后往前取出列表元素，先用最后一个元素构造 root，
     * 然后递归调用生成 root 的左右子树。
     *
     * 注意，从后往前在 nodes 列表中取元素，一定要先构造 root.right 子树，后构造 root.left 子树。
     *
     * 看完整代码：
     *
     * // 主函数，将字符串反序列化为二叉树结构
     * TreeNode deserialize(String data) {
     *     LinkedList<String> nodes = new LinkedList<>();
     *     for (String s : data.split(SEP)) {
     *         nodes.addLast(s);
     *     }
     *     return deserialize(nodes);
     * }
     *
     * // 辅助函数，通过 nodes 列表构造二叉树
     * TreeNode deserialize(LinkedList<String> nodes) {
     *     if (nodes.isEmpty()) return null;
     *     // 从后往前取出元素
     *     String last = nodes.removeLast();
     *     if (last.equals(NULL)) return null;
     *     TreeNode root = new TreeNode(Integer.parseInt(last));
     *     // 限构造右子树，后构造左子树
     *     root.right = deserialize(nodes);
     *     root.left = deserialize(nodes);
     *
     *     return root;
     * }
     *
     * 至此，后续遍历实现的序列化、反序列化方法也都实现了。
     *
     *
     *
     * 四、中序遍历解法
     *
     * 先说结论，中序遍历的方式行不通，因为无法实现反序列化方法 deserialize。
     *
     * 序列化方法 serialize 依然容易，只要把字符串的拼接操作放到中序遍历的位置就行了：
     *
     * // 辅助函数，将二叉树存入 StringBuilder
     * void serialize(TreeNode root, StringBuilder sb) {
     *     if (root == null) {
     *         sb.append(NULL).append(SEP);
     *         return;
     *     }
     *
     *     serialize(root.left, sb);
     *     // 中序遍历位置
     *     sb.append(root.val).append(SEP);
     *     serialize(root.right, sb);
     * }
     *
     * 但是，刚才说了，要想实现反序列方法，首先要构造 root 节点。前序遍历得到的 nodes 列表中，第一
     * 个元素是 root 节点的值；后序遍历得到的 nodes 列表中，最后一个元素是 root 节点的值。
     *
     * 你看上面这段中序遍历的代码，root 的值被夹在两棵子树的中间，也就是在 nodes 列表的中间，不知道
     * 确切的索引位置，所以无法找到 root 节点，也就无法进行反序列化。
     *
     *
     *
     * 五、层级遍历解法
     *
     * 首先，先写出层级遍历二叉树的代码框架：
     *
     * void traverse(TreeNode root) {
     *     if (root == null) return;
     *     // 初始化队列，将 root 加入队列
     *     Queue<TreeNode> q = new LinkedList<>();
     *     q.offer(root);
     *
     *     while (!q.isEmpty()) {
     *         TreeNode cur = q.poll();
     *
     *         // 层级遍历代码位置
     *         System.out.println(root.val);
     *
     *         if (cur.left != null) {
     *             q.offer(cur.left);
     *         }
     *
     *         if (cur.right != null) {
     *             q.offer(cur.right);
     *         }
     *     }
     * }
     *
     * 上述代码是标准的二叉树层级遍历框架，从上到下，从左到右打印每一层二叉树节点的值，可以看到，队列
     * q 中不会存在 null 指针。
     *
     * 不过在反序列化的过程中是需要记录空指针 null 的，所以可以把标准的层级遍历框架略作修改：
     *
     * void traverse(TreeNode root) {
     *     if (root == null) return;
     *     // 初始化队列，将 root 加入队列
     *     Queue<TreeNode> q = new LinkedList<>();
     *     q.offer(root);
     *
     *     while (!q.isEmpty()) {
     *         TreeNode cur = q.poll();
     *
     *         // 层级遍历代码位置
     *         if (cur == null) continue;
     *         System.out.println(root.val);
     *
     *         q.offer(cur.left);
     *         q.offer(cur.right);
     *     }
     * }
     *
     * 这样也可以完成层级遍历，只不过把对空指针的检验从「将元素加入队列」的时候改成了「从队列取出元素」
     * 的时候。
     *
     * 那么完全仿照这个框架即可写出序列化方法：
     *
     * String SEP = ",";
     * String NULL = "#";
     *
     * // 将二叉树序列化为字符串
     * String serialize(TreeNode root) {
     *     if (root == null) return "";
     *     StringBuilder sb = new StringBuilder();
     *     // 初始化队列，将 root 加入队列
     *     Queue<TreeNode> q = new LinkedList<>();
     *     q.offer(root);
     *
     *     while (!q.isEmpty()) {
     *         TreeNode cur = q.poll();
     *
     *         // 层级遍历代码位置
     *         if (cur == null) {
     *             sb.append(NULL).append(SEP);
     *             continue;
     *         }
     *         sb.append(cur.val).append(SEP);
     *
     *         q.offer(cur.left);
     *         q.offer(cur.right);
     *     }
     *
     *     return sb.toString();
     * }
     *
     * 从结果可以看到，每一个非空节点都会对应两个子节点，那么反序列化的思路也是用队列进行层级遍历，同时
     * 用索引 i 记录对应子节点的位置：
     *
     * // 将字符串反序列化为二叉树结构
     * TreeNode deserialize(String data) {
     *     if (data.isEmpty()) return null;
     *     String[] nodes = data.split(SEP);
     *     // 第一个元素就是 root 的值
     *     TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
     *
     *     // 队列 q 记录父节点，将 root 加入队列
     *     Queue<TreeNode> q = new LinkedList<>();
     *     q.offer(root);
     *
     *     for (int i = 1; i < nodes.length; ) {
     *         // 队列中存的都是父节点
     *         TreeNode parent = q.poll();
     *         // 父节点对应的左侧子节点的值
     *         String left = nodes[i++];
     *         if (!left.equals(NULL)) {
     *             parent.left = new TreeNode(Integer.parseInt(left));
     *             q.offer(parent.left);
     *         } else {
     *             parent.left = null;
     *         }
     *         // 父节点对应的右侧子节点的值
     *         String right = nodes[i++];
     *         if (!right.equals(NULL)) {
     *             parent.right = new TreeNode(Integer.parseInt(right));
     *             q.offer(parent.right);
     *         } else {
     *             parent.right = null;
     *         }
     *     }
     *     return root;
     * }
     *
     * 这段代码可以考验一下你的框架思维。仔细看一看 for 循环部分的代码，发现这不就是标准层级遍历的代码
     * 衍生出来的嘛：
     *
     * while (!q.isEmpty()) {
     *     TreeNode cur = q.poll();
     *
     *     if (cur.left != null) {
     *         q.offer(cur.left);
     *     }
     *
     *     if (cur.right != null) {
     *         q.offer(cur.right);
     *     }
     * }
     *
     * 只不过，标准的层级遍历在操作二叉树节点 TreeNode，而这里的函数在操作 nodes[i]，这也恰恰是反序
     * 列化的目的嘛。
     *
     * 到这里，对于二叉树的序列化和反序列化的几种方法就全部讲完了。
     */
    public static void main(String[] args) {

    }

}

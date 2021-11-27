package offer;

/**
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 *
 * @author goblin
 * @version 1.0.0
 * @since 2021-11-27 15:01
 */
public class _10_I_斐波纳契数列 {

    /**
     * 题目框架
     *
     * @param n 第几项
     * @return 值
     */
    public int fib(int n) {
        return 暴力递归(n);
    }


    /**
     * 暴力递归
     */
    public int 暴力递归(int n) {
        if (n == 0 || n == 1) return n;

        n = 暴力递归(n - 1) + 暴力递归(n - 2);

        return n % 1000000007;
    }

    /**
     * 备忘录/临时变量  方式
     */
    public int 备忘录(int n) {
        return 备忘录计算(new int[n + 1], n);
    }

    public int 备忘录计算(int[] fibArray, int n) {
        if (n == 0 || n == 1) return n;
        if (fibArray[n] != 0) return fibArray[n];
        fibArray[n] = (备忘录计算(fibArray, n - 1) + 备忘录计算(fibArray, n - 2)) % 1000000007;
        return fibArray[n];
    }


    /**
     * 自底向上
     *
     * @param n
     * @return
     */
    public int dp数组(int n) {
        if (n == 0 || n == 1) return n;

        int[] fibArray = new int[n + 1];
        fibArray[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibArray[i] = (fibArray[i - 1] + fibArray[i - 2]) % 1000000007;
        }
        return fibArray[n];
    }

    /**
     * 状态压缩
     *
     * @param n
     * @return
     */
    public int 临时变量(int n) {
        if (n == 0 || n == 1) return n;

        int prev = 0, curr = 1;

        for (int i = 2; i <= n; i++) {
            int tmp = (prev + curr) % 1000000007;
            prev = curr;
            curr = tmp;
        }
        return curr;
    }

}

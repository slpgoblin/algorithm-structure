package 题库;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/self-dividing-numbers/
 *
 * @author goblin
 * @version 1.0.0
 * @since 2022-03-31 23:06
 */
public class _728_自除数 {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();

        out:
        for (int i = left; i <= right; i++) {
            int cur = i;
            while (cur != 0) {
                int t = cur % 10;
                if (t == 0 || i % t != 0) continue out;
                cur /= 10;
            }
            result.add(i);
        }

        return result;
    }

}

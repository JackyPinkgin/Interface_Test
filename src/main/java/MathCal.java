/**
 * @author 80230531
 * @date 2022/5/11 11:18
 */
public class MathCal {

    public int factorial(int n) throws Exception {
        if (n < 0) {
            throw new Exception("负数没有阶乘");
        } else if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public int fibonacci(int n) throws Exception {
        if (n < 0) {
            throw new Exception("应该从1开始");
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }else {
            return fibonacci(n-1)+fibonacci(n-2);
        }
    }
}

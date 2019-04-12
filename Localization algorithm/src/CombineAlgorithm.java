import java.util.Arrays;

/*
* 组合算法 从m个数中选n个进行组合
*
* */
public class CombineAlgorithm {

    //src数组长度
    private int m;

    //需要获取n个数
    private int n;

    //临时变量,obj行数
    private int objLineidx;

    //存放结果的二维数组
    private Object[][] obj;

    public static void main(String[] args) throws Exception{

 	    Integer[] a = new Integer[]{1,2,3,4};
 	    CombineAlgorithm ca = new CombineAlgorithm(a,3);

 	    Object[][] c = ca.getResult();
 	    for(int i = 0; i < c.length; i++){
 	        System.out.println(Arrays.toString(c[i]));
 	    }
 	}

    CombineAlgorithm(Object[] src, int getNum) throws Exception {
        if(src == null)
            throw new Exception("数组为空");
        if(src.length < getNum)
            throw new Exception("非法取数");

        m = src.length;
        n = getNum;
        objLineidx = 0;
        obj = new Object[combination(m, n)][n];

        Object[] temp = new Object[n];

        combine(src, 0, 0, n, temp);

    }

    /**
     * 计算 C(m,n)个数 = (m!)/(n!*(m-n)!)
     * 				   即从M中选N个数，函数返回有多少种选法（参数M必须大于等于n）
     *
     * @param m
     * @param n
     * @return 返回有C(m,n)种选法
     */
    public int combination(int m, int n) {
        if(m < n)
            return 0;
        int k = 1;
        int v = 1;
        for(int i = n; i >= 1; i--) {
            k = k * m;
            v = v * n;
            m--;
            n--;
        }
        return k/v;
    }
    /*递归算法，把结果写到obj二维数组对象*/
    private void combine(Object[] src, int srcIdex, int i, int n, Object[] temp) {
        for(int j = srcIdex; j < src.length - (n-1); j++) {
            temp[i] = src[j];
            if (n == 1) {
                System.arraycopy(temp, 0, obj[objLineidx], 0, temp.length);
                objLineidx++;
            } else {
                n--;
                i++;
                combine(src, j+1, i, n, temp);
                n++;
                i--;
            }
        }
    }

    public Object[][] getResult() {
        return obj;
    }

}

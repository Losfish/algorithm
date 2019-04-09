import Jama.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Trilateral {
    private Location location;
    private double x1 = 0, x2 = 0, x3 = 2;
    private double y1 = 0, y2 = 1.3, y3 = 0;
    public static void main(String[] args) {
        Trilateral t1 = new Trilateral();
        Location l1 = t1.getLocation();
        String ans = l1.toString();
        System.out.println(ans);
    }
    public Location getLocation() {
        location = new Location();
        ArrayList<BleBase> uniqueBases = new ArrayList<>();
        BleBase b1 = new BleBase("1", 58);
        BleBase b2 = new BleBase("2", 58);
        BleBase b3 = new BleBase("3", 58);
        uniqueBases.add(b1);
        uniqueBases.add(b2);
        uniqueBases.add(b3);
        return calculate(uniqueBases);
    }

    private Location calculate(List<BleBase> bases) {
        int baseNum = bases.size();
        double distanceArray[] = new double[baseNum];
        String ids[] = new String[baseNum];

        int j = 0;

        for (BleBase base:bases){
            ids[j] = base.getId();
            //distanceArray[j] = base.getDistance();
            j++;
           // System.out.println(ids[j-1] + "," +distanceArray[j-1]);
        }
        distanceArray[0] = 0.96;
        distanceArray[1] = 1.35;
        distanceArray[2] = 1.10;
        int disArrayLen = distanceArray.length;
        double [][] a = new double[baseNum-1][2];
        double [][] b = new double[baseNum-1][1];
        /*初始化a数组*/

        a[0][0] = 2 * (x1 - x3);
        a[0][1] = 2 * (y1 - y3);
        a[1][0] = 2 * (x2 - x3);
        a[1][1] = 2 * (y2 - y3);

        /*初始化b数组*/
        b[0][0] = x1*x1 - x3*x3 + y1*y1 - y3*y3 + distanceArray[2]*distanceArray[2] - distanceArray[0]*distanceArray[0];
        b[1][0] = x2*x2 - x3*x3 + y2*y2 - y3*y3 + distanceArray[2]*distanceArray[2] - distanceArray[1]*distanceArray[1];

        /*将数组封装成矩阵*/
        Matrix b1 = new Matrix(b);
        Matrix a1 = new Matrix(a);

        /*求矩阵的转置*/
        Matrix a2  = a1.transpose();

        /*求矩阵a1与矩阵a1转置矩阵a2的乘积*/
        Matrix tmpMatrix1 = a2.times(a1);
        Matrix reTmpMatrix1 = tmpMatrix1.inverse();
        Matrix tmpMatrix2 = reTmpMatrix1.times(a2);

        /*中间结果乘以最后的b1矩阵*/
        Matrix resultMatrix = tmpMatrix2.times(b1);
        double[][] resultArray = resultMatrix.getArray();
        location.setxAxis(resultArray[0][0]);
        location.setyAxis(resultArray[1][0]);
        return location;
    }
}

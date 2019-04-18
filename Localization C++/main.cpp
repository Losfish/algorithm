#include <iostream>
#include <Eigen/Dense>
using namespace Eigen; 
using namespace std;

typedef struct data {
	double x, y;
	double rssi;
}DATA;

void calcXY(double x1, double y1, double rssi1, double x2, double y2, double rssi2, double x3, double y3, double rssi3, double &x, double &y)
{
	double distanceArray[3];
	distanceArray[0] = rssi1;
	distanceArray[1] = rssi2;
	distanceArray[2] = rssi3;
	MatrixXd A(2, 2); //定义2*2的矩阵
	A << 2 * (x1 - x3), 2 * (y1 - y3),
		2 * (x2 - x3), 2 * (y2 - y3);

	//矩阵的遍历方法
	//double row = A.rows();      //row为R矩阵的行数
	//double col = A.cols();      //col为R矩阵的列数
	//for (int i = 0;i < row;i++)
	//{
	//	cout << A.block(i, 0, 1, col) << endl;
	//}

	MatrixXd B(2, 1); //定义2*1的矩阵
	B << x1 * x1 - x3 * x3 + y1 * y1 - y3 * y3 + distanceArray[2] * distanceArray[2] - distanceArray[0] * distanceArray[0],
		x2*x2 - x3 * x3 + y2 * y2 - y3 * y3 + distanceArray[2] * distanceArray[2] - distanceArray[1] * distanceArray[1];

	/*求矩阵的转置A^T*/
	MatrixXd AT = A.transpose();
	/*求矩阵a与矩阵a转置矩阵aT的乘积 A^T * A */
	MatrixXd tmp_ATxA = AT * A;
	/*(A^T * A)^-1*/
	MatrixXd tmp_ATxA_inverse = tmp_ATxA.inverse();
	/*(A^T * A)^-1 * A^T */
	MatrixXd tmp_Matrix = tmp_ATxA_inverse * AT;
	/* (A^T * A)^-1 * A^T * B */
	MatrixXd ans_Matrix = tmp_Matrix * B;
	x = ans_Matrix(0, 0);
	y = ans_Matrix(1, 0);
}

int main()
{
	DATA Data[3];
	Data[0].x = 0;	Data[0].y = 0;	Data[0].rssi = 0.96;
	Data[1].x = 0;	Data[1].y = 1.3;	Data[1].rssi = 1.35;
	Data[2].x = 2;	Data[2].y = 0;	Data[2].rssi = 1.10;
	double ans_x, ans_y;
	calcXY(Data[0].x, Data[0].y, Data[0].rssi,
			Data[1].x, Data[1].y, Data[1].rssi,
			Data[2].x, Data[2].y, Data[2].rssi,
			ans_x, ans_y);
	cout << ans_x << " " << ans_y << endl;

	return 0;
}

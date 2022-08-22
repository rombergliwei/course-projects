#include <iostream>
#include<iomanip>
using namespace std;

void Hamming(double x)   //目标：得到误差范围内的值
{

	double k,f,f1=0;//k为迭代次数，f是级数和，f1为辅助变量
	for(k=1;k<1000000;k++)
	{
	    f1=f1+1/(k*(k+1)*(k+x));
	}
	f=(1-x)*f1+1;
	//经计算，迭代10的6次方即可满足条件

	cout<<setiosflags(ios::fixed)<<setprecision(1)<<x<<"     ";
	cout<<setiosflags(ios::fixed)<<setprecision(12)<<f<<endl;
}

int main()
{


    for(double x=0.0;x<=300;x+=0.1)
        Hamming(x);


    return 0;
}

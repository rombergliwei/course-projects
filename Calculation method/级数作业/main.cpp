#include <iostream>
#include<iomanip>
using namespace std;

void Hamming(double x)   //Ŀ�꣺�õ���Χ�ڵ�ֵ
{

	double k,f,f1=0;//kΪ����������f�Ǽ����ͣ�f1Ϊ��������
	for(k=1;k<1000000;k++)
	{
	    f1=f1+1/(k*(k+1)*(k+x));
	}
	f=(1-x)*f1+1;
	//�����㣬����10��6�η�������������

	cout<<setiosflags(ios::fixed)<<setprecision(1)<<x<<"     ";
	cout<<setiosflags(ios::fixed)<<setprecision(12)<<f<<endl;
}

int main()
{


    for(double x=0.0;x<=300;x+=0.1)
        Hamming(x);


    return 0;
}

#include<iostream>
#include<cmath>
#include<iomanip>

using namespace std;

double f(double x,double y)
{
	return(-x*x*y*y);
}//δ֪�����ĵ��� 

double Runge_Kutta(double x0,double y0,double xn,double h)
{
		double k1,k2,k3,k4,x,y;
        x=x0;
		y=y0;
        while(fabs(x-xn)>1e-12)
		{
			k1=f(x,y);
			k2=f(x+0.5*h,y+0.5*h*k1);
			k3=f(x+0.5*h,y+0.5*h*k2);
			k4=f(x+h,y+h*k3);
			y=y+h/6*(k1+2*k2+2*k3+k4);
            x=x+h;
		}//������ֻ��һ���ж����� 
		return(y);
}//����4��Runge-Kutta�����ⳣ΢�ַ��̵�ͨ�ó��� 

double Adams(double x0,double y0,double xn,double h)
{
        double k1,k2,k3,k4,x1,x2,y1,y2,y;
		x1=x0+h;
		x2=x0+2*h;
		y1=Runge_Kutta(y0,x0,x1,h);
		y2=Runge_Kutta(y0,x0,x2,h);//ʹ������ķ����ṩ��ֵ 
      while(fabs(x2-xn)>1e-10)
	  {
		k1=f(x2,y2);
		k2=f(x1,y1);
		k3=f(x0,y0);
		y=y1+h/3*(7*k1-2*k2+k3);
		k4=f(x0+3*h,y);
		y=y0+h/4*(3*k4+9*k2);
		x0=x0+h;
		x1=x1+h;
		x2=x2+h;
		y0=y1;
		y1=y2;
		y2=y;
	  }//���ǲ�ͣ������ֵ 
	  return(y2);
}//Adams��ʽ3�׷����ⳣ΢�ַ��̵�ͨ�ó���

int main()
{
	double x0=0,x=1.5,y0=3.0,h=0.2,y1,y;
    int i;
    y=3/(1+x*x*x);//��� 
    cout<<fixed<<left;
	cout<<"Runge-Kutta��:"<<endl;

	for(i=0;i<4;i++)
	{
		h=h/2;//�ı䲽�� 
        y1=Runge_Kutta(x0,y0,x,h);
		cout<<"������"<<setw(6)<<setprecision(4)<<h<<",";
		cout<<"���:"<<setw(14)<<setprecision(12)<<y1<<",";
		cout<<"��"<<setw(14)<<setprecision(12)<<y1-y<<endl;
 }
    cout<<"Adams����"<<endl;
	h=0.2;//�������ó�ֵ 
    for(i=0;i<4;i++)
	{
		h=h/2;
        y1=Adams(x0,y0,x,h);
		cout<<"������"<<setw(6)<<setprecision(4)<<h<<",";
		cout<<"���:"<<setw(14)<<setprecision(12)<<y1<<",";
		cout<<"��"<<setw(14)<<setprecision(12)<<y1-y<<endl;
	}
}

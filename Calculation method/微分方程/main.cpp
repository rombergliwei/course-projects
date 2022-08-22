#include<iostream>
#include<cmath>
#include<iomanip>

using namespace std;

double f(double x,double y)
{
	return(-x*x*y*y);
}//未知函数的导数 

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
		}//不是误差，只是一种判断条件 
		return(y);
}//经典4阶Runge-Kutta方法解常微分方程的通用程序 

double Adams(double x0,double y0,double xn,double h)
{
        double k1,k2,k3,k4,x1,x2,y1,y2,y;
		x1=x0+h;
		x2=x0+2*h;
		y1=Runge_Kutta(y0,x0,x1,h);
		y2=Runge_Kutta(y0,x0,x2,h);//使用上面的方法提供初值 
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
	  }//我们不停的求点的值 
	  return(y2);
}//Adams隐式3阶方法解常微分方程的通用程序

int main()
{
	double x0=0,x=1.5,y0=3.0,h=0.2,y1,y;
    int i;
    y=3/(1+x*x*x);//真解 
    cout<<fixed<<left;
	cout<<"Runge-Kutta法:"<<endl;

	for(i=0;i<4;i++)
	{
		h=h/2;//改变步长 
        y1=Runge_Kutta(x0,y0,x,h);
		cout<<"步长："<<setw(6)<<setprecision(4)<<h<<",";
		cout<<"结果:"<<setw(14)<<setprecision(12)<<y1<<",";
		cout<<"误差："<<setw(14)<<setprecision(12)<<y1-y<<endl;
 }
    cout<<"Adams法："<<endl;
	h=0.2;//重新设置初值 
    for(i=0;i<4;i++)
	{
		h=h/2;
        y1=Adams(x0,y0,x,h);
		cout<<"步长："<<setw(6)<<setprecision(4)<<h<<",";
		cout<<"结果:"<<setw(14)<<setprecision(12)<<y1<<",";
		cout<<"误差："<<setw(14)<<setprecision(12)<<y1-y<<endl;
	}
}

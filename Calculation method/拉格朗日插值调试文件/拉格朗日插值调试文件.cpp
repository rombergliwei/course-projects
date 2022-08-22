#include<stdio.h>
typedef struct stPoint
{
    double x;
    double y;
} Point;
int main(){
	int i; 
	int N[]={5,10,20,40};
	double x[100];//因为我们要定义的数组最多的源 
fx=0.0;
for(i=0;i<=n;i++)
{
    tmp=1.0;
    x[i]=-5+(10/N)*i;
    for(j=0;j<i;j++)
        tmp=tmp*(x-x[j])/(x[i]-x[j]);
    for(j=i+1;j<=n;j++)
        tmp=tmp*(x-x[j])/(x[i]-x[j]);
    fx=fx+tmp*y[i];
}
return fx;
}

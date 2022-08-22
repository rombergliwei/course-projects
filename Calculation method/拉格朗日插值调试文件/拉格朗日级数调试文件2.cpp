#include <stdio.h>
#define MAX 100 //这里因为我们输入的点的个数应该最多在四十个左右但是为了保险而且计算机也不缺空间所以我设置为100个 
typedef struct stPoint
{
    double x;//这里是为了保存输入的x点的值 
    double y;//这里是为了保存我们输入的y点的值 
} Point;

int main()
{
    int n;
	int N[4]={5,10,20,40};//这里的n代表我们将要输入的点的个数 
    int i,j;//这里的 i是代表的第n个插值点，这里的j是为了循环除了i之外的每一个采样点 
    Point points[MAX];//这里是为了给一个名字 
    double x,tmp,lagrange=0;//这里的x就是等会要输入的插值点的数值，在被保存到结构体内之前，我们需要通过一个循环来给结构体里面的x赋值，
	//但是这个数值会先每个保存到x里面再一个个地保存到结构体中，tmp是ppt上面给出来的用来乘的东西，larange是根据拉格朗日函数得出f(x)的值
   points[3].x=5.0;
   printf("%d",points[3].x);
}

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
   
   for(i=0;i<=n;i++)
       
      scanf("%lf%lf",&points[i].x,&points[i].y);
    //输入计算拉格朗日插值多项式的x值
    printf("\n请输入计算拉格朗日插值多项式的x值:");
    scanf("%lf",&x);
    //利用拉格朗日插值公式计算函数x值的对应f(x)
    for(i=0;i<=n;i++)
    {
        for(j=0,tmp=1;j<=n;j++)
        {
            if(j==i)   //去掉xi与xj相等的情况
            continue;  //范德蒙行列式下标就是j!=k,相等分母为０就没意义了
            tmp=tmp*(x-points[j].x)/(points[i].x-points[j].x);//这个就是套公式，没什么难度
            //tmp是拉格朗日基函数
        }
        lagrange=lagrange+tmp*points[i].y; //最后计算基函数*y,全部加起来，就是该x项的拉格朗日函数了
    }  
    //拉格朗日函数计算完毕，代入所求函数x的值，求解就ok了
    printf("\n拉格朗日函数f(%lf)=%lf\n",x,lagrange);
    return 0;
}

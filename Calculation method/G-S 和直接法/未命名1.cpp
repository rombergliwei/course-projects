#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;
const double epsilon=0.00000001;//设定的最小误差 
const int N=9;//我们要计算的是九阶方阵 
int main()
{
	double matrix[N][N]={//这里是要计算的矩阵，可以进行修改 
       {31,-13,0,0,0,-10,0,0,0},
       {-13,35,-9,0,-11,0,0,0,0},
       {0,-9,31,-10,0,0,0,0,0},
       {0,0,-10,79,-30,0,0,0,-9},
       {0,0,0,-30,57,-7,0,-5,0},
       {0,0,0,0,-7,47,-30,0,0},
       {0,0,0,0,0,-30,41,0,0},
       {0,0,0,0,-5,0,0,27,-2},
       {0,0,0,-9,0,0,0,-2,29}

    };
    double b[N]={-15,27,-23,0,-20,12,-7,7,10};//这里是矩阵的右边的值 
    double x[N]={0,0,0,0,0,0,0,0,0};//这里即将输出要计算的结果 
    double w;
    int i,counter,min_counter; 
    int Gauss_Seidel(double matrix[][N],double b[],double x[]);//这里是声明后面要使用的函数，并且输入的依次是二维数组的首地址， 
//一维数组的首地址和x的首地址 
    int SOR(double matrix[][N],double b[],double x[],double w);//这里是用另一种方法的函数，输入的变量多了一个松弛因子 
    cout<<fixed;
    for (i=0;i<N;i++)
    {
    	cout<<"x["<<i<<"]="<<setprecision(12)<<setw(15)<<x[i]<<endl;//指定格式进行x数组的输出 
    }
    cout<<"The total steps of SOR Iteration is:"<<endl;//输出SOR计算的步数 
   for( i=1;i<100;i++)
    {
    	counter=SOR(matrix,b,x,i/50.0);//不停改变松弛因子然后进行计算SOR的计算步数 
    	if (i==1) {w=i/50.0;min_counter=counter;}//如果是第一个，就把前面计算出来的结果赋值给这个数字 
    	else if (counter<min_counter) {w=i/50.0;min_counter=counter;}//此后如果算出来的数值小于第一个数，就更新松弛因子的数值然后使得最小的数值等于这个新计算出来的值 
    }
    cout<<"The best w is "<<setprecision(2)<<w<<endl;//然后循环以后就可以找到最为合适的松弛因子 
    cout<<endl;
    SOR(matrix,b,x,1.18);
    for (i=0;i<N;i++)
    {
    	cout<<"x["<<i<<"]="<<setprecision(12)<<setw(15)<<x[i]<<endl;//指定格式进行x数组的输出 
    }
    Gauss_Seidel(matrix,b,x);
    for (i=0;i<N;i++)
    {
    	cout<<"x["<<i<<"]="<<setprecision(12)<<setw(15)<<x[i]<<endl;//指定格式进行x数组的输出 
    }
}
int Gauss_Seidel(double matrix[][N],double b[],double x[])//下面是GS迭代法求解的函数 
{
	double t,max_error=100;
	double matrix[N][N],y[N];
	int i,j,counter=0;
	for (i=0;i<N;i++)
		for (j=0;j<N;j++)
			matrix[i][j]=matrix[i][j];//先一个一个将传过来的矩阵赋值给这里的新的矩阵 
	for (i=0;i<N;i++) y[i]=b[i];//将传过来的列向量赋值给这里的新的向量 
	for (i=0;i<N;i++) x[i]=0;//将传过来的x再次赋值为零 
	cout<<endl;
	for (i=0;i<N;i++)
	{
		t=matrix[i][i];
		matrix[i][i]=0;//循环行的时候将对角元进行赋值然后这个矩阵的对角元变成零 
		for (j=0;j<N;j++)
		{
			matrix[i][j]=-matrix[i][j]/t;//然后对列进行循环，并且将这一行中的每一个列除以其对角元 
		}
		y[i]=y[i]/t;//并且将右边的那一列的每一个元素也都除以对角元 
	}
	while(max_error >= epsilon)//当我们的结果始终达不到误差的时候 
	{
		max_error=0;//我们将最大的误差设置为零先 
		counter++;//计数器先加，也就是我们开始第一步的计算了 
		for(i=0;i<N;i++)//开始进行行的循环 
		{
			t=x[i];//将解的值赋值给t，然后将解自己的值变成零 
			x[i]=0;
			for(j=0;j<N;j++)//开始进行列的循环 
				x[i]=x[i]+x[j]*matrix[i][j];//然后将列矩阵带入我们已经设置好了的矩阵中，并且加上自己本来的值 
			x[i]=x[i]+y[i];//然后加上右边那列 
			if (fabs(t-x[i])>max_error)//如果一开始解的值和新得到的解的值的差值太大，自然不能作为解 
				max_error=fabs(t-x[i]);//那就重新赋值以方便循环 
		}
	}
	cout<<"The total steps of Gauss_Seidel Iteration is:"<<counter<<endl;//输出总的步数 
	return (1);
}
int SOR(double matrix[][N],double b[],double x[],double w)//下面开始使用SOR方法求解 
{
	double t,max_error=100;
	double matrix[N][N];
	double y[N];
	int i,j,counter=0;
	for (i=0;i<N;i++)
		for (j=0;j<N;j++)
			matrix[i][j]=matrix[i][j];
    for (i=0;i<N;i++) y[i]=b[i];
	for (i=0;i<N;i++) x[i]=0;//同上我们进行新的赋值并进行运算，可以避免将原数组进行修改 
	for (i=0;i<N;i++)
	{
		t=matrix[i][i]; 
		matrix[i][i]=0;//同上 
		for (j=0;j<N;j++)
		{
			matrix[i][j]=-matrix[i][j]/t;
		}
		y[i]=y[i]/t;//同上 
	}
	while(max_error >= epsilon)//当误差比较大的时候 
	{
		max_error=0;//最大的误差先设置为零 
		counter++;//然后计数器加一 
		for(i=0;i<N;i++)
		{
			t=x[i];//然后将解先赋值给前面的 
			x[i]=0;//然后将解设置为零 
			for(j=0;j<N;j++)
				x[i]=x[i]+x[j]*matrix[i][j];//然后我们将解迭代并加上前面的数值结果 
			x[i]=x[i]+y[i];//然后加上自己的右边的列 
			x[i]=(1-w)*t+w*x[i];//然后就是SOR方法自己的特殊性了，就是它自己的这个松弛因子 
			if (fabs(t-x[i])>max_error)//然后如果误差大就赋值让循环继续下去 
				max_error=fabs(t-x[i]);
		}
	}
	cout<<"w="<<setprecision(2)<<w<<"   total_steps:"<<counter<<endl;//然后就可以输出总的步数 
	return (counter);//然后我们把步数返回进行运算 
}


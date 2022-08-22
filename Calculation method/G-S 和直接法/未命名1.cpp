#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;
const double epsilon=0.00000001;//�趨����С��� 
const int N=9;//����Ҫ������ǾŽ׷��� 
int main()
{
	double matrix[N][N]={//������Ҫ����ľ��󣬿��Խ����޸� 
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
    double b[N]={-15,27,-23,0,-20,12,-7,7,10};//�����Ǿ�����ұߵ�ֵ 
    double x[N]={0,0,0,0,0,0,0,0,0};//���Ｔ�����Ҫ����Ľ�� 
    double w;
    int i,counter,min_counter; 
    int Gauss_Seidel(double matrix[][N],double b[],double x[]);//��������������Ҫʹ�õĺ�������������������Ƕ�ά������׵�ַ�� 
//һά������׵�ַ��x���׵�ַ 
    int SOR(double matrix[][N],double b[],double x[],double w);//����������һ�ַ����ĺ���������ı�������һ���ɳ����� 
    cout<<fixed;
    for (i=0;i<N;i++)
    {
    	cout<<"x["<<i<<"]="<<setprecision(12)<<setw(15)<<x[i]<<endl;//ָ����ʽ����x�������� 
    }
    cout<<"The total steps of SOR Iteration is:"<<endl;//���SOR����Ĳ��� 
   for( i=1;i<100;i++)
    {
    	counter=SOR(matrix,b,x,i/50.0);//��ͣ�ı��ɳ�����Ȼ����м���SOR�ļ��㲽�� 
    	if (i==1) {w=i/50.0;min_counter=counter;}//����ǵ�һ�����Ͱ�ǰ���������Ľ����ֵ��������� 
    	else if (counter<min_counter) {w=i/50.0;min_counter=counter;}//�˺�������������ֵС�ڵ�һ�������͸����ɳ����ӵ���ֵȻ��ʹ����С����ֵ��������¼��������ֵ 
    }
    cout<<"The best w is "<<setprecision(2)<<w<<endl;//Ȼ��ѭ���Ժ�Ϳ����ҵ���Ϊ���ʵ��ɳ����� 
    cout<<endl;
    SOR(matrix,b,x,1.18);
    for (i=0;i<N;i++)
    {
    	cout<<"x["<<i<<"]="<<setprecision(12)<<setw(15)<<x[i]<<endl;//ָ����ʽ����x�������� 
    }
    Gauss_Seidel(matrix,b,x);
    for (i=0;i<N;i++)
    {
    	cout<<"x["<<i<<"]="<<setprecision(12)<<setw(15)<<x[i]<<endl;//ָ����ʽ����x�������� 
    }
}
int Gauss_Seidel(double matrix[][N],double b[],double x[])//������GS���������ĺ��� 
{
	double t,max_error=100;
	double matrix[N][N],y[N];
	int i,j,counter=0;
	for (i=0;i<N;i++)
		for (j=0;j<N;j++)
			matrix[i][j]=matrix[i][j];//��һ��һ�����������ľ���ֵ��������µľ��� 
	for (i=0;i<N;i++) y[i]=b[i];//������������������ֵ��������µ����� 
	for (i=0;i<N;i++) x[i]=0;//����������x�ٴθ�ֵΪ�� 
	cout<<endl;
	for (i=0;i<N;i++)
	{
		t=matrix[i][i];
		matrix[i][i]=0;//ѭ���е�ʱ�򽫶Խ�Ԫ���и�ֵȻ���������ĶԽ�Ԫ����� 
		for (j=0;j<N;j++)
		{
			matrix[i][j]=-matrix[i][j]/t;//Ȼ����н���ѭ�������ҽ���һ���е�ÿһ���г�����Խ�Ԫ 
		}
		y[i]=y[i]/t;//���ҽ��ұߵ���һ�е�ÿһ��Ԫ��Ҳ�����ԶԽ�Ԫ 
	}
	while(max_error >= epsilon)//�����ǵĽ��ʼ�մﲻ������ʱ�� 
	{
		max_error=0;//���ǽ������������Ϊ���� 
		counter++;//�������ȼӣ�Ҳ�������ǿ�ʼ��һ���ļ����� 
		for(i=0;i<N;i++)//��ʼ�����е�ѭ�� 
		{
			t=x[i];//�����ֵ��ֵ��t��Ȼ�󽫽��Լ���ֵ����� 
			x[i]=0;
			for(j=0;j<N;j++)//��ʼ�����е�ѭ�� 
				x[i]=x[i]+x[j]*matrix[i][j];//Ȼ���о�����������Ѿ����ú��˵ľ����У����Ҽ����Լ�������ֵ 
			x[i]=x[i]+y[i];//Ȼ������ұ����� 
			if (fabs(t-x[i])>max_error)//���һ��ʼ���ֵ���µõ��Ľ��ֵ�Ĳ�ֵ̫����Ȼ������Ϊ�� 
				max_error=fabs(t-x[i]);//�Ǿ����¸�ֵ�Է���ѭ�� 
		}
	}
	cout<<"The total steps of Gauss_Seidel Iteration is:"<<counter<<endl;//����ܵĲ��� 
	return (1);
}
int SOR(double matrix[][N],double b[],double x[],double w)//���濪ʼʹ��SOR������� 
{
	double t,max_error=100;
	double matrix[N][N];
	double y[N];
	int i,j,counter=0;
	for (i=0;i<N;i++)
		for (j=0;j<N;j++)
			matrix[i][j]=matrix[i][j];
    for (i=0;i<N;i++) y[i]=b[i];
	for (i=0;i<N;i++) x[i]=0;//ͬ�����ǽ����µĸ�ֵ���������㣬���Ա��⽫ԭ��������޸� 
	for (i=0;i<N;i++)
	{
		t=matrix[i][i]; 
		matrix[i][i]=0;//ͬ�� 
		for (j=0;j<N;j++)
		{
			matrix[i][j]=-matrix[i][j]/t;
		}
		y[i]=y[i]/t;//ͬ�� 
	}
	while(max_error >= epsilon)//�����Ƚϴ��ʱ�� 
	{
		max_error=0;//�������������Ϊ�� 
		counter++;//Ȼ���������һ 
		for(i=0;i<N;i++)
		{
			t=x[i];//Ȼ�󽫽��ȸ�ֵ��ǰ��� 
			x[i]=0;//Ȼ�󽫽�����Ϊ�� 
			for(j=0;j<N;j++)
				x[i]=x[i]+x[j]*matrix[i][j];//Ȼ�����ǽ������������ǰ�����ֵ��� 
			x[i]=x[i]+y[i];//Ȼ������Լ����ұߵ��� 
			x[i]=(1-w)*t+w*x[i];//Ȼ�����SOR�����Լ����������ˣ��������Լ�������ɳ����� 
			if (fabs(t-x[i])>max_error)//Ȼ���������͸�ֵ��ѭ��������ȥ 
				max_error=fabs(t-x[i]);
		}
	}
	cout<<"w="<<setprecision(2)<<w<<"   total_steps:"<<counter<<endl;//Ȼ��Ϳ�������ܵĲ��� 
	return (counter);//Ȼ�����ǰѲ������ؽ������� 
}


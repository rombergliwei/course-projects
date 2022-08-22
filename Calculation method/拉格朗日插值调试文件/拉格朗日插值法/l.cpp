#include<stdio.h>
#include<string.h>
#include<math.h>
#include<stdlib.h>
int main(){
	int i,n,j,m,p,q,maxb;
	double x,fx,tmp,max,PI=3.1415926535;
	int N[4]={5,10,20,40};
	double X[100];
	memset(X, 0, sizeof(X));
	double Y[100];
    memset(Y, 0, sizeof(Y));//这里的两个数组主要是为了得到我们要求插值的平面上的点的位置参数 
	double K[600];
	memset(K, 0, sizeof(K));//这里是检验的点的横坐标 
	double L[600];
	memset(L, 0, sizeof(L));//这个是为了存放误差值 
	double M[600];
	memset(M, 0, sizeof(M));//这里是存放检验的点的真正的y值  
	double C[10];
	memset(C, 0, sizeof(C));//这个是为了存放第一组最大的误差 
	double D[10];
	memset(D, 0, sizeof(D));//这个是为了存放第二组最大的误差 
	FILE *outfile1;
	outfile1 = fopen("shujuwenjian1.txt", "w+");//由于老师需要数据结果所以在这里输出为一个文件方便发送  
	FILE *outfile2;
	outfile2 = fopen("shujuwenjian2.txt", "w+");
	for(i=0;i<4;i++){
		n=N[i];
		for(j=0;j<=n;j++){
		X[j]=-5.0+(10.0/n)*j;
		Y[j]=1.0/(1.0+(X[j])*(X[j]));//在这里给xy都赋值了 
	}
	for(m=0;m<=500;m++){
		K[m]=-5.0+m/50.0;
		x=K[m];
		M[m]=1.0/(1.0+x*x);
			fx=0.0;
			for(p=0;p<=n;p++){
			tmp=1.0;			    
		    for(q=0;q<p;q++) 
		    tmp=tmp*(x-X[q])/(X[p]-X[q]);
		    for(q=p+1;q<=n;q++)
		    tmp=tmp*(x-X[q])/(X[p]-X[q]);
		    fx=fx+tmp*Y[p];
			}//这里的循环可完全仿照老师的代码，就是我的数字定义比较多所以在这里就换了字母 
			L[m]=fabs((fx-M[m]));    //这里是求实型表达式的差的绝对值                                                                
	}
			for(j=0;j<=n;j++){
		X[j]=0.0;
		Y[j]=0.0;//在这里是为了使得数值清零，但是不清零应该也没什么大事 ，就意思意思，其他数组也是可以清零的，但是后面使用的时候我都
		//是严格按照需要的个数来复赋值 ，所以都不会出事 
	}
	max=L[0];maxb=0;
	for(j=0;j<502;j++){
		if(L[j]>max){
			max=L[j];maxb=j;
		}
	}//这是c的判断大小的标准格式 
	C[i]=max;//这里给了一个数组来放最大的那个 
	fprintf(outfile1,"n=%d , %3.12e\n",n,C[i]);//这里把我的数据放到那个文件夹里面 
	}
	//从这里开始我们是重复上面的过程只不过稍微有点点变化就不注释了。 
for(i=0;i<4;i++){
		n=N[i];
		for(j=0;j<=n;j++){
		X[j]=-(5.0)*(cos((((2.0)*j+1.0)*PI)/((2.0)*n+2.0)));
		Y[j]=1.0/(1.0+(X[j])*(X[j]));//在这里给xy都赋值了 
	}
	for(m=0;m<=500;m++){
		K[m]=-5.0+m/50.0;
		x=K[m];
		M[m]=1.0/(1.0+x*x);
			fx=0.0;
			for(p=0;p<=n;p++){
			tmp=1.0;			    
		    for(q=0;q<p;q++) 
		    tmp=tmp*(x-X[q])/(X[p]-X[q]);
		    for(q=p+1;q<=n;q++)
		    tmp=tmp*(x-X[q])/(X[p]-X[q]);
		    fx=fx+tmp*Y[p];
			}
			L[m]=fabs((fx-M[m]));                                                                    
	}
			for(j=0;j<=n;j++){
		X[j]=0.0;
		Y[j]=0.0;//在这里是为了使得数值清零，但是不清零应该也没什么大事 
	}
	max=L[0];maxb=0;
	for(j=0;j<502;j++){
		if(L[j]>max){
			max=L[j];maxb=j;
		}
	}
	D[i]=max;
	fprintf(outfile2,"n=%d , %3.12e\n",n,D[i]);
	}
}

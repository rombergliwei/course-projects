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
    memset(Y, 0, sizeof(Y));//���������������Ҫ��Ϊ�˵õ�����Ҫ���ֵ��ƽ���ϵĵ��λ�ò��� 
	double K[600];
	memset(K, 0, sizeof(K));//�����Ǽ���ĵ�ĺ����� 
	double L[600];
	memset(L, 0, sizeof(L));//�����Ϊ�˴�����ֵ 
	double M[600];
	memset(M, 0, sizeof(M));//�����Ǵ�ż���ĵ��������yֵ  
	double C[10];
	memset(C, 0, sizeof(C));//�����Ϊ�˴�ŵ�һ��������� 
	double D[10];
	memset(D, 0, sizeof(D));//�����Ϊ�˴�ŵڶ���������� 
	FILE *outfile1;
	outfile1 = fopen("shujuwenjian1.txt", "w+");//������ʦ��Ҫ���ݽ���������������Ϊһ���ļ����㷢��  
	FILE *outfile2;
	outfile2 = fopen("shujuwenjian2.txt", "w+");
	for(i=0;i<4;i++){
		n=N[i];
		for(j=0;j<=n;j++){
		X[j]=-5.0+(10.0/n)*j;
		Y[j]=1.0/(1.0+(X[j])*(X[j]));//�������xy����ֵ�� 
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
			}//�����ѭ������ȫ������ʦ�Ĵ��룬�����ҵ����ֶ���Ƚ϶�����������ͻ�����ĸ 
			L[m]=fabs((fx-M[m]));    //��������ʵ�ͱ��ʽ�Ĳ�ľ���ֵ                                                                
	}
			for(j=0;j<=n;j++){
		X[j]=0.0;
		Y[j]=0.0;//��������Ϊ��ʹ����ֵ���㣬���ǲ�����Ӧ��Ҳûʲô���� ������˼��˼����������Ҳ�ǿ�������ģ����Ǻ���ʹ�õ�ʱ���Ҷ�
		//���ϸ�����Ҫ�ĸ���������ֵ �����Զ�������� 
	}
	max=L[0];maxb=0;
	for(j=0;j<502;j++){
		if(L[j]>max){
			max=L[j];maxb=j;
		}
	}//����c���жϴ�С�ı�׼��ʽ 
	C[i]=max;//�������һ���������������Ǹ� 
	fprintf(outfile1,"n=%d , %3.12e\n",n,C[i]);//������ҵ����ݷŵ��Ǹ��ļ������� 
	}
	//�����￪ʼ�������ظ�����Ĺ���ֻ������΢�е��仯�Ͳ�ע���ˡ� 
for(i=0;i<4;i++){
		n=N[i];
		for(j=0;j<=n;j++){
		X[j]=-(5.0)*(cos((((2.0)*j+1.0)*PI)/((2.0)*n+2.0)));
		Y[j]=1.0/(1.0+(X[j])*(X[j]));//�������xy����ֵ�� 
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
		Y[j]=0.0;//��������Ϊ��ʹ����ֵ���㣬���ǲ�����Ӧ��Ҳûʲô���� 
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

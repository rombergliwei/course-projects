#include<stdio.h> 
#include<math.h>

double jisuan (int k,double a,double h){
	double g=0.0;
	int j=pow(2,k-2);
	for(int i=1;i<j;i++){
		g+=log(a+(2*i-1)*h);
	}
	return g;
}
main(){
	int M,n=1;
	double a,b,epsilon,f,h;
	double R[1000][1000];
	for(int i=0;i<1000;++i)
    for(int j=0;j<1000;++j)
    R[i][j]=0;
	double H[1000];
	for(int i=0;i<1000;++i)
	H[i]=0;
	scanf("%lf%lf%lf%d",&a,&b,&epsilon,&M);
	h=b-a;
	R[1][1]=(log(a)+log(b))*h/2.0;
	for(int k=2;k<=M;k++){
		H[k-1]=h/pow(2,k-2);
		H[k]=h/pow(2,k-1);
		R[k][1]=(R[k-1][1]+H[k-1]*jisuan(k,a,H[k]))/2.0;
		for(int j=2;j<=k;j++){
				int l=pow(4,j-1);
			R[k][j]=R[k][j-1]+(R[k][j-1]-R[k-1][j-1])/(j-1.0);
		}
		if(fabs(R[k][k]-R[k-1][k-1])<epsilon) break;
	}
	for(int k=2;k<=M;k++){
		for(int j=2;j<=k;j++){
			printf("%lf  ",R[k][j]);
		}
		printf("\n");
	}
}

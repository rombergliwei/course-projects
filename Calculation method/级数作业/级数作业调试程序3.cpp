#include<stdio.h>
#include<math.h>//这里是因为我下面要开根号所以要引入这个头文件 
#include<stdlib.h>//这里由于我要遍历的数字很多我不知道要占用多少空间，所以申请了一个动态的内存 
int main(){
    int i;
    int k;
    double sum[3001];
    double a[7]={0.0,0.5,1.0,sqrt(2),10.0,100.0,300.0};//这前面都是在为下面用到的函数 
    FILE *outfile;//这里定义文件，等下数据结果保存到这里面 
    outfile = fopen("shujuwenjian.txt", "w+");//一个可以写入的文件 
    for (i = 0; i < 7; i++){
        for (int k = 1; k < 1000000; k++){//我们取一个很大的数，加的次数足够多才好满足精度要求，之前加几千次几万次不放心 
            sum[i] += 1 /( k*(k + a[i]));//这里就是要求的数学公式 
            if (1 /( k*(k + a[i])) < 0.0000001){
            printf("%d\n",k);
            break;
        }
	}
        fprintf(outfile,"%6.2f , %16.12e\n",a[i],sum[i]);//这里是按照课件上的要求写入数据 
    }
}

#include<stdio.h>
#include<stdlib.h>//这里由于我要遍历的数字很多我不知道要占用多少空间，所以申请了一个动态的内存 
void main(){
int i;
    int k;
    double *sum = malloc(sizeof(double)*3000)
    double x = 0.0;
    for (i = 0; i < 3001; i++){
        sum[i] = 0.0;
        for (int k = 1; k < 1000000; k++){
            sum[i] += 1 /( k*(k + x));
        }
        x += 0.1;
    }
}

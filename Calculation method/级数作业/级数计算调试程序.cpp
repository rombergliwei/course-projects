#include<stdio.h>
#include<stdlib.h>//����������Ҫ���������ֺܶ��Ҳ�֪��Ҫռ�ö��ٿռ䣬����������һ����̬���ڴ� 
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

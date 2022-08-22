close all;
clear all;
clc;%清理之前的东西
my_fun=@(x)(sin(x));%这里是函数的形式，如果想换函数，只要在这里改变就好了
prompt1='Please input a\n';
a=input(prompt1);
prompt2='Please input b\n';
b=input(prompt2);%这里可以输入积分区间
for n=1:12
    c=2^n;
    h=(b-a)/c;
    x=zeros(1,c+1);%这里初始化
for i=1:c+1
    x(i)=a+(i-1)*h;
end%这里是为了给每个节点赋值
y=sin(x);%赋值
t=0;%初始化
for i=1:c
    t=t+h/2*(y(i)+y(i+1));
end%逐步算出
t%这里是为了输出数值积分值
true=quad(my_fun,1,5);%每次都会输出真值，作为对比
A=true-t%每次都输出数值积分值和真值的差
R=(b-a)/12*h^2*sin(3)%这里是为了算出误差，但是由于这里有h的平方，而三角函数的范围很小，误差公式中的点又是某点，所以我这里取中点
fprintf('%12f',R);
switch n
    case 1
        B=R;
    case 2
        C=R;
    case 3
        D=R;
    case 4
        E=R;
    case 5
        F=R;
    case 6
        G=R;
    case 7
        H=R;
    case 8
        I=R;
    case 9
        J=R;
    case 10
        K=R;
    case 11
        L=R;
    case 12
        M=R;
end
end
O1=log(B/C)/log(2)
O2=log(C/D)/log(2)
O3=log(D/E)/log(2)
O4=log(E/F)/log(2)
O5=log(F/G)/log(2)
O6=log(G/H)/log(2)
O7=log(H/I)/log(2)
O8=log(I/J)/log(2)
O9=log(J/K)/log(2)
O10=log(K/L)/log(2)
O11=log(L/M)/log(2)
O12=log(M/K)/log(1/4)
close all;
clear all;
clc;%����֮ǰ�Ķ���
my_fun=@(x)(sin(x));%�����Ǻ�������ʽ������뻻������ֻҪ������ı�ͺ���
prompt1='Please input a\n';
a=input(prompt1);
prompt2='Please input b\n';
b=input(prompt2);%������������������
for n=1:12
    c=2^n;
    h=(b-a)/c;
    x=zeros(1,c+1);%�����ʼ��
for i=1:c+1
    x(i)=a+(i-1)*h;
end%������Ϊ�˸�ÿ���ڵ㸳ֵ
y=sin(x);%��ֵ
t=0;%��ʼ��
for i=1:c
    t=t+h/2*(y(i)+y(i+1));
end%�����
t%������Ϊ�������ֵ����ֵ
true=quad(my_fun,1,5);%ÿ�ζ��������ֵ����Ϊ�Ա�
A=true-t%ÿ�ζ������ֵ����ֵ����ֵ�Ĳ�
R=(b-a)/12*h^2*sin(3)%������Ϊ���������������������h��ƽ���������Ǻ����ķ�Χ��С����ʽ�еĵ�����ĳ�㣬����������ȡ�е�
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
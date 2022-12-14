clear all;close all;clc;
prompt2='请输入T1\n';%某个完整周期的左端点的横坐标
T1=input(prompt2);
prompt3='请输入T2\n';%某个完整周期的右端点的横坐标
T2=input(prompt3);
prompt4='请输入逼近程度\n';
N=input(prompt4);%这里是给用户自行输入所有的参数
T3=T2-T1;
if isempty(N)
    N = 40;
else
end%这里判断用户有没有默认N
y=T3/2;
fs=500;%这里是运算的一些必要准备
for k = 1:N
    t = -3:1/fs:+3;%给t数值
    a = 2*T3*(2*(cos(k*pi/2))-1+((-1)^(k+1)))/((k^2)*(pi^2));%自己求出傅里叶级数的系数给出表达式
    y = y + a*cos(k*pi*t/T3);
    plot(t,y,'r','LineWidth',2)%设置图像的参数
    xlabel('t');
    ylabel('y');%设置坐标轴
    set(gca,'FontSize',20)%设置坐标示数大小的参数，但没加粗坐标轴，感觉顺眼就这样了
    title('用Fourier级数合成锯齿波');%写标题
    pause(1.0)%暂停一下
    F=getframe;
    im=frame2im(F);
    [I,map]=rgb2ind(im,256);
    if k==1
        imwrite(I,map,'10_1_9的傅里叶级数重构.gif','GIF', 'Loopcount',inf,'DelayTime',0.1);
    else
        imwrite(I,map,'10_1_9的傅里叶级数重构.gif','GIF','WriteMode','append','DelayTime',0.1);
    end
end
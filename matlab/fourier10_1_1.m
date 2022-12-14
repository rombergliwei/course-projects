clear all;close all;clc;
prompt2='请输入T1\n';
T1=input(prompt2);%某个完整周期的左端点的横坐标
prompt3='请输入T2\n';
T2=input(prompt3);%某个完整周期的右端点的横坐标
prompt4='请输入逼近程度\n';
N=input(prompt4);%这里是给用户自行输入所有的参数想要拟合的次数
if isempty(N)
    N = 40;
else
end%这里判断用户有没有默认N
y=pi/4;
fs=500;%这里是运算的一些必要准备
for k = 1:N
    t = -3:1/fs:+3;%给t数值
    a = ((-1)^k-1)/((k^2)*((T2-T1)/2));
    b = ((-1)^(k+1))/k;%自己求出傅里叶级数的系数给出表达式
    y = y + a*cos(k*t) + b*sin(k*t);
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
        imwrite(I,map,'10_1_1的傅里叶级数重构.gif','GIF', 'Loopcount',inf,'DelayTime',0.1);
    else
        imwrite(I,map,'10_1_1的傅里叶级数重构.gif','GIF','WriteMode','append','DelayTime',0.1);
    end
end
clear all;close all;clc;
prompt1='������A\n';%����벨���������ķ�ֵ
A=input(prompt1);
prompt2='������w\n';%����Ƶ��
w=input(prompt2);
prompt4='������ƽ��̶�\n';
N=input(prompt4);%�����Ǹ��û������������еĲ���
if isempty(N)
    N = 40;
else
end%�����ж��û���û��Ĭ��N
fs=500;%�����������һЩ��Ҫ׼��
t = -3:1/fs:+3;%��t��ֵ
y=A/pi+(A/2)*sin(w*t);
for k = 1:N
    a = 2*A/(pi*(1-4*(k^2)));%�Լ��������Ҷ������ϵ���������ʽ
    y = y + a*cos(2*k*w*t);
    plot(t,y,'r','LineWidth',2)%����ͼ��Ĳ���
    xlabel('t');
    ylabel('y');%����������
    set(gca,'FontSize',20)%��������ʾ����С�Ĳ�������û�Ӵ������ᣬ�о�˳�۾�������
    title('��Fourier�����ϳɾ�ݲ�');%д����
    pause(1.0)%��ͣһ��
    F=getframe;
    im=frame2im(F);
    [I,map]=rgb2ind(im,256);
    if k==1
        imwrite(I,map,'10_1_4�벨���������ĸ���Ҷ�ع�.gif','GIF', 'Loopcount',inf,'DelayTime',0.1);
    else
        imwrite(I,map,'10_1_4�벨���������ĸ���Ҷ�ع�.gif','GIF','WriteMode','append','DelayTime',0.1);
    end
end
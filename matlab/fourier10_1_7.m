clear all;close all;clc;
prompt4='������ƽ��̶�\n';
N=input(prompt4);%�����Ǹ��û������������еĲ���
if isempty(N)
    N = 40;
else
end%�����ж��û���û��Ĭ��N
y=1/3;
fs=500;%�����������һЩ��Ҫ׼��
for k = 1:N
    t = -3:1/fs:+3;%��t��ֵ
    a = 1/((k^2)*(pi^2));
    b = -1/(k*pi);%�Լ��������Ҷ������ϵ���������ʽ
    y = y + a*cos(2*k*pi*t) + b*sin(2*k*pi*t);
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
        imwrite(I,map,'10_1_7�ĸ���Ҷ�����ع�.gif','GIF', 'Loopcount',inf,'DelayTime',0.1);
    else
        imwrite(I,map,'10_1_7�ĸ���Ҷ�����ع�.gif','GIF','WriteMode','append','DelayTime',0.1);
    end
end
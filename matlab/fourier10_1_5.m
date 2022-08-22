clear all;close all;clc;
prompt4='������ƽ��̶�\n';
N=input(prompt4);%�����Ǹ��û������������еĲ���
if isempty(N)
    N = 40;
else
end%�����ж��û���û��Ĭ��N
fs=500;%�����������һЩ��Ҫ׼��
t = -6:1/fs:+6;%��t��ֵ
y=0;
for k = 1:N
    a = 2*((-1)^(k-1))/k;%�Լ��������Ҷ������ϵ���������ʽ
    y = y + a*sin(k*t);
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
        imwrite(I,map,'10_1_5�ĸ���Ҷ�����ع�.gif','GIF', 'Loopcount',inf,'DelayTime',0.1);
    else
        imwrite(I,map,'10_1_5�ĸ���Ҷ�����ع�.gif','GIF','WriteMode','append','DelayTime',0.1);
    end
end
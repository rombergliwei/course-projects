clear all;close all;clc;
prompt1='������A\n';
A=input(prompt1);
prompt2='������T1\n';%ĳ���������ڵ���˵�ĺ�����
T1=input(prompt2);
prompt3='������T2\n';%ĳ���������ڵ��Ҷ˵�ĺ�����
T2=input(prompt3);
prompt4='������ƽ��̶�\n';
N=input(prompt4);%�����Ǹ��û������������еĲ���
T3=(T2-T1)/2;
if A>=T3
    disp('���������룡')
    exit%%%%%%%%%!!!!!!!!!!!!!!!!!ע��ע���������Ҫ���Բ����������������һ�������ͻ��˳����У���������������������
else
end
if isempty(N)
    N = 40;
else
end%�����ж��û���û��Ĭ��N
y=1/(2*T3);
fs=500;%�����������һЩ��Ҫ׼��
for k = 1:N
    t = -3:1/fs:+3;%��t��ֵ
    a = 1/(pi*A*k);%�Լ��������Ҷ������ϵ���������ʽ
    y = y + a*sin((k*pi*A)/T3)*cos((k*pi*t)/T3);
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
        imwrite(I,map,'10_1_6����Ҷ�����ع�.gif','GIF', 'Loopcount',inf,'DelayTime',0.1);
    else
        imwrite(I,map,'10_1_6����Ҷ�����ع�.gif','GIF','WriteMode','append','DelayTime',0.1);
    end
end
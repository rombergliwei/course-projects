
// Integral-romberg���������.cpp : �������̨Ӧ�ó������ڵ㡣

//

/*

romberg���������

����Ҳ��Ϊ��ηְ���ٷ������������ι�ʽ��simpson��ʽ��newton-cotes��ʽ֮��Ĺ�ϵ�Ļ����ϣ�

�����һ�ּ��ټ�����ֵķ�������Ϊһ�������㷨�����ڲ����Ӽ�������ǰ������������ľ��ȡ�

�ڵȾ���������£��ü�����������ֵͨ�������ð�������ηְ�ķ������С�

������ǰһ�ηָ�õ��ĺ���ֵ�ڷְ��Ժ���Ȼ���Ա����ã��������ڱ�̡�



���н�����£�

���룺

0

3��14159

�����Romberg- -12��0703

���ӵ�����������߾���ʱ����������

�õ��Ľ������û��ʲô�仯�����Կ�����

��Simpson�������еĽ������һ�£���

Romberg���ٶȸ��졢���ȸ���





*/



#include "stdafx.h"







#include<iostream>

#include<math.h>

#define epsilon 0.00001

#define COUNT 100

using namespace std;



double fun(double x)

{

	return x * x;

}



double Romberg(double a, double b)

{

	int m, n;

	double h, x, s, q, ep;

	double p, *R = new double[COUNT];



	h = b - a;

	R[0] = h * (fun(a) + fun(b)) / 2.0;

	m = 1;

	n = 1;

	ep = epsilon + 1.0;

	while ((ep >= epsilon) && (m < COUNT))

	{

		p = 0.0;

		{

			for (int i = 0; i < n; i++)

			{

				x = a + (i + 0.5)*h;

				p = p + fun(x);

			}

			p = (R[0] + h * p) / 2.0;

			s = 1.0;

			for (int k = 1; k <= m; k++)

			{

				s = 4.0*s;

				q = (s*p - R[k - 1]) / (s - 1.0);

				R[k - 1] = p;

				p = q;

			}

			p = fabs(q - R[m - 1]);

			m = m + 1;

			R[m - 1] = q;

			n = n + n;

			h = h / 2.0;

		}

		return (q);

	}

}



int _tmain(int argc, _TCHAR* argv[])

{

	double a, b;

	cout << "Input a��b��aΪ���ޣ�bΪ����" << endl;

	cin >> a >> b;

	cout << "Romberg=" << Romberg(a, b) << endl;

	system("pause");

	return 0;

}


// Integral-romberg方法求积分.cpp : 定义控制台应用程序的入口点。

//

/*

romberg方法求积分

方法也称为逐次分半加速法。它是在梯形公式，simpson公式和newton-cotes公式之间的关系的基础上，

构造出一种加速计算积分的方法。作为一种外推算法，它在不增加计算量的前提下提高了误差的精度。

在等距基点的情况下，用计算机计算积分值通常都采用吧区间逐次分半的方法进行。

这样，前一次分割得到的函数值在分半以后仍然可以被利用，并且易于编程。



运行结果如下：

输入：

0

3．14159

输出：Romberg- -12．0703

增加迭代次数或提高精度时，程序运行

得到的结果几乎没有什么变化。可以看到，

和Simpson方法运行的结果基本一致，但

Romberg法速度更快、精度更高





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

	cout << "Input a，b：a为下限，b为上限" << endl;

	cin >> a >> b;

	cout << "Romberg=" << Romberg(a, b) << endl;

	system("pause");

	return 0;

}

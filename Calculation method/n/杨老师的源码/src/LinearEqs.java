
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class LinearEqs
{

	int eqType;
	double mAb[][];
	double x0[];
	double epsilon;
	int n;
	double a[];
	double b[];
	double c[];
	double f[];
	boolean initialized;
	DecimalFormat fmt;
	int dataw;

	private String dfmt(double d)
	{
		String s = fmt.format(d);
		for (int i = dataw - s.length(); i > 0; i--)
			s = (new StringBuilder()).append(" ").append(s).toString();

		return s;
	}

	public LinearEqs(String exiii, CompuMethod prnt)
	{
		initialized = true;
		fmt = new DecimalFormat("0.0000");
		dataw = 10;
		try
		{
			Scanner s = new Scanner(new File((new StringBuilder()).append(exiii).append(".txt").toString()));
			eqType = s.nextInt();
			n = s.nextInt();
			if (eqType == 1 || eqType == 3 || eqType == 4 || eqType == 5)
			{
				int col = n + 1;
				if (eqType == 4 || eqType == 5)
					col = n;
				mAb = new double[n][col];
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < col && s.hasNextDouble(); j++)
						mAb[i][j] = s.nextDouble();

				}

			}
			if (eqType == 3)
			{
				x0 = new double[n];
				for (int i = 0; i < n && s.hasNextDouble(); i++)
					x0[i] = s.nextDouble();

				if (s.hasNextDouble())
					epsilon = s.nextDouble();
			}
			if (eqType == 2)
			{
				a = new double[n - 1];
				b = new double[n];
				c = new double[n - 1];
				f = new double[n];
				for (int i = 0; i < n - 1 && s.hasNextDouble(); i++)
					a[i] = s.nextDouble();

				for (int i = 0; i < n && s.hasNextDouble(); i++)
					b[i] = s.nextDouble();

				for (int i = 0; i < n - 1 && s.hasNextDouble(); i++)
					c[i] = s.nextDouble();

				for (int i = 0; i < n && s.hasNextDouble(); i++)
					f[i] = s.nextDouble();

			}
		}
		catch (FileNotFoundException e)
		{
			initialized = false;
			JOptionPane.showMessageDialog(prnt, (new StringBuilder()).append("File not found!\n").append(e).toString());
		}
	}

	double[][] copyOfAb()
	{
		double Ab[][];
		if (eqType == 4)
		{
			Ab = new double[n][n + n];
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
					Ab[i][j] = mAb[i][j];

			}

			for (int i = 0; i < n; i++)
				Ab[i][n + i] = 1.0D;

		} else
		{
			int col = mAb[0].length;
			Ab = new double[n][col];
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < col; j++)
					Ab[i][j] = mAb[i][j];

			}

		}
		return Ab;
	}

	public String printLEqs(double Ab[][])
	{
		String s = "A|b\n";
		for (int i = 0; i < n; i++)
		{
			if (i == 0)
				s = (new StringBuilder()).append(s).append('\u250D').toString();
			else
			if (i < n - 1)
				s = (new StringBuilder()).append(s).append('\u2502').toString();
			else
				s = (new StringBuilder()).append(s).append('\u2515').toString();
			for (int j = 0; j < n; j++)
				s = (new StringBuilder()).append(s).append(dfmt(Ab[i][j])).toString();

			s = (new StringBuilder()).append(s).append(" │ ").append(dfmt(Ab[i][n])).toString();
			if (i == 0)
			{
				s = (new StringBuilder()).append(s).append("┑\n").toString();
				continue;
			}
			if (i < n - 1)
				s = (new StringBuilder()).append(s).append("│\n").toString();
			else
				s = (new StringBuilder()).append(s).append("┙\n").toString();
		}

		return s;
	}

	public String printX(double Ab[][])
	{
		String s = "the result is:\n(";
		for (int i = 0; i < n; i++)
			s = (new StringBuilder()).append(s).append(dfmt(Ab[i][n])).toString();

		s = (new StringBuilder()).append(s).append(" )'").toString();
		return s;
	}

	public String printMat(double Ab[][], int c1, int c2)
	{
		String s = "";
		for (int i = 0; i < n; i++)
		{
			if (i == 0)
				s = (new StringBuilder()).append(s).append('\u250D').toString();
			else
			if (i < n - 1)
				s = (new StringBuilder()).append(s).append('\u2502').toString();
			else
				s = (new StringBuilder()).append(s).append('\u2515').toString();
			for (int j = c1; j < c2; j++)
				s = (new StringBuilder()).append(s).append(dfmt(Ab[i][j])).toString();

			if (i == 0)
			{
				s = (new StringBuilder()).append(s).append(" ┑\n").toString();
				continue;
			}
			if (i < n - 1)
				s = (new StringBuilder()).append(s).append(" │\n").toString();
			else
				s = (new StringBuilder()).append(s).append(" ┙\n").toString();
		}

		return s;
	}

	String gauss(boolean selMel)
	{
		String s = "原始方程组为:\n";
		double Ab[][] = copyOfAb();
		s = (new StringBuilder()).append(s).append(printLEqs(Ab)).toString();
		Matrix.gauss(Ab, selMel);
		s = (new StringBuilder()).append(s).append("高斯消去完成后:\n").toString();
		s = (new StringBuilder()).append(s).append(printLEqs(Ab)).toString();
		for (int i = n - 1; i >= 0; i--)
		{
			for (int j = i + 1; j < n; j++)
				Ab[i][n] -= Ab[i][j] * Ab[j][n];

			Ab[i][n] /= Ab[i][i];
		}

		s = (new StringBuilder()).append(s).append(printX(Ab)).toString();
		return s;
	}

	String gaussJordan(boolean selMel)
	{
		String s = "原始方程组为:\n";
		double Ab[][] = copyOfAb();
		s = (new StringBuilder()).append(s).append(printLEqs(Ab)).toString();
		Matrix.gaussJordan(Ab, selMel);
		s = (new StringBuilder()).append(s).append("高斯约当消去完成后:\n").toString();
		s = (new StringBuilder()).append(s).append(printLEqs(Ab)).toString();
		s = (new StringBuilder()).append(s).append(printX(Ab)).toString();
		return s;
	}

	String invMat()
	{
		double Ab[][] = copyOfAb();
		Matrix.gaussJordan(Ab, true);
		String s = "A=\n";
		s = (new StringBuilder()).append(s).append(printMat(mAb, 0, n)).toString();
		s = (new StringBuilder()).append(s).append("A-1=\n").toString();
		s = (new StringBuilder()).append(s).append(printMat(Ab, n, n + n)).toString();
		return s;
	}

	private void selMainElement(double Ab[][])
	{
		int n = Ab.length;
		for (int i = 0; i < n; i++)
		{
			double max = Math.abs(Ab[i][i]);
			int rmax = i;
			for (int r = i + 1; r < n; r++)
				if (Math.abs(Ab[r][i]) > max)
				{
					max = Math.abs(Ab[r][i]);
					rmax = r;
				}

			if (rmax == i)
				continue;
			for (int j = 0; j < n + 1; j++)
			{
				double t = Ab[i][j];
				Ab[i][j] = Ab[rmax][j];
				Ab[rmax][j] = t;
			}

		}

	}

	String doolittle(boolean selMel)
	{
		String s = "原始方程组为:\n";
		double Ab[][] = copyOfAb();
		s = (new StringBuilder()).append(s).append(printLEqs(Ab)).toString();
		if (selMel)
			selMainElement(Ab);
		Matrix.doolittle(Ab);
		s = (new StringBuilder()).append(s).append("Doolittle分解后:\n").toString();
		s = (new StringBuilder()).append(s).append(printLEqs(Ab)).toString();
		for (int i = 1; i < n; i++)
		{
			for (int k = 0; k < i; k++)
				Ab[i][n] -= Ab[i][k] * Ab[k][n];

		}

		s = (new StringBuilder()).append(s).append("vector y: ").toString();
		s = (new StringBuilder()).append(s).append(printX(Ab)).toString();
		for (int i = n - 1; i >= 0; i--)
		{
			for (int k = i + 1; k < n; k++)
				Ab[i][n] -= Ab[i][k] * Ab[k][n];

			Ab[i][n] /= Ab[i][i];
		}

		s = (new StringBuilder()).append(s).append("\nvector x: ").toString();
		s = (new StringBuilder()).append(s).append(printX(Ab)).toString();
		return s;
	}

	String detMat()
	{
		String s = "原始矩阵为:\n";
		double A[][] = copyOfAb();
		s = (new StringBuilder()).append(s).append(printMat(A, 0, n)).toString();
		Matrix.doolittle(A);
		s = (new StringBuilder()).append(s).append("using Doolittle: A(LU)=\n").toString();
		s = (new StringBuilder()).append(s).append(printMat(A, 0, n)).toString();
		double det = 1.0D;
		for (int i = 0; i < n; i++)
			det *= A[i][i];

		s = (new StringBuilder()).append(s).append("det(A)=").append(dfmt(det)).append("\n").toString();
		A = copyOfAb();
		Matrix.crout(A);
		s = (new StringBuilder()).append(s).append("using Crout: A(LU)=\n").toString();
		s = (new StringBuilder()).append(s).append(printMat(A, 0, n)).toString();
		det = 1.0D;
		for (int i = 0; i < n; i++)
			det *= A[i][i];

		s = (new StringBuilder()).append(s).append("det(A)=").append(dfmt(det)).append("\n").toString();
		return s;
	}

	String crout(boolean selMel)
	{
		String s = "原始方程组为:\n";
		double Ab[][] = copyOfAb();
		s = (new StringBuilder()).append(s).append(printLEqs(Ab)).toString();
		if (selMel)
			selMainElement(Ab);
		Matrix.crout(Ab);
		s = (new StringBuilder()).append(s).append("Crout分解后:\n").toString();
		s = (new StringBuilder()).append(s).append(printLEqs(Ab)).toString();
		for (int i = 0; i < n; i++)
		{
			for (int k = 0; k < i; k++)
				Ab[i][n] -= Ab[i][k] * Ab[k][n];

			Ab[i][n] /= Ab[i][i];
		}

		s = (new StringBuilder()).append(s).append("vector y: ").toString();
		s = (new StringBuilder()).append(s).append(printX(Ab)).toString();
		for (int i = n - 2; i >= 0; i--)
		{
			for (int k = i + 1; k < n; k++)
				Ab[i][n] -= Ab[i][k] * Ab[k][n];

		}

		s = (new StringBuilder()).append(s).append("\nvector x: ").toString();
		s = (new StringBuilder()).append(s).append(printX(Ab)).toString();
		return s;
	}

	String threeTriagonal()
	{
		String s = "原始方程组为:\n";
		s = (new StringBuilder()).append(s).append("a:(").toString();
		for (int i = 0; i < a.length; i++)
			s = (new StringBuilder()).append(s).append(dfmt(a[i])).toString();

		s = (new StringBuilder()).append(s).append(" )\n").toString();
		s = (new StringBuilder()).append(s).append("b:(").toString();
		for (int i = 0; i < b.length; i++)
			s = (new StringBuilder()).append(s).append(dfmt(b[i])).toString();

		s = (new StringBuilder()).append(s).append(" )\n").toString();
		s = (new StringBuilder()).append(s).append("c:(").toString();
		for (int i = 0; i < c.length; i++)
			s = (new StringBuilder()).append(s).append(dfmt(c[i])).toString();

		s = (new StringBuilder()).append(s).append(" )\n").toString();
		s = (new StringBuilder()).append(s).append("f:(").toString();
		for (int i = 0; i < f.length; i++)
			s = (new StringBuilder()).append(s).append(dfmt(f[i])).toString();

		s = (new StringBuilder()).append(s).append(" )'\n").toString();
		f[0] /= b[0];
		for (int i = 1; i < n; i++)
		{
			c[i - 1] /= b[i - 1];
			b[i] -= a[i - 1] * c[i - 1];
			f[i] = (f[i] - a[i - 1] * f[i - 1]) / b[i];
		}

		for (int i = n - 2; i >= 0; i--)
			f[i] -= c[i] * f[i + 1];

		s = (new StringBuilder()).append(s).append("the result is:\n(").toString();
		for (int i = 0; i < n; i++)
			s = (new StringBuilder()).append(s).append(dfmt(f[i])).toString();

		s = (new StringBuilder()).append(s).append(" )'\n").toString();
		return s;
	}

	String iterJacobi()
	{
		String s = "原始方程组为:\n";
		s = (new StringBuilder()).append(s).append(printLEqs(mAb)).toString();
		double y[] = new double[n];
		double x[] = new double[n];
		int k = 0;
		s = (new StringBuilder()).append(s).append("Jacobi迭代的结果:\n  k").toString();
		for (int i = 0; i < n; i++)
			s = (new StringBuilder()).append(s).append("    x").append(i + 1).append("(k)").toString();

		s = (new StringBuilder()).append(s).append("\n").append(k <= 9 ? "  " : " ").append(k).toString();
		for (int i = 0; i < n; i++)
		{
			s = (new StringBuilder()).append(s).append(dfmt(x0[i])).toString();
			x[i] = x0[i];
		}

		s = (new StringBuilder()).append(s).append("\n").toString();
		double e;
		do
		{
			e = 0.0D;
			if (k > 0)
			{
				for (int i = 0; i < n; i++)
					x[i] = y[i];

			}
			for (int i = 0; i < n; i++)
			{
				y[i] = mAb[i][n];
				for (int j = 0; j < n; j++)
					if (j != i)
						y[i] -= mAb[i][j] * x[j];

				y[i] /= mAb[i][i];
				double exy = Math.abs(x[i] - y[i]);
				if (exy > e)
					e = exy;
			}

			k++;
			s = (new StringBuilder()).append(s).append(k <= 9 ? "  " : " ").append(k).toString();
			for (int i = 0; i < n; i++)
				s = (new StringBuilder()).append(s).append(dfmt(y[i])).toString();

			s = (new StringBuilder()).append(s).append("\n").toString();
		} while (e > epsilon && k < 100);
		return s;
	}

	String iterGaussSeidel()
	{
		String s = "原始方程组为:\n";
		s = (new StringBuilder()).append(s).append(printLEqs(mAb)).toString();
		double x[] = new double[n];
		int k = 0;
		s = (new StringBuilder()).append(s).append("Gauss Seidel迭代的结果:\n  k").toString();
		for (int i = 0; i < n; i++)
			s = (new StringBuilder()).append(s).append("    x").append(i + 1).append("(k)").toString();

		s = (new StringBuilder()).append(s).append("\n").append(k <= 9 ? "  " : " ").append(k).toString();
		for (int i = 0; i < n; i++)
		{
			s = (new StringBuilder()).append(s).append(dfmt(x0[i])).toString();
			x[i] = x0[i];
		}

		s = (new StringBuilder()).append(s).append("\n").toString();
		double e;
		do
		{
			e = 0.0D;
			for (int i = 0; i < n; i++)
			{
				double t = x[i];
				x[i] = mAb[i][n];
				for (int j = 0; j < n; j++)
					if (j != i)
						x[i] -= mAb[i][j] * x[j];

				x[i] /= mAb[i][i];
				double ex = Math.abs(x[i] - t);
				if (ex > e)
					e = ex;
			}

			k++;
			s = (new StringBuilder()).append(s).append(k <= 9 ? "  " : " ").append(k).toString();
			for (int i = 0; i < n; i++)
				s = (new StringBuilder()).append(s).append(dfmt(x[i])).toString();

			s = (new StringBuilder()).append(s).append("\n").toString();
		} while (e > epsilon && k < 100);
		return s;
	}

	public static double[] threeTriagonal(double a[], double b[], double c[], double f[])
	{
		int n = f.length;
		f[0] /= b[0];
		for (int i = 1; i < n; i++)
		{
			c[i - 1] /= b[i - 1];
			b[i] -= a[i - 1] * c[i - 1];
			f[i] = (f[i] - a[i - 1] * f[i - 1]) / b[i];
		}

		for (int i = n - 2; i >= 0; i--)
			f[i] -= c[i] * f[i + 1];

		return f;
	}

	public static double[] gauss(double Ab[][])
	{
		int n = Ab.length;
		double res[] = new double[n];
		Matrix.gauss(Ab, true);
		for (int i = n - 1; i >= 0; i--)
		{
			for (int j = i + 1; j < n; j++)
				Ab[i][n] -= Ab[i][j] * Ab[j][n];

			Ab[i][n] /= Ab[i][i];
			res[i] = Ab[i][n];
		}

		return res;
	}
}

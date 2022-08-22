
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Interpolation
{

	int type;
	int n;
	double x[];
	double y[];
	double m[];
	double dq[];
	int boundType;
	double b0;
	double bn;
	boolean initialized;
	double pfA[];
	double alpha[];
	double beta[];
	double aOfP[];
	DecimalFormat fmt;
	int dataw;

	private String dfmt(double d)
	{
		String s = fmt.format(d);
		for (int i = dataw - s.length(); i > 0; i--)
			s = (new StringBuilder()).append(" ").append(s).toString();

		return s;
	}

	public Interpolation(double x[], double y[])
	{
		initialized = true;
		fmt = new DecimalFormat("0.0000");
		dataw = 10;
		this.x = x;
		this.y = y;
		type = 1;
		n = x.length;
	}

	public Interpolation(File f, CompuMethod prnt)
	{
		initialized = true;
		fmt = new DecimalFormat("0.0000");
		dataw = 10;
		try
		{
			Scanner s = new Scanner(f);
			type = s.nextInt();
			n = s.nextInt();
			x = new double[n];
			y = new double[n];
			for (int i = 0; i < n && s.hasNextDouble(); i++)
				x[i] = s.nextDouble();

			for (int i = 0; i < n && s.hasNextDouble(); i++)
				y[i] = s.nextDouble();

			if (type == 2)
			{
				m = new double[n];
				for (int i = 0; i < n && s.hasNextDouble(); i++)
					m[i] = s.nextDouble();

			}
			if (type == 3)
			{
				if (s.hasNextInt())
					boundType = s.nextInt();
				if (s.hasNextDouble())
					b0 = s.nextDouble();
				if (s.hasNextDouble())
					bn = s.nextDouble();
			}
		}
		catch (FileNotFoundException e)
		{
			initialized = false;
			JOptionPane.showMessageDialog(prnt, (new StringBuilder()).append("File not found!\n").append(e).toString());
		}
	}

	public Interpolation(String exiii, CompuMethod prnt)
	{
		this(new File((new StringBuilder()).append(exiii).append(".txt").toString()), prnt);
	}

	public double[] getX()
	{
		return x;
	}

	public double[] getY()
	{
		return y;
	}

	public double interpoLagrange(double xx)
	{
		double ln = 0.0D;
		for (int i = 0; i < n; i++)
		{
			double p = 1.0D;
			for (int j = 0; j < n; j++)
				if (j != i)
					p *= (xx - x[j]) / (x[i] - x[j]);

			ln += p * y[i];
		}

		return ln;
	}

	public double interpoNewton(double xx)
	{
		return interpoNewton(xx, n - 1);
	}

	public double interpoNewton(double xx, int od)
	{
		if (dq == null)
			dq = calculateDQ();
		double nn = 0.0D;
		for (int i = 0; i <= od; i++)
		{
			double p = 1.0D;
			for (int j = 0; j < i; j++)
				p *= xx - x[j];

			nn += dq[i] * p;
		}

		return nn;
	}

	private double[] calculateDQ()
	{
		double f[] = new double[n];
		for (int i = 0; i < n; i++)
			f[i] = y[i];

		for (int od = 1; od < n; od++)
		{
			for (int i = n - 1; i >= od; i--)
				f[i] = (f[i] - f[i - 1]) / (x[i] - x[i - od]);

		}

		return f;
	}

	public String getDQ()
	{
		String s = null;
		if (dq != null)
		{
			s = "差商为：";
			double arr$[] = dq;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++)
			{
				double d = arr$[i$];
				s = (new StringBuilder()).append(s).append(dfmt(d)).toString();
			}

			s = (new StringBuilder()).append(s).append("\n").toString();
		}
		return s;
	}

	public double interpoSegLinear(double xx)
	{
		int ind = findSeg(xx);
		if (ind == -1)
			return 0.0D;
		else
			return ((xx - x[ind + 1]) / (x[ind] - x[ind + 1])) * y[ind] + ((xx - x[ind]) / (x[ind + 1] - x[ind])) * y[ind + 1];
	}

	private int findSeg(double xx)
	{
		int ind = -1;
		int i = 0;
		do
		{
			if (i >= n)
				break;
			if (xx < x[i])
			{
				ind = i - 1;
				break;
			}
			i++;
		} while (true);
		if (xx == x[n - 1])
			ind = n - 2;
		return ind;
	}

	public double interpoSeg3Hermite(double xx)
	{
		int ind = findSeg(xx);
		if (ind == -1)
		{
			return 0.0D;
		} else
		{
			double x0 = x[ind];
			double x1 = x[ind + 1];
			double h0 = (1.0D + (2D * (xx - x0)) / (x1 - x0)) * Math.pow((xx - x1) / (x0 - x1), 2D);
			double h1 = (1.0D + (2D * (xx - x1)) / (x0 - x1)) * Math.pow((xx - x0) / (x1 - x0), 2D);
			double H0 = (xx - x0) * Math.pow((xx - x1) / (x0 - x1), 2D);
			double H1 = (xx - x1) * Math.pow((xx - x0) / (x1 - x0), 2D);
			return h0 * y[ind] + h1 * y[ind + 1] + H0 * m[ind] + H1 * m[ind + 1];
		}
	}

	public double interpoSpline(double xx)
	{
		if (m == null)
			m = calculateM();
		return interpoSeg3Hermite(xx);
	}

	public String getM4Spline()
	{
		String s = null;
		if (m != null)
		{
			s = "样条插值节点的一阶导数为：\n";
			double arr$[] = m;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++)
			{
				double d = arr$[i$];
				s = (new StringBuilder()).append(s).append(dfmt(d)).toString();
			}

			s = (new StringBuilder()).append(s).append("\n").toString();
		}
		return s;
	}

	private double[] calculateM()
	{
		double mat[][] = new double[4][];
		mat[0] = new double[n - 1];
		mat[1] = new double[n];
		mat[2] = new double[n - 1];
		mat[3] = new double[n];
		for (int i = 1; i < n - 1; i++)
		{
			double hi = x[i + 1] - x[i];
			double hi_1 = x[i] - x[i - 1];
			mat[0][i - 1] = hi / (hi + hi_1);
			mat[1][i] = 2D;
			mat[2][i] = hi_1 / (hi + hi_1);
			mat[3][i] = 3D * ((mat[2][i] * (y[i + 1] - y[i])) / hi + (mat[0][i - 1] * (y[i] - y[i - 1])) / hi_1);
		}

		switch (boundType)
		{
		case 1: // '\001'
			mat[1][0] = 1.0D;
			mat[2][0] = 0.0D;
			mat[3][0] = b0;
			mat[0][n - 2] = 0.0D;
			mat[1][n - 1] = 1.0D;
			mat[3][n - 1] = bn;
			break;

		case 2: // '\002'
			mat[1][0] = 2D;
			mat[2][0] = 1.0D;
			mat[3][0] = (3D * (y[1] - y[0])) / (x[1] - x[0]) - ((x[1] - x[0]) * b0) / 2D;
			mat[0][n - 2] = 1.0D;
			mat[1][n - 1] = 2D;
			mat[3][n - 1] = (3D * (y[n - 1] - y[n - 2])) / (x[n - 1] - x[n - 2]) - ((x[n - 1] - x[n - 2]) * bn) / 2D;
			break;
		}
		return LinearEqs.threeTriagonal(mat[0], mat[1], mat[2], mat[3]);
	}

	public void polyFit(int m)
	{
		if (pfA != null && pfA.length == m + 1)
			return;
		int nn = m + 1;
		double Ab[][] = new double[nn][];
		for (int i = 0; i < nn; i++)
			Ab[i] = new double[nn + 1];

		for (int i = 0; i < nn; i++)
		{
			for (int j = i; j < nn; j++)
			{
				Ab[i][j] = prodxx(i, j);
				if (j != i)
					Ab[j][i] = Ab[i][j];
			}

			Ab[i][nn] = prodxy(i);
		}

		pfA = LinearEqs.gauss(Ab);
	}

	private double prodxx(int j, int k)
	{
		double s = 0.0D;
		for (int i = 0; i < n; i++)
			s += Math.pow(x[i], j + k);

		return s;
	}

	private double prodxy(int j)
	{
		double s = 0.0D;
		for (int i = 0; i < n; i++)
			s += Math.pow(x[i], j) * y[i];

		return s;
	}

	public double yOfPolyFit(double xx)
	{
		int n = pfA.length;
		double yy = pfA[n - 1];
		for (int i = 0; i < n - 1; i++)
			yy = yy * xx + pfA[n - i - 2];

		return yy;
	}

	public String getA4PolyFit()
	{
		String s = null;
		if (pfA != null)
		{
			s = "多项式拟合系数为：";
			double arr$[] = pfA;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++)
			{
				double d = arr$[i$];
				s = (new StringBuilder()).append(s).append(dfmt(d)).toString();
			}

			s = (new StringBuilder()).append(s).append("\n").toString();
		}
		return s;
	}

	public void polyFitX(int m)
	{
		if (aOfP != null && aOfP.length == m + 1)
			return;
		alpha = new double[m];
		beta = new double[m - 1];
		aOfP = new double[m + 1];
		alpha[0] = getAlpha(0);
		for (int i = 1; i < m; i++)
		{
			alpha[i] = getAlpha(i);
			beta[i - 1] = getBeta(i - 1);
		}

		for (int i = 0; i <= m; i++)
			aOfP[i] = getAofP(i);

	}

	private double p(int i, double x)
	{
		if (i == 0)
			return 1.0D;
		if (i == 1)
			return x - alpha[0];
		else
			return (x - alpha[i - 1]) * p(i - 1, x) - beta[i - 2] * p(i - 2, x);
	}

	private double getAlpha(int k)
	{
		double s1 = 0.0D;
		double s2 = 0.0D;
		for (int i = 0; i < n; i++)
		{
			double pk = p(k, x[i]);
			s1 += x[i] * pk * pk;
			s2 += pk * pk;
		}

		return s1 / s2;
	}

	private double getBeta(int k)
	{
		double s1 = 0.0D;
		double s2 = 0.0D;
		for (int i = 0; i < n; i++)
		{
			double pk = p(k, x[i]);
			double pk1 = p(k + 1, x[i]);
			s1 += pk1 * pk1;
			s2 += pk * pk;
		}

		return s1 / s2;
	}

	private double getAofP(int k)
	{
		double s1 = 0.0D;
		double s2 = 0.0D;
		for (int i = 0; i < n; i++)
		{
			double pk = p(k, x[i]);
			s1 += pk * y[i];
			s2 += pk * pk;
		}

		return s1 / s2;
	}

	public double yOfPolyFitX(double xx)
	{
		int n = aOfP.length;
		double yy = 0.0D;
		for (int i = 0; i < n; i++)
			yy += aOfP[i] * p(i, xx);

		return yy;
	}

	public String getA4PolyFitX()
	{
		String s = null;
		if (pfA != null)
		{
			s = "正交多项式拟合：\nalpha:";
			double arr$[] = alpha;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++)
			{
				double d = arr$[i$];
				s = (new StringBuilder()).append(s).append(dfmt(d)).toString();
			}

			s = (new StringBuilder()).append(s).append("\nbeta:").toString();
			arr$ = beta;
			len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++)
			{
				double d = arr$[i$];
				s = (new StringBuilder()).append(s).append(dfmt(d)).toString();
			}

			s = (new StringBuilder()).append(s).append("\n系数为:").toString();
			arr$ = aOfP;
			len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++)
			{
				double d = arr$[i$];
				s = (new StringBuilder()).append(s).append(dfmt(d)).toString();
			}

			s = (new StringBuilder()).append(s).append("\n").toString();
		}
		return s;
	}
}

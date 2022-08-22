

class Fun345 extends Function
{

	public Fun345(double x0, double e, int m)
	{
		super(x0, e);
		super.m = m;
		setFunName("x*x*x-x*x-x+1=0");
	}

	public double f(double x)
	{
		return (x * x * x - x * x - x) + 1.0D;
	}

	double df(double x)
	{
		return 3D * x * x - 2D * x - 1.0D;
	}

	double d2f(double x)
	{
		return 6D * x - 2D;
	}

	public double phiNt(double x)
	{
		return x - f(x) / df(x);
	}

	public double phiNt1(double x)
	{
		return x - ((double)m * f(x)) / df(x);
	}

	public double phiNt2(double x)
	{
		return x - (f(x) * df(x)) / (df(x) * df(x) - f(x) * d2f(x));
	}
}

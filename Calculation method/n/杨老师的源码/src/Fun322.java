
class Fun322 extends Function
{

	public Fun322(double a, double b, double e)
	{
		super(a, b, e);
		setFunName("x*x*x-x-1=0");
	}

	public Fun322(double x0, double e)
	{
		super(x0, e);
		setFunName("x*x*x-x-1=0");
		setIterFunName("x=pow(x+1,1/3)");
	}

	public double f(double x)
	{
		return x * x * x - x - 1.0D;
	}

	double df(double x)
	{
		return 3D * x * x - 1.0D;
	}

	public double phi(double x)
	{
		return Math.pow(x + 1.0D, 0.33333333333333331D);
	}

	public double phi(double x0, double x1)
	{
		return x1 - (f(x1) * (x1 - x0)) / (f(x1) - f(x0));
	}

	public double phiNt(double x)
	{
		return x - f(x) / df(x);
	}
}

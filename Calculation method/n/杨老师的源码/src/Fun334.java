
class Fun334 extends Function
{

	public Fun334(double a, double b, double e)
	{
		super(a, b, e);
	}

	public Fun334(double x0, double e)
	{
		super(x0, e);
		setIterFunName("x=pow(1+x*x,1/3)");
	}

	public double phi(double x)
	{
		return Math.pow(1.0D + x * x, 0.33333333333333331D);
	}

	public double f(double x)
	{
		return 0.0D;
	}
}


class Fun321 extends Function
{

	public Fun321(double a, double b, double e)
	{
		super(a, b, e);
		setFunName("sin(x)-x*x/4=0");
	}

	public Fun321(double x0, double e)
	{
		super(x0, e);
	}

	public double f(double x)
	{
		return Math.sin(x) - (x * x) / 4D;
	}
}

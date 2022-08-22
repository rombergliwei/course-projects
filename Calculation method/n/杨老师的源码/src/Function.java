
public abstract class Function
{

	double a;
	double b;
	double x0;
	double e;
	int m;
	String funName;
	String iterFunName;

	public Function(double a, double b, double x0, double e)
	{
		this.a = a;
		this.b = b;
		this.x0 = x0;
		this.e = e;
	}

	public Function(double a, double b, double e)
	{
		this(a, b, 0.0D, e);
	}

	public Function(double x0, double e)
	{
		this(0.0D, 0.0D, x0, e);
	}

	int getPrecision()
	{
		return (int)(-Math.log10(e) + 4D);
	}

	public double phi(double x)
	{
		return 0.0D;
	}

	public double phi(double x0, double x1)
	{
		return 0.0D;
	}

	public double phiNt(double x)
	{
		return 0.0D;
	}

	public double phiNt1(double x)
	{
		return 0.0D;
	}

	public double phiNt2(double x)
	{
		return 0.0D;
	}

	public void setFunName(String n)
	{
		funName = n;
	}

	public void setIterFunName(String n)
	{
		iterFunName = n;
	}

	public String getFunName()
	{
		return funName;
	}

	public String getIterFunName()
	{
		return iterFunName;
	}

	public abstract double f(double d);
}


import java.text.DecimalFormat;

public class RootOfEquation
{

	public RootOfEquation()
	{
	}

	public static String bisect(Function fun)
	{
		int k = 0;
		int p = fun.getPrecision();
		double a = fun.a;
		double b = fun.b;
		double y1 = fun.f(a);
		double y2 = fun.f(b);
		String pad = "";
		String fmt = "0.";
		for (int i = 0; i < p; i++)
		{
			pad = (new StringBuilder()).append(pad).append(" ").toString();
			fmt = (new StringBuilder()).append(fmt).append("0").toString();
		}

		DecimalFormat dfmt = new DecimalFormat(fmt);
		DecimalFormat dfmt2 = new DecimalFormat("00");
		String s = (new StringBuilder()).append("方程：").append(fun.getFunName()).toString();
		if (y1 * y2 > 0.0D)
		{
			s = (new StringBuilder()).append(s).append("\nNo root in this interval.").toString();
			return s;
		}
		s = (new StringBuilder()).append(s).append("\n k").append(pad).append("ak").append(pad).append("bk").append(pad).append("xk").append(pad).append("f(xk)").toString();
		for (; Math.abs(b - a) > fun.e && k < 100; k++)
		{
			double x = (a + b) / 2D;
			double y = fun.f(x);
			s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(a < 0.0D ? " " : "  ").append(dfmt.format(a)).append(b < 0.0D ? " " : "  ").append(dfmt.format(b)).append(x < 0.0D ? " " : "  ").append(dfmt.format(x)).append(y < 0.0D ? " " : "  ").append(dfmt.format(y)).toString();
			if (y1 * y < 0.0D)
			{
				b = x;
			} else
			{
				a = x;
				y1 = y;
			}
		}

		return s;
	}

	public static String fixedPtIter(Function fun)
	{
		int k = 0;
		int p = fun.getPrecision();
		double x0 = fun.x0;
		double x1 = x0;
		String pad = "  ";
		String fmt = "0.";
		for (int i = 0; i < p; i++)
		{
			pad = (new StringBuilder()).append(pad).append(" ").toString();
			fmt = (new StringBuilder()).append(fmt).append("0").toString();
		}

		DecimalFormat dfmt = new DecimalFormat(fmt);
		DecimalFormat dfmt2 = new DecimalFormat("00");
		String s = (new StringBuilder()).append("迭代方程：").append(fun.getIterFunName()).toString();
		s = (new StringBuilder()).append(s).append("\nk").append(pad).append("xk").toString();
		s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x0 < 0.0D ? " " : "  ").append(dfmt.format(x0)).toString();
		do
		{
			k++;
			x0 = x1;
			x1 = fun.phi(x0);
			s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x1 < 0.0D ? " " : "  ").append(dfmt.format(x1)).toString();
		} while (Math.abs(x0 - x1) > fun.e && k < 100);
		return s;
	}

	public static String newtonIter(Function fun)
	{
		int k = 0;
		int p = fun.getPrecision();
		double x0 = fun.x0;
		double x1 = x0;
		String pad = "  ";
		String fmt = "0.";
		for (int i = 0; i < p; i++)
		{
			pad = (new StringBuilder()).append(pad).append(" ").toString();
			fmt = (new StringBuilder()).append(fmt).append("0").toString();
		}

		DecimalFormat dfmt = new DecimalFormat(fmt);
		DecimalFormat dfmt2 = new DecimalFormat("00");
		String s = (new StringBuilder()).append("方程：").append(fun.getFunName()).toString();
		s = (new StringBuilder()).append(s).append("\nk").append(pad).append("xk").toString();
		s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x0 < 0.0D ? " " : "  ").append(dfmt.format(x0)).toString();
		do
		{
			k++;
			x0 = x1;
			x1 = fun.phiNt(x0);
			s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x1 < 0.0D ? " " : "  ").append(dfmt.format(x1)).toString();
		} while (Math.abs(x0 - x1) > fun.e && k < 100);
		return s;
	}

	public static String newton1Iter(Function fun)
	{
		int k = 0;
		int p = fun.getPrecision();
		double x0 = fun.x0;
		double x1 = x0;
		String pad = "  ";
		String fmt = "0.";
		for (int i = 0; i < p; i++)
		{
			pad = (new StringBuilder()).append(pad).append(" ").toString();
			fmt = (new StringBuilder()).append(fmt).append("0").toString();
		}

		DecimalFormat dfmt = new DecimalFormat(fmt);
		DecimalFormat dfmt2 = new DecimalFormat("00");
		String s = (new StringBuilder()).append("方程：").append(fun.getFunName()).toString();
		s = (new StringBuilder()).append(s).append("\nk").append(pad).append("xk").toString();
		s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x0 < 0.0D ? " " : "  ").append(dfmt.format(x0)).toString();
		do
		{
			k++;
			x0 = x1;
			x1 = fun.phiNt1(x0);
			s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x1 < 0.0D ? " " : "  ").append(dfmt.format(x1)).toString();
		} while (Math.abs(x0 - x1) > fun.e && k < 100);
		return s;
	}

	public static String newton2Iter(Function fun)
	{
		int k = 0;
		int p = fun.getPrecision();
		double x0 = fun.x0;
		double x1 = x0;
		String pad = "  ";
		String fmt = "0.";
		for (int i = 0; i < p; i++)
		{
			pad = (new StringBuilder()).append(pad).append(" ").toString();
			fmt = (new StringBuilder()).append(fmt).append("0").toString();
		}

		DecimalFormat dfmt = new DecimalFormat(fmt);
		DecimalFormat dfmt2 = new DecimalFormat("#0");
		String s = (new StringBuilder()).append("方程：").append(fun.getFunName()).toString();
		s = (new StringBuilder()).append(s).append("\nk").append(pad).append("xk").toString();
		s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x0 < 0.0D ? " " : "  ").append(dfmt.format(x0)).toString();
		do
		{
			k++;
			x0 = x1;
			x1 = fun.phiNt2(x0);
			s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x1 < 0.0D ? " " : "  ").append(dfmt.format(x1)).toString();
		} while (Math.abs(x0 - x1) > fun.e && k < 100);
		return s;
	}

	public static String chordInter(Function fun)
	{
		int k = 0;
		int p = fun.getPrecision();
		double x0 = fun.a;
		double x1 = fun.b;
		String pad = "  ";
		String fmt = "0.";
		for (int i = 0; i < p; i++)
		{
			pad = (new StringBuilder()).append(pad).append(" ").toString();
			fmt = (new StringBuilder()).append(fmt).append("0").toString();
		}

		DecimalFormat dfmt = new DecimalFormat(fmt);
		DecimalFormat dfmt2 = new DecimalFormat("00");
		String s = (new StringBuilder()).append("方程：").append(fun.getFunName()).toString();
		s = (new StringBuilder()).append(s).append("\nk").append(pad).append("xk").toString();
		s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x0 < 0.0D ? " " : "  ").append(dfmt.format(x0)).toString();
		s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(++k)).append(x1 < 0.0D ? " " : "  ").append(dfmt.format(x1)).toString();
		double x2 = x1;
		x1 = x0;
		do
		{
			k++;
			x0 = x1;
			x1 = x2;
			x2 = fun.phi(x0, x1);
			s = (new StringBuilder()).append(s).append("\n").append(dfmt2.format(k)).append(x2 < 0.0D ? " " : "  ").append(dfmt.format(x2)).toString();
		} while (Math.abs(x2 - x1) > fun.e && k < 100);
		return s;
	}
}

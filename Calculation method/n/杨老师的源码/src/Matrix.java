

class Matrix
{

	Matrix()
	{
	}

	public static void gauss(double Ab[][], boolean selMel)
	{
		int n = Ab.length;
		for (int i = 0; i < n - 1; i++)
		{
			if (selMel)
			{
				double max = Math.abs(Ab[i][i]);
				int rmax = i;
				for (int r = i + 1; r < n; r++)
					if (Math.abs(Ab[r][i]) > max)
					{
						max = Math.abs(Ab[r][i]);
						rmax = r;
					}

				if (rmax != i)
				{
					for (int j = i; j < n + 1; j++)
					{
						double t = Ab[i][j];
						Ab[i][j] = Ab[rmax][j];
						Ab[rmax][j] = t;
					}

				}
			}
			for (int r = i + 1; r < n; r++)
			{
				Ab[r][i] /= Ab[i][i];
				for (int j = i + 1; j < n + 1; j++)
					Ab[r][j] -= Ab[i][j] * Ab[r][i];

			}

		}

	}

	public static void gaussJordan(double Ab[][], boolean selMel)
	{
		int n = Ab.length;
		int col = Ab[0].length;
		for (int i = 0; i < n; i++)
		{
			if (selMel)
			{
				double max = Math.abs(Ab[i][i]);
				int rmax = i;
				for (int r = i + 1; r < n; r++)
					if (Math.abs(Ab[r][i]) > max)
					{
						max = Math.abs(Ab[r][i]);
						rmax = r;
					}

				if (rmax != i)
				{
					for (int j = i; j < col; j++)
					{
						double t = Ab[i][j];
						Ab[i][j] = Ab[rmax][j];
						Ab[rmax][j] = t;
					}

				}
			}
			for (int j = i + 1; j < col; j++)
				Ab[i][j] /= Ab[i][i];

			for (int r = 0; r < n; r++)
			{
				if (r == i)
					continue;
				for (int j = i + 1; j < col; j++)
					Ab[r][j] -= Ab[i][j] * Ab[r][i];

			}

		}

	}

	public static void doolittle(double Ab[][])
	{
		int n = Ab.length;
		for (int k = 0; k < n; k++)
		{
			if (k > 0)
			{
				for (int j = k; j < n; j++)
				{
					for (int r = 0; r < k; r++)
						Ab[k][j] -= Ab[k][r] * Ab[r][j];

				}

			}
			for (int i = k + 1; i < n; i++)
			{
				for (int r = 0; r < k; r++)
					Ab[i][k] -= Ab[i][r] * Ab[r][k];

				Ab[i][k] /= Ab[k][k];
			}

		}

	}

	public static void crout(double Ab[][])
	{
		int n = Ab.length;
		for (int k = 0; k < n; k++)
		{
			if (k > 0)
			{
				for (int i = k; i < n; i++)
				{
					for (int r = 0; r < k; r++)
						Ab[i][k] -= Ab[i][r] * Ab[r][k];

				}

			}
			for (int j = k + 1; j < n; j++)
			{
				for (int r = 0; r < k; r++)
					Ab[k][j] -= Ab[k][r] * Ab[r][j];

				Ab[k][j] /= Ab[k][k];
			}

		}

	}
}

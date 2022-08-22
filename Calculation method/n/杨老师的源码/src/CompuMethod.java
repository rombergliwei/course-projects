
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;

public class CompuMethod extends JFrame
{
	private class InterpoItemListener
		implements ItemListener
	{
		public void itemStateChanged(ItemEvent evt)
		{
			jrbIntpItemStateChanged(evt);
		}
	}

	private class EqsItemListener
		implements ItemListener
	{
		public void itemStateChanged(ItemEvent evt)
		{
			jrbEqsItemStateChanged(evt);
		}
	}

	private class Ch3ItemListener
		implements ItemListener
	{
		public void itemStateChanged(ItemEvent evt)
		{
			jrbCh3ItemStateChanged(evt);
		}
	}


	private JButton jBtnGt1;
	private JButton jBtnLt1;
	private JButton jbtnClr;
	private JComboBox jcbClr;
	private JPanel jpOutput;
	private JTextArea jtaOutput;
	private JSplitPane jSplitPane[];
	private JTabbedPane jTabbedPane1;
	private JButton jbtRoot;
	private JRadioButton jrbCh3[];
	private ButtonGroup bg4Ch3;
	private JList jlEqs;
	private JTextField jtfEqs;
	private JRadioButton jrbEqs[];
	private ButtonGroup bg4Eqs;
	private JCheckBox jcbEqs;
	private JButton jbtEqs;
	private JPanel jpPlot;
	private JButton jbnLunge;
	private JButton jbnFile;
	private JButton jbnRootX;
	private JButton jbnPlot;
	private JComboBox jcbPlotClr;
	private JRadioButton jrbIntp[];
	private JComboBox jcbPolyFit;
	private JLabel jlbPolyFit;
	private JCheckBox jchPolyFit;
	private JTextField jtfIntpP;
	private JLabel jlbIntpP;
	private JTextArea jtaOutIntp;
	private LinearEqs leqs;
	private Interpolation intpo;
	private int plotIndex;
	private boolean bHoldOn;
	private double kw;
	private double kh;
	private double maxx;
	private double minx;
	private double maxy;
	private double miny;
	private DecimalFormat fmt;
	private DecimalFormat fmt4;

	public CompuMethod()
	{
		fmt = new DecimalFormat("0.0");
		fmt4 = new DecimalFormat("0.0000");
		initComponents();
	}

	private void initComponents()
	{
		setDefaultCloseOperation(3);
		setTitle("计算方法");
		jTabbedPane1 = new JTabbedPane();
		jSplitPane = new JSplitPane[3];
		jTabbedPane1.setTabPlacement(3);
		jTabbedPane1.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent evt)
			{
				jTabbedPane1StateChanged(evt);
			}
		});
		String tabTxt[] = {
			"方程求根", "解线性方程组", "函数插值与曲线拟合"
		};
		for (int i = 0; i < jSplitPane.length; i++)
		{
			jSplitPane[i] = new JSplitPane();
			jSplitPane[i].setDividerLocation(600);
			jSplitPane[i].setOneTouchExpandable(true);
			jTabbedPane1.addTab(tabTxt[i], jSplitPane[i]);
		}

		jpOutput = new JPanel();
		jpOutput.setLayout(new BorderLayout());
		jpOutput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "求解结果", 0, 0, new Font("宋体", 0, 12), Color.CYAN));
		jtaOutput = new JTextArea();
		jtaOutput.setFont(new Font("宋体", 0, 18));
		jtaOutput.setForeground(Color.GREEN);
		jpOutput.add(new JScrollPane(jtaOutput));
		JPanel jpFont = new JPanel();
		jpFont.setLayout(new FlowLayout());
		jpFont.add(new JLabel("字体"));
		jBtnLt1 = new JButton();
		jBtnGt1 = new JButton();
		jBtnLt1.setText("缩小");
		jBtnLt1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				jBtnLt1ActionPerformed(evt);
			}
		});
		jBtnGt1.setText("放大");
		jBtnGt1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				jBtnGt1ActionPerformed(evt);
			}
		});
		jpFont.add(jBtnGt1);
		jpFont.add(jBtnLt1);
		String clrStr[] = {
			"GREEN", "BLUE", "CYAN", "MAGENTA", "RED", "ORANGE", "PINK", "YELLOW", "BLACK"
		};
		jcbClr = new JComboBox(clrStr);
		jcbClr.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent evt)
			{
				jcbClrItemStateChanged(evt);
			}

		});
		jbtnClr = new JButton("更多颜色");
		jbtnClr.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				jbtnClrActionPerformed(evt);
			}
		});
		jpFont.add(new JLabel("字体颜色"));
		jpFont.add(jcbClr);
		jpFont.add(jbtnClr);
		jpOutput.add(jpFont, "South");
		jSplitPane[0].setLeftComponent(jpOutput);
		JPanel jpR4Ch3 = new JPanel();
		jpR4Ch3.setLayout(new GridLayout(8, 1, 20, 10));
		jpR4Ch3.setBorder(BorderFactory.createTitledBorder("请选择例题"));
		jrbCh3 = new JRadioButton[7];
		bg4Ch3 = new ButtonGroup();
		String rbTxt[] = {
			"ex3.2.1二分法sin x-x^2/4=0", "ex3.2.2二分法x^3-x-1=0", "ex3.3.1不动点迭代x^3-x-1=0", "ex3.3.4不动点迭代x^3-x^2-1=0", "ex3.4.1牛顿迭代x^3-x-1=0", "ex3.4.5牛顿迭代及2种改善方法x^3-x^2-x+1=0", "ex3.5.1弦截法x^3-x-1=0"
		};
		Ch3ItemListener rbCh3Listener = new Ch3ItemListener();
		for (int i = 0; i < jrbCh3.length; i++)
		{
			jrbCh3[i] = new JRadioButton(rbTxt[i]);
			bg4Ch3.add(jrbCh3[i]);
			jrbCh3[i].addItemListener(rbCh3Listener);
			jpR4Ch3.add(jrbCh3[i]);
		}

		jrbCh3[0].setSelected(true);
		jbtRoot = new JButton();
		jbtRoot.setText("求方程的根");
		jbtRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				jbtRootActionPerformed(evt);
			}
		});
		JPanel jpBtroot = new JPanel();
		jpBtroot.add(jbtRoot);
		jpR4Ch3.add(jpBtroot);
		jSplitPane[0].setRightComponent(jpR4Ch3);
		JPanel jpR4Eqs = new JPanel();
		jpR4Eqs.setLayout(new BorderLayout());
		JPanel jpEx4eqs = new JPanel();
		jpEx4eqs.setLayout(new BorderLayout());
		jpEx4eqs.setBorder(BorderFactory.createTitledBorder("请选择例题"));
		String eqsTxt[] = {
			"ex421", "ex422", "ex427", "ex428", "ex431", "ex432", "ex440", "ex441", "ex442", "ex450", 
			"ex521"
		};
		jlEqs = new JList(eqsTxt);
		jlEqs.setVisibleRowCount(10);
		jlEqs.setSelectionMode(0);
		jlEqs.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent evt)
			{
				jlEqsValueChanged(evt);
			}
		});
		jpEx4eqs.add(new JScrollPane(jlEqs));
		JPanel jpEqsInput = new JPanel();
		jpEqsInput.setLayout(new BorderLayout());
		jpEqsInput.add(new JLabel("或输入题目编号XXX"), "West");
		jtfEqs = new JTextField(3);
		jtfEqs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				jtfEqsActionPerformed(evt);
			}
		});
		jpEqsInput.add(jtfEqs);
		jpEx4eqs.add(jpEqsInput, "South");
		jpR4Eqs.add(jpEx4eqs, "North");
		JPanel jpEqsMthd = new JPanel();
		jpEqsMthd.setLayout(new BorderLayout());
		jpEqsMthd.setBorder(BorderFactory.createTitledBorder("请选择方法"));
		JPanel jpMthdL = new JPanel();
		jpMthdL.setLayout(new GridLayout(6, 1));
		JPanel jpMthdR = new JPanel();
		jpMthdR.setLayout(new GridLayout(6, 1));
		jpEqsMthd.add(jpMthdL);
		jpEqsMthd.add(jpMthdR, "East");
		bg4Eqs = new ButtonGroup();
		jrbEqs = new JRadioButton[6];
		EqsItemListener jrbEqsListener = new EqsItemListener();
		String mthdTxt[] = {
			"Gauss", "Gauss-Jordan", "Decomp Doolittle", "Decomp Crout", "Iter Jacobi", "Iter Gauss-Seidel"
		};
		for (int i = 0; i < jrbEqs.length; i++)
		{
			jrbEqs[i] = new JRadioButton(mthdTxt[i]);
			jrbEqs[i].setEnabled(false);
			jrbEqs[i].addItemListener(jrbEqsListener);
			bg4Eqs.add(jrbEqs[i]);
			jpMthdL.add(jrbEqs[i]);
		}

		jcbEqs = new JCheckBox("选择列主元");
		jcbEqs.setSelected(true);
		jcbEqs.setEnabled(false);
		jpMthdR.add(jcbEqs);
		jpR4Eqs.add(jpEqsMthd, "Center");
		jbtEqs = new JButton("求解");
		jbtEqs.setEnabled(false);
		jbtEqs.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				jbtEqsActionPerformed(evt);
			}
		});
		jpR4Eqs.add(jbtEqs, "South");
		jSplitPane[1].setRightComponent(jpR4Eqs);
		jpPlot = new JPanel();
		jSplitPane[2].setLeftComponent(jpPlot);
		JPanel jpTab2 = new JPanel(new GridLayout(2, 1, 10, 20));
		JPanel jptab20 = new JPanel(new BorderLayout());
		JPanel jptab21 = new JPanel(new BorderLayout());
		JPanel jptab20c = new JPanel(new GridLayout(1, 2));
		JPanel jptab20s = new JPanel();
		Box bxTab20c = Box.createVerticalBox();
		Box bxTab20cr = Box.createVerticalBox();
		JPanel jpBxTab20cr1 = new JPanel();
		JPanel jpBxTab20cr2 = new JPanel();
		ButtonGroup bgTab20c = new ButtonGroup();
		JPanel jptab21n = new JPanel(new GridLayout(2, 1));
		JPanel jptab21n1 = new JPanel();
		JPanel jptab21n2 = new JPanel();
		jbnFile = new JButton("请选择文件");
		jbnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				jbnFileActionPerformed(evt);
			}
		});
		jrbIntp = new JRadioButton[6];
		String intpTxt[] = {
			"离散数据的曲线拟合", "Lagrange插值", "Newton插值", "分段线性插值", "分段3次Hermite插值", "3次Spline插值"
		};
		InterpoItemListener jrbIntpListener = new InterpoItemListener();
		for (int i = 0; i < 6; i++)
		{
			jrbIntp[i] = new JRadioButton(intpTxt[i]);
			bxTab20c.add(Box.createVerticalGlue());
			bxTab20c.add(jrbIntp[i]);
			bgTab20c.add(jrbIntp[i]);
			jrbIntp[i].setEnabled(false);
			jrbIntp[i].addItemListener(jrbIntpListener);
		}

		String odTxt[] = {
			"1", "2", "3", "4", "5", "6", "7"
		};
		jcbPolyFit = new JComboBox(odTxt);
		jchPolyFit = new JCheckBox("正交多项式拟合");
		jcbPolyFit.setEnabled(false);
		jchPolyFit.setEnabled(false);
		jpBxTab20cr1.add(jchPolyFit);
		bxTab20cr.add(jpBxTab20cr1);
		jlbPolyFit = new JLabel("曲线拟合次数:");
		jlbPolyFit.setEnabled(false);
		jpBxTab20cr2.add(jlbPolyFit);
		jpBxTab20cr2.add(jcbPolyFit);
		bxTab20cr.add(jpBxTab20cr2);
		bxTab20cr.add(Box.createVerticalStrut(110));
		jbnPlot = new JButton("绘制曲线");
		jbnPlot.setEnabled(false);
		jbnPlot.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				jbnPlotActionPerformed(evt);
			}

		});
		jcbPlotClr = new JComboBox(clrStr);
		jcbPlotClr.setEnabled(false);
		jptab20s.add(jbnPlot);
		jptab20s.add(jcbPlotClr);
		jbnRootX = new JButton("root(x)插值");
		jbnRootX.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				jbnRootXActionPerformed(evt);
			}

		});
		jptab21n1.add(jbnRootX);
		jbnLunge = new JButton("Runge现象");
		jbnLunge.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				jbnLungeActionPerformed(evt);
			}
		});
		jptab21n1.add(jbnLunge);
		jlbIntpP = new JLabel("请输入插值点x:");
		jptab21n2.add(jlbIntpP);
		jlbIntpP.setEnabled(false);
		jtfIntpP = new JTextField(5);
		jtfIntpP.setEnabled(false);
		jtfIntpP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt)
			{
				jtfIntpPActionPerformed(evt);
			}
		});
		jptab21n2.add(jtfIntpP);
		jtaOutIntp = new JTextArea();
		jtaOutIntp.setFont(new Font("宋体", 0, 18));
		jtaOutIntp.setForeground(Color.MAGENTA);
		jpTab2.add(jptab20);
		jptab20.add(jbnFile, "North");
		jptab20c.setBorder(BorderFactory.createTitledBorder("请选择方法"));
		jptab20c.add(bxTab20c);
		jptab20c.add(bxTab20cr);
		jptab20.add(jptab20c, "Center");
		jptab20.add(jptab20s, "South");
		jpTab2.add(jptab21);
		jptab21.add(jptab21n, "North");
		jptab21n.add(jptab21n1);
		jptab21n.add(jptab21n2);
		jptab21.add(new JScrollPane(jtaOutIntp), "Center");
		plotIndex = 0;
		jSplitPane[2].setRightComponent(jpTab2);
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1, -1, 922, 32767));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1, -1, 542, 32767));
		pack();
		setLocation(100, 50);
	}

	private void jTabbedPane1StateChanged(ChangeEvent evt)
	{
		int i = jTabbedPane1.getSelectedIndex();
		if (i != 2)
		{
			jSplitPane[i].setLeftComponent(jpOutput);
			jSplitPane[i].setDividerLocation(600);
		}
		repaint();
	}

	private void jrbCh3ItemStateChanged(ItemEvent evt)
	{
		jtaOutput.setText("");
	}

	private void jbtRootActionPerformed(ActionEvent evt)
	{
		int i;
		for (i = 0; i < jrbCh3.length && !jrbCh3[i].isSelected(); i++);
		switch (i)
		{
		case 0: // '\0'
			jtaOutput.setText(RootOfEquation.bisect(new Fun321(1.5D, 2D, 0.01D)));
			break;

		case 1: // '\001'
			jtaOutput.setText(RootOfEquation.bisect(new Fun322(1.0D, 2D, 0.001D)));
			break;

		case 2: // '\002'
			jtaOutput.setText(RootOfEquation.fixedPtIter(new Fun322(1.5D, 9.9999999999999995E-007D)));
			break;

		case 3: // '\003'
			jtaOutput.setText(RootOfEquation.fixedPtIter(new Fun334(1.5D, 9.9999999999999995E-007D)));
			break;

		case 4: // '\004'
			jtaOutput.setText(RootOfEquation.newtonIter(new Fun322(1.5D, 9.9999999999999995E-007D)));
			break;

		case 5: // '\005'
			Function f345 = new Fun345(1.5D, 1.0000000000000001E-009D, 2);
			jtaOutput.setText("Newton method\n");
			jtaOutput.append(RootOfEquation.newtonIter(f345));
			jtaOutput.append("\n\nImproved Newton method 重根数已知\n");
			jtaOutput.append(RootOfEquation.newton1Iter(f345));
			jtaOutput.append("\n\nImproved Newton method 重根数未知\n");
			jtaOutput.append(RootOfEquation.newton2Iter(f345));
			break;

		case 6: // '\006'
			jtaOutput.setText(RootOfEquation.chordInter(new Fun322(1.0D, 1.5D, 9.9999999999999995E-007D)));
			break;
		}
	}

	private void jBtnLt1ActionPerformed(ActionEvent evt)
	{
		Font f = jtaOutput.getFont();
		int sz = f.getSize();
		sz = sz >= 10 ? sz - 4 : sz;
		jtaOutput.setFont(new Font(f.getName(), f.getStyle(), sz));
	}

	private void jBtnGt1ActionPerformed(ActionEvent evt)
	{
		Font f = jtaOutput.getFont();
		int sz = f.getSize();
		sz = sz <= 48 ? sz + 4 : sz;
		jtaOutput.setFont(new Font(f.getName(), f.getStyle(), sz));
	}

	private void jcbClrItemStateChanged(ItemEvent evt)
	{
		Color clrArr[] = {
			Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.RED, Color.ORANGE, Color.PINK, Color.YELLOW, Color.BLACK
		};
		if (evt.getStateChange() == 1)
			jtaOutput.setForeground(clrArr[jcbClr.getSelectedIndex()]);
	}

	private void jbtnClrActionPerformed(ActionEvent evt)
	{
		Color clr = JColorChooser.showDialog(this, "Color", Color.GREEN);
		jtaOutput.setForeground(clr);
	}

	private void jlEqsValueChanged(ListSelectionEvent evt)
	{
		jtaOutput.setText("");
		leqs = new LinearEqs(jlEqs.getSelectedValue().toString(), this);
		setMethod(leqs);
	}

	private void jtfEqsActionPerformed(ActionEvent evt)
	{
		jtaOutput.setText("");
		leqs = new LinearEqs((new StringBuilder()).append("ex").append(evt.getActionCommand()).toString(), this);
		setMethod(leqs);
	}

	private void setMethod(LinearEqs eqs)
	{
		if (eqs.initialized)
		{
			switch (eqs.eqType)
			{
			case 1: // '\001'
				for (int i = 0; i < jrbEqs.length - 2; i++)
					jrbEqs[i].setEnabled(true);

				for (int i = jrbEqs.length - 2; i < jrbEqs.length; i++)
					jrbEqs[i].setEnabled(false);

				jrbEqs[1].setSelected(true);
				jcbEqs.setEnabled(true);
				break;

			case 3: // '\003'
				for (int i = 0; i < jrbEqs.length; i++)
					jrbEqs[i].setEnabled(true);

				jrbEqs[jrbEqs.length - 1].setSelected(true);
				jcbEqs.setEnabled(false);
				break;

			default:
				for (int i = 0; i < jrbEqs.length; i++)
					jrbEqs[i].setEnabled(false);

				jcbEqs.setEnabled(false);
				break;
			}
			jbtEqs.setEnabled(true);
		} else
		{
			for (int i = 0; i < jrbEqs.length; i++)
				jrbEqs[i].setEnabled(false);

			jcbEqs.setEnabled(false);
			jbtEqs.setEnabled(false);
		}
	}

	private void jrbEqsItemStateChanged(ItemEvent evt)
	{
		int n = jrbEqs.length;
		if (jrbEqs[n - 1].isSelected() || jrbEqs[n - 2].isSelected())
			jcbEqs.setEnabled(false);
		else
			jcbEqs.setEnabled(true);
	}

	private void jbtEqsActionPerformed(ActionEvent evt)
	{
		String s = "";
		switch (leqs.eqType)
		{
		default:
			break;

		case 1: // '\001'
		case 3: // '\003'
			s = "解线性方程组\n";
			int i;
			for (i = 0; i < jrbEqs.length && !jrbEqs[i].isSelected(); i++);
			switch (i)
			{
			case 0: // '\0'
				s = (new StringBuilder()).append(s).append(leqs.gauss(jcbEqs.isSelected())).toString();
				break;

			case 1: // '\001'
				s = (new StringBuilder()).append(s).append(leqs.gaussJordan(jcbEqs.isSelected())).toString();
				break;

			case 2: // '\002'
				s = (new StringBuilder()).append(s).append(leqs.doolittle(jcbEqs.isSelected())).toString();
				break;

			case 3: // '\003'
				s = (new StringBuilder()).append(s).append(leqs.crout(jcbEqs.isSelected())).toString();
				break;

			case 4: // '\004'
				s = (new StringBuilder()).append(s).append(leqs.iterJacobi()).toString();
				break;

			case 5: // '\005'
				s = (new StringBuilder()).append(s).append(leqs.iterGaussSeidel()).toString();
				break;
			}
			break;

		case 2: // '\002'
			s = "解三对角方程组\n";
			s = (new StringBuilder()).append(s).append(leqs.threeTriagonal()).toString();
			break;

		case 4: // '\004'
			s = "求方阵的逆阵\n";
			s = (new StringBuilder()).append(s).append(leqs.invMat()).toString();
			break;

		case 5: // '\005'
			s = "求方阵的行列式\n";
			s = (new StringBuilder()).append(s).append(leqs.detMat()).toString();
			break;
		}
		jtaOutput.setText(s);
	}

	public void holdOn(boolean f)
	{
		bHoldOn = f;
	}

	public void plot(double x[], double y[], Color c, int style)
	{
		Graphics g = jpPlot.getGraphics();
		g.setColor(Color.BLACK);
		int w = jpPlot.getWidth();
		int h = jpPlot.getHeight();
		int margin = 25;
		int tipLen = 5;
		if (!bHoldOn)
		{
			g.clearRect(0, 0, w, h);
			g.drawLine(margin, h - margin, w - margin, h - margin);
			g.drawLine(margin, margin, margin, h - margin);
			maxx = x[0];
			minx = x[0];
			maxy = y[0];
			miny = y[0];
			for (int i = 1; i < x.length; i++)
			{
				if (x[i] > maxx)
					maxx = x[i];
				if (x[i] < minx)
					minx = x[i];
				if (y[i] > maxy)
					maxy = y[i];
				if (y[i] < miny)
					miny = y[i];
			}

			kw = (double)(w - 2 * margin) / (maxx - minx);
			kh = (double)(h - 2 * margin) / (maxy - miny);
			double dw = (double)(w - 2 * margin) / 10D;
			double dx = (maxx - minx) / 10D;
			double dh = (double)(h - 2 * margin) / 10D;
			double dy = (maxy - miny) / 10D;
			for (int i = 0; i <= 10; i++)
			{
				g.drawLine((int)((double)margin + (double)i * dw), h - margin, (int)((double)margin + (double)i * dw), (h - margin) + tipLen);
				g.drawString(fmt.format(minx + (double)i * dx), (int)(((double)margin + (double)i * dw) - (double)tipLen), h);
				g.drawLine(margin - tipLen, (int)((double)(h - margin) - (double)i * dh), margin, (int)((double)(h - margin) - (double)i * dh));
				g.drawString(fmt.format(miny + (double)i * dy), 0, (int)(((double)(h - margin) - (double)i * dh) + (double)tipLen));
			}

		}
		g.setColor(c);
		if (style == 0)
		{
			for (int i = 0; i < x.length - 1; i++)
				g.drawLine((int)((double)margin + (x[i] - minx) * kw), (int)((double)(h - margin) - (y[i] - miny) * kh), (int)((double)margin + (x[i + 1] - minx) * kw), (int)((double)(h - margin) - (y[i + 1] - miny) * kh));

		} else
		{
			for (int i = 0; i < x.length; i++)
			{
				if (style == 1)
				{
					g.fillOval((int)((double)margin + (x[i] - minx) * kw) - 3, (int)((double)(h - margin) - (y[i] - miny) * kh) - 3, 6, 6);
					continue;
				}
				if (style == 2)
					g.fillRect((int)((double)margin + (x[i] - minx) * kw) - 3, (int)((double)(h - margin) - (y[i] - miny) * kh) - 3, 6, 6);
			}

		}
	}

	private void jbnPlotActionPerformed(ActionEvent evt)
	{
		plotIntpo();
		plotIndex = 3;
	}

	private void plotIntpo()
	{
		double x[] = intpo.getX();
		double y[] = intpo.getY();
		int nn = 201;
		double dh = (x[x.length - 1] - x[0]) / 200D;
		double xx[] = new double[nn];
		double yy[] = new double[nn];
		int k;
		for (k = 0; k < 6 && !jrbIntp[k].isSelected(); k++);
		if (jrbIntp[0].isSelected())
		{
			int od = jcbPolyFit.getSelectedIndex() + 1;
			if (jchPolyFit.isSelected())
				intpo.polyFitX(od);
			else
				intpo.polyFit(od);
		}
		for (int i = 0; i < nn; i++)
		{
			xx[i] = x[0] + (double)i * dh;
			switch (k)
			{
			default:
				break;

			case 0: // '\0'
				if (jchPolyFit.isSelected())
					yy[i] = intpo.yOfPolyFitX(xx[i]);
				else
					yy[i] = intpo.yOfPolyFit(xx[i]);
				break;

			case 1: // '\001'
				yy[i] = intpo.interpoLagrange(xx[i]);
				break;

			case 2: // '\002'
				yy[i] = intpo.interpoNewton(xx[i]);
				break;

			case 3: // '\003'
				yy[i] = intpo.interpoSegLinear(xx[i]);
				break;

			case 4: // '\004'
				yy[i] = intpo.interpoSeg3Hermite(xx[i]);
				break;

			case 5: // '\005'
				yy[i] = intpo.interpoSpline(xx[i]);
				break;
			}
		}

		Color clrArr[] = {
			Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.RED, Color.ORANGE, Color.PINK, Color.YELLOW, Color.BLACK
		};
		holdOn(false);
		plot(xx, yy, clrArr[jcbPlotClr.getSelectedIndex()], 0);
		holdOn(true);
		plot(x, y, Color.RED, 2);
		String s;
		switch (k)
		{
		case 0: // '\0'
			s = (new StringBuilder()).append("曲线拟合(次数:").append(jcbPolyFit.getSelectedIndex() + 1).append(")\n").toString();
			if (jchPolyFit.isSelected())
				s = (new StringBuilder()).append(s).append(intpo.getA4PolyFitX()).toString();
			else
				s = (new StringBuilder()).append(s).append(intpo.getA4PolyFit()).toString();
			break;

		case 2: // '\002'
			s = (new StringBuilder()).append("Newton插值:\n").append(intpo.getDQ()).toString();
			break;

		case 5: // '\005'
			s = intpo.getM4Spline();
			break;

		default:
			s = "";
			break;
		}
		jtaOutIntp.setText(s);
		jlbIntpP.setEnabled(true);
		jtfIntpP.setEnabled(true);
	}

	private void jbnRootXActionPerformed(ActionEvent evt)
	{
		plotRootX();
		plotIndex = 2;
	}

	private void plotRootX()
	{
		double x2[] = {
			100D, 121D, 144D
		};
		double y2[] = {
			10D, 11D, 12D
		};
		Interpolation intp2 = new Interpolation(x2, y2);
		int nn = 201;
		double dh = 0.22D;
		double xx[] = new double[nn];
		double yy[] = new double[nn];
		double yy1[] = new double[nn];
		double yy2[] = new double[nn];
		for (int i = 0; i < nn; i++)
		{
			xx[i] = 100D + (double)i * dh;
			yy[i] = Math.sqrt(xx[i]);
			yy1[i] = intp2.interpoNewton(xx[i], 1);
			yy2[i] = intp2.interpoNewton(xx[i], 2);
		}

		holdOn(false);
		plot(xx, yy1, Color.CYAN, 0);
		holdOn(true);
		plot(xx, yy2, Color.BLUE, 0);
		plot(xx, yy, Color.RED, 0);
		plot(x2, y2, Color.GREEN, 2);
		String s = "Root(x) Newton线性插值和抛物插值:\n";
		s = (new StringBuilder()).append(s).append(intp2.getDQ()).toString();
		s = (new StringBuilder()).append(s).append("\nsqroot(115)=\n").append(fmt4.format(intp2.interpoNewton(115D))).append(" 抛物插值\n").toString();
		s = (new StringBuilder()).append(s).append(fmt4.format(intp2.interpoNewton(115D, 1))).append(" 线性插值\n").toString();
		jtaOutIntp.setText(s);
	}

	private void jbnLungeActionPerformed(ActionEvent evt)
	{
		plotRunge();
		plotIndex = 1;
	}

	private void plotRunge()
	{
		int n = 11;
		double x[] = new double[n];
		double y[] = new double[n];
		for (int i = 0; i < n; i++)
		{
			x[i] = -5 + i;
			y[i] = 1.0D / (x[i] * x[i] + 1.0D);
		}

		int nn = 201;
		double dh = 0.050000000000000003D;
		double xx[] = new double[nn];
		double yy[] = new double[nn];
		double yy2[] = new double[nn];
		Interpolation intp = new Interpolation(x, y);
		for (int i = 0; i < nn; i++)
		{
			xx[i] = -5D + (double)i * dh;
			yy[i] = 1.0D / (xx[i] * xx[i] + 1.0D);
			yy2[i] = intp.interpoNewton(xx[i]);
		}

		holdOn(false);
		plot(xx, yy2, Color.BLUE, 0);
		holdOn(true);
		plot(xx, yy, Color.RED, 0);
		plot(x, y, Color.GREEN, 2);
		jtaOutIntp.setText("");
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		if (jTabbedPane1.getSelectedIndex() == 2)
			switch (plotIndex)
			{
			case 1: // '\001'
				plotRunge();
				break;

			case 2: // '\002'
				plotRootX();
				break;

			case 3: // '\003'
				plotIntpo();
				break;
			}
	}

	private void jtfIntpPActionPerformed(ActionEvent evt)
	{
		String s = evt.getActionCommand();
		double y = 0.0D;
		double x;
		try
		{
			x = Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			jtaOutIntp.append((new StringBuilder()).append("\n!!!!请输入数据\n").append(e).append("\n").toString());
			return;
		}
		s = (new StringBuilder()).append("\n插值点:").append(s).toString();
		int k;
		for (k = 0; k < 6 && !jrbIntp[k].isSelected(); k++);
		switch (k)
		{
		default:
			break;

		case 0: // '\0'
			if (jchPolyFit.isSelected())
				y = intpo.yOfPolyFitX(x);
			else
				y = intpo.yOfPolyFit(x);
			break;

		case 1: // '\001'
			y = intpo.interpoLagrange(x);
			break;

		case 2: // '\002'
			y = intpo.interpoNewton(x);
			break;

		case 3: // '\003'
			y = intpo.interpoSegLinear(x);
			break;

		case 4: // '\004'
			y = intpo.interpoSeg3Hermite(x);
			break;

		case 5: // '\005'
			y = intpo.interpoSpline(x);
			break;
		}
		s = (new StringBuilder()).append(s).append("插值(拟合)函数值:").append(fmt4.format(y)).append("\n").toString();
		jtaOutIntp.append(s);
	}

	private void jbnFileActionPerformed(ActionEvent evt)
	{
		JFileChooser fileChooser = new JFileChooser(".");
		fileChooser.setFileFilter(new FileFilter() {

			public boolean accept(File f)
			{
				if (f.isDirectory())
					return true;
				String name = f.getName().toLowerCase();
				return name.endsWith("txt") && (name.startsWith("ex6") || name.startsWith("ex7"));
			}

			public String getDescription()
			{
				return "text file(*.txt)";
			}
		});
		int ret = fileChooser.showOpenDialog(this);
		File file = fileChooser.getSelectedFile();
		Interpolation intpoNew = null;
		if (ret == 0 && file != null)
			intpoNew = new Interpolation(file, this);
		if (intpoNew != null && intpoNew.initialized)
		{
			intpo = intpoNew;
			switch (intpo.type)
			{
			default:
				break;

			case 1: // '\001'
				for (int i = 0; i < 4; i++)
					jrbIntp[i].setEnabled(true);

				jrbIntp[1].setSelected(true);
				for (int i = 4; i < 6; i++)
					jrbIntp[i].setEnabled(false);

				break;

			case 2: // '\002'
				for (int i = 0; i < 6; i++)
					if (i == 4)
						jrbIntp[i].setEnabled(true);
					else
						jrbIntp[i].setEnabled(false);

				jrbIntp[4].setSelected(true);
				break;

			case 3: // '\003'
				for (int i = 0; i < 6; i++)
					if (i == 5)
						jrbIntp[i].setEnabled(true);
					else
						jrbIntp[i].setEnabled(false);

				jrbIntp[5].setSelected(true);
				break;
			}
			jbnPlot.setEnabled(true);
			jcbPlotClr.setEnabled(true);
			jlbIntpP.setEnabled(false);
			jtfIntpP.setEnabled(false);
		}
	}

	private void jrbIntpItemStateChanged(ItemEvent evt)
	{
		if (jrbIntp[0].isSelected())
		{
			jchPolyFit.setEnabled(true);
			jcbPolyFit.setEnabled(true);
			jlbPolyFit.setEnabled(true);
		} else
		{
			jchPolyFit.setEnabled(false);
			jcbPolyFit.setEnabled(false);
			jlbPolyFit.setEnabled(false);
		}
	}

	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() {

			public void run()
			{
				(new CompuMethod()).setVisible(true);
			}

		});
	}

















}

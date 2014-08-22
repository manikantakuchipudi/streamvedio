package com.graph.viewdata;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class GraphGenerator {

	public GraphGenerator() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphGenerator gg=new GraphGenerator();
		  ArrayList<Double> a=new ArrayList<Double>();
		  ArrayList<Double> b=new ArrayList<Double>();
		  double dt=0.1;
		  double ygen=0.0;
		  double t=0.0;
		  while(t<3)
		  {
		  t=t+dt;
		  a.add(t);
		  ygen=Math.cos(2*3.14*t*t+(3*3.14)/2)	;
		  b.add(ygen);
		  }
		  double[] x=gg.getData(a);
		  double[] y=gg.getData(b);
		  gg.plotDraw(x,y);

	}

	public double[] getData(ArrayList<Double> datalist)
	{
		double[] data=new double[datalist.size()];
		for(int i=0;i<datalist.size();i++)
		{
			data[i]=datalist.get(i);
		}
		
		return data;
	}
	
	
	public void plotDraw(double[] x,double[] y)
	{
		
		  // create your PlotPanel (you can use it as a JPanel)
		  Plot2DPanel plot = new Plot2DPanel();
		 
		  // add a line plot to the PlotPanel
		  plot.addLinePlot("my plot", x, y);
		 
		  // put the PlotPanel in a JFrame, as a JPanel
		  JFrame frame = new JFrame("a plot panel");
		  frame.setContentPane(plot);
		  frame.setVisible(true);
		
	}
	
	
}

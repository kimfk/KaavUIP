package gestures;

import java.util.ArrayList;

import javax.swing.SwingUtilities;





import kaav.main.GeometryContainer;
import actions.*;


/**
 * This is a main class for testing the gesturizer
 * @author Ganryu
 */

public class GestureMain {
	public static void main(String argv[]){
		Drawpanel dPanel = new Drawpanel();
		//SwingUtilities.invokeLater(dPanel);
		
		Drawpanel2 panel2 = new Drawpanel2();
		SwingUtilities.invokeLater(panel2);		
		GeometryContainer container = new GeometryContainer();
		
		Levenshtein l = new Levenshtein();
		//l.getDistance(new int[]{1, 1, 1}, new int[]{1, 1, 1});
		//l.getDistance(new int[]{1, 1, 3, 5}, new int[]{3, 2, 1});
		
		ArrayList<SimpleVector> list = new ArrayList<SimpleVector>();
		
		// Build a set of datapoints for smoothing
		double a;
		double b;
		double amplitude;
		
		for (int i = 0; i < 200; i ++){
			amplitude = 50+Math.random()*60;			
			a = Math.cos(((double)i/200)*2*Math.PI) * amplitude;
			b = Math.sin(((double)i/200)*2*Math.PI) * amplitude;
			list.add(new SimpleVector(0+a, 0+b));
		}

		Gesturizer g = new Gesturizer(100, 50);
		ArrayList<SimpleVector> result = g.filter(list);
		ArrayList<Double> kernel = g.getKernel();
		g.setPanel(panel2);

		for (int i = 0; i < 60; i ++){
			SimpleVector v = new SimpleVector(
					Math.cos(((double)i)/20 * 2 * Math.PI),
					Math.sin(((double)i)/20 * 2 * Math.PI));
			System.out.println(v.getDirection());
		}

		dPanel.setBaseList(list);
		dPanel.setResultList(result);
		dPanel.setKernel(kernel);

		ArrayList<Integer> input  = new ArrayList<Integer>();
		ArrayList<Integer> square = new ArrayList<Integer>();
		ArrayList<Integer> square2 = new ArrayList<Integer>();
		ArrayList<Integer> streamlineTest  = new ArrayList<Integer>();

		streamlineTest.add(1);
		streamlineTest.add(1);
		streamlineTest.add(2);
		streamlineTest.add(3);
		streamlineTest.add(4);
		streamlineTest.add(1);
		streamlineTest.add(1);
		streamlineTest.add(5);
		streamlineTest.add(5);
		streamlineTest.add(5);
		streamlineTest.add(4);
		streamlineTest.add(4);
		streamlineTest.add(4);
		streamlineTest.add(4);
		
		ArrayList<Integer> out = g.streamlineSafeV2(streamlineTest, 2);
		System.out.println(out);
		
		input.add(0);
		input.add(2);
		input.add(4);

		square.add(16);
		square.add(2);
		square.add(5);
		square.add(6);

		square2.add(0);
		square2.add(2);

		g.teachSequence(5, square);
		g.teachAction(5, new CreateCircle(container));
		//g.compareAndTrigger(input);	
		
		ArrayList<SimpleVector> testList = new ArrayList<SimpleVector>();
		testList.add(new SimpleVector(1.0/5,	2.5/5));
		testList.add(new SimpleVector(2.0/5,	4.0/5));
		testList.add(new SimpleVector(3.0/5,	4.0/5));
		testList.add(new SimpleVector(4.0/5,	2.5/5));
		
		g.compare(testList);
		
		SimpleVector d1 = new SimpleVector(1,0);
		SimpleVector d2 = new SimpleVector(1,1);
		SimpleVector d3 = new SimpleVector(0,1);
		SimpleVector d4 = new SimpleVector(-1,1);
		SimpleVector d5 = new SimpleVector(-1,0);
		SimpleVector d6 = new SimpleVector(-1,-1);
		SimpleVector d7 = new SimpleVector(0,-1);
		SimpleVector d8 = new SimpleVector(1,-1);
		System.out.println("--------------------");
		System.out.println(d1.getDirection());
		System.out.println(d2.getDirection());
		System.out.println(d3.getDirection());
		System.out.println(d4.getDirection());
		System.out.println(d5.getDirection());
		System.out.println(d6.getDirection());
		System.out.println(d7.getDirection());
		System.out.println(d8.getDirection());
		System.out.println("TEST END");
	}
}

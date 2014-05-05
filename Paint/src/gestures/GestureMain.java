package gestures;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

//import org.neuroph.core.NeuralNetwork;
//import org.neuroph.core.data.DataSet;
//import org.neuroph.core.learning.LearningRule;

public class GestureMain {
	public static void main(String argv[]){
		Drawpanel dPanel = new Drawpanel();
		SwingUtilities.invokeLater(dPanel);
		
		Levenshtein l = new Levenshtein();
		//l.getDistance(new int[]{1, 1, 1}, new int[]{1, 1, 1});
		//l.getDistance(new int[]{1, 1, 3, 5}, new int[]{3, 2, 1});
		
		ArrayList<SimpleVector> list = new ArrayList<SimpleVector>();
		
		double a;
		double b;
		double amplitude;
		for (int i = 0; i < 200; i ++){
			amplitude = 50+Math.random()*60;			
			a = Math.cos(((double)i/200)*2*Math.PI) * amplitude;
			b = Math.sin(((double)i/200)*2*Math.PI) * amplitude;
			
			list.add(new SimpleVector(0+a, 0+b));
		}

		Gesturizer g = new Gesturizer(400, 110);
		ArrayList<SimpleVector> result = g.filter(list);
		ArrayList<Double> kernel = g.getKernel();
		
		for (int i = 0; i < 60; i ++){
			SimpleVector v = new SimpleVector(
					Math.cos(((double)i)/20 * 2 * Math.PI),
					Math.sin(((double)i)/20 * 2 * Math.PI));
			System.out.println(v.getDirection());
		}
		
		//g.printK();
		
		dPanel.setListA(list);
		dPanel.setListB(result);
		dPanel.setKernel(kernel);

		ArrayList<Integer> input  = new ArrayList<Integer>();
		ArrayList<Integer> square = new ArrayList<Integer>();
		ArrayList<Integer> square2 = new ArrayList<Integer>();
		
		input.add(0);
		input.add(2);
		input.add(4);

		square.add(16);
		square.add(2);
		square.add(5);
		square.add(6);

		square2.add(0);
		square2.add(2);

		
		g.teach(5, square);
		g.teach(5, square2);
		g.compareAndTrigger(input);		
		
		
		
		/*for(SimpleVector sv : list)
			sv.print();
		
		System.out.println("------------------------------------");
		
		for(SimpleVector sv : result)
			sv.print();*/
	}
}


package actions;

import kaav.main.GAction;
import gestures.DataPackage;
import kaav.main.GAction;
import kaav.main.GeometryContainer;


public class CreateTriangle implements GAction{
       GeometryContainer cont;
       /**
	 * Pass this a container into which objects will be put.
	 * 
	 * @param container
	 */
	public void CreateTriangle(GeometryContainer container) {
		cont = container;
	}
        
	//@Override
	public void act(DataPackage data) {
		float x = (float)(data.minimumX);
		float y = (float)(data.minimumY);
                float width = (float)(data.maximumX - data.minimumX);
                float hight = (float)(data.maximumY - data.minimumY);
		
		
		cont.CreateTriangle(x, y, width,hight);

	}
}

package de.thro.inf.prg3.a10.kitchen;

import de.thro.inf.prg3.a10.internals.displaying.ProgressReporter;
import de.thro.inf.prg3.a10.model.Dish;
import de.thro.inf.prg3.a10.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Cook implements Runnable {
	private static final Logger logger = LogManager.getLogger(Cook.class);
	String name;
	ProgressReporter progressReporter;
	KitchenHatch kitchenHatch;

	public void Cook (String n,KitchenHatch h,ProgressReporter p){
		progressReporter = p;
		kitchenHatch = h;
		name = n;
	}

@Override
	public void run(){
		Order o;
		do{

			o = kitchenHatch.dequeueOrder();
			if(o != null) {
				Dish dish = new Dish(kitchenHatch.dequeueDish().getMealName());
				try {
					Thread.sleep(dish.getCookingTime());
				} catch (InterruptedException e) {
					logger.error("Failed to cook meal", e);
				}

				kitchenHatch.enqueueDish(dish);

				progressReporter.updateProgress();
			}
		}while(o != null);

	progressReporter.notifyCookLeaving();

	}


}

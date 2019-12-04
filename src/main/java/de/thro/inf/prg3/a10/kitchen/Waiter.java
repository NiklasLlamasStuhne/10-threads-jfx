package de.thro.inf.prg3.a10.kitchen;
import de.thro.inf.prg3.a10.internals.displaying.ProgressReporter;
import de.thro.inf.prg3.a10.model.Dish;
import de.thro.inf.prg3.a10.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Waiter implements Runnable {

	private static final Logger logger = LogManager.getLogger(Waiter.class);
	private String name;
	private KitchenHatch kitchenHatch;
	private ProgressReporter progressReporter;

	public Waiter(String name, KitchenHatch kitchenHatch, ProgressReporter progressReporter) {
		this.name = name;
		this.kitchenHatch = kitchenHatch;
		this.progressReporter = progressReporter;
	}

	@Override
	public void run(){
		Dish d;

		do{
			d = kitchenHatch.dequeueDish();
			if(d != null){

				try{
					Thread.sleep ((long)Math.random() *1000);
				}catch (InterruptedException e){

					logger.error("Failure serving meal");
				}
				progressReporter.updateProgress();

			}


		}while(d != null);

		progressReporter.notifyWaiterLeaving();

	}

}

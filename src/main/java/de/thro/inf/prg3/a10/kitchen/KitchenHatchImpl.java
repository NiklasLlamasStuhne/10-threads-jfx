package de.thro.inf.prg3.a10.kitchen;

import de.thro.inf.prg3.a10.model.Dish;
import de.thro.inf.prg3.a10.model.Order;

import java.util.Deque;

public class KitchenHatchImpl implements KitchenHatch {

	private int maxMeals;
	private Deque<Order> Orders;
	private int ordercount;
	private int dishescount;
	private Deque<Dish> Dishes;

	public KitchenHatchImpl(int m,Deque<Order> orders){
		this.maxMeals = m;
		this.Orders = orders;
		ordercount =0;
		dishescount = 0;
	}


	@Override
	public int getMaxDishes() {
		return maxMeals;
	}

	@Override
	public Order dequeueOrder() {
		return Orders.getLast();
	}

	@Override
	public Order dequeueOrder(long timeout) {
		if(Orders.isEmpty()){
		try {
			Orders.wait(timeout);
		}catch(InterruptedException e){ }

		}
		return Orders.getLast();
	}

	@Override
	public int getOrderCount() {
		return ordercount;
	}

	@Override
	public Dish dequeueDish() {
		return Dishes.getLast();
	}

	@Override
	public Dish dequeueDish(long timeout) {

		if(Dishes.isEmpty()){
			try{
				Dishes.wait(timeout);
			}catch (InterruptedException e){}

		}

		return Dishes.getLast();
	}

	@Override
	public void enqueueDish(Dish m) {
		Dishes.add(m);
	}

	@Override
	public int getDishesCount() {
		return dishescount;
	}
}

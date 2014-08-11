package com.teamz.util;

public class CostEstimatorCaluculator {


	private double costOfDecorations;
	private double costOfDrinksPerPerson;
	private double costOfFoodPerPerson;
	private double numberOfPeople;
	private double totalCost;
	private boolean HealthyOption;

	public void setTotalCost()
	{
		totalCost = costOfDecorations + ((costOfDrinksPerPerson + costOfFoodPerPerson) *
				numberOfPeople);
	}


	public double getTotalCost()
	{

		if (HealthyOption)
			return totalCost *.95;
		else
			return totalCost;

	}


	public boolean isHealthyOption() {
		return HealthyOption;
	}


	public void setHealthyOption(boolean healthyOption) {
		HealthyOption = healthyOption;
	}


	public double getCostOfDecorations() {
		return costOfDecorations;
	}


	public void setCostOfDecorations(double costOfDecorations) {
		this.costOfDecorations = costOfDecorations;
	}


	public double getCostOfDrinksPerPerson() {
		return costOfDrinksPerPerson;
	}


	public void setCostOfDrinksPerPerson(double costOfDrinksPerPerson) {
		this.costOfDrinksPerPerson = costOfDrinksPerPerson;
	}


	public double getCostOfFoodPerPerson() {
		return costOfFoodPerPerson;
	}


	public void setCostOfFoodPerPerson(double costOfFoodPerPerson) {
		this.costOfFoodPerPerson = costOfFoodPerPerson;
	}


	public double getNumberOfPeople() {
		return numberOfPeople;
	}


	public void setNumberOfPeople(double numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

}

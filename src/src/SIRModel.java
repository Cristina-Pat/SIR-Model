package src;

/**
 * The SIRModel class models how a particular infection would spread,
 * on any given day, where each individual in the population is
 * either Susceptible, Infected or Recovered. 
 * 
 * The total number of susceptible, infected and recovered individuals 
 * is equal to the population size.
 * 
 * 
 * @author Cristina Pat
 * @version 2022.04.02
 */

public class SIRModel {
	// Fields of the SIRModel class
	private static final int POPULATION = 150;
	private static final int INITIAL_INFECTIONS = 4;
	private static final int DAYS = 60; // a period of 60 days
	private static double TRANS_RATE; //a transmission rate, values between 0 and 1
	private static double REC_RATE; // a recovery rate, values between 0 and 1.
	private double[] susceptible;
	private double[] infected;
	private double[] recovered;
	
	/**
     * Constructor for objects of class SIRModel
     * initialises transmission rate to the first double parameter
     * and recovery rate to the second double parameter
     * 
     * @param transRate is the transmission rate with values between 0 and 1
     * @param recRate is the recovery rate with values between 0 and 1
     */
	
	public SIRModel(double transRate, double recRate) {
		TRANS_RATE = transRate;
		REC_RATE = recRate;
		susceptible = new double[DAYS];
		infected = new double[DAYS];
		recovered = new double[DAYS];
		infected[0] = INITIAL_INFECTIONS;
		susceptible[0] = POPULATION - INITIAL_INFECTIONS;
	}
	
	/**
	 * Calculate the expected number of newly infected people in a day 
	 * using a (density-dependent) model of infection transmission
	 * 
	 * @param currentInfected  The number of current infected people in a day.
	 * @param currentSusceptible The number of current susceptible people in a day.
	 * 
	 * @return newlyInfected  A double for the expected newly infected number in a day.
	 */
	public double newlyInfected(double currentInfected,
								double currentSusceptible) 
	{
		double newlyInfected = (TRANS_RATE*currentInfected*currentSusceptible)
								/ POPULATION;
		return newlyInfected;
	}
	
	/**
	 * Calculate the expected number of newly recovered people in a day.
	 * 
	 * @param currentInfected  The number of current infected people in a day.
	 * 
	 * @return newlyRecovered The number of people expected to recover in a day.
	 * 
	 */
	public double newlyRecovered(double currentInfected) 
	{
		double newlyRecovered = REC_RATE*currentInfected;
		return newlyRecovered;
	}
	
	/**
	 * Calculate the change in the number of people expected to be infected 
	 * after a day has passed.
	 * 
	 * @param currentInfected  The number of current infected people in a day.
	 * @param currentSusceptible The number of current susceptible people in a day.
	 * 
	 * @return expectedInfected The number of people expected to be infected.
	 * 
	 */
	public double changeInInfected(double currentInfected,
								   double currentSusceptible) 
	{
		double expectedInfected = newlyInfected(currentInfected, currentSusceptible) 
				- newlyRecovered(currentInfected);
		return expectedInfected;
	}
	
	/** 
	 * Calculates the number of infected, recovered, and susceptible people 
	 * based on the previous day's data.
	 * 
	 * @param prevDay The index that allows to access the previous day's 
	 * data in the susceptible, infected and recovered arrays.
	 * 
	 */
	public void nextDay(int prevDay) 
	{	
		infected[prevDay+1] = infected[prevDay] + changeInInfected(infected[prevDay], susceptible[prevDay]);
		
		recovered[prevDay+1] = recovered[prevDay] + newlyRecovered(infected[prevDay]);
		
		susceptible[prevDay+1] = POPULATION - infected[prevDay+1] - recovered[prevDay+1];
	}
	
	/**
	 * Compute how the infection is spreading in the school population.
	 * This method populate the infected, recovered, and susceptible arrays with data.
	 * 
	 */
	public void createData() 
	{
		int i  = 0;
		while (i < DAYS -1) {
			nextDay(i);
			i++;
		}
	}
}

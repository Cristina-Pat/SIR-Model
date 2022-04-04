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
	

}

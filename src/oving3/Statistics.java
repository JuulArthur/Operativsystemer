package oving3;

/**
 * This class contains a lot of public variables that can be updated
 * by other classes during a simulation, to collect information about
 * the run.
 */
public class Statistics
{
	/** The number of processes that have exited the system */
	public long nofCompletedProcesses = 0;
	/** The number of processes that have entered the system */
	public long nofCreatedProcesses = 0;
	/** The number of processed I/O operations */
	public long nofProcessedIoOperations = 0;
	/** The total time that all completed processes have spent waiting for memory */
	public long totalTimeSpentWaitingForMemory = 0;
	/** The time-weighted length of the memory queue, divide this number by the total time to get average queue length */
	public long memoryQueueLengthTime = 0;
	/** The largest memory queue length that has occured */
	public long memoryQueueLargestLength = 0;
	/** The largest CPU queue length that has occured */
	public long CpuQueueLargestLength = 0;
	/** The time-weighted length of the CPU queue, divide this number by the total time to get average queue length */
	public long cpuQueueLengthTime = 0;
	/** Total time all processes have waited in the CPU-queue*/
	public long totalTimeInReadyQueue;
	/** The largest IO queue length that has occured */
	public long IoQueueLargestLength = 0;
	/** The time-weighted length of the IO queue, divide this number by the total time to get average queue length */
	public long ioQueueLengthTime = 0;
	/** Total time all processes have waited in the IO-queue*/
	public long totalTimeInIoQueue;
	/** The number of times processes has been forced to switch*/
	public long nofForcedProcessSwitch = 0;
	/** The number of times processes has needed IO operations*/
	public long nofIoOperations = 0;
	/** The total amount of time the CPU has been in use */
	public long totalCpuTime = 0;
	/** The total time all finished processes has been in the system*/
	public long timeInSystem = 0;
	/** The total number of times processes has been in the I/O queue*/
	public int nofTimesInIoQue = 0;
	/** The total number of times processes has been in the CPU queue*/
	public int nofTimesInCpuQue = 0;

	/**
	 * Prints out a report summarizing all collected data about the simulation.
	 * @param simulationLength	The number of milliseconds that the simulation covered.
	 */
	public void printReport(long simulationLength) {
		System.out.println();
		System.out.println("Simulation statistics:");
		System.out.println();
		System.out.println("Number of completed processes:                                    "+nofCompletedProcesses);
		System.out.println("Number of created processes:                                      "+nofCreatedProcesses);
		System.out.println("Number of forced process switches:                                "+nofForcedProcessSwitch);
		System.out.println("Number of processed I/O operations:                               "+nofIoOperations);
		System.out.println("Average thtoughput (processes per second):                        "+(float)nofCompletedProcesses/simulationLength*1000);
		System.out.println();
		System.out.println("Total time CPU has been in use:                                   "+totalCpuTime);
		System.out.println("Total time CPU has been free:                                     "+(simulationLength-totalCpuTime));
		System.out.println("Total CPU utilisation:                                            "+((float)totalCpuTime/simulationLength));
		System.out.println("Fraction of CPU time spent waiting:                               "+(1-(float)totalCpuTime/simulationLength));
		System.out.println();
		if(nofCompletedProcesses > 0) {
			System.out.println("Largest occuring memory queue length:                             "+memoryQueueLargestLength);
			System.out.println("Average memory queue length:                                      "+(float)memoryQueueLengthTime/simulationLength);
			System.out.println("Average time spent waiting for memory per process:                "+
					(float)totalTimeSpentWaitingForMemory/nofCompletedProcesses+" ms");
			System.out.println("Largest occuring CPU queue length:                                "+CpuQueueLargestLength);
			System.out.println("Average CPU queue length:                                         "+(float)cpuQueueLengthTime/simulationLength);
			System.out.println("Average time spent in the CPU queue:                              "+totalTimeInReadyQueue/nofCompletedProcesses);
			System.out.println("Largest occuring I/O queue length:                                "+IoQueueLargestLength);
			System.out.println("Average I/O queue length:                                         "+(float)ioQueueLengthTime/simulationLength);
			System.out.println("Average time spent in the I/O queue:                              "+(float)totalTimeInIoQueue/nofCompletedProcesses);
			System.out.println("Average # of times a process has been placed in memory queue:     "+1);
			System.out.println("Average # of times a process has been placed in CPU queue:        "+(float)nofTimesInCpuQue/nofCompletedProcesses);
			System.out.println("Average # of times a process has been placed in I/O queue:        "+(float)nofTimesInIoQue/nofCompletedProcesses);
		}
	}
}

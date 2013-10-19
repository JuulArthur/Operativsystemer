package oving3;

public class IO {
	/** The active process by the IO*/
	private Process activeProcess;
	/** The GUI showing the processes*/
	private Gui gui;
	/** The queue for the processes waiting for IO*/
	private Queue ioQueue;
	/** Avg time needed for IO*/
	private long ioTime;
	/** The statistics for the simulation*/
	private Statistics statistics;
	
	public IO(Gui gui, Queue ioQueue, long ioTime, Statistics statistics){
		this.gui = gui;
		this.ioQueue = ioQueue;
		this.ioTime = ioTime;
		this.statistics = statistics;
		this.activeProcess = null;
	}
	
	/**
	 * 
	 * @param process   The process to add to the IO
	 * @param clock   The global clock used to update the timers
	 * @return   Returns true if the queue is empty
	 */
	public boolean addProcess(Process process, long clock){
		statistics.nofTimesInIoQue++;
		if(activeProcess==null){
			activeProcess=process;
			process.enterIo(clock);
			gui.setIoActive(process);
			return true;
		}
		process.enterIoQueue(clock);
		ioQueue.insert(process);
		if(ioQueue.getQueueLength()>statistics.IoQueueLargestLength){
			statistics.IoQueueLargestLength = ioQueue.getQueueLength();
		}
		return false;
	}
	
	public Process start(){
		if (ioQueue.isEmpty()){
			return null;
		}
		Process process = (Process) ioQueue.removeNext();
		this.activeProcess = process;
		gui.setIoActive(process);
		return process;
	}
	
	public Process getActiveProcess(){
		Process process = this.activeProcess;
		this.activeProcess = null;
		gui.setIoActive(this.activeProcess);
		return process;
	}
	
	public long getRandomIoTime(){
		return (long) (Math.random()*(ioTime+0.5*ioTime));
	}
	
	public void timePassed(long timePassed) {
		statistics.ioQueueLengthTime += ioQueue.getQueueLength()*timePassed;
		if (ioQueue.getQueueLength() > statistics.IoQueueLargestLength) {
			statistics.IoQueueLargestLength = ioQueue.getQueueLength(); 
		}
    }

}

package oving3;

import java.util.ArrayList;
import java.util.List;

public class CPU {
	
	/** The queue where all the processes in the CPU are */
	private Queue cpuQueue;
	/** The process being currently executed*/
	private Process activeProcess;
	/** The GUI used by the CPU*/
	private Gui gui;
	/** The maximum amount of time a process can be in the CPU*/
	private long maxCpuTime;
	/** The statistics used for the simulation*/
	private Statistics statistics;
	
	/** Creates a CPU instance
	 * @param cpuQueue the memory queue for the processes in the CPU
	 * @param gui is the gui to show actions by the CPU
	 * @param maxCpuTime defines how long a process can be handled by the CPU
	 */
	public CPU(Queue cpuQueue, Gui gui, long maxCpuTime, Statistics statistics){
		this.cpuQueue = cpuQueue;
		this.gui = gui;
		this.maxCpuTime = maxCpuTime;
		this.statistics = statistics;
		this.activeProcess = null;
	}
	
	public void addProcess(Process process){
		statistics.nofTimesInCpuQue++;
		cpuQueue.insert(process);
	}
	
	public Process removeProcess(){
		if(!cpuQueue.isEmpty()){
			Process process = (Process) cpuQueue.removeNext();
			return process;
		}
		return null;
	}
	
	public Boolean isIdle(){
		return this.activeProcess==null;
	}
	
	public Process getActiveProcess(){
		Process process = this.activeProcess;
		this.activeProcess = null;
		return process;
	}
	
	public Process startNextProcess(){
		if(cpuQueue.isEmpty()){
			this.activeProcess = null;
			gui.setCpuActive(activeProcess);
		}
		else{
			this.activeProcess = removeProcess();
			gui.setCpuActive(activeProcess);
		}
		return this.activeProcess;
	}

	public long getMaxCpuTime() {
		return this.maxCpuTime;
	}
	
	public void timePassed(long timePassed) {
		statistics.cpuQueueLengthTime += cpuQueue.getQueueLength()*timePassed;
		if (cpuQueue.getQueueLength() > statistics.CpuQueueLargestLength) {
			statistics.CpuQueueLargestLength = cpuQueue.getQueueLength(); 
		}
    }

}

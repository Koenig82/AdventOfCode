package y2019.intcodeComputer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class CPUControl {
	
	private Core[] cores;
	private Thread[] processes;
	private IO io;

	public CPUControl(int nrOfcores) {
		cores = new Core[nrOfcores];
		processes = new Thread[nrOfcores];
		io = new IO(nrOfcores);
		for(int i = 0;i<nrOfcores;i++) {
			cores[i] = new Core(i, io);
			processes[i] = new Thread(cores[i]);
		}
	}
	
	public void loadProgramAtCoreId(String path, int coreId) throws UnsupportedEncodingException, IOException {
		cores[coreId].loadProgram(path);
		
	}

	public void writeToCacheIndexAtCoreId(int symbol, int index, int coreId) {
		cores[coreId].writeToMemory(symbol, index);
	}
	
	public void executePrograms() {
		for (Thread process : processes) {
			process.run();
		}
		/*for(Thread process : processes) {
			try {
				process.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
	public void executeXNrOfCores(int nrOfCores) {
		for(int i = 0; i < nrOfCores; i++) {
			processes[i].run();
		}
		for(int i = 0; i < nrOfCores; i++) {
			try {
				processes[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public BlockingQueue<Integer> getOutputFromCore(int coreId) {
		return io.getOutput(coreId);
	}
	public BlockingQueue<Integer> getInputFromCore(int coreId) {
		return io.getInput(coreId);
	}
	public int getValueFromCoreAtIndex(int coreId, int index) {
		return cores[coreId].getFromMemory(index);
	}
	public void pipeCoreToCore(int coreIdA, int coreIdB) {
		cores[coreIdA].setPipe(coreIdB);
	}
}

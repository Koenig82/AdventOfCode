package y2019.intcodeComputer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Threadpool {
	
	//private Queue<> cores;
	private IO io;
	private ExecutorService threadpool;
	
	public Threadpool(int nrOfcores) {
		threadpool = Executors.newFixedThreadPool(nrOfcores);
	}
	
	
	
	//kanske bara kora ett program på en core och ge nån klar signal
	//så en ny core kan användas när den är klar
	
	
//	public BlockingQueue<Integer> getOutputFromCore(int coreId) {
//		return io.getOutput(coreId);
//	}
//	public BlockingQueue<Integer> getInputFromCore(int coreId) {
//		return io.getInput(coreId);
//	}
//	public int getValueFromCoreAtIndex(int coreId, int index) {
//		return cores[coreId].getFromMemory(index);
//	}
//	public void pipeCoreToCore(int coreIdA, int coreIdB) {
//		cores[coreIdA].setPipe(coreIdB);
//	}

	public void waitUntilDone() {
		threadpool.shutdown();
		try {
			threadpool.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("Threadpool interrupted");
			e.printStackTrace();
		}
	}

	public void runInParallell(Runnable core, Runnable after) {
		threadpool.execute(() ->{
			core.run();
			after.run();
		});
	}
}

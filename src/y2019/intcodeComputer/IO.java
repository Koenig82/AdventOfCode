package y2019.intcodeComputer;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IO {
	
	private class CoreAccess{
		BlockingQueue<Integer> input;
		BlockingQueue<Integer> output;
		public CoreAccess() {
			input = new LinkedBlockingQueue<>();
			output = new LinkedBlockingQueue<>();
		}
	}

	private ArrayList<CoreAccess> access;

	public IO(int nrOfCores) {
		access = new ArrayList<>();
		for(int i = 0; i < nrOfCores; i++) {
			access.add(new CoreAccess());
		}
	}

	public BlockingQueue<Integer> getInput(int coreId) {
		
		return access.get(coreId).input;
	}

	public BlockingQueue<Integer> getOutput(int coreId) {
		
		return access.get(coreId).output;
	}
}

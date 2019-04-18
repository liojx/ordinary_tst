package studiii.zlsj_test.basics.thread.thinkinjava;

import java.util.concurrent.Callable;

public class TaskWithResults implements Callable<String>{
	private int id;
	public TaskWithResults(int id ) {
		this.id = id;
	}
	@Override
	public String call() throws Exception {
		return "result of TaskWithResult " + id;
	}
	

}

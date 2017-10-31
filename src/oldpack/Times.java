package oldpack;

public class Times {
	
	private long times[][];
	private int num_test;
	
	public Times(int num_library,int num_test) {
	
		this.num_test = num_test;		
		times = new long[num_library][num_test];		
	}
	
	public long get(int library, int test) {
		return times[library][test];
	}
	
	public void set(int library, int test, long value) {
		times[library][test] = value;
	}
	
	public long mean(int library) {
		
		int mean = 0;
		
		for(int i=0; i<num_test; i++)
			mean += times[library][i];
		
		mean = mean / num_test;
		
		return mean;
	}
	
	
	

}

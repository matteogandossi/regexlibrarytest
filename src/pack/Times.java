package pack;

public class Times {
	
	private long times[][][];
	private int tot_num_test;
	
	public Times(int num_library, int test_case, int tot_num_test) {
	
		this.tot_num_test = tot_num_test;		
		times = new long[num_library][test_case][tot_num_test];		
	}
	
	public long get(int library, int test_case, int num_test) {
		return times[library][test_case][num_test];
	}
	
	public void set(int library, int test_case, int num_test, long value) {
		times[library][test_case][num_test] = value;
	}
	
	public long mean(int library, int test_case) {
		
		int mean  = 0;
		
		for(int i=0; i<tot_num_test; i++)
			mean += times[library][test_case][i];
		
		mean = mean / tot_num_test;
		
		return mean;
	}

}

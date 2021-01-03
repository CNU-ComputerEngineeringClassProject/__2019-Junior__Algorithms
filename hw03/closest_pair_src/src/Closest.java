
public class Closest {

	double xy[][];
	int idx;

	public Closest(int num) {
		this.xy = new double[num][2];
		this.idx = 0;
	}

	public void add(double x, double y) {
		this.xy[idx][0] = x;
		this.xy[idx++][1] = y;
	}
	public double closest_start() {

		xy = sorting_xy(xy,0);
		return closest_pair(0,xy.length-1);
	}
	public double closest_pair(int st_idx , int end_idx) {
		int arr_size = end_idx-st_idx+1;

		if(arr_size < 4) 	
			return bruteForce(st_idx, end_idx);


		int m_idx= (int) Math.floor((st_idx+end_idx)/2);
		double min_f = closest_pair(st_idx,m_idx);
		double min_s = closest_pair(m_idx+1,end_idx);
		double min = minValue(min_f,min_s);

		//n/2 지점에서 x좌표 값으로부터 d이내에 있는 좌표만 분리
		double max_dx = xy[m_idx][0]+min;
		double min_dx = xy[m_idx][0]-min;

		int half_xmin, half_xmax;

		for(half_xmin=m_idx; 0 <= half_xmin;half_xmin--) {
			if(xy[half_xmin][0] < min_dx) {
				half_xmin++;
				break;
			}
		}
		if(half_xmin==-1) half_xmin++;

		for(half_xmax=m_idx; half_xmax<xy.length;half_xmax++) {
			if(xy[half_xmax][0] > max_dx) {
				half_xmax--;
				break;
			}
		}

		if(half_xmax==xy.length) half_xmax--;
		
		double xy_2d[][] = new double[half_xmax-half_xmin+1][2];

		for(int idx=0;idx < xy_2d.length;idx++) {
			xy_2d[idx][0] = xy[half_xmin+idx][0];
			xy_2d[idx][1] = xy[half_xmin+idx][1];
		}

		if(xy_2d.length > 1 ) { 
			xy_2d = sorting_xy(xy_2d,1);
		}

		double min_temp = minY_dis(xy_2d,min);
		
		if(0 <= min_temp && min_temp < min) min = min_temp;

		return min;
	}

	private double bruteForce(int st_idx , int end_idx) {

		double min = Math.sqrt((Math.pow((xy[st_idx+1][0]-xy[st_idx][0]), 2)+Math.pow((xy[st_idx+1][1]-xy[st_idx][1]), 2)));


		if((end_idx-st_idx+1) > 2) {
			double	m_value1 = Math.sqrt((Math.pow((xy[end_idx][0]-xy[st_idx][0]), 2)+Math.pow((xy[end_idx][1]-xy[st_idx][1]), 2)));
			double	m_value2 = Math.sqrt((Math.pow((xy[end_idx][0]-xy[end_idx-1][0]), 2)+Math.pow((xy[end_idx][1]-xy[end_idx-1][1]), 2)));
			if(m_value1 < min) min = m_value1;
			if(m_value2 < min) min = m_value2;
		}

		return min; 
	}

	private double minValue(double mf, double ms) {
		if(mf < ms) return mf;
		return ms;
	}

	private double minY_dis(double[][]arr,double min) {

		double m=-1;
		double m_v;

		for(int i=0; i<arr.length-1 ; i++) {

			for(int j=i+1 ; j<arr.length ; j++) {
				if(Math.abs(arr[j][1]-arr[i][1]) < min) {

					if(m==-1) {
						m=Math.sqrt((Math.pow((arr[j][0]-arr[i][0]), 2)+Math.pow((arr[j][1]-arr[i][1]), 2)));
					}else {
						m_v=Math.sqrt((Math.pow((arr[j][0]-arr[i][0]), 2)+Math.pow((arr[j][1]-arr[i][1]), 2)));
						if(m_v <m) m=m_v;
					}
				}	
			}

		}
	
		return m;
	}

	private double[][] sorting_xy(double[][]arr, int idx){

		int i =0;
		int converse_idx;

		if(idx == 0) converse_idx=1;
		else converse_idx=0;

		for(int j = 1; j < arr.length ; j++) {

			double converse_key = arr[j][converse_idx];
			double key = arr[j][idx];

			for(i=j-1 ; 0 <= i ; i--) {

				if(key < arr[i][idx]) {
					arr[i+1][converse_idx] = arr[i][converse_idx];
					arr[i+1][idx] = arr[i][idx];
				}else {
					arr[i+1][converse_idx] = converse_key;
					arr[i+1][idx] = key;
					break;
				}
			}
			if(i < 0) {
				arr[i+1][converse_idx] = converse_key;
				arr[i+1][idx] = key;
			}
		}
		return arr;
	}

}

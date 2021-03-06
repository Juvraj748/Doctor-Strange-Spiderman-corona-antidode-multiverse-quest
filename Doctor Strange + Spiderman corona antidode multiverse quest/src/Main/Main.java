package Main;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int univ = s.nextInt();
		int portals = s.nextInt();
		int[][] arr = new int[univ+1][univ+1];
		
		for(int i=0;i<univ+1;i++) {
			for(int j=0;j<univ+1;j++) {
				arr[i][j] = -1;
			}
		}
		
		for(int i=0; i<portals ;i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			int time = s.nextInt();
			
			arr[a][b] = time;
			arr[b][a] = time;
		}
		
		HashSet<Integer>[] timestamps = new HashSet[univ+1];
		
		for(int i=1; i<univ+1 ;i++ ) {
			int size = s.nextInt();
			HashSet<Integer> set = new HashSet<Integer>();
			for(int j=0 ; j<size ; j++) {
				set.add(s.nextInt());
			}
			
			timestamps[i] = set;
		}
		
		
		boolean[] visited = new boolean[univ+1];
		int[] minTime = new int[univ+1];
		
		for(int i=1; i<univ+1 ;i++) {
			visited[i] = false;
		}
		
		for(int i=2; i<univ+1 ;i++) {
			minTime[i] = Integer.MAX_VALUE;
		}
		
		for(int a=1 ; a<univ+1 ; a++) {
			int currUniv = -1;
			for(int i = 1; i<univ+1 ;i++) {
				if(!visited[i] && (currUniv == -1 || minTime[currUniv] > minTime[i] )) {
					currUniv = i;
				}
			}
			visited[currUniv] = true;
			
			for(int i=1 ; i<univ+1 ; i++) {
				if(arr[currUniv][i]>=0 && !visited[i]) {
					HashSet<Integer> timestamp = timestamps[i];
					int patrolTime = 0;
					int time = minTime[currUniv] + arr[currUniv][i];
					
					if(timestamp!=null && timestamp.contains(time)) {
						patrolTime=1;
					}
					
					if(time + patrolTime < minTime[i]) {
						minTime[i] = time + patrolTime ;
					}
				}
			}
			
		}
		
//		Final answer
		System.out.println(minTime[univ]);
		
	}
}

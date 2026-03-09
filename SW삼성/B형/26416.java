package SWEA_26416;

//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Objects;
//import java.util.Set;

//import SWEA_26416.UserSolution.Plant;

class UserSolution {
	int N;
	//int[][] map; 
	//HashMap<Pair, Plant> plantTypes; //작물의 품종.
	int[] gTime; // 품종당 작물 자라는 시간.
	Plant[][] plants; // 식물 관리
	
	/*static class Pair implements Comparable<Pair>{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Pair o) {
			if(o.x == this.x && o.y == this.y) return 1;
			return 0;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Pair) {
				obj = (Pair) obj;
				if(((Pair) obj).x == this.x && ((Pair) obj).y == this.y) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}*/
	
	public class Plant {
		int no; // 품종 번호.
		int time; // 심겨진 시간.
		int water;
		
		public Plant(int no, int time){
			this.no = no;
			this.time = time;
		}

	}
	void init(int N, int mGrowthTime[])	{
		this.N = N; 
		gTime = mGrowthTime;
		plants = new Plant[N][N];
		/*for(int i = 0; i < N; i++) {
			Arrays.fill(map[i], -1);
		}
		
		plantTypes = new HashMap<>();*/
	}

	int sow(int mTime, int mRow, int mCol, int mCategory) {
		if(plants[mRow][mCol] != null) {
			return 0;
		}
		
		Plant plant = new Plant(mCategory, mTime);
		plants[mRow][mCol] = plant;
		
		/*if(map[mRow][mCol] != -1) {
			return 0;
		}
		
		map[mRow][mCol] = 0;
		Pair position = new Pair(mRow, mCol);
		Plant plant = new Plant(mCategory, mTime);
		plantTypes.put(position, plant);*/
		return 1;
	}

	int water(int mTime, int G, int mRow, int mCol, int mHeight, int mWidth) {
		int cnt = 0;
		for(int i = mRow; i < mRow+mHeight; i++) {
			for(int j = mCol; j < mCol+mWidth; j++) {
				if(plants[i][j] != null) {
					plants[i][j].water += G;
					cnt++;
				}
			}
		}
		/*Set<Pair> positions = plantTypes.keySet();
		for(Pair position: positions) {
			if(position.x >= mRow && position.y >= mCol && position.x < mRow+mHeight && position.y < mCol+mWidth) {
				map[position.x][position.y] += G;
				cnt++;
			}
		}*/
		/*for(int i = mRow; i < mRow+mHeight; i++) {
			for(int j = mCol; j < mCol+mWidth; j++) {
				if(map[i][j] >= 0) {
					map[i][j] += G;
					cnt++;
				}
			}
		}*/
		return cnt;
	}

	int harvest(int mTime, int L, int mRow, int mCol, int mHeight, int mWidth) {
		int cnt = 0;
		for(int i = mRow; i < mRow+mHeight; i++) {
			for(int j = mCol; j < mCol+mWidth; j++) {
				if(plants[i][j] == null) continue;
				int type = plants[i][j].no;
				int time = plants[i][j].time;
				int water = plants[i][j].water;
				int totalGrown = ((mTime-time) / gTime[type]) + water;
				if(totalGrown < L) {
					return 0;
				}
			}
		}
		
		for(int i = mRow; i < mRow+mHeight; i++) {
			for(int j = mCol; j < mCol+mWidth; j++) {
				if(plants[i][j] != null) {
					plants[i][j] = null;
					cnt++;
				}
			}
		}
		/*Set<Pair> positions = plantTypes.keySet();
		for(Pair position: positions) {
			if(position.x >= mRow && position.y >= mCol && position.x < mRow+mHeight && position.y < mCol+mWidth) {
				int i = position.x;
				int j = position.y;
				int grown = map[i][j];
				int type = plantTypes.get(new Pair(i,j)).no;
				int time = plantTypes.get(new Pair(i,j)).time;
				int totalGrown = ((mTime-time) / gTime[type]) + grown;
				if(totalGrown < L) {
					return 0;
				}
			}
		}
		
		Pair[] array = new Pair[positions.size()];
		positions.toArray(array);
		
		for(int z = 0; z < positions.size(); z++) {
			Pair position = array[z];
			if(position.x >= mRow && position.y >= mCol && position.x < mRow+mHeight && position.y < mCol+mWidth) {
				int i = position.x;
				int j = position.y;
				map[i][j] = -1;
				plantTypes.remove(position);
				cnt++;
			}
		}*/
		
		/*for(int i = mRow; i < mRow+mHeight; i++) {
			for(int j = mCol; j < mCol+mWidth; j++) {
				int grown = map[i][j];
				if(grown == -1) continue;
				int type = plantTypes.get(new Pair(i,j)).no;
				int time = plantTypes.get(new Pair(i,j)).time;
				int totalGrown = ((mTime-time) / gTime[type]) + grown;
				if(totalGrown < L) {
					return 0;
				}
			}
		}
		
		for(int i = mRow; i < mRow+mHeight; i++) {
			for(int j = mCol; j < mCol+mWidth; j++) {
				if(map[i][j] >= 0) {
					map[i][j] = -1;
					cnt++;
					plantTypes.remove(new Pair(i,j));
				}
			}
		}*/
		return cnt;
	}
}

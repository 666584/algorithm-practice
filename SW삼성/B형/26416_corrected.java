package SWEA_26416;

import java.util.ArrayList;
import java.util.TreeSet;

class UserSolution {
	int N;
	int[] gTime; // 품종당 작물 자라는 시간.
    TreeSet<Integer>[] plantedCols;
    
    int[][] type;
    int[][] sowTime;
    Bucket[][] buckets;
    boolean[][] exists;
    boolean[][] field;
    int BUCKET_SIZE = 32;
    int bucketCnt;
    
    class Bucket{
    	int lazyWater;
    	ArrayList<Crop> crops = new ArrayList<>();
    	
    	Bucket(){}
    }
    
    class Crop{
    	int sowTime;
    	int r;
    	int c;
    	int type;
    	int water;
    	int baseLazy;
    	Crop(int row, int col, int sowTime, int type){
    		this.r = row;
    		this.c = col;
    		this.sowTime = sowTime;
    		this.type = type;
    		
    	}
    }

	void init(int N, int mGrowthTime[])	{
		this.N = N; 
		gTime = mGrowthTime;
		bucketCnt = (N + BUCKET_SIZE - 1) / BUCKET_SIZE;
        buckets = new Bucket[bucketCnt][bucketCnt];
        field = new boolean[N][N];
        
	}

	int sow(int mTime, int mRow, int mCol, int mCategory) {
		if(field[mRow][mCol] == true) {
			return 0;
		}
		
		int r = mRow / BUCKET_SIZE;
		int c = mCol / BUCKET_SIZE;
		Crop crop = new Crop(mRow, mCol, mTime, mCategory);
		if(buckets[r][c] == null) {
			buckets[r][c] = new Bucket();
		}else crop.baseLazy = buckets[r][c].lazyWater;
		buckets[r][c].crops.add(crop);
		field[mRow][mCol] = true;
		return 1;
	}

	int water(int mTime, int G, int mRow, int mCol, int mHeight, int mWidth) {
		int cnt = 0;
		int br1 = mRow / BUCKET_SIZE;
		int bc1 = mCol / BUCKET_SIZE;
		int br2 = (mRow + mHeight) / BUCKET_SIZE;
		int bc2 = (mCol + mWidth) / BUCKET_SIZE;
		
		for(int br = br1; br <= br2; br++) {
			for(int bc = bc1; bc <= bc2; bc++) {
				Bucket bucket = buckets[br][bc];
				if(bucket == null) continue;
				int r = br * BUCKET_SIZE;
				int c = bc * BUCKET_SIZE;
				int er = Math.min(N - 1, r + BUCKET_SIZE - 1);
				int ec = Math.min(N - 1, c + BUCKET_SIZE - 1);
				if(mRow <= r && mCol <= c && er < mRow + mHeight && ec < mCol + mWidth) {
					bucket.lazyWater += G;
					cnt += bucket.crops.size();
				}
				else {
					for(Crop crop: bucket.crops) {
						if(mRow <= crop.r &&
								mCol <= crop.c &&
								crop.r < mRow + mHeight &&
								crop.c < mCol + mWidth) {
							crop.water += G;
							cnt++;
						}
					}
				}
			}
		}
		return cnt;
	}

	int harvest(int mTime, int L, int mRow, int mCol, int mHeight, int mWidth) {
		int cnt = 0;
		int br1 = mRow / BUCKET_SIZE;
		int bc1 = mCol / BUCKET_SIZE;
		int br2 = (mRow + mHeight) / BUCKET_SIZE;
		int bc2 = (mCol + mWidth) / BUCKET_SIZE;
		
		for(int br = br1; br <= br2; br++) {
			for(int bc = bc1; bc <= bc2; bc++) {
				Bucket bucket = buckets[br][bc];
				if(bucket == null) continue;
				for(Crop crop: bucket.crops) {
					if(mRow <= crop.r &&
							mCol <= crop.c &&
							crop.r < mRow + mHeight &&
							crop.c < mCol + mWidth) {
						int grown = (mTime - crop.sowTime)/gTime[crop.type] + (bucket.lazyWater - crop.baseLazy) + crop.water;
						//System.out.println("grown"+grown+ " " + crop.r +" "+ crop.c);
						if(grown < L) return 0;
					}
				}
			}
		}
		for(int br = br1; br <= br2; br++) {
			for(int bc = bc1; bc <= bc2; bc++) {
				Bucket bucket = buckets[br][bc];
				if(bucket == null) continue;
				int size = bucket.crops.size();
				for(int i = size-1; i >= 0; i--) {
					Crop crop = bucket.crops.get(i);
					if(mRow <= crop.r &&
							mCol <= crop.c &&
							crop.r < mRow + mHeight &&
							crop.c < mCol + mWidth) {
						//System.out.println("removed "+crop.r + " "+crop.c);
						bucket.crops.remove(i);
						field[crop.r][crop.c] = false;
						cnt++;
					}
				}
			}
		}

		return cnt;
	}
}

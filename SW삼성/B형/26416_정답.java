import java.util.*;
 
class UserSolution {
    static final int MAX_N = 1000;
    static final int BUCKET_SIZE = 32;
     
    class Crop {
        int r, c, category, sowTime, addedWater;
        boolean isHarvested;
 
        Crop(int r, int c, int cat, int time) {
            this.r = r;
            this.c = c;
            this.category = cat;
            this.sowTime = time;
            this.addedWater = 0;
        }
 
        int getHeight(int curTime, int gTime) {
            return (curTime - sowTime) / gTime + addedWater;
        }
    }
     
    class Bucket{
        int lazyWater;
        int count;
        ArrayList<Crop> cops = new ArrayList<>();
    }
 
     
    int N;
    int[] growthTime = new int[3];
    Crop[][] crop = new Crop[MAX_N][MAX_N];
    Bucket[][] buckets = new Bucket[MAX_N / BUCKET_SIZE + 1][MAX_N / BUCKET_SIZE + 1];
     
    Crop[] pollCrop = new Crop[100005];
    //rrayList<Crop> rowList[] = new ArrayList[1005];
    int cropcnt;
 
    void init(int N, int mGrowthTime[]) {
       this.N = N;
       for(int i = 0; i < 3; i++ ) {
           growthTime[i] = mGrowthTime[i];
       }
       cropcnt = 0;
       for(int i = 0; i < N; i++) {
           for(int j = 0; j < N; j++) {
               crop[i][j] = null;
           }
            
       }
        
       for(int i = 0; i < MAX_N / BUCKET_SIZE + 1; i++) {
           for(int j = 0; j < MAX_N / BUCKET_SIZE + 1; j ++) {
               if(buckets[i][j] == null) buckets[i][j] = new Bucket();
               buckets[i][j].cops.clear();
               buckets[i][j].count = 0;
               buckets[i][j].lazyWater = 0;
           }
       }
    }
 
    int sow(int mTime, int mRow, int mCol, int mCategory) {
        if(crop[mRow][mCol] != null) return 0;
        Crop c = new Crop(mRow, mCol, mCategory, mTime);
        crop[mRow][mCol] = c;
        pollCrop[cropcnt] = c;
        int rbIs = mRow / BUCKET_SIZE;
        int cbIds = mCol / BUCKET_SIZE;
        c.addedWater -= buckets[rbIs][cbIds].lazyWater;
        buckets[rbIs][cbIds].cops.add(c);
        buckets[rbIs][cbIds].count++;
        cropcnt++;
        return 1;
    }
 
    int water(int mTime, int G, int mRow, int mCol, int mHeight, int mWidth) {
        int count = 0;
        int rowEnd = mRow + mHeight;
        int colEnd = mCol + mWidth;
         
        int rbStart = mRow / BUCKET_SIZE;
        int rbEnd = (rowEnd - 1) / BUCKET_SIZE;
        int cbStart = mCol / BUCKET_SIZE;
        int cbend = (colEnd - 1) / BUCKET_SIZE;
         
        for(int rb = rbStart; rb <= rbEnd; rb++) {           
             
            for(int cb = cbStart; cb <= cbend; cb++) {
                if(buckets[rb][cb].cops.isEmpty()) continue;
                 
                if(mCol <= cb * BUCKET_SIZE && (1 + cb) * BUCKET_SIZE - 1 < colEnd && mRow <= rb * BUCKET_SIZE && (rb + 1) * BUCKET_SIZE - 1 < rowEnd ) {
                    buckets[rb][cb].lazyWater += G;
                    count += buckets[rb][cb].count;
                } else {
                    for( Crop c : buckets[rb][cb].cops) {
                        if(mCol <= c.c && c.c < colEnd && mRow <= c.r && c.r < rowEnd) {
                            c.addedWater += G;
                            count++;
                        }
                    }
                }
            }
        }
         
        return count;
    }
 
    int harvest(int mTime, int L, int mRow, int mCol, int mHeight, int mWidth) {
        int totalInRegion = 0;
        int rowEnd = mRow + mHeight;
        int colEnd = mCol + mWidth;
         
         
        int rbStart = mRow / BUCKET_SIZE;
        int rbEnd = (rowEnd - 1) / BUCKET_SIZE;
        int cbstart = mCol / BUCKET_SIZE;
        int cbend = (colEnd - 1) / BUCKET_SIZE;
         
        for(int rb = rbStart; rb <= rbEnd; rb++) {
             
            for(int cb = cbstart; cb <= cbend; cb++) {
                if(buckets[rb][cb].cops.isEmpty()) continue;
                for(Crop c : buckets[rb][cb].cops ) {
                    if(mCol <= c.c && c.c < colEnd && mRow <= c.r && c.r < rowEnd) {
                        if(L > c.getHeight(mTime, growthTime[c.category]) + buckets[rb][cb].lazyWater ) {
                            //미만이면 진행하지 않음
                            return 0;
                        } else {
                            totalInRegion++;
                        }
                    }
                }
            }
        }
         
        for(int rb = rbStart; rb <= rbEnd; rb++) {
             
            for(int cb = cbstart; cb <= cbend; cb++) {
                if(buckets[rb][cb].cops.isEmpty()) continue;
                Iterator<Crop> it = buckets[rb][cb].cops.iterator();
                 
                while(it.hasNext()) {
                    Crop c = it.next();
                    if(mCol <= c.c && c.c < colEnd && mRow <= c.r && c.r < rowEnd) {
                     
                        crop[c.r][c.c] = null;
                        it.remove();
                        buckets[rb][cb].count--;
                    }
                }
            }
        }
        return totalInRegion;
    }
}

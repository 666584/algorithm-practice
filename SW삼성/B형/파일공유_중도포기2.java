package 파일공유;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class UserSolution {
	int N; //컴퓨터 개수
	Map<Integer, File> files;// 파일 id, 파일들.
	Map<Integer, ArrayList<Node>> links; // 컴퓨터 id, 네트워크의 링크.
	int K; // 링크 개수
	
	class File{
		Map<Integer, int[]> shareComputers; // 각 컴퓨터 아이디와 컴퓨터 당 같이 다운로드 하는 수. 시작시간.
		Map<Integer, int[]> requestComputers; // 각 컴퓨터 아이디와 컴퓨터 당 요청한 파일을 다운로드 수.시작시간.
		int size;
		int id;
		File(int size, int id, Map<Integer, int[]> shareComputers){
			this.size = size;
			this.id = id;
			this.shareComputers = shareComputers;
		}
	}
	
	class Node{
		int computer;
		int distance;
		
		Node(int computer, int distance){
			this.computer = computer;
			this.distance = distance;
		}
	}
	
	
	void init(int N, int mShareFileCnt[], int mFileID[][], int mFileSize[][])
	{
		this.N = N;
		files = new HashMap<>();
		for(int i = 1; i <= N; i++) {
			int K = mShareFileCnt[i-1];
			for(int j = 0; j < K; j++) {
				int fileId = mFileID[i-1][j];
				if(files.containsKey(fileId)) {
					File file = files.get(fileId);
					file.shareComputers.put(i, new int[] {0, -1});
				}
				else {
					Map<Integer, int[]> shareComputers = new HashMap<>();
					shareComputers.put(i, new int[] {0, -1});
					files.put(fileId, new File(mFileSize[i-1][j], fileId, shareComputers));
				}
			}
		}
	}

	void makeNet(int K, int mComA[], int mComB[], int mDis[])
	{
		links = new HashMap<>();
		for(int i = 0; i < K; i++) {
			if(links.containsKey(mComA[i])) {
				links.get(mComA[i]).add(new Node(mComB[i], mDis[i]));
			}
			
			if(links.containsKey(mComB[i])) {
				links.get(mComB[i]).add(new Node(mComA[i], mDis[i]));
			}
			if (!links.containsKey(mComA[i])) {
				ArrayList<Node> link = new ArrayList<>();
				link.add(new Node(mComB[i], mDis[i]));
				links.put(mComA[i], link);
			}
			if (!links.containsKey(mComB[i])) {
				ArrayList<Node> link = new ArrayList<>();
				link.add(new Node(mComA[i], mDis[i]));
				links.put(mComB[i], link);
			}
		}
	}

	void addLink(int mTime, int mComA, int mComB, int mDis)
	{
		
		if(links.containsKey(mComA)) links.get(mComA).add(new Node(mComB, mDis));
		if(links.containsKey(mComB)) links.get(mComB).add(new Node(mComA, mDis));
		if(!links.containsKey(mComA)) {
			ArrayList<Node> link = new ArrayList<>();
			link.add(new Node(mComB, mDis));
			links.put(mComA, link);
		}
		if(!links.containsKey(mComB)) {
			ArrayList<Node> link = new ArrayList<>();
			link.add(new Node(mComA, mDis));
			links.put(mComB, link);
		}

		// 모든 파일을 전부 확인해야한다. 
		Set<Integer> fileIDs = files.keySet();
		
		for(int fileID: fileIDs) {
			//이 파일에 대하여 request 한 컴퓨터가 있는지 확인하기
			File file = files.get(fileID);
			if(file.requestComputers == null) continue;
			// 있다면 거리 계산 하고 업데이트 하기. 
			Set<Integer> requestCIDs = file.requestComputers.keySet();
			
			for(int rcID: requestCIDs) {
				if((mComA == 1 && mComB == 4) &&(fileID == 111)) System.out.println("com: "+rcID);
				int result = downloadFile(mTime, rcID, fileID);
				if(mComA == 1 && mComB == 4) System.out.println("result: "+result+ " "+rcID);
			}
		}
	}

	void addShareFile(int mTime, int mComA, int mFileID, int mSize)
	{
		// 이 파일 아이디가 없었을 경우 다운로드를 하지 않는다. 
		if(!files.containsKey(mFileID)) {
			Map<Integer, int[]> shareComputers = new HashMap<>();
			shareComputers.put(mComA, new int[] {0, -1});
			files.put(mFileID, new File(mSize, mFileID, shareComputers));
		}
		
		else {
			File file = files.get(mFileID);
			file.shareComputers.put(mComA, new int[] {0, -1});
			//이 파일에 대하여 request 한 컴퓨터가 있는지 확인하기.
			if(file.requestComputers == null) return;
			// 있다면 거리 계산 하기. 
			Set<Integer> requestCIDs = file.requestComputers.keySet();
			for(int rcID: requestCIDs) {
				// 이미 request는 받았으나 다운로드는 되지 않았다. 
				int result = downloadFile(mTime, rcID, mFileID);
				if(mTime==15&&mComA==4) System.out.println("Add"+ result);
			}
		}
	}

	int downloadFile(int mTime, int mComA, int mFileID)
	{
		// 현 파일이 요청 되어있지 않거나 컴퓨터가 요청하지 않은 경우.
		if(files.get(mFileID).requestComputers == null ||!files.get(mFileID).requestComputers.containsKey(mComA)) {
			Map<Integer, int[]> requestComputers = new HashMap<>();
			requestComputers.put(mComA, new int[] {0,mTime});
			files.get(mFileID).requestComputers = requestComputers;
		}
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		ArrayList<Integer> downloadComputers = new ArrayList<>();
		queue.add(new int[] {mComA, 0});
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int computerId = curr[0];
			int currDistance = curr[1];
			if (files.get(mFileID).shareComputers.containsKey(computerId) && computerId != mComA) {
				if(files.get(mFileID).shareComputers.get(computerId)[0] == 0) {
					downloadComputers.add(computerId);
				}
			}
			if(!links.containsKey(computerId)) continue;
			for(Node next: links.get(computerId)) {
				if(currDistance + next.distance > 5000) continue;
				if(downloadComputers.contains(next.computer)) continue; // 이런식으로 하면 visited 로 하지 않기 때문에 실행 시간이 많이 길어질 수 있음. 
				queue.addFirst(new int[] {next.computer, currDistance + next.distance});
			}
		}
		
		int downloadCnt = downloadComputers.size();
		
		// 추가적으로 다운로드 되는 컴퓨터 발생될 경우 그 시각을 기록해야 한다. 
		if(files.get(mFileID).requestComputers.get(mComA)[0] == 0) {
			files.get(mFileID).requestComputers.get(mComA)[0] = downloadCnt;
			files.get(mFileID).requestComputers.get(mComA)[1] = mTime;
		}
		
		for(int dCom: downloadComputers) {
			files.get(mFileID).shareComputers.get(dCom)[0] = downloadCnt;
			files.get(mFileID).shareComputers.get(dCom)[1] = mTime;
		}
		
		if(mFileID == 513 && mTime == 32) System.out.println("download: "+downloadCnt);
		return downloadCnt;
	}

	int getFileSize(int mTime, int mComA, int mFileID)
	{
		int fileSize = files.get(mFileID).size;
		if(files.get(mFileID).requestComputers != null) {
			if(files.get(mFileID).requestComputers.containsKey(mComA)) {
				int downloadCnt = files.get(mFileID).requestComputers.get(mComA)[0];
				if(mFileID == 513  && mTime == 39) System.out.println("getFileSize: " +downloadCnt);
				if(downloadCnt == 0) return 0;
				int startTime = files.get(mFileID).requestComputers.get(mComA)[1];
				
				int currSize = 9*(mTime-startTime)*downloadCnt;
				if(mTime == 8 && mComA == 3) System.out.println("size: "+ currSize);
				if(currSize > fileSize) return fileSize;
				return currSize;
			}
		}
		if(files.get(mFileID).shareComputers.containsKey(mComA)) {
			int downloadCnt = files.get(mFileID).shareComputers.get(mComA)[0];
			if(downloadCnt == 0) return 0;
			int startTime = files.get(mFileID).shareComputers.get(mComA)[1];
			int currSize = 9*(mTime-startTime)*downloadCnt;
			if(currSize > fileSize) return fileSize;
			return currSize;
		}
		return 0;
	}
}

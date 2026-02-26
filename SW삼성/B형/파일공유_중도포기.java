package 파일공유;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class UserSolution {
	
	ArrayList<Node>[] links; // 간선 리스트
	int N; // 컴퓨터 개수
	Computer[] computers;
	int MAX_DISTANCE = 5000;
	HashMap<Integer, File> requestFilesByComputerID;
	
	class File{
		int startTime = -1;
		int downloadComputer = 0;
		int id;
		int size;
		File(int id, int size){
			this.id = id;
			this.size = size;
		}
		File(int id, int size, int startTime, int downloadComputer){
			this.id = id;
			this.size = size;
			this.startTime = startTime;
			this.downloadComputer = downloadComputer;
		}
		
		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}
		
		public void setDownloadComputer(int downloadComputer) {
			this.downloadComputer = downloadComputer;
		}
		
		public int getSizeAtTime(int mTime) {
			if (downloadComputer == 0) return 0;
			int downloadSize = (mTime - startTime)*9*downloadComputer;
			if(downloadSize > this.size) {
				return this.size;
			}
			return downloadSize;
		}
	}
	
	class Computer {
		Map<Integer, File> shareFiles;
		Map<Integer, File> requestFiles;
		
		public Computer(Map<Integer, File> shareFiles, Map<Integer, File> requestFiles) {
			this.shareFiles = shareFiles;
			this.requestFiles = requestFiles;
		}
		
		public void updateShareFiles(int FileID, int startTime, int downloadComputer) {
			shareFiles.get(FileID).setStartTime(startTime);
			shareFiles.get(FileID).setDownloadComputer(downloadComputer);
		}
		
		public int getSize(int mTime, int mFileID) {
			File shareFile = shareFiles.get(mFileID);
			File requestFile = requestFiles.get(mFileID);
			if(shareFile == null && requestFile == null) return 0;
			if(shareFile != null) {
				return shareFile.getSizeAtTime(mTime);
			}else return requestFile.getSizeAtTime(mTime);
			
		}
	}
	
	class Node {
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
		computers = new Computer[N+1];
		requestFilesByComputerID = new HashMap<>();
		for(int i = 1; i <= N; i++) {
			
			int fileCnt = mShareFileCnt[i-1];
			
			Map<Integer, File> shareFiles = new HashMap<>();
			Map<Integer, File> requestFiles = new HashMap<>();	
			
			for(int j = 0; j < fileCnt; j++) {
				int fileId = mFileID[i-1][j];
				int fileSize = mFileSize[i-1][j];
				File file = new File(fileId, fileSize);
				shareFiles.put(fileId, file);
			}
			
			computers[i] = new Computer(shareFiles, requestFiles);
		}
		
	}

	void makeNet(int K, int mComA[], int mComB[], int mDis[])
	{
		System.out.println("Starting making network");
		links = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			links[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < K; i++) {
			int c1 = mComA[i];
			int c2 = mComB[i];
			int distance = mDis[i];
			links[c1].add(new Node(c2, distance));
			links[c2].add(new Node(c1, distance));
		}
		System.out.println("Finished making Network");
	}
	
	void updateRequestFile(int mTime) {
		Set<Integer> computerIDs = requestFilesByComputerID.keySet();
		Queue<int[]> queue = new ArrayDeque<>();
		Map<Integer, List<Integer>> visitedComputers = new HashMap<>();
		
		for(int cID: computerIDs) {
			if(requestFilesByComputerID.get(cID).downloadComputer == 0) {
				queue.add(new int[] {cID, cID, requestFilesByComputerID.get(cID).id, 0});
				visitedComputers.put(cID, new ArrayList<>());
			}
		}
		
		if(queue.isEmpty()) return;
		
		while(!queue.isEmpty()) {	
			int[] curr = queue.poll();
			int start = curr[0];
			int computer = curr[1];
			int fileID = curr[2];
			int distance = curr[3];
			
			if(computers[computer].shareFiles.containsKey(fileID)) {
				visitedComputers.get(start).add(computers[computer].shareFiles.get(fileID));
			}
			for(Node next: links[computer]) {
				if(distance+next.distance > MAX_DISTANCE) continue;
				queue.add(new int[] {start, next.computer, fileID, distance+next.distance});
			}
		}
		
		Set<Integer> requestedComputerIDs = visitedComputers.keySet();
		for(int rID: requestedComputerIDs) {
			List<File> files = visitedComputers.get(rID);
			int downloadComputer = files.size();
			for(File f: files) {
				f.setStartTime(mTime);
				f.setDownloadComputer(downloadComputer);
				computers[rID].requestFiles.replace(f.id,f);
			}
			requestFilesByComputerID.put(rID, );
		}
		
	}

	void addLink(int mTime, int mComA, int mComB, int mDis)
	{
		
	}

	void addShareFile(int mTime, int mComA, int mFileID, int mSize)
	{
		
	}

	int downloadFile(int mTime, int mComA, int mFileID)
	{
		// dfs search
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {mComA, 0});
		
		ArrayList<Integer> downloadComputers = new ArrayList<>();
		int fileSize = 0;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int computer = curr[0];
			int distance = curr[1];
			if(computers[computer].shareFiles.containsKey(mFileID)) {
				downloadComputers.add(computer);
				fileSize = computers[computer].shareFiles.get(mFileID).size;
			}
			for(Node next: links[computer]) {
				if(distance+next.distance > MAX_DISTANCE) continue;
				queue.add(new int[] {next.computer, distance+next.distance});
			}
		}
		
		int dComCnt = downloadComputers.size();

		for(int dCom: downloadComputers) {
			computers[dCom].updateShareFiles(mFileID, mTime, dComCnt);
		}
		

		computers[mComA].requestFiles.put(mFileID, new File(mFileID, fileSize, mTime, dComCnt));
		requestFilesByComputerID.put(mComA, new File(mFileID, fileSize, mTime, dComCnt));
		System.out.println("comcnt"+ " " + dComCnt);
		return dComCnt;
	}

	int getFileSize(int mTime, int mComA, int mFileID)
	{
		int fileSize = computers[mComA].getSize(mTime, mFileID);
		System.out.println(fileSize);
		return fileSize;
	}
  }ㄴ

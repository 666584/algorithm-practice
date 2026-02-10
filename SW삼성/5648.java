import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_5648_원자_소멸_시뮬레이션_조유림 {
	static int T, N; // 원자 개수
	static List<Atom> atoms; // 원자들 위치, 이동 방향 및 방출 에너지
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int total;
	static TreeSet<Collision> collisions;
	static Set<Atom> collidedAtom;
	
	public static class Atom {
		int x, y, direction, energy;

		Atom(int x, int y, int direction, int energy) {
	        this.x = x;
	        this.y = y;
	        this.direction = direction;
	        this.energy = energy;
	    }
		
		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Collision)) return false;
	        Atom c = (Atom) o;
	        return c.x == this.x && c.y == this.y;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(x, y);
	    }
	    
	    @Override
	    public String toString() {
	    	return this.x +" "+this.y+" "+this.energy;
	    }
	}
	
	public static class Collision {
	    int x, y, time;

	    Collision(int x, int y, int time) {
	        this.x = x;
	        this.y = y;
	        this.time = time;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Collision)) return false;
	        Collision c = (Collision) o;
	        return x == c.x && y == c.y && time == c.time;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(x, y, time);
	    }
	    

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			atoms = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = 2*Integer.parseInt(st.nextToken()); //x
				int y = 2*Integer.parseInt(st.nextToken()); //y
				int d = Integer.parseInt(st.nextToken()); //이동방향
				int e = Integer.parseInt(st.nextToken()); //보유에너지
				atoms.add(new Atom(x,y,d,e));
			}
			
			total = 0;
			collisions = new TreeSet<>(
				    Comparator.comparingInt((Collision c) -> c.time)
		              .thenComparingInt(c -> c.x)
		              .thenComparingInt(c -> c.y)
			);
			
			
			getCollision();
			
			getTotalEnergy();
			System.out.println("#"+t+" "+total);
			
		}
	}
	
	public static void addValueToCollision(int cx, int cy, int time) {
		collisions.add(new Collision(cx, cy, time));
	}
	
	// 충돌 가능한 모든 경우 저장
	public static void getCollision() {
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				Atom a = atoms.get(i);
				Atom b = atoms.get(j);
				int ax = a.x;
				int ay = a.y;
				int bx = b.x;
				int by = b.y;
				int ad = a.direction;
				int bd = b.direction;
				//y좌표 동일 서로 같은 방향으로 갈 경우(좌우)
				if(ay == by) {
					int time = Math.abs(ax - bx) / 2;
					if(ax <= bx) {
						if(ad == 3 && bd == 2) {
							addValueToCollision(atoms.get(i).x + time, atoms.get(i).y, time);
						}
					}
					else {
						if(atoms.get(i).direction == 2 && atoms.get(j).direction == 3) {
							addValueToCollision(atoms.get(j).x + time, atoms.get(i).y, time);
						}
					}
				}
				//x좌표 동일 서로 같은 방향으로 갈 경우 (상하)
				if(atoms.get(i).x == atoms.get(j).x) {
					int time = Math.abs(atoms.get(j).y - atoms.get(i).y) / 2;
					if(atoms.get(i).y <= atoms.get(j).y) {
						if(atoms.get(i).direction == 0 && atoms.get(j).direction == 1) {
							addValueToCollision(atoms.get(i).x, atoms.get(i).y + time, time);	
						}
					}
					else {
						if(atoms.get(i).direction == 1 && atoms.get(j).direction == 0) {
							addValueToCollision(atoms.get(i).x, atoms.get(j).y + time, time);
						}
					}
				}
				
				// 직각 충돌
				// 기울기가 1일 경우: 
				int diffx = atoms.get(i).x - atoms.get(j).x;
				int diffy = atoms.get(i).y - atoms.get(j).y;
				if(diffx != 0 && diffy != 0) {
					int gradient = Math.abs(diffy / diffx);
					if(gradient == 1) {
						if (diffx == -dx[atoms.get(i).direction] * Math.abs(diffx) &&
								diffy == -dy[atoms.get(j).direction] * Math.abs(diffy)) {
						    int time = Math.abs(diffx) / 2;
						    addValueToCollision(atoms.get(j).x, atoms.get(i).y, time);
						}
					}
					
				}
			}
		}
	}

	
	// 충돌 상황 프린트
	public static void printCollusions() {
		for (Collision c : collisions) {
			System.out.println(c.x + " " + c.y + " " + c.time);
		}
	}
	
	public static void getTotalEnergy() {
		collidedAtom = new HashSet<>();
		for (Collision c : collisions) {
		    HashSet<Atom> newAtoms = new HashSet<>();
		    for (Atom a : getCollisionAtTime(c)) {
		        if (!collidedAtom.contains(a)) newAtoms.add(a);
		    }
		    
		    if (newAtoms.size() >= 2) {
		        collidedAtom.addAll(newAtoms);
		        total += newAtoms.stream().mapToInt(a -> a.energy).sum();
		    }
		}
	}
	
	public static List<Atom> getCollisionAtTime(Collision c) {
		List<Atom> currCollisions = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int direction = atoms.get(i).direction;
			int nx = atoms.get(i).x + dx[direction]*c.time;
			int ny = atoms.get(i).y + dy[direction]*c.time;
			if(nx == c.x && ny == c.y) currCollisions.add(atoms.get(i));
		}
		return currCollisions;

	}
	
}





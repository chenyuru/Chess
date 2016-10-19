package test.example.com;

import java.util.ArrayList;
import java.util.Stack;

public class Pannel {
	final static public int EMPTY =0;
	final static public int RED = 1;
	final static public int GREEN =2;
	final static int ROW =6;
	final static int COL =7;
//	private Button[][] buttons =new Button[ROW][COL];
	private ArrayList<PannelState> statesStack = new ArrayList<>();
	
	int getCurrentState(int i,int j){
		int size = statesStack.size();
		return statesStack.get(size-1).states[i][j];
	}
	boolean isLegalToPut(int i,int j,int color){
		int state = getCurrentState(i, j);
		if(state == EMPTY){
			return true;
		}
		return false;
	}
	
	//add state
	boolean put(int i,int j,int color){
		int size = statesStack.size();
		PannelState newPan =new PannelState(statesStack.get(size-1));
		if(isLegalToPut(size, j, color)){
			newPan.states[i][j] = color;
			statesStack.add(newPan);
			return true;
		}else{
			return false;
		}
	}
	
	//may return null
	ArrayList<PannelLocate> isHorizonOver(int i,int j){
		ArrayList<PannelLocate> pls =new ArrayList<>();
		int current =getCurrentState(i, j);
		for(int s=j+1;s<COL;s++){
			if(getCurrentState(i, s)==current)
				pls.add(new PannelLocate(i, s));
			else{
				break;
			}
		}
		for(int s=j;s>=0;s--){
			if(getCurrentState(i, s)==current)
				pls.add(new PannelLocate(i, s));
			else{
				break;
			}
		}
		if(pls.size()<4)
			return null;
		return pls;
	}
	
	ArrayList<PannelLocate> isVerticalOver(int i,int j){
		ArrayList<PannelLocate> pls =new ArrayList<>();
		int current =getCurrentState(i, j);
		for(int s=i+1;s<ROW;s++){
			if(getCurrentState(s, j)==current)
				pls.add(new PannelLocate(s, j));
			else{
				break;
			}
		}
		for(int s=i;s>=0;s--){
			if(getCurrentState(s, j)==current)
				pls.add(new PannelLocate(s, j));
			else{
				break;
			}
		}
		if(pls.size()<4)
			return null;
		return pls;
	}
	
	ArrayList<PannelLocate> isPosiOver(int i,int j){
		ArrayList<PannelLocate> pls =new ArrayList<>();
		int current =getCurrentState(i, j);
		int s=i;
		int t=j;
		for(s=i,t=j;s>=0 && j<COL ; s--,t++){
			if(getCurrentState(s, t)==current)
				pls.add(new PannelLocate(s, t));
			else{
				break;
			}
		}
		for(s=i+1,t=j-1;s<ROW && t>=0 ; s++,t--){
			if(getCurrentState(s, t)==current)
				pls.add(new PannelLocate(s, t));
			else{
				break;
			}
		}
		if(pls.size()<4)
			return null;
		return pls;
	}
	
	ArrayList<PannelLocate> isNegaOver(int i,int j){
		ArrayList<PannelLocate> pls =new ArrayList<>();
		int current =getCurrentState(i, j);
		int s=i;
		int t=j;
		for(s=i,t=j;s>=0 && j>=0 ; s--,t--){
			if(getCurrentState(s, t)==current)
				pls.add(new PannelLocate(s, t));
			else{
				break;
			}
		}
		for(s=i+1,t=j+1;s<ROW && t<COL ; s++,t++){
			if(getCurrentState(s, t)==current)
				pls.add(new PannelLocate(s, t));
			else{
				break;
			}
		}
		if(pls.size()<4)
			return null;
		return pls;
	}
	
	//return null if empty return newest state
	PannelState regret(){
		int size =statesStack.size();
		if(size == 0)
			return null;
		statesStack.remove(size-1);
		if(statesStack.isEmpty())
			return null;
		return statesStack.get(size-2);
	}
	
//	public static PannelLocate getLocateByIndex(int index){
//		for(int i=0;i<ROW;i++)
//			for(int j=0;j<COL;j++){
//				{
//					if(i*COL+j==index)
//						
//				}
//	}
	class PannelState{
		public int[][] states = new int[ROW][COL];
		public void setState(int i,int j,int state){
			states[i][j] = state;
		}
		
		public PannelState(){
			for(int i=0;i<ROW;i++)
				for(int j=0;j<COL;j++){
					states[i][j] = EMPTY;
				}
		}
		
		public PannelState(PannelState p){
			for(int i=0;i<ROW;i++)
				for(int j=0;j<COL;j++){
					states[i][j] =p.states[i][j];
				}
		}
	}
	
	class PannelLocate{
		public int i;
		public int j;
		public PannelLocate(int i,int j){
			this.i=i;
			this.j=j;
		}
	}
	
}

package spg.test;
import spg.runtime.*;
import java.util.List;
import java.util.ArrayList;
public class ConfigurationImpl implements Configuration{
private int[]l=new int[]{1,0,4};
private Action[][]a=new Action[][]{new Action[]{Action.ERROR,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ACCEPT},new Action[]{Action.ERROR,Action.SHIFT,Action.SHIFT,Action.REDUCE,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR},new Action[]{Action.ERROR,Action.SHIFT,Action.SHIFT,Action.REDUCE,Action.ERROR},new Action[]{Action.ERROR,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE},new Action[]{Action.ERROR,Action.SHIFT,Action.SHIFT,Action.REDUCE,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.ERROR}};
private int[][]n=new int[][]{new int[]{-1,1,2,-1,1},new int[]{-1,-1,-1,-1,-1},new int[]{-1,3,4,1,-1},new int[]{-1,-1,-1,5,-1},new int[]{-1,6,4,1,-1},new int[]{-1,7,2,-1,1},new int[]{-1,-1,-1,8,-1},new int[]{-1,-1,-1,-1,2},new int[]{-1,9,4,1,-1},new int[]{-1,-1,-1,2,-1}};
public String[]v=new String[]{"0","1"};
public boolean[]g=new boolean[]{false,false};
public int getLength(int n){
return l[n];
}
public RuleManager getManager(){
return new RuleManagerImpl();
}
public Table getTable(){
List<List<Action>>ac=new ArrayList<>();List<List<Integer>>nx=new ArrayList<>();
for(int i=0;i<a.length;i++){
ac.add(new ArrayList<>());
nx.add(new ArrayList<>());
for(int j=0;j<a[i].length;j++){
ac.get(ac.size()-1).add(a[i][j]);
nx.get(nx.size()-1).add(n[i][j]);
}
}
return new Table(ac,nx);
}
public int getTerminalNumber(){
return 2;
}
public int getNonTerminalNumber(){
return 2;
}
public List<String> getValues(){
List<String>va=new ArrayList<>();
for(int i=0;i<v.length;i++){
va.add(v[i]);
}
return va;
}
public List<Boolean>getIgnore(){
List<Boolean>ig=new ArrayList<>();
for(int i=0;i<g.length;i++){
ig.add(g[i]);
}
return ig;
}
}

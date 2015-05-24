/*Auto-generated code.*/
package spg.test;
import spg.runtime.*;
import java.util.List;
import java.util.ArrayList;
public class Config implements Configuration{
private int[]l=new int[]{1,3,1,3,1,3,1,1,3};
private Action[][]a=new Action[][]{new Action[]{Action.ERROR,Action.SHIFT,Action.SHIFT,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ACCEPT},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.SHIFT,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.SHIFT,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.SHIFT,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.SHIFT,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.REDUCE,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.SHIFT,Action.ERROR,Action.REDUCE,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE,Action.ERROR,Action.REDUCE,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.SHIFT,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE},new Action[]{Action.ERROR,Action.ERROR,Action.SHIFT,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.SHIFT,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.SHIFT,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.ERROR,Action.ERROR,Action.REDUCE,Action.ERROR,Action.ERROR},new Action[]{Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.ERROR,Action.REDUCE,Action.REDUCE,Action.ERROR,Action.REDUCE,Action.ERROR,Action.ERROR}};
private int[][]n=new int[][]{new int[]{-1,1,2,3,4,-1,5,-1,-1,6,-1,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,7,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2,2},new int[]{-1,-1,-1,-1,-1,-1,-1,8,-1,-1,-1,4,4},new int[]{-1,-1,-1,-1,-1,-1,-1,6,9,-1,-1,6,6},new int[]{-1,-1,-1,-1,-1,-1,-1,7,7,-1,-1,7,7},new int[]{-1,-1,10,11,12,-1,13,-1,-1,14,-1,-1,-1},new int[]{-1,-1,15,3,4,-1,5,-1,-1,6,-1,-1,-1},new int[]{-1,-1,16,3,4,-1,5,-1,-1,6,-1,-1,-1},new int[]{-1,-1,-1,17,4,-1,5,-1,-1,6,-1,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,19,-1,-1,4,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,6,20,-1,6,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,7,7,-1,7,-1,-1},new int[]{-1,-1,21,11,12,-1,13,-1,-1,14,-1,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1,1},new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3,3},new int[]{-1,-1,-1,-1,-1,-1,-1,5,-1,-1,-1,5,5},new int[]{-1,-1,-1,-1,-1,-1,-1,8,8,-1,-1,8,8},new int[]{-1,-1,22,11,12,-1,13,-1,-1,14,-1,-1,-1},new int[]{-1,-1,-1,23,12,-1,13,-1,-1,14,-1,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,24,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,5,-1,-1,5,-1,-1},new int[]{-1,-1,-1,-1,-1,-1,-1,8,8,-1,8,-1,-1}};
public String[]v=new String[]{ "\\s", "-?\\d+", "\\+", "\\*", "\\(", "\\)", ";"};
public boolean[]g=new boolean[]{true,false,false,false,false,false,false};
public int getLength(int n){
return l[n];
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
return 7;
}
public int getNonTerminalNumber(){
return 5;
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
private L rule1(L $0,Terminal $1,E $2) {
L res=new L();
    res.list = $0.list;    res.list.add($2.value);return res;
}
private L rule2(E $0) {
L res=new L();
    res.list = new ArrayList<>();    res.list.add($0.value);return res;
}
private E rule3(M $0,Terminal $1,E $2) {
E res=new E();
    res.value = $0.value + $2.value;return res;
}
private E rule4(M $0) {
E res=new E();
    res.value = $0.value;return res;
}
private M rule5(U $0,Terminal $1,M $2) {
M res=new M();
    res.value = $0.value * $2.value;return res;
}
private M rule6(U $0) {
M res=new M();
    res.value = $0.value;return res;
}
private U rule7(Terminal $0) {
U res=new U();
    res.value = Integer.parseInt($0.value);return res;
}
private U rule8(Terminal $0,E $1,Terminal $2) {
U res=new U();
    res.value = $1.value;return res;
}
public Unit reduce(int n,List<Unit>r){
switch(n){
case 1:
return rule1((L)r.get(0),(Terminal)r.get(1),(E)r.get(2));
case 2:
return rule2((E)r.get(0));
case 3:
return rule3((M)r.get(0),(Terminal)r.get(1),(E)r.get(2));
case 4:
return rule4((M)r.get(0));
case 5:
return rule5((U)r.get(0),(Terminal)r.get(1),(M)r.get(2));
case 6:
return rule6((U)r.get(0));
case 7:
return rule7((Terminal)r.get(0));
case 8:
return rule8((Terminal)r.get(0),(E)r.get(1),(Terminal)r.get(2));
default:
throw new AssertionError();
}
}
}

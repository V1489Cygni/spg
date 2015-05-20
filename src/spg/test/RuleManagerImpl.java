package spg.test;

import spg.runtime.*;
import java.util.List;
import java.util.ArrayList;
public class RuleManagerImpl implements RuleManager{
private NT0 rule0(NT1 $0) {
NT0 res=new NT0();
res.n=$0.n;
return res;
}
private NT1 rule1() {
NT1 res=new NT1();
return res;
}
private NT1 rule2(Terminal $0,NT1 $1,Terminal $2,NT1 $3) {
NT1 res=new NT1();
res.n=Math.max($1.n+1,$3.n);return res;
}
public Unit reduce(int n,List<Unit>r){
switch(n){
case 0:
return rule0((NT1)r.get(0));
case 1:
return rule1();
case 2:
return rule2((Terminal)r.get(0),(NT1)r.get(1),(Terminal)r.get(2),(NT1)r.get(3));
default:
throw new AssertionError();
}
}
}

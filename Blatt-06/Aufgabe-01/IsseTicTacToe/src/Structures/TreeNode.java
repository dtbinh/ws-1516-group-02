package Structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TreeNode {
    private TreeNode parent;
    private Vector<TreeNode> children;
    private Vector<Integer> crosses, noughts;
    private Integer value;
    

    private TreeNode(Vector<Integer> crosses, Vector<Integer> noughts) {
        this.crosses = crosses;
        this.noughts = noughts;
        this.children = new Vector<TreeNode>();
    }

    public static TreeNode createTree(){
    	TreeNode t = new TreeNode(new Vector<Integer>(), new Vector<Integer>());
    	t.parent = null;
    	return t;
    }
    
    
    public TreeNode addChild(Vector<Integer> c, Vector<Integer> n){
    	TreeNode child = new TreeNode(c,n);
    	this.children.add(child);
    	child.parent=this;
    	return child;
    }
    
    public Vector<TreeNode> getChildren(){
    	return children;
    }

    public Vector<Integer> getCrosses(){
    	return this.crosses;
    }
    
    public Vector<Integer> getNoughts(){
    	return this.noughts;
    }
    
    public void setValue(int v){
    	value = v;
    }
    
    public Integer getValue(){
    	return value;
    }
    
}
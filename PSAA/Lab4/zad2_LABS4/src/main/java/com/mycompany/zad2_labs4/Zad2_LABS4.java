package com.mycompany.zad2_labs4;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;



class BNode<E> {
    public E info;
    public BNode<E> left, right;
    static int LEFT = 1, RIGHT = 2;
    
    public BNode(E info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }
    
    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BTree<E> {
    public BNode<E> root;
    
    public BTree() {
        root = null;
    }
    
    public BTree(E info) {
        root = new BNode<E>(info);
    }
    
    public BNode<E> addChild(BNode<E> node, int where, E info) {
        BNode<E> tmp = new BNode<E>(info);
        
        if (where == BNode.LEFT) {
            if (node.left != null) {
                return null;
            }
            node.left = tmp;
        } else {
            if (node.right != null) {
                return null;
            }
            node.right = tmp;
        }
        
        return tmp;
    }
    
    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.info + " ");
            inorder(r.right);
        }
    }
    
    public void preorder(BNode<E> r) {
        if (r != null) {
            System.out.println(r.info);
            preorder(r.left);
            preorder(r.right);
        }
    }
    
    public void postorder(BNode<E> r) {
        if (r != null) {
            postorder(r.left);
            postorder(r.right);
            System.out.println(r.info);
        }
    }
    
    public void inorderUsingStack(BNode<E> r) {
        Stack<BNode<E>> s = new Stack();
        
        BNode<E> p = root;
        
        while (true) {
            while (p != null) {
                s.push(p);
                p = p.left;
            }
            
            if (s.isEmpty()) {
                break;
            }
            
            p = s.pop();
            
            System.out.println(p.info.toString());
            
            p = p.right;
        }
    }
    
    int numLeaves(BNode<E> r) {
        if (r == null) {
            return 0;
        }
        if (r.left == null && r.right == null) {
            return 1;
        } else {
            return numLeaves(r.left) + numLeaves(r.right);
        }
    }
    
    int numInNodes(BNode<E> r) {
        if (r == null) {
            return 0;
        }
        if (r.left == null && r.right == null) {
            return 0;
        } else {
            return 1 + numInNodes(r.left) + numInNodes(r.right);
        }
    }
}



public class Zad2_LABS4 {

    public static void reconstruct(BNode rt,int val,boolean desno){
        
        if(rt==null) return;
        
        if(val==-1) rt.info=0;
        else{ 
            
            if (desno) {
                rt.info=val*2 +2;
            } else {
                rt.info=val*2 +1;
                
            }

        }
        reconstruct(rt.right,(int)rt.info,true);
        reconstruct(rt.left,(int)rt.info,false);
    }
    
    public static boolean findElement(BNode rt,int el){ //BFS
       Queue<BNode> red = new LinkedList<>();
       red.add(rt);
       
       while(!red.isEmpty()){
           BNode curr= red.poll();
           
           if(curr.info.equals(el)){
               return true;
           }
           
           if(curr.left !=null) red.add(curr.left);
           if(curr.right !=null) red.add(curr.right);
       }
       return false;
    }
    
    
    public static void main(String[] args) {
        BTree<Integer> tree= new BTree<>(-1);
        tree.addChild(tree.root, 0,-1);
        tree.addChild(tree.root.right, 0,-1);
        tree.addChild(tree.root.right, 1,-1);
        tree.addChild(tree.root.right.left, 0,-1);
        tree.addChild(tree.root.right.left, 1,-1);
        tree.addChild(tree.root.right.left.left, 0,-1);
        tree.addChild(tree.root.right.left.left, 1,-1);
        
        
        reconstruct(tree.root,-1,false);
        
        BNode<Integer> rt = tree.root;
        
        tree.inorder(rt);
//        
//        System.out.println("\n" + rt.info);
//        
        System.out.println(findElement(rt,5) ? "najdeme go ja go ja": "zhal mi e sledeci put");

    }
}

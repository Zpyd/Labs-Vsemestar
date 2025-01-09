
package com.mycompany.zad3_labs4;

import java.util.*;

class BNode <E extends Comparable <E>> {
    public E info;
    public BNode <E> left;
    public BNode <E> right;
    
	public BNode(E info) {
		this.info = info;
		left = null;
		right = null;
	}
	
	public BNode(E info, BNode<E> left, BNode<E> right) {
		this.info = info;
		this.left = left;
		this.right = right;
	}  
}

class BSTree <E extends Comparable<E>> {
    public BNode<E> root;   
	
    public BSTree() {       
        root = null;      
    }
	
    public BSTree(E info) {
        root = new BNode(info);
    }

	public void inorder() {
		System.out.print("INORDER: ");
		inorderR(root);
		System.out.println();
	}
	
	public void inorderR(BNode<E> n) {
		if (n != null) {
			inorderR(n.left);
			System.out.print(n.info.toString()+ " ");
			inorderR(n.right);  
		}
	}
	
	public void preorder() {
		System.out.print("PREORDER: ");
		preorderR(root);
		System.out.println();
	}
	
	public void preorderR(BNode<E> n) {
		if (n != null) {
			System.out.print(n.info.toString()+ " ");
			preorderR(n.left);
			preorderR(n.right);
		}     
	}
	
	public void postorder() {
		System.out.print("POSTORDER: ");
		postorderR(root);
		System.out.println();
	}

	public void postorderR(BNode<E> n) {
		if (n != null) {
			postorderR(n.left);
			postorderR(n.right);
			System.out.print(n.info.toString()+ " ");
		}
	}

	public boolean findR(E x, BNode <E> r) {
		if(r==null) return false;
		int comp=x.compareTo(r.info);
		if(comp<0) 
			return  findR(x, r.left);
		else if(comp >0) 
			return  findR(x, r.right);
		else 
			return true;
	}

	public boolean isEmpty() {
		return root==null;
	}

	public BNode <E> insert(E x, BNode<E> r) {
		if (r == null) return new BNode(x);
		int comp=x.compareTo(r.info);
		if (comp < 0) {
			r.left = insert(x, r.left);
		} 
		else if (comp > 0) {
			r.right = insert(x, r.right);
		}
		else; 
		return r;
	}
public BNode <E> findMin(BNode<E> r)
{
if(r==null) return null; 
else if(r.left==null) return r; 
else return findMin(r.left);
}
public BNode <E> findMax(BNode <E> r)
{
if(r!=null)
while(r.right!=null) r=r.right;
return r; 
}

	public BNode <E> remove (E x, BNode<E> r) {
		if(r==null) return r;
		int comp=x.compareTo(r.info);
		if(comp<0)    
			r.left=remove(x,r.left);
		else if(comp>0)
			r.right=remove(x, r.right);
		else {
			if(r.left!=null && r.right!=null) {
				r.info=findMin(r.right).info;
				r.right=remove(r.info,r.right); 
			}
			else 
				if(r.left!=null)
					return r.left;
				else return r.right;            
		}    
		return r;    
	}

	int numLeaves(BNode <E> r) {
		if(r==null) return 0; 
		if(r.left==null && r.right==null)
			return 1+numLeaves(r.left)+numLeaves(r.right);
		else
			return numLeaves(r.left)+numLeaves(r.right);     
	}

	int numNodes(BNode <E> r) {
		if(r==null) return 0; 
		if(r.left==null && r.right==null)
			return 0;
		else
			return 1+numNodes(r.left)+numNodes(r.right);     
	}
        
        public BNode<E> findRight (BNode<E> root, E value, int level){
            if(level==0){
                System.out.println("roots dont have brothers dummy");
                return null;
            }
            
            int nivo=0; BNode<E> bro=null;
            Queue<BNode<E>> segasen= new LinkedList<>();
            Queue<BNode<E>> sleden= new LinkedList<>();
            segasen.add(root);
            
            while(!segasen.isEmpty()){
             
//                System.out.println(nivo);
                
                if(level==nivo){ 
                    while(!segasen.isEmpty()){
//                        System.out.println("ulavam tuka");
                        BNode<E> elem=segasen.poll();
                        System.out.println(elem.info);
                        if(value.compareTo(elem.info)==0){
                            bro=segasen.poll();//i da ni dae null and nie pa da go vrneme it dont matter poso istoto ke e
                            break;
                        }
                    }
                    return bro;
                }
                
                BNode<E> curr=segasen.poll();
                
                if(curr.left !=null) sleden.add(curr.left);
                if(curr.right !=null) sleden.add(curr.right);
                
                if(segasen.size()==0){
                    nivo++;
                    
                    Queue<BNode<E>> pom= segasen;
                    segasen=sleden;
                    sleden=pom;
                }
                
                
                if(segasen.size()==1){
                    nivo++;
                    curr=segasen.poll();
                    
                    if(curr.left !=null) sleden.add(curr.left);
                    if(curr.right !=null) sleden.add(curr.right);
                    Queue<BNode<E>> pom= segasen;
                    segasen=sleden;
                    sleden=pom;
                }
            }
            
            return null;
        }
}



public class Zad3_LABS4 {

    public static void main(String[] args) {
        BSTree<Integer> drvo= new BSTree<>(10);
        drvo.insert(6, drvo.root);
        drvo.insert(5, drvo.root);
        drvo.insert(3, drvo.root);
        drvo.insert(7, drvo.root);
        drvo.insert(9, drvo.root);
        drvo.insert(12, drvo.root);
        drvo.insert(11, drvo.root);
        
        drvo.inorder();
    
        System.out.println(drvo.findRight(drvo.root, 5, 2).info);
    }
}


package com.mycompany.zad1_labs4;

import java.util.ArrayList;


class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left, right;
    
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

class BSTree<E extends Comparable<E>> {
    public BNode<E> root;
    
    public BSTree() {
        root = null;
    }
    
    public BSTree(E info) {
        root = new BNode(info);
    }
    
    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.info + ", ");
            inorder(r.right);
        }
    }
    
    public BNode<E> insert(E info, BNode<E> r) {
        if (r == null) {
            return new BNode(info);
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) { //left
            r.left = insert(info, r.left);
        } else if (comp > 0) { //right
            r.right = insert(info, r.right);
        } else {
            // ne pravi nishto
        }
        
        return r;
    }
    
    public boolean contains(E info, BNode<E> r) {
        if (r == null) {
            return false;
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            return contains(info, r.left);
        } else if (comp > 0) {
            return contains(info, r.right);
        } else {
            return true;
        }
    }
    
    public BNode<E> remove(E info, BNode<E> r) {
        if (r == null) {
            return r;
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            r.left = remove(info, r.left);
        } else if (comp > 0) {
            r.right = remove(info, r.right);
        } else { // brishi go jazolot info
            if (r.left != null && r.right != null) {
                r.info = findMin(r.right).info;
                r.right = remove(r.info, r.right);
            } else {
                if (r.left != null) {
                    return r.left;
                } else if (r.right != null) {
                    return r.right;
                } else {
                    return null;
                }
            }
        }
        
        return r;
    }

    private BNode<E> findMin(BNode<E> r) {
        if (r == null) {
            return null;
        } else if (r.left == null) {
            return r;
        } else {
            return findMin(r.left);
        }
    }
    
    public boolean checkTreesEqual(BNode<E> r1, BNode<E> r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        
        if (r1 != null && r2 != null) {
            return r1.info.compareTo(r2.info) == 0 && 
                    checkTreesEqual(r1.left, r2.left) &&
                    checkTreesEqual(r1.right, r2.right);
        }
        
        return false;
    }
    
    public boolean checkIfLeavesEqual(BNode<E> r1, BNode<E> r2) {
        if (r1.left == null && r2.left == null && r1.right == null && r2.right == null) {
            return r1.info.compareTo(r2.info) == 0;
        } else if (r1.left != null && r2.left != null && r1.right != null && r2.right != null) {
            return checkIfLeavesEqual(r1.left, r2.left) && checkIfLeavesEqual(r1.right, r2.right);
        } else if (r1.left == null && r2.left == null && r1.right != null && r2.right != null) {
            return  checkIfLeavesEqual(r1.right, r2.right);
        } else if (r1.left != null && r2.left != null && r1.right == null && r2.right == null) {
            return checkIfLeavesEqual(r1.left, r2.left);
        } else {
            return false;
        }
    }
    
    public void getLeaves(BNode<E> r, ArrayList<E> list) {
        if (r.left == null && r.right == null) {
            list.add(r.info);
        }
        if (r.left != null)
            getLeaves(r.left, list);
        if (r.right != null)
            getLeaves(r.right, list);
    }
    
    public boolean compareLists(BSTree<E> tree1, BSTree<E> tree2) {
        ArrayList<E> list1 = new ArrayList();
        ArrayList<E> list2 = new ArrayList();
        tree1.getLeaves(tree1.root, list1);
        tree2.getLeaves(tree2.root, list2);
        int i;
        
        if (list1.size() != list2.size()) {
            return false;
        } else {
            for (i = 0; i < list1.size(); i++) {
                if (list1.get(i) != list2.get(i)) {
                    return false;
                }
            }
            
            return true;
        }
    }
    
    static int count = 0;
    
    private BNode<E> findNMin(BNode<E> r, int N) {
        if (r == null) {
            return null;
        }
        
        BNode<E> left = findNMin(r.left, N);
        
        if (left != null) {
            return left;
        }
        
        count++;
        if (count == N) {
            return r;
        }
        
        return findNMin(r.right, N);
    }
    
    
    
}

public class Zad1_LABS4 {
    
    
    public static boolean isLeaf(BNode jazel){
        return jazel.left==null && jazel.right==null;
    }
    
    public static int checkParentChildWithoutRightChild(BNode rt){
        if(isLeaf(rt)){
            return 0;
        }
        
        if(rt.right==null && rt.left!=null && !isLeaf(rt.left) && rt.left.right==null){
            return 1;
        }
        
        if (checkParentChildWithoutRightChild(rt.left)==1) return 1; 
        if (checkParentChildWithoutRightChild(rt.right)==1)return 1;
        
        return 0;
    }
    
    public static void main(String[] args) {
        
        
        BSTree tree1= new BSTree(2);
        BNode rt= tree1.root;
        tree1.insert(1, rt);
        tree1.insert(0, rt);
        tree1.insert(4, rt);
        tree1.insert(3, rt);
        tree1.insert(5, rt);
        tree1.inorder(rt);
        
        
        
        System.out.println(checkParentChildWithoutRightChild(rt) == 1 ? "Ima takov slucaj" : "Nema takov slucaj");

    }
}
    
 
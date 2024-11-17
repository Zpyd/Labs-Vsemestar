

package com.mycompany.zad1_labs2;

class Node<E> {
    protected E data;
    protected Node<E> next;
    
    public Node() {
        data = null;
        next = null;
    }
    
    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}

class SLinkedList<E> {
    private Node<E> head;
    
    public Node<E> getHead() {
        return head;
    }
    
    public SLinkedList() {
        head = null;
    }
    
    public void insertFirst(E e) {
        Node<E> first = new Node(e, head);
        head = first;
    }
    
    public void insertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node(e, n.next);
            n.next = node;
        } else {
            System.out.println("Error.");
        }
    }
    
    public void insertBefore(E e, Node<E> n) {
        if (head != null) {
            Node<E> tmp = head;
            if (tmp == n) {
                this.insertFirst(e);
                return;
            }
            
            while(tmp.next != n && tmp.next != null) {
                tmp = tmp.next;
            }
            
            if (tmp.next == n) {
                Node<E> node = new Node(e, n);
                tmp.next = node;
            }
        }
    }
    
    public void insertLast(E e) {
        if (head != null) {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            
            Node<E> last = new Node(e, null);
            tmp.next = last;
        } else {
            this.insertFirst(e);
        }
    }
    
    public void deleteFirst() {
        if (head != null) {
            head = head.next;
        } else {
            System.out.println("Error.");
        }
    }
    
    public void printList() {
        Node<E> tmp = head;
        
        while (tmp.next != null) {
            System.out.print(tmp.data + " -> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }
    
    public void reverse() {
        if (head != null) {
            Node<E> curr = head;
            Node<E> prev = null, next = null;
            
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            
            head = prev;
        }
    }
    
    public int lenght(){
        int n=1;
        if(head==null){
            return 0;
        }
        for (Node<E> temp=head;temp.next!=null; temp=temp.next) {
            n++;
        }
        return n;
    }
}

public class Zad1_LABS2 {

    public static void main(String[] args) {
        SLinkedList<Integer> l1=new SLinkedList<>();
//        for (int i = 0; i < 10; i++) {
//            l1.insertFirst(i);
//        }
        
        System.out.println(l1.lenght()); 
        
        System.out.println("Hello World!");
    }
}



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
    
}

public class Zad1_LABS2 {

    public static void ostraniOdKrajN(Node<Integer> head,int poz){
        //is it a pretty sollution? no, Do i like it? no, Does it work? meh
        Node<Integer> temp;
        int n=1;
        if(head==null){
            System.out.println("Nema go taj element");
        }
        for (temp=head;temp.next!=null; temp=temp.next) {
            n++;
        }
        
        
        
        if((n-poz)<0){
            System.out.println("Nema tolku pozicii od krajo care");
            System.exit(1);
        }
        
        temp=head;
        for (int i = 0; i < n-poz-1; i++) {
            temp=temp.next;
        }
        temp.next=temp.next.next;
    }
    
    
    public static void main(String[] args) {
        SLinkedList<Integer> l1=new SLinkedList<>();
        for (int i = 0; i < 10; i++) {
            l1.insertLast(i);
        }
        
        ostraniOdKrajN(l1.getHead(),2);
        l1.printList();
        
        
        System.out.println("Hello World!");
    }
}

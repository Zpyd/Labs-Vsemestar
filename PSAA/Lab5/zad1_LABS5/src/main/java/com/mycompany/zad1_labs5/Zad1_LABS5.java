

package com.mycompany.zad1_labs5;
import java.util.ArrayList;
import java.util.LinkedList;

    class Vraboten{
        String ime,prezime,pozicija,iskustvo;
        int id;
        
        Vraboten(String ime,String prez, String pozicija,String iskustvo,int id){
            this.ime=ime;
            this.prezime=prez;
            this.pozicija=pozicija;
            this.iskustvo=iskustvo;
            this.id=id;
        }
        
        @Override
        public String toString(){
            return String.format("%s, %s, Pozicija: %s, Iskustvo: %s ,Id: %d", ime,prezime,pozicija,iskustvo,id);
        }
    }
    
    class Zadaca{
        Vraboten vra;
        String sodrzina,rok;
        String status;
        
        Zadaca(Vraboten v,String sodrzina,String rok,String stat){
            this.vra=v;
            this.sodrzina=sodrzina;
            this.rok=rok;
            this.status=stat;
        }
    
    @Override
        public String toString(){
            return String.format("%d id na rabotnik, Sho treba da naprae: %s, do koga: %s, izvrsenos: %s", vra.id,sodrzina,rok,status);
        }
    }
    
    
    

class Map<K extends Comparable<K>, E> {

    public K key;
    public E value;

    public Map(K key, E value) {
        this.key = key;
        this.value = value;
    }
}

class SLLNode<E> {

    public E info;
    public SLLNode<E> next;

    public SLLNode(E info, SLLNode<E> next) {
        this.info = info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E> {

    private SLLNode<Map<K, E>>[] htable;

    public SLLHT(int n) {
        htable = new SLLNode[n];
        for (int i = 0; i < n; i++) {
            htable[i] = null;
        }
    }

    private int hash(K key) {
        return (key.toString().charAt(0) - 'a') % htable.length;
    }

    public SLLNode<Map<K, E>> find(K look) {
        int h = hash(look);

        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node
                = node.next) {
            if (look.equals(node.info.key)) {
                return node;
            }
        }
        return null;
    }

    public void insert(K key, E value) {
        Map<K, E> entry = new Map(key, value);

        int h = hash(key);

        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node
                = node.next) {
            if (key.equals(node.info.key)) {
                node.info = entry;
                return;
            }
        }

        htable[h] = new SLLNode<Map<K, E>>(entry, htable[h]);
    }

    public void delete(K key) {
        int h = hash(key);

        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node
                != null; pred = node, node = node.next) {
            if (key.equals(node.info.key)) {
                if (pred == null) {
                    htable[h] = node.next;
                } else {
                    pred.next = node.next;
                }
                return;
            }
        }

    }
}

 public class Zad1_LABS5 {
    public static void main(String[] args) {
       
        Vraboten[] vraboteni= new Vraboten[3];
        vraboteni[0]=new Vraboten("jack","jackerson","CEO","20years old with 40 years work experience",1);
        vraboteni[1]=new Vraboten("jack","jackerson","Senior engener","1month bootcamp(he doesnt know sh*t (he just like me fr)",1);
        vraboteni[2]=new Vraboten("jack","jackerson","CTO","designed his verey own quantum computer at 4 yeras old",3);
        
        SLLHT<Integer,Vraboten> workerht= new SLLHT(10);
       
        for (int i = 0; i < 3; i++) {
            workerht.insert(i,vraboteni[i]);
        }

       
        
        Zadaca[] zadaci= new Zadaca[4];
        zadaci[0]=new Zadaca(vraboteni[0],"exploit workers","till theyre bled dry","nezavrsena");
        zadaci[1]=new Zadaca(vraboteni[1],"dont burn down the servers","till not fired","nezavrsena");
        
        SLLHT<Integer,LinkedList<Zadaca>> taskht=new SLLHT(10);
        
        for (Zadaca zadaca : zadaci) {
            SLLNode<Map<Integer, LinkedList<Zadaca>>> taskNode = taskht.find(zadaca.vra.id);
            if (taskNode == null) {
                LinkedList<Zadaca> newList = new LinkedList<>();
                newList.add(zadaca);
                taskht.insert(zadaca.vra.id, newList);
            } else {
                taskNode.info.value.add(zadaca);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            SLLNode<Map<Integer,Vraboten>> rabotnik= workerht.find(i);
            if(rabotnik!=null){
                System.out.println("rabotniko: "+ rabotnik);
                System.out.println("---Zadaci:---");
                SLLNode<Map<Integer,LinkedList<Zadaca>>> zadaciNode = taskht.find(i);
                if(zadaciNode==null || zadaciNode.info.value.isEmpty()){
                    System.out.println("NEMA");
                }else{
                    LinkedList<Zadaca> zadacii= zadaciNode.info.value;
                    ArrayList<Zadaca> Aktivni= new ArrayList<>();
                    for(Zadaca zad : zadacii){
                        if(zad.status.equals("nezavrsena")){
                            Aktivni.add(zad);
                        }
                    }
                    
                    if(Aktivni.isEmpty()){
                        System.out.println("Se si zavrsil bravo za nego");
                    }else{
                        for(Zadaca zad : Aktivni){
                            System.out.println(zad);
                        }
                    }
                }    
            }
                            
            else{
                System.out.println("ne postoe taj rabotnik so taj id:" + i);
            }
        }
    }
}
    
    
    



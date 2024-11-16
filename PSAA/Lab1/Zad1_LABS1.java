package com.mycompany.zad1_labs1;

import java.util.*;

public class Zad1_LABS1 {

    public static class Laboratorija {
        String ime;
        String ime_Institut;
        int brojRabotniMesta;
        int vrednost = 120000;

        Laboratorija(String ime, String inst, int brRabM, int vrednost) {
            this.ime = ime;
            this.ime_Institut = inst;
            this.brojRabotniMesta = brRabM;
            calcVrednost();
        }

        void calcVrednost() {
            this.vrednost = (int) (this.vrednost * 1.05 * this.brojRabotniMesta);
        }

        int getVrednost() {
            return vrednost;
        }

        protected void print() {
            System.out.println("ime: " + this.ime);
            System.out.println("ime na institut: " + this.ime_Institut);
            System.out.println("broj na rabotni mesta: " + this.brojRabotniMesta);
            System.out.println("vrednost: " + this.vrednost);
        }
    }

    public static class SoftverskaLab extends Laboratorija {
        int brSoftver; // max 15
        HashMap<String, Boolean> softver = new HashMap<>(); // za softver i dali e licenciran

        SoftverskaLab(String ime, String inst, int brRabM, int vrednost, int brSoftver, HashMap<String, Boolean> softver) {
            super(ime, inst, brRabM, vrednost);

            if (brSoftver > 15) {
                System.out.println("15 samo softvera mozes baki CEOto mu treba nov lambo");
                System.exit(1);
            }

            this.brSoftver = brSoftver;
            this.softver = softver;
            calcVrednost();
        }

        void calcVrednost() {
            for (Boolean key : softver.values()) {
                if (key) {
                    this.vrednost += 5000 * this.brojRabotniMesta;
                } else {
                    this.vrednost += 5 * brojRabotniMesta;
                }
            }
        }

        protected void print() {
            super.print();
            for (Map.Entry<String, Boolean> entry : softver.entrySet()) {
                System.out.print("Softver ime: " + entry.getKey() + " - ");
                if (entry.getValue()) {
                    System.out.print("Licenciran\n");
                } else {
                    System.out.print("Sloboden\n");
                }
            }
        }
    }

    public static class HardverskaLab extends Laboratorija {
        Boolean visokostrujna;
        int brHardver; // max 50
        HashMap<Integer, Integer> hardver = new HashMap<>(); // id i cena

        HardverskaLab(String ime, String inst, int brRabM, int vrednost, HashMap<Integer, Integer> hardver, Boolean visoko, int brhard) {
            super(ime, inst, brRabM, vrednost);

            if (brhard > 50) {
                System.out.println("Treba pomalce od 50 buddy");
                System.exit(1);
            }

            this.hardver = hardver;
            this.visokostrujna = visoko;
            this.brHardver = brhard;
            calcVrednost();
        }

        void calcVrednost() {
            for (Map.Entry<Integer, Integer> entry : hardver.entrySet()) {
                this.vrednost += entry.getValue();
            }

            if (visokostrujna) {
                this.vrednost += 10000;
            } else {
                this.vrednost += 2000;
            }
        }

        protected void print() {
            super.print();
            System.out.println("Broj na hardver: " + this.brHardver);
            for (Map.Entry<Integer, Integer> entry : hardver.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", Cena: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        Laboratorija lab = new Laboratorija("Lab1", "Institut1", 10, 150000);
        lab.print();

        System.out.println("\n--- Softverska Lab ---");
        HashMap<String, Boolean> softverMap = new HashMap<>();
        softverMap.put("Photoshop", true);
        softverMap.put("Word", false);
        SoftverskaLab softLab = new SoftverskaLab("SoftLab1", "Institut2", 15, 200000, 2, softverMap);
        softLab.print();

        System.out.println("\n--- Hardverska Lab ---");
        HashMap<Integer, Integer> hardverMap = new HashMap<>();
        hardverMap.put(1, 5000);
        hardverMap.put(2, 7000);
        HardverskaLab hardLab = new HardverskaLab("HardLab1", "Institut3", 12, 180000, hardverMap, true, 2);
        hardLab.print();
    }
}

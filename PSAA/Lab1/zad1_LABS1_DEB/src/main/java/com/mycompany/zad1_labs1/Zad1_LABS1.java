import java.util.*;

public class Zad1_LABS1 {

    public static void main(String[] args) {
        Laboratorija lab = new Laboratorija("Lab1", "Institut1", 10);
        lab.print();

        System.out.println("\n--- Softverska Lab ---");
        HashMap<String, Boolean> softverMap = new HashMap<>();
        softverMap.put("Photoshop", true);
        softverMap.put("Word", false);
        SoftverskaLab softLab = new SoftverskaLab("SoftLab1", "Institut2", 15, 2, softverMap);
        softLab.print();

        System.out.println("\n--- Hardverska Lab ---");
        HashMap<Integer, Integer> hardverMap = new HashMap<>();
        hardverMap.put(1, 5000);
        hardverMap.put(2, 7000);
        HardverskaLab hardLab = new HardverskaLab("HardLab1", "Institut3", 12, hardverMap, true, 2);
        hardLab.print();
    }

    public static class Laboratorija {
        protected String ime;
        protected String ime_na_institut;
        protected int broj_na_rabotni_mesta;
        protected int vrednost;

        Laboratorija(String ime, String ime_na_institut, int broj_na_rabotni_mesta) {
            this.ime = ime;
            this.ime_na_institut = ime_na_institut;
            this.broj_na_rabotni_mesta = broj_na_rabotni_mesta;

            calcVrednost();
        }

        /* private sprechuva da crashnuva programata. Ako e public, child klasata ja overwrinuva metodata i sekogash se povika child metodata kao this.softveri
            ne e inicializiran i prave runtime error deka null nema vrednost softveri.values().

            Ako e private, se sekogash povikuva ovaa metdoa. Stavih go 120000 specifichno u ovaa funkcija, deka ako e gore sekako se mene vrednosta od calcVrednost
            pa mi imashe malku povekje smisla. Toa e whatever though.

            Glavno za da ne krashe, stavahs ovaa da bide private.
         */
        private void calcVrednost() {
            vrednost = 120000;
            vrednost = (int) (vrednost * 1.05 * broj_na_rabotni_mesta);
        }

        protected void print() {
            System.out.println("Ime: " + ime);
            System.out.println("Ime na institut: " + ime_na_institut);
            System.out.println("Broj na rabotni mesta: " + broj_na_rabotni_mesta);
            System.out.println("Vrednost: " + vrednost);
        }
    }

    public static class SoftverskaLab extends Laboratorija {
        int broj_na_softveri; // max 15
        HashMap<String, Boolean> softveri; // za softver i dali e licenciran

        SoftverskaLab(String ime, String ime_na_institut, int broj_na_rabotni_mesta, int broj_na_softveri, HashMap<String, Boolean> softveri) {
            super(ime, ime_na_institut, broj_na_rabotni_mesta);

            if (broj_na_softveri > 15) {
                System.out.println("15 samo softvera mozes baki CEOto mu treba nov lambo");
                System.exit(1);
            }

            this.broj_na_softveri = broj_na_softveri;
            this.softveri = softveri;

            calcVrednost();
        }

        void calcVrednost() {
            vrednost = 120000;  // Resetiraj ja vrednosta na 120,000 (Pochetnata Vrednost).

            // Presmetaj nvoa vrednost
            for (Boolean key : softveri.values()) {
                if (key)
                    vrednost += (5000 * broj_na_rabotni_mesta);
                else
                    vrednost += (5 * broj_na_rabotni_mesta);
            }
        }

        protected void print() {
            super.print();
            for (Map.Entry<String, Boolean> entry : softveri.entrySet()) {
                System.out.print("Softver ime: " + entry.getKey() + " - ");
                if (entry.getValue())
                    System.out.println("Licenciran");
                else
                    System.out.println("Sloboden");
            }
        }
    }

    public static class HardverskaLab extends Laboratorija {
        Boolean e_visokostrujna;
        int broj_na_parchinja_harver; // max 50
        HashMap<Integer, Integer> hardveri; // id i cena

        HardverskaLab(String ime, String ime_na_institut, int broj_na_rabotni_mesta,
                      HashMap<Integer, Integer> hardveri, Boolean e_visokostrujna, int broj_na_parchinja_harver)
        {
            super(ime, ime_na_institut, broj_na_rabotni_mesta);


            if (broj_na_parchinja_harver > 50) {
                System.out.println("Treba pomalce od 50 buddy");
                System.exit(1);
            }
            this.hardveri = hardveri;

            this.e_visokostrujna = e_visokostrujna;
            this.broj_na_parchinja_harver = broj_na_parchinja_harver;
            calcVrednost();
        }

        void calcVrednost() {
            vrednost = 120000; // Resetiraj ja vrednosta na pochetnata vrednost

            // Dodaj go bonuso:
            if (e_visokostrujna)
                vrednost += 10000;
            else
                vrednost += 2000;

            // Presmetaj nova vrednost.
            for (Map.Entry<Integer, Integer> entry : hardveri.entrySet())
                this.vrednost += entry.getValue();
        }

        protected void print() {
            super.print();
            System.out.println("Broj na hardver: " + this.broj_na_parchinja_harver);
            for (Map.Entry<Integer, Integer> entry : hardveri.entrySet())
                System.out.println("ID: " + entry.getKey() + ", Cena: " + entry.getValue());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package cz.itnetwork.pojistovna;

import static cz.itnetwork.pojistovna.Konzole.*;
import static cz.itnetwork.pojistovna.Produkt.*;
import java.sql.SQLException;

/**
 * @author Marketa Vokacova
 */
public class Pojistovna {

    public static void main(String[] args) throws Exception {

        try ( Database database = new Database("jdbc:mysql://localhost/pojistovna_db?user=root&password=fgret125-56a");) {
            Pojistnik.database = database;
            Pojistnici.database = database;
            Pojisteni.database = database;
            PojisteniSeznam.database = database;
            Produkt.database = database;

            int volba = -1;
            while (volba != 4) {
                vypisMenu();
                volba = vstupInt("Prosím zadejte volbu: ");
                System.out.println();

                switch (volba) {
                    case 1 -> {
                        //přidání nového pojistníka
                        if (vstupAnoNe("Opravdu si přejete přidat nového pojistníka? (zadejte ano/ne): ")) {
                            Pojistnik novyPojistnik = vyplnPojistnika().ulozPojistnika();
                            System.out.println("\nByl přidán pojistník: \n" + novyPojistnik.toString() + "\n");
                        }
                    }

                    case 2 -> //vypsání seznamu všech pojištěných osob
                        //int preskocitRadku = vstupInt("Zadej číslo: ");
                        //int zobrazitRadku = vstupInt("Zadej číslo: ");
                        System.out.println(new Pojistnici(/*preskocitRadku, zobrazitRadku*/).toString());

                    case 3 -> {
                        //vyhledání pojistníka podle jména nebo id a možnosti editace
                        Pojistnici nalezeniPojistnici = vyhledaniPojistnika("Zadejte jméno a příjmení nebo id pojistníka: ");
                        System.out.print("\n" + nalezeniPojistnici.toString() + "\n");
                        int idPojistnik = vyhledaPojistníkaPojisteniVratiId(nalezeniPojistnici);

                        String vyber = "";
                        while (!vyber.equals("f")) {
                            vypisSubmenu();
                            vyber = vstupString("Prosím zadejte volbu: ").toLowerCase();
                            System.out.println();

                            switch (vyber) {
                                case "a" -> {
                                    //editace pojistníka
                                    Pojistnik upravovanyPojistnik = new Pojistnici(idPojistnik).getPojistnik(0);
                                    System.out.print(upravovanyPojistnik.toString());
                                    Pojistnik upravenyPojistnik = upravPojistnika(upravovanyPojistnik).ulozPojistnika();
                                    System.out.println("\nByl změněn pojistník: \n" + upravenyPojistnik.toString());
                                }

                                case "b" -> {
                                    //založení nového pojištění
                                    System.out.println(vratListProduktu(vytvorListProduktu()));
                                    if (vstupAnoNe("Opravdu si přejete založit nové pojištění (zadejte ano/ne): ")) {
                                        Pojisteni novePojisteni = vyplniPojisteni(idPojistnik).ulozPojisteni();
                                        System.out.println("\nBylo uzavřeno nové pojištění: \n" + novePojisteni.toString() + "\n");
                                    }
                                }

                                case "c" -> {
                                    //editace existujícího pojištění
                                    Pojisteni upravovanePojisteni = vyhledaPojisteni(idPojistnik);
                                    if (upravovanePojisteni != null) {
                                        System.out.print(upravovanePojisteni.toString());
                                        Pojisteni upravenePojisteni = upravPojisteni(upravovanePojisteni).ulozPojisteni();
                                        System.out.println("\nBylo změněno pojištění: \n" + upravenePojisteni.toString());
                                    }
                                }

                                case "d" -> {
                                    //smaže vybrané pojištění
                                    int pojisteniId = vstupInt("Zadejte id pojištění: ");
                                    if (vstupAnoNe("Opravdu si přejete odstranit toto pojištění: id " + pojisteniId + " ? (zadejte ano/ne): ")) {
                                        Pojisteni.smazPojisteni(pojisteniId);
                                    }
                                }

                                case "e" -> {
                                    //smaže pojistníka a všechna jeho pojištění
                                    if (vstupAnoNe("Opravdu si přejete odstranit pojistníka? (zadejte ano/ne): ")) {
                                        Pojistnik.smazPojistnika(idPojistnik);
                                    }
                                }
                            }
                        }
                    }
                }

            }

        } catch (SQLException ex) {
            System.out.println("Chyba spojení s databází." + ex.getMessage());
        }

    }
}

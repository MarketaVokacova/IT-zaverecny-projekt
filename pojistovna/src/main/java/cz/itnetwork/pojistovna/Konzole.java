/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.pojistovna;

import static cz.itnetwork.pojistovna.Pojistnici.najdiPojistnika;
import static cz.itnetwork.pojistovna.Produkt.vratTypPojisteni;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Třída reprezentuje konzolovou komunikaci s uživatelem. Pracuje s informačními
 * výpisy a vstupy od uživatele.
 *
 * @author Marketa Vokacova
 */
public class Konzole {

    private static Scanner sc = new Scanner(System.in, "Windows-1250");

    /**
     * Metoda vyzve uživatele k zadáni hodnoty typu String, načte od uživatele
     * vstup a ověří že vstup není prázdný. Opakuje se tak dlouho dokud uživatel
     * nezadá neprázdný vstup.
     *
     * @param vyzvaZadej Informace jaký vstup je od uživatele očekáván.
     * @return Vstup od uživatele.
     */
    public static String vstupString(String vyzvaZadej) {
        while (true) {
            System.out.print(vyzvaZadej);
            String vstup = sc.nextLine().trim();
            if (vstup.matches("[ \t]*")) {
                System.out.println("Vstup je prázdný, opakujte prosím zadání.");
                continue;
            }
            return vstup;
        }
    }

    /**
     * Metoda vyzve uživatele k zadáni změny hodnoty typu String a zjišťuje zda
     * je vstup prázdný nebo obsahuje text. Pokud je vstup prázdný, vrací metoda
     * hodnotou z druhého parametru.
     *
     * @param vyzvaZadej Informace jaký vstup je od uživatele očekáván.
     * @param puvodniString Hodnota která bude vrácena v případě prázdného
     * vstupu.
     * @return Vstup od uživatele nebo hodnota druhého parametru.
     */
    public static String vstupUpdateString(String vyzvaZadej, String puvodniString) {
        System.out.print(vyzvaZadej);
        String vstup = sc.nextLine().trim();
        if (vstup.equals("")) {
            return puvodniString;
        }
        return vstup;
    }

    /**
     * Metoda vyzve uživatele k zadáni hodnoty typu int, ověří že vstup není
     * prázdný a obsahuje celé číslo. Opakuje se tak dlouho dokud uživatel
     * nezadá platnou hodnotu.
     *
     * @param vyzvaZadej Informace jaký vstup je od uživatele očekáván.
     * @return Vstup od uživatele.
     */
    public static int vstupInt(String vyzvaZadej) {
        while (true) {
            try {
                System.out.print(vyzvaZadej);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Zadaná hodnota není celé číslo, zadejte prosím celé číslo.");
            }
        }
    }

    /**
     * Metoda vyzve uživatele k zadáni změny hodnoty typu int a zjišťuje zda je
     * vstup prázdný nebo obsahuje celé číslo. Pokud je vstup prázdný, je
     * vrácena hodnota z druhého parametru. V případě chybného vstupu (neprázdný
     * vstup obsahující něco jiného než celé číslo) se metoda opakuje.
     *
     * @param vyzvaZadej Informace jaký vstup je od uživatele očekáván.
     * @param puvodniInt Hodnota která bude vrácena v případě prázdného vstupu.
     * @return Vstup od uživatele nebo hodnota druhého parametru.
     */
    public static int vstupUpdateInt(String vyzvaZadej, int puvodniInt) {
        while (true) {
            try {
                System.out.print(vyzvaZadej);
                String vstup = sc.nextLine().trim();
                if (vstup.equals("")) {
                    return puvodniInt;
                }
                return Integer.parseInt(vstup);
            } catch (NumberFormatException e) {
                System.out.println("Zadaná hodnota není celé číslo, zadejte prosím celé číslo.");
            }
        }
    }

    /**
     * Metoda vyzve uživatele k zadáni data ve formátu den.měsíc.rok, ověří že
     * vstup není prázdný a je ve správném formátu. Opakuje se tak dlouho dokud
     * uživatel nezadá platnou hodnotu.
     *
     * @param vyzvaZadej Informace jaký vstup je od uživatele očekáván.
     * @return Vstup od uživatele.
     */
    public static LocalDate vstupDatum(String vyzvaZadej) {
        while (true) {
            try {
                System.out.print(vyzvaZadej);
                return LocalDate.parse(sc.nextLine().trim(), DateTimeFormatter.ofPattern("d'.'M'.'y"));
            } catch (Exception ex) {
                System.out.println("Nesprávně zadané datum, zadejte ho prosím znovu");
            }
        }
    }

    /**
     * Metoda vyzve uživatele k zadáni data ve formátu den.měsíc.rok a zjišťuje
     * zda je vstup prázdný nebo obsahuje řetězec ve správném formátu. Pokud je
     * vstup prázdný, je vrácena hodnota z druhého parametru. V případě chybného
     * vstupu se metoda opakuje.
     *
     * @param vyzvaZadej Informace jaký vstup je od uživatele očekáván.
     * @param puvodniDatum Hodnota která bude vrácena v případě prázdného
     * vstupu.
     * @return Vstup od uživatele nebo hodnota druhého parametru.
     */
    public static LocalDate vstupUpdateDatum(String vyzvaZadej, LocalDate puvodniDatum) {
        while (true) {
            try {
                System.out.print(vyzvaZadej);
                String vstup = sc.nextLine().trim();
                if (vstup.equals("")) {
                    return puvodniDatum;
                }
                return LocalDate.parse(vstup, DateTimeFormatter.ofPattern("d'.'M'.'y"));
            } catch (Exception ex) {
                System.out.println("Nesprávně zadané datum, zadejte ho prosím znovu");
            }
        }
    }

    /**
     * Metoda pro vstup "ano" vs "ne".
     *
     * @param vyzvaZadej Informace jaký vstup je od uživatele očekáván.
     * @return True pokud uživatel zadal "ano", false pokud zadal jiný neprázdný
     * vstup.
     */
    public static boolean vstupAnoNe(String vyzvaZadej) {
        return vstupString(vyzvaZadej).toLowerCase().equals("ano");
    }

    /**
     * Metoda vyzve uživatele k zadání unikátního id typu pojištění (třída
     * Produkt) a ověří zadání platné hodnoty. V případě chybného vstupu se
     * metoda opakuje.
     *
     * @return Id produktu
     * @throws SQLException
     */
    public static int vstupTypPojisteni() throws SQLException {
        while (true) {
            int idTypPojisteni = vstupInt("Zadejte typ pojištění číselným kódem: ");
            String typPojisteni = vratTypPojisteni(idTypPojisteni);
            if (!typPojisteni.isEmpty()) {
                return idTypPojisteni;
            }
            System.out.println("Zvolený typ pojištění neexistuje.");
        }
    }

    /**
     * Metoda získá relevantní vstupy od uživatele a volá konstruktor třídy
     * Pojistník.
     *
     * @return Nová instance Pojistnik
     * @throws SQLException
     */
    public static Pojistnik vyplnPojistnika() throws SQLException {
        String prijmeni = vstupString("Zadejte příjmení: ");
        String jmeno = vstupString("Zadejte jméno: ");
        LocalDate datumNarozeni = vstupDatum("Zadejte datum narození: ");
        String ulice = vstupString("Zadejte ulici a čp.: ");
        int psc = vstupInt("Zadejte PSČ: ");
        String mesto = vstupString("Zadejte město: ");
        String telefon = vstupString("Zadejte telefon: ");
        Pojistnik novyPojistnik = new Pojistnik(prijmeni, jmeno, datumNarozeni, ulice, psc, mesto, telefon);
        return novyPojistnik;
    }

    /**
     * Metoda získá od uživatele aktualizaci atributů existujícího Pojistníka a
     * volá konstruktor třídy Pojistník, který vytvoří novou pozměněnou instanci
     * Pojistnika.
     *
     * @param pojistnik Existujicí instance třídy Pojistnik se kterou metoda
     * pracuje.
     * @return Upravená instance třídy Pojistnik
     * @throws SQLException
     */
    public static Pojistnik upravPojistnika(Pojistnik pojistnik) throws SQLException {
        System.out.println("Vyplňtě položky, které chcete upravit, ostatní nechte prázdné a stiskněte enter.\n");
        int idPojistnik = pojistnik.getIdPojistnik();
        String prijmeni = vstupUpdateString("Příjmení: " + pojistnik.getPrijmeni() + ", změna: ", pojistnik.getPrijmeni());
        String jmeno = vstupUpdateString("Jméno: " + pojistnik.getJmeno() + ", změna: ", pojistnik.getJmeno());
        LocalDate datumNarozeni = pojistnik.getDatumNarozeni();
        System.out.printf("Datum narození: %s - nemáte oprávnění měnit datum narození.\n", pojistnik.getDatumNarozeni().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        String ulice = vstupUpdateString("Ulice a čp.: " + pojistnik.getUlice() + ", změna: ", pojistnik.getUlice());
        int psc = vstupUpdateInt("PSČ: " + pojistnik.getPsc() + ", změna: ", pojistnik.getPsc());
        String mesto = vstupUpdateString("Město: " + pojistnik.getMesto() + ", změna: ", pojistnik.getMesto());
        String telefon = vstupUpdateString("Telefon: " + pojistnik.getTelefon() + ", změna: ", pojistnik.getTelefon());
        Pojistnik upravenyPojistnik = new Pojistnik(idPojistnik, prijmeni, jmeno, datumNarozeni, ulice, psc, mesto, telefon);
        return upravenyPojistnik;
    }

    /**
     * Metoda získá relevantní vstupy od uživatele a volá konstruktor třídy
     * Pojištění.
     *
     * @param idPojistnik Unikátní id pojistníka
     * @return Nová instance třídy Pojisteni
     * @throws SQLException
     */
    public static Pojisteni vyplniPojisteni(int idPojistnik) throws SQLException {
        int idTypPojisteni = vstupTypPojisteni();
        String typPojisteni = vratTypPojisteni(idTypPojisteni);
        int pojistnyLimit = vstupInt("Zadejte pojistný limit jako celé číslo: ");
        LocalDate platnostOd = vstupDatum("Zadejte datum začátku pojištění: ");
        LocalDate platnostDo = vstupDatum("Zadejte datum konce pojištění: ");
        Pojisteni novePojisteni = new Pojisteni(idTypPojisteni, typPojisteni, pojistnyLimit, platnostOd, platnostDo, idPojistnik);
        return novePojisteni;
    }

    /**
     * Metoda vyhledá podle unikátního id pojistníka všechna jeho pojištení.
     * Volá konstruktor třídy PojisteniSeznam.
     *
     * @param idPojistnik Unikátní id pojistníka
     * @return Instance třídy PojisteniSeznam
     * @throws SQLException
     */
    public static Pojisteni vyhledaPojisteni(int idPojistnik) throws SQLException {
        PojisteniSeznam listPojisteni = new PojisteniSeznam(idPojistnik);
        if (listPojisteni.getListPojisteni().isEmpty()) {
            System.out.print("Tento pojistník nemá žádné pojištění.\n");
            return null;
        }
        while (true) {
            int idPojisteni = vstupInt("Zadejte id pojištění: ");
            Pojisteni pojisteni = listPojisteni.getPojisteni(idPojisteni);
            if (pojisteni != null) {
                return pojisteni;
            }
            System.out.println("Pojištění s tímto id nebylo nalezeno.\n");
        }
    }

    /**
     * Metoda získá od uživatele aktualizaci atributů existujícího pojištění a
     * volá konstruktor třídy Pojisteni, který vytvoří novou pozměněnou instanci
     * Pojisteni.
     *
     * @param pojisteni Existující instance třídy Pojisteni
     * @return Upravená instance třídy Pojisteni
     * @throws SQLException
     */
    public static Pojisteni upravPojisteni(Pojisteni pojisteni) throws SQLException {
        System.out.println("Vyplňtě položky, které chcete upravit, ostatní nechte prázdné a stiskněte enter.\n");
        int idPojisteni = pojisteni.getIdPojisteni();
        int idTypPojisteni = pojisteni.getIdTypPojisteni();
        String typPojisteni = vratTypPojisteni(idTypPojisteni);
        int pojistnyLimit = vstupUpdateInt("Pojistný limit: " + pojisteni.getPojistnyLimit() + ", změna: ", pojisteni.getPojistnyLimit());
        LocalDate platnostOd = pojisteni.getPlatnostOd();
        System.out.printf("Datum začátku pojištění: %s - nemáte oprávnění měnit datum začátku pojištění.\n", pojisteni.getPlatnostOd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        System.out.printf("Datum konce pojištění: %s, změna: ", pojisteni.getPlatnostDo().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        LocalDate platnostDo = vstupUpdateDatum("", pojisteni.getPlatnostDo());
        int idPojistnik = pojisteni.getIdPojistnik();
        Pojisteni upravenePojisteni = new Pojisteni(idPojisteni, idTypPojisteni, typPojisteni, pojistnyLimit, platnostOd, platnostDo, idPojistnik);
        return upravenePojisteni;
    }

    /**
     * Metoda vyzve uživatele k zadání kritéria hledání. Ověří, zda byl nalezen
     * alespoň jeden pojistník. V případě prázdného výsledku se metoda opakuje.
     *
     * @param vyzvaZadej Informace jaký vstup je od uživatele očekáván.
     * @return Instance třídy Pojistnici s vybranými pojistníky.
     * @throws SQLException
     */
    public static Pojistnici vyhledaniPojistnika(String vyzvaZadej) throws SQLException {
        while (true) {
            String hledejPojistnika = vstupString(vyzvaZadej);
            Pojistnici nalezeniPojistnici = najdiPojistnika(hledejPojistnika);
            if (nalezeniPojistnici.getListPojistniku().isEmpty()) {
                System.out.println("Hledaná osoba není v databázi pojistníků.\n");
            } else {
                return nalezeniPojistnici;
            }
        }
    }

    /**
     * Metoda vypíše pojistníka z instance třídy Pojistnici a k němu přiřazená
     * pojištění. Pokud je v instanci více jak jeden pojistník, je uživatel
     * nejprve vyzván k vybrání konkrétního pojistníka.
     *
     * @param vybraniPojistnici Instance třídy Pojistnici s vybranými
     * pojistníky.
     * @return Unikátní id pojistníka
     * @throws SQLException
     */
    public static int vyhledaPojistníkaPojisteniVratiId(Pojistnici vybraniPojistnici) throws SQLException {
        while (true) {
            try {
                Pojistnik pojistnik;
                if (vybraniPojistnici.getListPojistniku().size() > 1) {
                    int id = vstupInt("Zadejte id pojistníka se kterým si přejete pracovat: ");
                    System.out.println();
                    pojistnik = new Pojistnici(id).getPojistnik(0);
                    System.out.print(pojistnik.toString());
                } else {
                    pojistnik = vybraniPojistnici.getPojistnik(0);
                }
                int idPojistnik = pojistnik.getIdPojistnik();
                System.out.println("Sjednaná pojištění: \n" + new PojisteniSeznam(idPojistnik).toString());
                return idPojistnik;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Pojistník s tímto id nebyl nalezen.");
            }
        }
    }

    /**
     * Metoda vypíše hlavní menu.
     */
    public static void vypisMenu() {
        System.out.println("---------------------");
        System.out.println("Vítá Vás POJIŠŤOVNA");
        System.out.println("---------------------");
        System.out.println("1 - přidání nového pojistníka");
        System.out.println("2 - vypsání seznamu všech pojištěných osob");
        System.out.println("3 - vyhledání pojistníka podle jména nebo id a možnosti editace");
        System.out.println("4 - konec");
        System.out.println("---------------------");
    }

    /**
     * Metoda vypíše submenu.
     */
    public static void vypisSubmenu() {
        System.out.println("---------------------");
        System.out.println("Možnosti editace:");
        System.out.println("---------------------");
        System.out.println("a - editace pojistníka");
        System.out.println("b - založení nového pojištění");
        System.out.println("c - editace existujícího pojištění");
        System.out.println("d - smaže vybrané pojištění");
        System.out.println("e - smaže pojistníka a všechna jeho pojištění");
        System.out.println("f - návrat do hlavního menu");
        System.out.println("---------------------");
    }

}

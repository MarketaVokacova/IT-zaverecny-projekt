/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.pojistovna;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Třída reprezentuje obsah tabulky pojistnici v sql databázi. Třída má 3
 * konstruktory.
 *
 * @author Marketa Vokacova
 */
public class Pojistnici {

    /**
     * Proměnná třídy Database reprezentujicí spojení s sql databází.
     */
    static public Database database = null;

    /**
     * List obsahující instance třidy Pojistník.
     */
    private ArrayList<Pojistnik> listPojistniku = new ArrayList<>();

    /**
     * Konstruktor, který vola sql rutinu pro vytvoření seznamu všech pojistníků
     * serazených podle abecedy. Vrací ArrayList se všemi aktuálními instancemi
     * Pojistníků. Metoda používá dva napevno zadané limity, ale lze ji upravit
     * tak, aby šlo množství zobrazených řádků a počátek výpisu nastavit v
     * parametru metody.
     *
     * @throws SQLException
     */
    public Pojistnici(/*preskocitRadku, zobrazitRadku*/) throws SQLException {
        PreparedStatement dotaz = database.getConnection().prepareStatement("CALL `SeradPodlePrijmeniJmena`(0, 50)");
        //dotaz.setInt(1, preskocitRadku);
        //dotaz.setInt(2, zobrazitRadku);
        vysledekDotazu(dotaz);
    }

    /**
     * Konstruktor, který vrátí ArrayList s Pojistníky vyhledanými podle
     * zadaného jména a příjemní.
     *
     * @param hledejPrijmeni Příjmení
     * @param hledejJmeno Jméno
     * @throws SQLException
     */
    public Pojistnici(String hledejPrijmeni, String hledejJmeno) throws SQLException {
        PreparedStatement dotaz = database.getConnection().prepareStatement("SELECT * FROM pojistnici WHERE prijmeni=? AND jmeno=?");
        dotaz.setString(1, hledejPrijmeni);
        dotaz.setString(2, hledejJmeno);
        vysledekDotazu(dotaz);
    }

    /**
     * Přetížený konstruktor, který vrátí ArrayList s právě jedním Pojistníkem,
     * vyhledaným podle unikátního klíče.
     *
     * @param idPojistnik Unikátní id pojistníka
     * @throws SQLException
     */
    public Pojistnici(int idPojistnik) throws SQLException {
        PreparedStatement dotaz = database.getConnection().prepareStatement("SELECT * FROM pojistnici WHERE id=?");
        dotaz.setInt(1, idPojistnik);
        vysledekDotazu(dotaz);
    }

    /**
     * Pomocná metoda spustí sql dotaz, získaný výsledek zpracuje, vytvoří
     * instance pojistníků a vloží je do instance třidy PojisteniSenam.
     *
     * @param dotaz Sql dotaz, který chceme spustit.
     * @throws SQLException
     */
    private void vysledekDotazu(PreparedStatement dotaz) throws SQLException {
        ResultSet vysledekDotazu = dotaz.executeQuery();
        while (vysledekDotazu.next()) {
            int idPojistnik = vysledekDotazu.getInt("id");
            String prijmeni = vysledekDotazu.getString("prijmeni");
            String jmeno = vysledekDotazu.getString("jmeno");
            LocalDate datumNarozeni = vysledekDotazu.getDate("datum_narozeni").toLocalDate();
            String ulice = vysledekDotazu.getString("ulice");
            int psc = vysledekDotazu.getInt("psc");
            String mesto = vysledekDotazu.getString("mesto");
            String telefon = vysledekDotazu.getString("telefon");
            Pojistnik vybranyPojistnik = new Pojistnik(idPojistnik, prijmeni, jmeno, datumNarozeni, ulice, psc, mesto, telefon);
            listPojistniku.add(vybranyPojistnik);
        }
    }

    /**
     * Metoda zjistí zda je zadaná hodnota String nebo int a podle toho vybere
     * konstruktor Pojistníků.
     *
     * @param hledejPojistnika Vstup od uživatele.
     * @return Instence třídy Pojistníci s jedním nebo více pojistníky.
     * @throws SQLException
     */
    public static Pojistnici najdiPojistnika(String hledejPojistnika) throws SQLException {
        try {
            int idPojistnik = Integer.parseInt(hledejPojistnika);
            return new Pojistnici(idPojistnik);

        } catch (NumberFormatException e) {
            String[] jmenoPrijmeni = hledejPojistnika.split(" ");
            return new Pojistnici(jmenoPrijmeni[1], jmenoPrijmeni[0]);
        }
    }

    public ArrayList<Pojistnik> getListPojistniku() {
        return listPojistniku;
    }

    public Pojistnik getPojistnik(int index) {
        return listPojistniku.get(index);
    }

    /**
     * Cyklus for postupně vrátí řetězcovou reprezentaci všech objektů uložených
     * v ArrayListu. Formát výpisu je nastaven metodou toString() třídy
     * Pojistnik. Každý objekt je vypsán na samostatném řádku.
     *
     * @return Spojený řetězec textové reprezentace pojistníků.
     */
    @Override
    public String toString() {
        String pojistnici = "";
        for (Pojistnik pojistnik : listPojistniku) {
            pojistnici += pojistnik.toString();
        }
        return pojistnici;
    }

}

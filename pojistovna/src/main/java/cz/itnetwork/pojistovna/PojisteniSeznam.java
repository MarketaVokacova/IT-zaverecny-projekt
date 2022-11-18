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
 * Třída reprezentuje obsah tabulky smlouvy v sql databázi.
 *
 * @author Marketa Vokacova
 */
public class PojisteniSeznam {

    /**
     * Proměnná třídy Database reprezentujicí spojení s sql databází.
     */
    static public Database database = null;

    /**
     * List obsahující instance třidy Pojisteni.
     */
    private ArrayList<Pojisteni> listPojisteni = new ArrayList<>();

    /**
     * Konstruktor pro vytvoření instance třídy PojisteniSeznam obsahující
     * všechny instance třídy Pojisteni, které patří jednomu konkrétnímu
     * pojistníkovi.
     *
     * @param idPojistnik Unikátní id pojistníka
     * @throws SQLException
     */
    public PojisteniSeznam(int idPojistnik) throws SQLException {
        PreparedStatement dotaz = database.getConnection().prepareStatement("SELECT * FROM `pojistnici_smlouvy` WHERE id_pojistnika = ?");
        dotaz.setInt(1, idPojistnik);
        ResultSet vysledekDotazu = dotaz.executeQuery();
        while (vysledekDotazu.next()) {
            int idPojisteni = vysledekDotazu.getInt("id_smlouvy");
            int idTypPojisteni = vysledekDotazu.getInt("id_produktu");
            String typPojisteni = vysledekDotazu.getString("typ_pojisteni");
            int pojistnyLimit = vysledekDotazu.getInt("pojistny_limit");
            LocalDate platnostOd = vysledekDotazu.getDate("platnost_od").toLocalDate();
            LocalDate platnostDo = vysledekDotazu.getDate("platnost_do").toLocalDate();
            Pojisteni pojisteni = new Pojisteni(idPojisteni, idTypPojisteni, typPojisteni, pojistnyLimit, platnostOd, platnostDo, idPojistnik);
            listPojisteni.add(pojisteni);
        }
    }

    /**
     * Metoda vybere z listu jedno konkrétní pojištění.
     *
     * @param idPojisteni Unikátní id pojištění
     * @return Instance třídy Pojisteni, může vracet i null
     */
    public Pojisteni getPojisteni(int idPojisteni) {
        for (Pojisteni pojisteni : listPojisteni) {
            if (pojisteni.getIdPojisteni() == idPojisteni) {
                return pojisteni;
            }
        }
        return null;
    }

    public ArrayList<Pojisteni> getListPojisteni() {
        return listPojisteni;
    }

    /**
     * Cyklus for postupně vrátí řetězcovou reprezentaci všech objektů uložených
     * v ArrayListu. Formát výpisu je nastaven metodou toString() třídy
     * Pojisteni. Každý objekt je vypsán na samostatném řádku.
     *
     * @return Spojený řetězec textové reprezentace pojištění.
     */
    @Override
    public String toString() {
        if (listPojisteni.isEmpty()) {
            return "Pojištění nenalezeno.";
        }
        String stringPojisteni = "";
        for (Pojisteni pojisteni : listPojisteni) {
            stringPojisteni += pojisteni.toString();
        }
        return stringPojisteni;
    }

}

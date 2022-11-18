/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.pojistovna;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Třída reprezentuje obsah tabulky produkty v sql databázi.
 *
 * @author Marketa Vokacova
 */
public class Produkt {

    /**
     * Proměnná třídy Database reprezentujicí spojení s sql databází.
     */
    static public Database database = null;
    /**
     * Atributy třídy Produkt, odpovídající struktuře tabulky v sql databázi.
     */
    private int idProdukt;
    private String typPojisteni;

    /**
     * Konstruktor instance třídy Produkt, která odpovídá jednomu řádku z
     * tabulky produkty.
     *
     * @param idProdukt Unikátní id produktu
     * @param typPojisteni Název typu pojištění (produktu)
     */
    public Produkt(int idProdukt, String typPojisteni) {
        this.idProdukt = idProdukt;
        this.typPojisteni = typPojisteni;
    }

    /**
     * Metoda vytvoří list obsahující všechny instance třídy Produkt.
     *
     * @return List produktů
     * @throws SQLException
     */
    public static ArrayList<Produkt> vytvorListProduktu() throws SQLException {
        PreparedStatement dotaz = database.getConnection().prepareStatement("SELECT * FROM produkty");
        ResultSet vysledekDotazu = dotaz.executeQuery();
        ArrayList<Produkt> listProduktu = new ArrayList();
        while (vysledekDotazu.next()) {
            int idProdukt = vysledekDotazu.getInt("id");
            String typPojisteni = vysledekDotazu.getString("typ_pojisteni");
            listProduktu.add(new Produkt(idProdukt, typPojisteni));
        }
        return listProduktu;
    }

    /**
     * Cyklus for postupně vrátí řetězcovou reprezentaci všech objektů uložených
     * v ArrayListu a vytvoří z nich jeden dlouhý řetězec.
     *
     * @param listProduktu List produktů
     * @return Spojený řetězec textové reprezentace produktů.
     */
    public static String vratListProduktu(ArrayList<Produkt> listProduktu) {
        String produkty = "";
        for (Produkt produkt : listProduktu) {
            produkty += produkt.toString();
        }
        return produkty;
    }

    /**
     * Metoda vybere podle zadaného id z sql tabulky produkty odpovídající název
     * typu pojištění.
     *
     * @param idTypPojisteni Unikátní id produktu
     * @return Název typu pojištění (produktu)
     * @throws SQLException
     */
    public static String vratTypPojisteni(int idTypPojisteni) throws SQLException {
        PreparedStatement dotaz = database.getConnection().prepareStatement("SELECT `typ_pojisteni` FROM produkty WHERE id=?");
        dotaz.setInt(1, idTypPojisteni);
        ResultSet vysledekDotazu = dotaz.executeQuery();
        vysledekDotazu.next();
        if (!vysledekDotazu.next()) {
            return "";
        }
        return vysledekDotazu.getString("typ_pojisteni");
    }

    /**
     * Řetězcová reprezentace objektu ve formátu: 1 - Název typu pojištění.
     *
     * @return Řetězcová reprezentace objektu.
     */
    @Override
    public String toString() {
        return idProdukt + " - " + typPojisteni + "\n";
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.pojistovna;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Třída reprezentuje jedno uzavřené pojištění (řádek v sql tabulce smlouvy).
 *
 * @author Marketa Vokacova
 */
public class Pojisteni {

    /**
     * Proměnná třídy Database reprezentujicí spojení s sql databází.
     */
    static public Database database = null;

    /**
     * Atributy třídy Pojisteni, odpovídající struktuře tabulky v sql databázi.
     */
    private int idPojisteni;
    private int idTypPojisteni;
    private String typPojisteni;
    private int pojistnyLimit;
    private LocalDate platnostOd;
    private LocalDate platnostDo;
    private int idPojistnik;

    /**
     * Konstruktor již existujícího pojištění v sql databázi.
     *
     * @param idPojisteni Unikatní id Pojištění
     * @param idTypPojisteni Unikátní id Produktu
     * @param typPojisteni Název vybraného typu Produktu
     * @param pojistnyLimit Maximální pojistná částka
     * @param platnostOd Datum začátku pojištení
     * @param platnostDo Datum konce pojištění
     * @param idPojistnik Unikátní id pojištěné osoby
     */
    public Pojisteni(int idPojisteni, int idTypPojisteni, String typPojisteni, int pojistnyLimit, LocalDate platnostOd, LocalDate platnostDo, int idPojistnik) {
        this.idPojisteni = idPojisteni;
        this.idTypPojisteni = idTypPojisteni;
        this.typPojisteni = typPojisteni;
        this.pojistnyLimit = pojistnyLimit;
        this.platnostOd = platnostOd;
        this.platnostDo = platnostDo;
        this.idPojistnik = idPojistnik;
    }

    /**
     * Konstruktor vytvářející úplně novou instanci Pojisteni, která zatím v sql
     * databázi neexistuje. Jelikož je id pojištění primárním klíčem s funkcí
     * AUTO_INCREMENT sql databáze je jeho hodnota v konstruktoru implicitně
     * nastavena na -1. Skutečná hodnota id je instanci přidělena v okamžiku
     * uložení do sql databáze.
     *
     * @param idTypPojisteni Unikátní id Produktu
     * @param typPojisteni Název vybraného typu Produktu
     * @param pojistnyLimit Maximální pojistná částka
     * @param platnostOd Datum začátku pojištení
     * @param platnostDo Datum konce pojištění
     * @param idPojistnik Unikátní id pojištěné osoby
     */
    public Pojisteni(int idTypPojisteni, String typPojisteni, int pojistnyLimit, LocalDate platnostOd, LocalDate platnostDo, int idPojistnik) {
        this(-1, idTypPojisteni, typPojisteni, pojistnyLimit, platnostOd, platnostDo, idPojistnik);
    }

    /**
     * Metoda vytvářející propojení s sql databází, vytváří nový řádek v tabulce
     * smlouvy nebo umožnuje editaci-UPDATE již existující položky. V případě
     * použití dotazu INSERT INTO, vrací zpět databází vygenerovaný PRIMARY KEY
     * a přiřadí ho k idPojisteni.
     *
     * @return Nová nebo upravená instance Pojisteni.
     * @throws SQLException
     */
    public Pojisteni ulozPojisteni() throws SQLException {
        PreparedStatement dotaz;
        if (getIdPojisteni() == -1) {
            dotaz = database.getConnection().prepareStatement("INSERT INTO smlouvy (`typ_pojisteni_id`, `pojistny_limit`, `platnost_od`, `platnost_do`, `pojistnik_id`) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        } else {
            dotaz = database.getConnection().prepareStatement("UPDATE smlouvy SET `typ_pojisteni_id`=?, `pojistny_limit`=?, `platnost_od`=?, `platnost_do`=?, `pojistnik_id`=? WHERE `id`=?");
            dotaz.setInt(6, getIdPojisteni());
        }
        dotaz.setInt(1, getIdTypPojisteni());
        dotaz.setInt(2, getPojistnyLimit());
        dotaz.setString(3, getPlatnostOd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dotaz.setString(4, getPlatnostDo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dotaz.setInt(5, getIdPojistnik());
        dotaz.executeUpdate();
        if (getIdPojisteni() == -1) {
            ResultSet ids = dotaz.getGeneratedKeys();
            ids.next();
            idPojisteni = ids.getInt(1);
        }
        return this;
    }

    /**
     * Metoda vyhledá podle zadaného id pojištěni odpovídající záznam v sql
     * databázi a odstraní ho.
     *
     * @param idPojisteni Unikátní id pojištění
     * @throws SQLException
     */
    public static void smazPojisteni(int idPojisteni) throws SQLException {
        PreparedStatement dotaz = database.getConnection().prepareStatement("DELETE FROM smlouvy WHERE id=?");
        dotaz.setInt(1, idPojisteni);
        int radku = dotaz.executeUpdate();
        if (radku != 0) {
            System.out.println("Pojištění bylo smazáno.");
        }
    }

    /**
     * Řetězcová reprezentace objektu ve formátu: id: 1 - 1 Název typu pojištšní
     * 10000000 11.11.1111 11.11.1111.
     *
     * @return Řetězcová reprezentace objektu.
     */
    @Override
    public String toString() {
        return "id: " + getIdPojisteni() + " - " + getIdTypPojisteni() + " " + getTypPojisteni() + " " + getPojistnyLimit() + " " + getPlatnostOd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " + getPlatnostDo().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n";
    }

    public int getIdPojisteni() {
        return idPojisteni;
    }

    public int getIdTypPojisteni() {
        return idTypPojisteni;
    }

    public String getTypPojisteni() {
        return typPojisteni;
    }

    public int getPojistnyLimit() {
        return pojistnyLimit;
    }

    public LocalDate getPlatnostOd() {
        return platnostOd;
    }

    public LocalDate getPlatnostDo() {
        return platnostDo;
    }

    public int getIdPojistnik() {
        return idPojistnik;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.pojistovna;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Třída reprezentuje jednu osobu Pojistníka (řádek v sql tabulce pojistnici).
 *
 * @author Marketa Vokacova
 */
public class Pojistnik {

    /**
     * Proměnná třídy Database reprezentujicí spojení s sql databází.
     */
    static public Database database = null;

    /**
     * Atributy třídy Pojistnik, odpovídající struktuře tabulky v sql databázi.
     */
    private int idPojistnik;
    private String prijmeni;
    private String jmeno;
    private LocalDate datumNarozeni;
    private String ulice;
    private int psc;
    private String mesto;
    private String telefon;

    /**
     * Konstruktor již existujícího Pojistníka v sql databázi.
     *
     * @param idPojistnik Unikatní id Pojistníka
     * @param prijmeni Příjmení
     * @param jmeno Jméno
     * @param datumNarozeni Datum narození
     * @param ulice Ulice a číslo popisné
     * @param psc PSČ
     * @param mesto Město
     * @param telefon Telefonní číslo
     */
    Pojistnik(int idPojistnik, String prijmeni, String jmeno, LocalDate datumNarozeni, String ulice, int psc, String mesto, String telefon) {
        this.idPojistnik = idPojistnik;
        this.prijmeni = prijmeni;
        this.jmeno = jmeno;
        this.datumNarozeni = datumNarozeni;
        this.ulice = ulice;
        this.psc = psc;
        this.mesto = mesto;
        this.telefon = telefon;
    }

    /**
     * Konstruktor vytvářející úplně novou instanci Pojistníka, která zatím v
     * sql databázi neexistuje. Jelikož je id Pojistníka primárním klíčem s
     * funkcí AUTO_INCREMENT sql databáze je jeho hodnota v konstruktoru
     * implicitně nastavena na -1. Skutečná hodnota id instanci přidělena
     * v okamžiku uložení do sql databáze.
     *
     * @param prijmeni Příjmení
     * @param jmeno Jméno
     * @param datumNarozeni Datum narození
     * @param ulice Ulice a číslo popisné
     * @param psc PSČ
     * @param mesto Město
     * @param telefon Telefonní číslo
     */
    public Pojistnik(String prijmeni, String jmeno, LocalDate datumNarozeni, String ulice, int psc, String mesto, String telefon) {
        this(-1, prijmeni, jmeno, datumNarozeni, ulice, psc, mesto, telefon);
    }

    /**
     * Metoda vytvářející propojení s sql databází, vytváří nový řádek v tabulce
     * pojistníci nebo umožnuje editaci-UPDATE již existující položky. V případě
     * použití dotazu INSERT INTO, vrací zpět databází vygenerovaný PRIMARY KEY
     * a přiřadí ho k idPojistnik.
     *
     * @return Nová nebo upravená instance Pojistníka.
     * @throws SQLException
     */
    public Pojistnik ulozPojistnika() throws SQLException {
        PreparedStatement dotaz;
        if (getIdPojistnik() == -1) {
            dotaz = database.getConnection().prepareStatement("INSERT INTO pojistnici (`prijmeni`, `jmeno`, `datum_narozeni`, `ulice`, `psc`, `mesto`, `telefon`) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        } else {
            dotaz = database.getConnection().prepareStatement("UPDATE pojistnici SET `prijmeni`=?, `jmeno`=?, `datum_narozeni`=?, `ulice`=?, `psc`=?, `mesto`=?, `telefon`=? WHERE `id`=?");
            dotaz.setInt(8, getIdPojistnik());
        }
        dotaz.setString(1, getPrijmeni());
        dotaz.setString(2, getJmeno());
        dotaz.setDate(3, Date.valueOf(getDatumNarozeni()));
        dotaz.setString(4, getUlice());
        dotaz.setInt(5, getPsc());
        dotaz.setString(6, getMesto());
        dotaz.setString(7, getTelefon());
        dotaz.executeUpdate();
        if (getIdPojistnik() == -1) {
            ResultSet ids = dotaz.getGeneratedKeys();
            ids.next();
            idPojistnik = ids.getInt(1);
        }
        return this;
    }

    /**
     * Metoda vyhledá podle zadaného id pojistníka odpovídající záznam v sql databázi a
     * odstraní ho.
     *
     * @param idPojistnik Unikátní id pojistníka
     * @throws SQLException
     */
    public static void smazPojistnika(int idPojistnik) throws SQLException {
        PreparedStatement dotaz = database.getConnection().prepareStatement("DELETE FROM pojistnici WHERE id=?");
        dotaz.setInt(1, idPojistnik);
        int radku = dotaz.executeUpdate();
        if (radku != 0) {
            System.out.println("Pojistník byl smazán");
        }
    }

    /**
     * Řetězcová reprezentace objektu ve formátu: id: 1 - Příjmení Jméno,
     * 11.11.1111, Ulice 1, 11111 Město, 111111111.
     * 
     * @return Řetězcová reprezentace objektu.
     */
    @Override
    public String toString() {
        return "id: " + getIdPojistnik() + " - " + getPrijmeni() + " " + getJmeno() + ", " + getDatumNarozeni().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ", " + getUlice() + ", " + getPsc() + " " + getMesto() + ", " + getTelefon() + "\n";
    }

    public int getIdPojistnik() {
        return idPojistnik;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getJmeno() {
        return jmeno;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public String getUlice() {
        return ulice;
    }

    public int getPsc() {
        return psc;
    }

    public String getMesto() {
        return mesto;
    }

    public String getTelefon() {
        return telefon;
    }

}

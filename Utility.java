/*
Creazione nuovo Project per Array List Class
Classe e Pack per calcolo dell'età con funzione Period static.
@Alfio Ezechiele Cavallaro 17/06/2026 */

package utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Utility
{
    public static int calcolaEta(LocalDate abra)
    {
        //LocalDate.now() prende la data di oggi. Period,between(nascita / oggi) serve epr fare il conto .getyears() serve per stampare il numero degli anni
        return Period.between(abra, LocalDate.now()).getYears(); //Così stampa solo gli anni.

    }

    //dichiarazione della variabile formatter per impostare i giorni,mesi , anni come vogliamo noi seguendo sempre il pattern CONSENTITO di. ofPattern();
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
/*
Creazione nuovo Project per HashMap Class
@Alfio Ezechiele Cavallaro 17/06/2026 */
package model; //dentro il model vanno le entità o classi
import utils.Utility;

import java.time.LocalDate;
import java.util.Objects; //modello per utilizzare Object
import java.time.format.DateTimeFormatter; //modello per utilizzare le funzione di LocalData per formattare il tempo a nostro piacimento.

public class Persona
{
    //alt + ing per creare in automatico getter e setter e altre funzioni! Intellj tips.
    //setter incapsulato a private.
    private String nome;
    private String cognome;
    private int id; //aggiunto il 17
    //private int eta; //sostituito con datanascita il 17
    private LocalDate datanascita; //gg/mm/aaaa
    private String cf;

    //getter
    //public int getEta() {return eta;}
    //public void setEta(int eta) {this.eta = eta}

    //Settiamo il counter già ora da fuori e dentro persona, così da avere ad ogni creazione un nuovo ID.
    private static Integer cont=0;

    public int getId() {return id;}
    //usiamo ++cont così il primo elemento sarà 1 e non 0, come per cont++
    public void setId() {this.id = ++cont;}

    public LocalDate getDatanascita() {return datanascita;}//setter
    public void setDatanascita(LocalDate datanascita) {this.datanascita = datanascita;}//getter

    public String getCf() {
        return cf;
    }//setter
    public void setCf(String cf) { this.cf = cf; }//getter

    public void setNome(String nome)
    {
        this.nome=nome;
    }//setter
    public String getNome() {
        return nome;
    }//getter

    public void setCognome(String cognome)
    {
        this.cognome=cognome;
    }//setter
    public String getCognome() {
        return cognome;
    }//getter


    //Costruttore
    public Persona(int id, String nome, String cognome,LocalDate datanascita, String cf) /*int eta*/
    {
        this.id=id;
        this.nome=nome;
        this.cognome=cognome;
        this.datanascita=datanascita;
        //this.eta=eta;
        this.cf=cf;
    }

    //Costruttore vuoto che può contenere i null, in Java serve ricreare la classe onde evitare problemi di null.
    public Persona()
    {

    }

    //Stampa la stringa che darà in eredità in questo modo e li stampa in modo leggibile.
    //senza vedremo qualcosa come model.Persona@4f023edb
    //.format(Formatter) per richiamare la funzione dichiarata prima per impostare gtli anni durante la stampa (dd/MM/yyyy) 17/06
    //Calcolo.calcolaEta() per usare la funzione in utils per calcolare gli anni di chi viene inserito. 17/06
    @Override
    public String toString() {
        //dichiarazione della variabile formatter per impostare i giorni,mesi , anni come vogliamo noi seguendo sempre il pattern CONSENTITO di. ofPattern();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "ID :[" +id+ "] Nome:[" + nome + "] Cognome:[" + cognome + "] Data di nascità:[" + datanascita.format(f) + "] C.F:[" +cf+ "] Età: [" + Utility.calcolaEta(datanascita) + "]"; //Età:[" + eta + "]
    }


    //stampa intero
    @Override
    public int hashCode()
    {
        return Objects.hash(id, cf, cognome, datanascita, nome); //eta
    }


    //Controllo se due persone sono uguali
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Persona other = (Persona) obj;
        //Confronto strighe in modo sicuro con and (&&)
        //&& eta == other.eta Cambiato il 17 in data di nascita
        return Objects.equals(cognome, other.cognome)
                && Objects.equals(datanascita,other.datanascita)
                && Objects.equals(nome, other.nome)
                && Objects.equals(cf, other.cf)
                && Objects.equals(id, other.id);
    }

}
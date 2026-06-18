/*
Creazione nuovo Project per HashMap Class
@Alfio Ezechiele Cavallaro 17/06/2026
Stiamo creando un progetto di menù, repository, modello e controller per l'utilizzo degli array list.*/
package view;

import model.Persona;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Collection;


public class View
{
    //input output console
    private Scanner input= new Scanner(System.in);

    public int leggiValore(String s)
    {
        while(true)
        {
            try
            {
                System.out.println(s);
                return Integer.parseInt(input.nextLine());
            }
            //catch è una rete che cerca di prendere l'eccezione, il numberformatexception è per i numeri.
            catch(NumberFormatException e)
            {
                System.out.println("Inserisci un numero valido"+e.getMessage());
            }
            catch(IllegalArgumentException | NullPointerException e)
            {
                System.out.println("Argomento non valido, inserisci un numero "+e.getMessage());
            }
            //inseriamo anche la generica in caso di altri errori che non sono dettagliati, cosi da prendere tutti i problemi.
            catch(Exception e)
            {
                System.out.println("Errore generico, inserisci un numero "+e.getMessage());
            }
        }
    }

    public String leggiString(String s)
    {
        while(true)
        {
            try
            {
                System.out.println(s);
                return input.nextLine();
            }
            catch(Exception e)
            {
                System.out.println("Errore generico, inserisci una stringa valida"+e.getMessage());
            }
        }
    }
//Funzione per cambiare il formato di leggidata, anziché anno/mese/giorno in giorno/mese/anno
    public LocalDate leggidata(String s)
    {
        //usiamo classe sottostante per impostare il metodo.
        //dichiarazione della variabile formatter per impostare i giorni,mesi , anni come vogliamo noi seguendo sempre il pattern CONSENTITO di. ofPattern();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while(true)
        {
        String stringData = leggiString(s);
            try
            {
                return LocalDate.parse(stringData,f);
            }
            catch(DateTimeParseException e)
            {
                System.out.println("Errore generico, inserisci una stringa valida"+e.getMessage());
            }
        }
    }

    public void formInit(Persona p)
    {
        p.setId();
        p.setNome(leggiString("Inserisci il nome: "));
        p.setCognome(leggiString("Inserisci il cognome: "));
        p.setDatanascita(leggidata("Insersci data nascita in formato (gg/mm/aaaa): "));
        p.setCf(leggiString("Insersci Codice Fiscale: "));
    }
//Modifica del prof
    public Persona formUpdate(Persona p)
    {
        //usiamo classe sottostante per impostare il metodo.
        //dichiarazione della variabile formatter per impostare i giorni,mesi , anni come vogliamo noi seguendo sempre il pattern CONSENTITO di. ofPattern();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Se premi Invio vai avanti nella modifica, anche se non hai fatto nulla.");
        String nome=leggiString("Nome: ["+p.getNome()+"]:");
        nome = nome.isEmpty() ? p.getNome() : nome;
        String cognome=leggiString("Cognome: ["+p.getCognome()+"]:");
        cognome = cognome.isEmpty() ? p.getCognome() : cognome;

        //String etaVal=leggiString("Età: ["+p.getEta()+"]:");
        //int eta=etaVal.isEmpty() ? p.getEta() : Integer.parseInt(etaVal);

        //Passiamo la stringa, creando un nuovo valore, utilizziamo la funzione .format(DataTimeFormatter.ofPattern) per seguire il pattern richiesto anche durante il print terminale
        //Poi la passiamo in LocalDate, dove si, se è vuota la stringa, rimane quella che è, sennò la Str diventa un LocalDate e prende quel pattern.
        //.parse oltre che per Int, Float, funziona anche per LocalDate. Da STR a X
        String dataStr = leggiString("Data nascita (gg/mm/yyyy) [" + p.getDatanascita().format(f) + "]: ");
        LocalDate datadinascita = dataStr.isEmpty() ? p.getDatanascita() : LocalDate.parse(dataStr, f);

        String cf=leggiString("CF: ["+p.getCf()+"]:");
        cf = cf.isEmpty() ? p.getCf() : cf;
        if (leggiString("Sei sicuro di apportare queste modifiche? (si/no)").equalsIgnoreCase("Si"))
            return new Persona(p.getId(),nome,cognome,datadinascita,cf);
        else
            return p;
    }
//Modifica mia. da rivedere
/*
    public Persona modificaPersona(Persona p)
    {
        //Metodo in view, formUpdate, per l'eventuale modifica
        //nome['antonio']
        //se digiti invio non modifica valore attuale, altrimenti digitate nuovo nome.
        //non dobbiamo modificare l'oggetto con i set, perché alla fine di tutte le modifiche se volessimo tornare indietro dovrebbe essere possibile.
        //Alla fine messaggio, se si, viene modificato con il set.

        //Dichiariamo nuovamente i dati di base, mettiamo la nostra funzione leggi stringa
        String nome = leggiString("Nome [" + p.getNome() + "]: ");
        String cognome = leggiString("Cognome [" + p.getCognome() + "]: ");
        //Dobbiamo tenere eta in String perché isBlank, che conta gli spazi è solo per stringhe
        String eta = leggiString("Età [" + p.getEta() + "]: ");

        return new Persona(
                nome.isBlank() ? p.getNome() : nome,
                cognome.isBlank() ? p.getCognome() : cognome,
                eta.isBlank() ? p.getEta() : Integer.parseInt(eta),
                p.getCF()
        );
    }
*/
    public void printPersona(Persona p)
    {
        System.out.println(p);
    }

    public void printPersone(Collection<Persona> persone)
    {
        if (persone.isEmpty())
        {
            System.out.println("Lista vuota!");
            return;
        }

        for (Persona p : persone)
        {
            System.out.println(p);
        }
    }

    public void msg(String s)
    {
        System.out.println(s);
    }

    public int menu()
    {
        System.out.println("_________Schermata Iniziale________");
        System.out.println("|   1 - Inserisci nuovo utente    |");
        System.out.println("|   2 - Stampa                    |");
        System.out.println("|   3 - Ricerca per ID            |");
        System.out.println("|   4 - Modifica per ID           |");
        System.out.println("|   5 - Elimina per ID            |");
        System.out.println("|   0 - Chiudi                    |");
        System.out.println("|_________________________________|");
        return leggiValore(("Fai una scelta: "));
    }
}

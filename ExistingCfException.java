/*
Creazione dell'eccezione personalizzata
@Alfio Ezechiele Cavallaro 17/06/2026 */
package exceptions;

//Estendiamo al Exception per renderla tale, sennò non avrebbe funzionato
//Comportamento inatteso, va sempre dichiarato anche se prende l'estensione della classe
//Check Repository e Avvio per vedere il suo utilizzo
public class ExistingCfException extends Exception
{
    public ExistingCfException(String s)
    {
        //Ctrl + click per vedere cosa fa una funzione!!!
        //Eccezione personalizzata
        super(s);
    }
}

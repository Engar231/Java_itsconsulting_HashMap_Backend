/*
Creazione nuovo Project per HashMap Class
@Alfio Ezechiele Cavallaro 17/06/2026 */
package controller;

import exceptions.ExistingCfException;
import model.Persona;
import view.View;
import repository.Repository;

public class Avvio
{
    public static void main(String[] args)
    {
        //Main
        View v = new View(); //Messi fuori perché servono per una funziona esterna, e dovrannoe ssere static
        int id; //dichiarato prima sennò salta tutto in aria.
        Persona p; //Senza () perché la stiamo creando qua, totalmente a nuovo.
        Repository repo = new Repository();
        boolean flag = true;
        do
        {
            switch(v.menu())
            {
                case 1:
                    //Inserimento
                    try {
                        p = new Persona();
                        v.formInit(p);
                        repo.checkCf(p); //ExistingCFException
                        id = repo.insert(p); //Spostato sopra Print Persona sennò dava errore di ID 0
                        v.printPersona(p);
                    //Il ternario salta, perché essendo che cerchiamo per ID non possiamo più fare il boolean.
                        if (id > 0)
                            v.msg("Inserimento avvenuto");
                        else
                            v.msg("Errore inserimento");
                    } catch (ExistingCfException e)
                    {
                        v.msg(e.getMessage());
                    }
                    break;
                case 2:
                    //Stampa persona/elenco
                    v.printPersone(repo.getHash().values());//Prendiamo da collection la richiesta di stampaggio
                    break;
                case 3:
                    //Ricerca per ID
                    id=v.leggiValore("Inserisci l'id  della persona da ricercare: ");
                    p=repo.getPersona(id);
                    if(p!=null)
                        v.printPersona(p);
                    else v.msg("Persona non trovata");
                    break;
                case 4:
                    //Ricerca per ID dell'utente da eliminare
                    id=v.leggiValore("Inserisci l'id della persona da eliminare: ");
                    p = repo.getPersona(id);
                    if(p!=null)
                    {
                        if(v.leggiString(
                                "Sei sicuro di voler eliminare la seguente persona? (Si/No) "+p).equalsIgnoreCase("si"))
                            v.msg(repo.delete(id) ? "La persona è stata eliminata": "Qualcosa è andata storto");
                        else
                            v.msg("Eliminazione annullata");
                    }
                    else
                        v.msg("Persona non trovata");
                    break;
                case 5:
                    //Modifica tramite ID
                    Persona pNew;
                    id=v.leggiValore("Inserisci l'ID della persona da cercare: ");
                    p=(repo.getPersona(id));
                    if(p!=null)
                    {
                        pNew=v.formUpdate(p);
                        if(!pNew.equals(p))
                        {
                            try 
                            {
                                repo.checkCf(p, pNew); //non va bene
                                repo.update(id, pNew); //a differenza di array, l'aggiornamento qui avviene prima per id, poi persona nuova
                            } catch (ExistingCfException e)
                            {
                                System.out.println("Errore, già presente");
                            }
                        }
                        else
                            v.msg("Nessuna modifica effettuata");
                    }
                    else
                        v.msg("Persona non trovata");
                    break;
                case 0:
                    //Chiusura
                    flag=false;
                    break;
                default:
                    v.msg("Voce di menu non valida");
                    break;
            }
        } while(flag);
    }
}
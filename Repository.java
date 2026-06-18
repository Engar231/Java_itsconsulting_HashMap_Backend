/*
Creazione nuovo Project per Hashmap List Class
@Alfio Ezechiele Cavallaro 17/06/2026 */
package repository;

import exceptions.ExistingCfException;
import model.Persona;
import java.util.HashMap;


//Dove i nostri dati vengono messi.
public class Repository
{
//l'hash contiene Persona e accetta solo i suoi oggetti, Map è il nome così' sappiamo cos'è e come chiamarlo;
//Perché private? così possiamo usarla SOLO dentro questa classe. In questo momento stiamo usando CHIAVE/VALORE
// Integer = 0,1,2,3 , Persona = Classe Persona e i suoi oggetti
    private HashMap<Integer, Persona> map = new HashMap<>();

//Inserimento della nuova Persona sbloccata.
public int insert(Persona p)
{
    //Put non da booleano, put ritorna il valore precedente o null
    map.put(p.getId(), p);
    return p.getId();
}
//Funzione per ritornare tutta la lista in hash
    public HashMap<Integer, Persona> getHash()
    {
        return map;
    }
//Funzione per ritorno della lista Array di Persona se il CF è valido
    public Persona getPersona(int id)
    {
        return map.get(id);
    }
//Funzione di eliminazione
    public boolean delete(int id)
    {
        return map.remove(id) != null;
    }
//Funzione di modifica del prof
    public void update(int id, Persona pNew)
    {
        map.put(id, pNew);
    }

    public void checkCf(Persona p) throws ExistingCfException
    {
        //for map.entryset() ti fa lavorare su chiave + valore
        //for (Map.Entry<Integer, Persona> entry : map.entrySet())
        //{
        //    Integer key = entry.getKey();
        //    Persona val = entry.getValue();
        //}
        //For recupero hashmap ed iterò persone con map.values dove prendo i valori e ci si lavora sopra.
        for (Persona pers : map.values())
        {
            if (pers.getCf().equalsIgnoreCase(p.getCf()))
            {
                //return p;
                throw new ExistingCfException("Errore! persona con CF già presente");
            }
        }
        //return null;
    }

//Funzione di propagazione, attraverso throws, della funzione errore creata nel pacchetto exceptions
    public void checkCf(Persona p, Persona pNew) throws ExistingCfException
    {
        for (Persona pers :map.values())
        {
            if (pers.getCf().equalsIgnoreCase(pNew.getCf())
                    && !p.getCf().equals(pNew.getCf()))
            {
                throw new ExistingCfException("Errore! persona con CF già presente");
            }
        }
    }
}


package almapacasa.ClassesMetier;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alexandre on 27/04/2016.
 */
public class Visite {

    private String id;
    private Personne personne;
    private Date heureDebut;
    private Date heureFin;
    private String commentaire;
    private Date date;
    private ArrayList<Soin> lesSoinsPrevu;
    private ArrayList<Soin> lesSoinRealise;
    private Timestamp timestamp;

    public Visite(String Id, String Nom, String Prenom, String Adresse, String Numero, String HeureDebut, String HeureFin, String Commentaire, String Date){
        this.id = Id;
        this.personne = new Personne(Nom, Prenom, Adresse, Numero);
        DateFormat df = new SimpleDateFormat("kk:mm:ss");
        try{
            this.heureDebut = df.parse(HeureDebut);
            this.heureFin = df.parse(HeureFin);
            df = new SimpleDateFormat("yyyy-MM-dd");
            this.date = df.parse(Date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        this.commentaire = Commentaire;
        setLesSoinsPrevu(new ArrayList<Soin>());
        setLesSoinRealise(new ArrayList<Soin>());
    }

    public Visite()
    {
      this.lesSoinsPrevu = new ArrayList<Soin>();
      this.lesSoinRealise = new ArrayList<Soin>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        DateFormat df = new SimpleDateFormat("EEEE", Locale.FRANCE);
        return df.format(date).toString();
    }

    @Deprecated
    public String getIdDayOfWeek() {
        return android.text.format.DateFormat.format("u", date).toString();
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Soin> getLesSoinsPrevu() {
        return lesSoinsPrevu;
    }

    public void setLesSoinsPrevu(ArrayList<Soin> lesSoinsPrevu) {
        this.lesSoinsPrevu = lesSoinsPrevu;
    }

    public ArrayList<Soin> getLesSoinRealise() {
        return lesSoinRealise;
    }

    public void setLesSoinRealise(ArrayList<Soin> lesSoinRealise) {
        this.lesSoinRealise = lesSoinRealise;
    }
}

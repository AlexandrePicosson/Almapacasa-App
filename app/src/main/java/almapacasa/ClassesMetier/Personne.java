package almapacasa.ClassesMetier;

/**
 * Created by Alexandre on 27/04/2016.
 */
public class Personne {
    private String nom;
    private String prenom;
    private String adresse;
    private String numero;

    public Personne(String Nom, String Prenom, String Adresse, String Numero) {
        this.nom = Nom;
        this.prenom = Prenom;
        this.adresse = Adresse;
        this.numero = Numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        adresse = adresse;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        numero = numero;
    }
}

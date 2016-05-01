package almapacasa.ClassesMetier;

/**
 * Created by Alexandre on 01/05/2016.
 */
public class Soin {
    private String id;
    private String idTypeSoin;
    private String libelle;

    public Soin(String Id, String IdTypeSoin, String Libelle)
    {
        this.id = Id;
        this.idTypeSoin = IdTypeSoin;
        this.libelle = Libelle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTypeSoin() {
        return idTypeSoin;
    }

    public void setIdTypeSoin(String idTypeSoin) {
        this.idTypeSoin = idTypeSoin;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

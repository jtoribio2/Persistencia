package NEGOCI.MODEL;

public class Escola {
protected int id;
protected String nom,lloc,aproximacio,popularitat;
    public Escola(int id, String nom, String lloc, String aproximacio, String popularitat){

        this.id = id;
        this.nom = nom;
        this.lloc = lloc;
        this.aproximacio = aproximacio;
        this.popularitat = popularitat;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
    }

    public String getPopularitat() {
        return popularitat;
    }

    public void setPopularitat(String popularitat) {
        this.popularitat = popularitat;
    }

    @Override
    public String toString() {
        return "escola{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", lloc='" + lloc + '\'' +
                ", aproximacio='" + aproximacio + '\'' +
                ", popularitat='" + popularitat + '\'' +
                '}';
    }
}


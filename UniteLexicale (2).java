public class UniteLexicale {
    private Categorie categorie;
    private Object lexeme;

    public UniteLexicale(Categorie categoriee, Object lexeme1) {
        this.categorie=categoriee;
        this.lexeme=lexeme1;
    }

    public Categorie getCategoriie() {
        return this.categorie;
    }
    
    public String getCategorie() {
        return this.categorie.toString();
    }

    public String toString() {
        return "< "+this.categorie.toString()+" , "+this.lexeme+" >";
    }
}

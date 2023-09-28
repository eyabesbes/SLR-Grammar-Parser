public enum Categorie
{
    EOF,
    $,
    dp,
    af,
    ao,
    pf,
    po,
    NUL,
    ID,
    Entier,
    OpAr,
    opLog,
    Reel,
    Chaine,
    If,
    Then,
    Else,
    While,
    Do,
    PV,
    OPPAff,
    OppRel,
    write,
    read,
    Type,
    NON,
    Booleen,;
/*La méthode java string toLowerCase () renvoie la chaîne en minuscules. En d'autres termes,
il convertit tous les caractères de la chaîne en minuscules. */

    public String toString() {
        return this.name().toLowerCase();
    }
    /*
La méthode equalsIgnoreCase() compare deux chaînes en ignorant les différences entre
minuscules et majuscules et renvoie « true » si les chaînes sont égales sinon renvoie « false ».
*/
    public static Categorie toCategorie(String s)
    {
        for(Categorie c:Categorie.values())
            if(c.toString().equalsIgnoreCase(s))
                return c;
        return null;
    }
    /*La méthode ordinal() permet de retrouver le numéro d'ordre d'un élément énuméré,
     dans la liste de tous les éléments d'une énumération. Le premier numéro d'ordre est 0.
    */
}



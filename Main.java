import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args)
	 {
       ArrayList<String> tab= new ArrayList<String>();


	     Scanner anaLex=new Scanner("compilationtest.txt");
		
	     System.out.println("***********************Analyse lexicale*************************");

	    UniteLexicale ul=null;
				do {
					ul=anaLex.lexemeSuivant();
					tab.add(ul.getCategorie());
					System.out.println(ul);
		
				} while(ul.getCategoriie()!= Categorie.EOF);
				String tab1[]= new String[tab.size()];


	    System.out.println("***********************Analyse Syntaxique*************************");	 

		for( int i=0; i<tab.size();i++)		       	
		       	tab1[i]=tab.get(i);


		Parsernew test22 = new Parsernew();
        test22.analyzeSLnew(tab1);  
    }
}

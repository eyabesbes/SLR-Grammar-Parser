import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*La ArrayListclasse est un tableau redimensionnable , qui peut être trouvé dans le java.utilpackage.

La différence entre un tableau intégré et un tableau ArrayListen Java,
est que la taille d'un tableau ne peut pas être modifiée (si vous voulez ajouter ou supprimer des éléments dans / d'un tableau,
 vous devez en créer un nouveau).
Alors que des éléments peuvent être ajoutés et supprimés d'un ArrayListfichier quand vous le souhaitez.
*/

public class Scanner 
{
    private ArrayList<Character> fluxCaracteres;
    private int indiceCourant;
    private char caractereCourant;
    private boolean eof;
    
    public Scanner() {
        this("");
    }
    public Scanner(String nomFich) {
        BufferedReader f = null;
        int car = 0;
        this.fluxCaracteres = new ArrayList<Character>();
        indiceCourant = 0;
        eof = false;
        try {
            f = new BufferedReader(new FileReader(nomFich));
        } catch (IOException e) {
            System.out.println("taper votre texte ci-dessous (ctrl+z pour finir)");
            f = new BufferedReader(new InputStreamReader(System.in));
        }

        try {
            while ((car = f.read()) != -1)
                this.fluxCaracteres.add((char) car);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void caractereSuivant() {
        if (indiceCourant < this.fluxCaracteres.size())
            caractereCourant = fluxCaracteres.get(indiceCourant++);
        else
            eof = true;
    }
    public void reculer() {
        if (indiceCourant > 0)
            indiceCourant--;
    }
    public UniteLexicale lexemeSuivant() 
    {
        caractereSuivant();

        while (eof || Character.isWhitespace(caractereCourant)) {
            if (eof)
                return new UniteLexicale(Categorie.EOF, "");
            caractereSuivant();
        }

       



        if (Character.isDigit(caractereCourant))
        {
            
           
            return getNombre();
           
        }



        if (caractereCourant == ':')
            {return getOPPAff();}
        if (caractereCourant == 'n')
            {return getNON();}
        if (caractereCourant == 't'|| caractereCourant == 'f' )
           return getBooleen();
        if (caractereCourant == ';')
            return new UniteLexicale(Categorie.PV, ";");


        if (caractereCourant == '<' || caractereCourant == '>' || caractereCourant == '=')
            return getOPPRel();
        if (caractereCourant == '+')
         return new UniteLexicale(Categorie.OpAr, "+");
        if (caractereCourant == '-')
         return new UniteLexicale(Categorie.OpAr, "-");
        if (caractereCourant == '*')
         return new UniteLexicale(Categorie.OpAr, "*");
        if (caractereCourant=='/')
         return new UniteLexicale(Categorie.OpAr, "/");
        if (caractereCourant == 'D')
            return getDo();
        if (caractereCourant == 'W')
            return getWhile();
        if (caractereCourant == 'w')
            return getWrite();
        if (caractereCourant == 'r')
            return getRead();
        if (caractereCourant == 'I')
            return getIf();
        if (caractereCourant == 'E')
            return getElse();

        if (caractereCourant == 'T')
            return getThen();
        if (caractereCourant == '&')
        return new UniteLexicale(Categorie.opLog, "et");
         if (caractereCourant == '|')
         return new UniteLexicale(Categorie.opLog, "ou");
         if (caractereCourant == '_')
         return getChaine();
         if (caractereCourant == '(')
         return new UniteLexicale(Categorie.po, "(");
         if (caractereCourant == ')')
         return new UniteLexicale(Categorie.pf, ")");
         if (caractereCourant == '{')
         return new UniteLexicale(Categorie.ao, "{");
         if (caractereCourant == '}')
         return new UniteLexicale(Categorie.af, "}");

        if (caractereCourant == 'i' || caractereCourant == 'F' || caractereCourant == 'B'|| caractereCourant == 'A' || caractereCourant == 'S'|| caractereCourant == 'C')
           { return getType();}
        if (Character.isLetter(caractereCourant))
        {
            
            return getID();}
       
     
    
       return null;
    }
    public UniteLexicale getID() 
    {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (Character.isLetter(caractereCourant))
                        sb.append(caractereCourant);
                    etat = 1;
                case 1:
                    caractereSuivant();
                    if (eof)
                        etat = 3;
                    else if (Character.isLetterOrDigit(caractereCourant))
                        sb.append(caractereCourant);
                    else
                        etat = 2;
                    break;
                case 2:
                    reculer();
                    return new UniteLexicale(Categorie.ID, sb.toString());
                case 3:
                    return new UniteLexicale(Categorie.ID, sb.toString());
            }
        }
    }
    public UniteLexicale getWrite()
    
        {
            int etat = 0;
            StringBuffer sb = new StringBuffer();
            while (true) {
                switch (etat) {
                    case 0:
                        if (caractereCourant == 'w') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 1;
                        }

                    case 1:
                    
                        if (caractereCourant == 'r') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 2;
                        }
                        
                        
                    case 2:
                         if (caractereCourant == 'i') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 3;
                        }
                    case 3:
                        if (caractereCourant == 't') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 4;
                        }
    
                        case 4:
                        if (caractereCourant == 'e') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 5;
                        }

                    case 5:
                        if (eof)
                            { 
                            return new UniteLexicale(Categorie.write, '0');}
                        else
                            reculer();
                        return new UniteLexicale(Categorie.write, '0');
    
                }

            }
    
        }
    public UniteLexicale getRead()
    
        {
            int etat = 0;
            StringBuffer sb = new StringBuffer();
            while (true) {
                switch (etat) {
                    case 0:
                        if (caractereCourant == 'r') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 1;
                        }
                    case 1:
                    
                        if (caractereCourant == 'e') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 2;
                        }
                        
                       
                        
                    case 2:
                         if (caractereCourant == 'a') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 3;
                        }
                    case 3:
                        if (caractereCourant == 'd') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 4;
                        }
    
                    case 4:
                        if (eof)
    
                            return new UniteLexicale(Categorie.read, '0');
                        else
                            reculer();
                        return new UniteLexicale(Categorie.read, '0');
    
                }
            }
    
        }
    public UniteLexicale getChaine() 
    {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    etat = 1;
                    break;
                case 1:
                    caractereSuivant();
                    if (eof)
                        etat = 3;
                    else if (Character.isLetterOrDigit(caractereCourant))
                        sb.append(caractereCourant);
                    else
                        etat = 2;
                    break;
                case 2:
                    reculer();
                    return new UniteLexicale(Categorie.Chaine, sb.toString());
                case 3:
                    return new UniteLexicale(Categorie.Chaine, sb.toString());
            }
        }
    }
    public UniteLexicale getElse() 
    {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == 'E') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;
                    }
                case 1:
                    if (eof)
                        break;
                    else if (caractereCourant == 'l') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                    }

                case 2:
                    if (eof)
                        break;
                    else if (caractereCourant == 's') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 3;
                    }
                case 3:
                    if (eof)
                        break;
                    else if (caractereCourant == 'e') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 4;
                    }
                case 4:
                    return new UniteLexicale(Categorie.Else, '0');

            }

        }
    }
    public UniteLexicale getDo() 
    {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == 'D') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;
                    }
                case 1:
                    if (eof)
                        break;
                    else if (caractereCourant == 'o') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                    }

                case 2:
                    if (eof)

                        return new UniteLexicale(Categorie.Do, '0');
                    else
                        reculer();
                    return new UniteLexicale(Categorie.Do, '0');


            }
        }
    }
    public UniteLexicale getNombre() 
    {
		int etat=0;
		StringBuffer sb=new StringBuffer();
		while(true) {
			switch(etat) 
            {
			case 0 : etat=1; 
					 sb.append(caractereCourant); 
					 break;
			case 1 : caractereSuivant();                   
					 if(eof)
						 etat=3;
					 else
						 if(Character.isDigit(caractereCourant)) 
							 sb.append(caractereCourant);
						 else
							 if (caractereCourant=='.')
							 {
								 sb.append(caractereCourant);
								 etat=4;
							 }
							 else				
								 etat=2;
					 break;
			case 2 : 
				reculer();
				return new UniteLexicale(Categorie.Entier, sb.toString());
					 
			case 3 : 
				return new UniteLexicale(Categorie.Entier, sb.toString());
			
			case 4 :
				caractereSuivant();
				if(eof)
					etat=5;
				else
					if(Character.isDigit(caractereCourant)) 
						sb.append(caractereCourant);
					else
						etat=6;
				break;
			
			case 5 : 
				return new UniteLexicale(Categorie.Reel, sb.toString());
				
			case 6 : 
				{
					reculer();
					return new UniteLexicale(Categorie.Reel,sb.toString());
				}
			}
		}
		
		}
    public UniteLexicale getIf() 
        {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == 'I') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;
                    }
                case 1:
                    if (eof)
                        break;
                    else if (caractereCourant == 'f') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                    }

                case 2:
                    if (eof)
                        return new UniteLexicale(Categorie.If, '0');

                    else
                        return new UniteLexicale(Categorie.If, '0');

            }

        }
    }
    public UniteLexicale getWhile() 
    {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (caractereCourant == 'W') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat =1;
                    }
                    case 1:
                    if (caractereCourant == 'h') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat =2;
                    }
                case 2:
                    if (caractereCourant == 'i') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat =3;
                    }
                case 3:
                    if (caractereCourant == 'l') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat =4;
                    }
                case 4:
                    if (caractereCourant == 'e') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat =5;
                    }


                case 5:
                    if (eof)

                        return new UniteLexicale(Categorie.While, '0');
                    else
                        reculer();
                    return new UniteLexicale(Categorie.While, '0');

            }
        }

    }   
    public UniteLexicale getOPPRel() {
        int etat = 0;
           StringBuffer sb = new StringBuffer();
           while (true) {
               switch (etat) {
                   case 0 :
                      if (caractereCourant == '=') {
                            sb.append(caractereCourant);
                            etat=1;}
                      
                      else if (caractereCourant == '>') {
                                   sb.append(caractereCourant);
                                   caractereSuivant();
                                   etat=2;}
                       
                      else {
                                   sb.append(caractereCourant);
                                   caractereSuivant();
                                   etat = 5;}
                      break;
                          
                   case 1 : 
                       return new UniteLexicale(Categorie.OppRel, "EGA"); 
                 
                   case 2: 
                        if(caractereCourant == '=')
                        {sb.append(caractereCourant);
                            etat=3;}
                        else
                            etat=4;
                        break;
                   case 3:
                        return new UniteLexicale(Categorie.OppRel, "PGE");
                   
                   case 4:
                      { reculer();
                       return new UniteLexicale(Categorie.OppRel, "PGQ");}
                       
                   case 5 :
                        if(caractereCourant == '=')
                            
                            {sb.append(caractereCourant);
                            etat=6;}
                        
                        else if (caractereCourant == '>')
                        {
                            sb.append(caractereCourant);
                            etat=7;
                        }
                        else 
                            etat=8;
                        break;
                   case 6 :
                       return new UniteLexicale(Categorie.OppRel, "PPE");
                   case 7 : 
                       return new UniteLexicale(Categorie.OppRel, "DIF");
                   case 8 : 
                      { reculer();
                       return new UniteLexicale(Categorie.OppRel, "PPQ"); }             	
                       
               }}}
    public UniteLexicale getThen() 
    {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == 'T') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;
                    }
                case 1:
                    if (eof)
                        break;
                    else if (caractereCourant == 'h') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                    }
                case 2:
                    if (eof)
                        break;
                    else if (caractereCourant == 'e') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 3;
                    }
                case 3:
                    if (eof)
                        break;
                    else if (caractereCourant == 'n') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 4;
                    }


                case 4:
                    if (eof)

                        return new UniteLexicale(Categorie.Then, '0');
                    else
                        reculer();
                    return new UniteLexicale(Categorie.Then, '0');



            }


        }
    }
    public UniteLexicale getOPPAff() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                   
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;
                        break;

                case 1:
                    if (eof || Character.isWhitespace(caractereCourant))
                    return new UniteLexicale(Categorie.dp, sb.toString());
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;

                    } else
                        break;

                case 2:
                    if (eof)
                        etat = 3;
                    else
                        etat = 4;
                case 3:

                    return new UniteLexicale(Categorie.OPPAff, sb.toString());
                case 4:
                    reculer();
                    return new UniteLexicale(Categorie.OPPAff, sb.toString());


            }

        }
    }
    public UniteLexicale getType() 
        {
           int etat = 0;
           StringBuffer sb = new StringBuffer();
          while (true)
            {
               switch (etat) {
                   case 0 :
                   
                      if (caractereCourant == 'i') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat=1;}
                            
                      
                      else if (caractereCourant == 'F') {
                                   sb.append(caractereCourant);
                                   caractereSuivant();
                                   etat=8;}
                     else if (caractereCourant == 'B') 
                     {
                                    sb.append(caractereCourant);
                                    caractereSuivant();
                                    etat=13;}
                     else if (caractereCourant == 'C') {
                                        sb.append(caractereCourant);
                                        caractereSuivant();
                                        etat=17;}
                     
                    else if (caractereCourant == 'S')
                     {
                                            sb.append(caractereCourant);
                                            caractereSuivant();
                                            etat=21;}                      
                                        
                    break;
                          
                   case 1 : 
                
                   if (caractereCourant == 'n') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=2;} 
                   break;
                   case 2: 
                   
                        if(caractereCourant == 't')
                        {sb.append(caractereCourant);
                            caractereSuivant();
                            etat=3;}
                        break;      
                   case 3:
                   if (caractereCourant == 'e') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=4;}
                   break;
                   case 4:
                  
                   if (caractereCourant == 'g') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=5;}
                    break;
                   case 5 :
                        if(caractereCourant == 'e')
                            
                            {sb.append(caractereCourant);
                                caractereSuivant();
                            etat=6;}
                        
                     break;     
                   case 6 :
                  if (caractereCourant == 'r') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=7;}
                    break;
                   case 7 : 
                       return new UniteLexicale(Categorie.Type, "integer");
                   case 8 : 
                   if (caractereCourant == 'l') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=9;}
                    break;  
                    case 9 : 
                   if (caractereCourant == 'o') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=10;}
                   break;
                    case 10 : 
                    if (caractereCourant == 'a') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=11;}
                    break;
                    case 11 : 
                    if (caractereCourant == 't') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=12;}
                    break;
                    case 12 : 
                    return new UniteLexicale(Categorie.Type, "Float");
                    case 13 : 
                    
                    if (caractereCourant == 'o') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=14;}
                    break;
                    case 14 : 
                    if (caractereCourant == 'o') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=15;}
                   break;
                    case 15 : 
                   if (caractereCourant == 'l') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=16;}
                    break;
                    case 16 : 
                     return new UniteLexicale(Categorie.Type, "Bool");
                    case 17 : 
                   if (caractereCourant == 'h') {
                     sb.append(caractereCourant);
                     caractereSuivant();
                     etat=18;}
                     break;
                    case 18 : 
                    if (caractereCourant == 'a') {
                      sb.append(caractereCourant);
                      caractereSuivant();
                      etat=19;}
                     break; 
                    case 19 : 
                    if (caractereCourant == 'r') {
                       sb.append(caractereCourant);
                       caractereSuivant();
                       etat=20;} 
                      break; 
                   case 20 : 
                       return new UniteLexicale(Categorie.Type, "Char");     
                    case 21 : 
                     if (caractereCourant == 't') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat=22;}
                      break;  
                    case 22 : 
                   if (caractereCourant == 'r') {
                         sb.append(caractereCourant);

                         caractereSuivant();
                         etat=23;}   
                         break;           
                    case 23 : 
                         return new UniteLexicale(Categorie.Type, "Str");



                }                  	
                       
            }
        }
    public UniteLexicale getNON() 
    {
        int etat = 0;
            StringBuffer sb = new StringBuffer();
            while (true)
            {
                switch (etat) {
                    case 0:
                        if (caractereCourant == 'n') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 1;
                        }
                        case 1:
                        if (caractereCourant == 'o') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 2;
                        }
                        case 2:
                        if (caractereCourant == 'n') {
                            sb.append(caractereCourant);
                            caractereSuivant();
                            etat = 3;
                        }
                        case 3:
                        if (eof)
                            { 
                            return new UniteLexicale(Categorie.NON, '0');}
                        else
                            reculer();
                        return new UniteLexicale(Categorie.NON, '0');




                }
            }
    } 
    public UniteLexicale getBooleen() 
    {  int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0 :
                if (caractereCourant == 't') {
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat =1;}
                    else if (caractereCourant == 'f') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat=5;}
                 case 1:
                   if (caractereCourant == 'r') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                    }
                    case 2:
                    if (caractereCourant == 'u') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 3;
                    }
                    case 3:
                    if (caractereCourant == 'e') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 4;
                    }
                    case 4:
                    if (eof)
                        { 
                        return new UniteLexicale(Categorie.Booleen, "true");}
                    else
                        reculer();
                    return new UniteLexicale(Categorie.Booleen, "true");
                    case 5:
                    if (caractereCourant == 'a') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 6;
                    }
                    case 6:
                    if (caractereCourant == 'l') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 7;
                    }
                    case 7:
                    if (caractereCourant == 's') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 8;
                    }
                    case 8:
                    if (caractereCourant == 'e') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 9;
                    }
                    case 9:
                    if (eof)
                        { 
                        return new UniteLexicale(Categorie.Booleen, "false");}
                    else
                        reculer();
                    return new UniteLexicale(Categorie.Booleen, "false");



    }
}} 
  
    @Override
    public String toString()
   {
       
        return fluxCaracteres.toString();
    }
}











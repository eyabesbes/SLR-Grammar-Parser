import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Parsernew {

//Remarque:  Dans ce programme, on notera epsilon '' ( la chaîne vide)

public String[] LRGS = {                                 
"D->ID dp Type PV D",
"D->I",         
"I->If po E pf Then ao I af I",
"I->If po E pf Then ao I af Else ao I af I", 
"I->While po E pf Do ao I af I" ,
"I->ID OPPAff terme PV I" ,
"I->write E PV I",
"I->read E PV I",
"I-> ",
"E->po E pf K",
"E->ao E af K",
"E->non po E pf K",
"E->terme K" ,
"E-> ",
"K->OpRel E",  
"K->OpAr E",
"K->opLog E",
"K->OPPAff E",
"K-> ",
"terme->Entier",
"terme->ID",
"terme->Booleen",
"terme->Reel",  
"terme->Chaine"

};




public String[][] tableSLR = 
 {{"etat/VT", "id","dp", "type", "pv", "if","po", "pf", "then","ao","af","else","while","do","oppaff","write","read","non","opprel","opar","oplog","entier","booleen","reel","chaine","eof","D","I","E","K","terme"},
 {"0","s2" ,"err","err","err","s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","1","3","err","err","err" },
 {"1","err","err","err","err","err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "ACC","err","err","err","err","err" },
 {"2","err","s8","err","err","err","err","err","err","err","err","err","err","s9","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"3","err","err","err","err","err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","r2","err","err","err","err","err" },
 {"4","err","err","err","err","err","s10","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
 {"5","err","err","err","err","err","s11","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
 {"6","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21","err","err","err","12","err","16" },
 {"7","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21","err","err","err","22","err","16" },
 {"8","err","err","s23","err","err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err"},
 {"9","s18","err","err","err","err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","s17","s19","s20","s21","err","err","err","err","err","24" },
 {"10","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21","err","err","err","25","err","16" },
 {"11","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21","err","err","err","26","err","16" },
 {"12","err","err","err","s27","err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err" },
 {"13","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21","err","err","err","28","err","16" },
 {"14","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21","err","err","err","29","err","16" },
 {"15","err","err","err","err","err","s30","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err" },
 {"16","err","err","err","r19","err","err","r19","err","err","r19","err","err","err","s35", "err","err","err","s32","s33","s34","err","err","err","err","err","err","err","err","31","err" },
 {"17","err","err","err","r20","err","err","r20","err","err","r20","err","err","err","r20", "err","err","err","r20","r20","r20","err","err","err", "err","err","err","err","err","err","err" },
 {"18","err","err","err","r21","err","err","r21","err","err","r21","err","err","err","r21", "err","err","err","r21","r21","r21","err","err","err","err", "err","err","err","err","err","err" },
 {"19","err","err","err","r22","err","err","r22","err","err","r22","err","err","err","r22", "err","err","err","r22","r22","r22","err","err","err","err", "err","err","err","err","err","err" },
 {"20","err","err","err","r23","err","err","r23","err","err","r23","err","err","err","r23", "err","err","err","r23","r23","r23","err","err","err","err", "err","err","err","err","err","err" },
 {"21","err","err","err","r24","err","err","r24","err","err","r24","err","err","err","r24", "err","err","err","r24","r24","r24","err","err","err","err", "err","err","err","err","err","err" },
 {"22","err","err","err","s36","err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"23","err","err","err","s37","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err" },
 {"24","err","err","err","s38","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err" },
 {"25","err","err","err","err","err","err","s39","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"26","err","err","err","err","err","err","s40","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"27","s42","err","err","err","s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","41","err","err","err" },
 {"28","err","err","err","err","err","err","s43","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"29","err","err","err","err","err","err","err","err","err","s44","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"30","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21", "err","err","err","45","err","16" },
 {"31","err","err","err","r13","err","err","r13","err","err","r13","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err" },
 {"32","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21", "err","err","err","46","err","16" },
 {"33","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21", "err","err","err","47","err","16" },
 {"34","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21", "err","err","err","48","err","16" },
 {"35","s18","err","err","r14","err","s13","r14","err","s14","r14","err","err","err","err", "err","err","s15","err","err","err","s17","s19","s20","s21", "err","err","err","49","err","16" },
 {"36","s42","err","err","err","s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","50","err","err","err" },
 {"37","s2","err","err","err","s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","51","3","err","err","err" },
 {"38","s42","err","err","err","s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","52","err","err","err" },
 {"39","err","err","err","err","err","err","err","s53","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err"},
 {"40","err","err","err","err","err","err","err","err","err","err","err","err","s54","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"41","err","err","err","err", "err","err","err","err","err","r7","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "r7","err","err","err","err","err" },
 {"42","err","err","err","err", "err","err","err","err","err","err","err","err","err","s9", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"43","err","err","err","r19", "err","err","r19","err","err","r19","err","err","err","s35", "err","err","err","s32","s33","s34","err","err","err","err", "err","err","err","err","55","err" },
 {"44","err","err","err","r19", "err","err","r19","err","err","r19","err","err","err","s35", "err","err","err","s32","s33","s34","err","err","err","err", "err","err","err","err","56","err" },
 {"45","err","err","err","err", "err","err","s57","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"46","err","err","err","r15", "err","err","r15","err","err","r15","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"47","err","err","err","r16", "err","err","r16","err","err","r16","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"48","err","err","err","r17", "err","err","r17","err","err","r17","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err" },
 {"49","err","err","err","r18", "err","err","r18","err","err","r18","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err" },
 {"50","err","err","err","err", "err","err","err","err","err","r8","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "r8","err","err","err","err","err" },
 {"51","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "r1","err","err","err","err","err" },
 {"52","err","err","err","err", "err","err","err","err","err","r6","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "r6","err","err","err","err","err" },
 {"53","err","err","err","err", "err","err","err","err","s58","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"54","err","err","err","err", "err","err","err","err","s59","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err"},
 {"55","err","err","err","r10", "err","err","r10","err","err","r10","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"56","err","err","err","r11", "err","err","r11","err","err","r11","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err"},
 {"57","err","err","err","r19", "err","err","r19","err","err","r19","err","err","err","s35", "err","err","err","s32","s33","s34","err","err","err","err", "err","err","err","err","60","err" },
 {"58","s42","err","err","err", "s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","61","err","err","err" },
 {"59","s42","err","err","err", "s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","62","err","err","err" },
 {"60","err","err","err","r12", "err","err","r12","err","err","r12","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"61","err","err","err","err", "err","err","err","err","err","s63","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err","err" },
 {"62","err","err","err","err", "err","err","err","err","err","s64","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"63","s42","err","err","err", "s4","err","err","err","err","r9","s66","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","65","err","err","err" },
 {"64","s42","err","err","err", "s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","67","err","err","err" },
 {"65","err","err","err","err", "err","err","err","err","err","r3","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "r3","err","err","err","err","err" },
 {"66","err","err","err","err", "err","err","err","err","s68","err","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"67","err","err","err","err", "err","err","err","err","err","r5","err","err","err","err", "err","err","err","err","err","err","err","err","err","err","r5","err","err","err","err","err" },
 {"68","s42","err","err","err", "s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","69","err","err","err" },
 {"69","err","err","err","err", "err","err","err","err","err","s70","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "err","err","err","err","err","err" },
 {"70","s42","err","err","err", "s4","err","err","err","err","r9","err","s5","err","err", "s6","s7","err","err","err","err","err","err","err","err", "r9","err","71","err","err","err" },
 {"71","err","err","err","err", "err","err","err","err","err","r4","err","err","err","err", "err","err","err","err","err","err","err","err","err","err", "r4","err","err","err","err","err" }};
 

     
    public Stack<String> stackState = new Stack<>();
    
    
    public Stack<String> analyse = new Stack<>();
    
    public Stack<String> stackSymbol = new Stack<>();
    public String strInput ;
    
   
    public String action = "";
    
    
    
    int index = 0;

    public void analyzeSLnew(String []tt) {
    	
        action = "";
        index = 0;
       
        analyse.push("0");
        
       
        System.out.println("********  pile     	    Entree            Action  ***********");
        this.AfficherSLRnew(tt);
    
       while(index<tt.length) 
        
        {
        	
    	   
          //  String s = stackState.peek();
            
            String s = analyse.peek();
            
          
            if (Action(s,tt[index]).charAt(0) == 's') {
            	
            	
                //stackState.push(Action(s, ch[index]).substring(1));
                //stackSymbol.push(ch[index]);
                
                analyse.push(tt[index]);
                analyse.push(Action(s, tt[index]).substring(1));
               
                
                
              
                index++;
                action = " shift ";
                
                AfficherSLRnew(tt);
            }
            // Réduction
            else if (Action(s,tt[index]).charAt(0) == 'r') {
                //
                String str = LRGS[Integer.parseInt(Action(s, tt[index]).substring(1)) - 1];
               
                String tabparties[]= str.split("->");
                
                
                String Partiegauche=tabparties[0];
               // System.out.println("Partiegauche"+Partiegauche); 
                
                String Partiedroite=tabparties[1];
                //System.out.println("Partiedroite"+Partiedroite); 
                
                String tabtoken[]= Partiedroite.split(" ");
                int taillepile= tabtoken.length +tabtoken.length;
               
               
                for (int i = 0; i < taillepile; i++) {
                	
                  
                    
                    analyse.pop();
                    
                }
                String sommetpile = analyse.peek();
                analyse.push(Partiegauche);
                
                analyse.push(Action(sommetpile, Partiegauche));
                
               
                action = " reduce: " + str;
                AfficherSLRnew(tt);
            } 
            //acceptation
            else if (Action(s,tt[index]).equals("ACC"))
            	{
            	System.out.println("analyze SLR successfull"); 
            	break;}
            	
            else
            	//erreur
            	{
            	
            	//System.out.println("texterreur"+Action(s,ch[index])+s+ch[index]+index); 
            	System.out.println(" analyze SLR failed "); 
        	break;
            	}
               
        }
        
    }
    
    
    
    
    public String Action(String s, String a) {
        for (int i = 1; i <74 ; i++)
            if (tableSLR[i][0].equals(s))
                for (int j = 1; j <32; j++)
                    if (tableSLR[0][j].equals(a))
                        return tableSLR[i][j];
        return "err";
    }

    public void AfficherSLRnew(String []tt) {
        //  SLR
    	
    	
    	String ss= "-------";
    	String ss1= "-------";
    	 int taillepile=analyse.size();
    	int taillepilediv2= taillepile /2;
         for(int i=0;i<taillepilediv2;i++)
     		ss=ss + "-------" ;
         int tailleinput=tt.length;
         for(int i=0;i<tailleinput;i++)
     		ss1=ss1 + "-------" ;
    	
    	
    	
        
       
        strInput=" ";
        for(int i=index; i<tt.length;i++)
        	strInput= strInput+ tt[i];
       
        System.out.printf("%s", analyse +" " + ss1);
        System.out.printf("%s", strInput+" "+ ss);
        System.out.printf("%s", action);
        System.out.println();
    }

    public void ouput() {
        
        
        System.out.println("**********Tableau SLR¨********");

        for (int i = 0; i < 74 ; i++) {
            for (int j = 0; j <32; j++) {
                System.out.printf("%6s", tableSLR[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("**********Fin tableau SLR********");

    }
    
   
 

    
    
    
    
    

}



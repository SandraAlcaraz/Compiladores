import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class lexico {
	
	
	protected void anlisisLexico(File selectedFile) {
        String textFile = "";
        
        try {
            FileReader fr = new FileReader(selectedFile);
            FileInputStream fis = new FileInputStream(selectedFile);
            ArrayList<token> myArrayList = new ArrayList<token>();
            boolean asig;
            char current;
            boolean state=false;
            boolean first=true; 
            String tok="";
            String tipo;
            int numline=1;
            token t=new token();
            int countcharacter=0;
            while (fis.available() > 0) {
              current = (char) fis.read();
              countcharacter++; //modificar para que sólo cuente desde el principio
              if((current=='('||current==')'||current==';'||current==','||current=='!')){
            	 
            	  if(!tok.isEmpty()){
            		  tipo=automata(tok);
                	  t= new token(tok, tipo,numline, (countcharacter-1)); //lo anterior al 
                	 // System.out.println(t.toString());
                	  myArrayList.add(t);
                	  tipo=automata(Character.toString(current));
                	  t= new token(Character.toString(current), tipo,numline, (countcharacter));
                	 // System.out.println(t.toString());
                	  myArrayList.add(t);
                	  tok="";
            	  }
            	  else{
            		  tipo=automata(Character.toString(current));
                	  t= new token(Character.toString(current), tipo,numline, (countcharacter));
                	 // System.out.println(t.toString());
                	  myArrayList.add(t);
                	  tok="";  
            	  }
            	  
            	  
              }
              else if((current==' '||current=='\n'|| current=='\r'||current=='\t')){
            	  if(current=='\n'){
            			numline++;  
            	  }
            	  if (!first){
            		  if(tok.isEmpty()){
            			  
            		  }
            		  else{
            			  tipo=automata(tok);
                    	  t= new token(tok, tipo,numline, (countcharacter-1)); //lo anterior al 
                    	 
                    	  myArrayList.add(t);
                    	  tok="";	  
            		  }
            		  
   
            	  }  
              }else{
            	  tok=tok+current;
            	  first=false;
            	  
            	  if(state){
            		  
            		 // System.out.print(" ");
            	  }
            	  state=false;
            	  //System.out.print(current);
              }
       
            }
            tipo=automata(Character.toString('}'));
      	  t= new token(Character.toString('}'), tipo,numline, (countcharacter));
            myArrayList.add(t);
            fr.close();
            
           System.out.println( myArrayList.toString());
           
           System.out.println("TABLA DE IDENTIFICADORES");
           ArrayList<String> aRLidentificadores = new ArrayList<String>();
           Set<String> hs = new HashSet<>();
           
        for(token x :myArrayList){
        	if(x.getTipo()=="identificador"){
        		hs.add(x.getContenido());
        		
        	}
        }
        System.out.println( hs.toString());
           
        } catch (Exception error) {
            error.printStackTrace();
        }

        System.out.println(textFile);
    }
	
	public static String automata(String str){
		int ascii;
		
			ascii=(int)str.toCharArray()[0];
			if(ascii>96&&ascii<123){
				return identificador(str);
			}
			else if(ascii>47&&ascii<58){
				return numeros(str);
			}
			else if(ascii==44){
				//coma
				return "coma";
			}
			else if(ascii==40||ascii==41){
				//parentesis
				return "parentesis";
			}
			else if(ascii==123||ascii==125){
				//llaves {
				return "llave";
			}
			else if(ascii==59){
				//punto y coma
				return "punto y coma";
			}
			else if(ascii==38||ascii==124){
				//logicos  & | 
				return "logicos";
			}
			else if(ascii==33){
				//not !
				return "not";
			}
			else if(ascii==42||ascii==43||ascii==45||ascii==47||ascii==94){
				return "aritmetico";
			}
			else if(ascii==60||ascii==62){
				//> <
				return "relacionales";
			}
			else if(ascii==61){
				//=
				
				return iguales(str);
				//*completar
			}
		
		
		return "error";
	}
	
	public static String identificador(String x){
		ArrayList<String> palabrasReservadas=new ArrayList<String>();
        
        palabrasReservadas.add("int");
        palabrasReservadas.add("void");
        palabrasReservadas.add("else");
        palabrasReservadas.add("if");
        palabrasReservadas.add("while");
        palabrasReservadas.add("return");
       
        
        if(palabrasReservadas.contains(x)){
        	return "palabra reservada";
        }
		int ascii;
		
		for(char c: x.toCharArray()){
			ascii=(int)c;
			if(!(ascii>96&&ascii<123||(ascii>64&&ascii<91)||(ascii>47&&ascii<58))){
				return "error identificador";
			}
			}
		
		return "identificador";
	}
	
	
	public static String iguales(String str){
		int ascii1=(int) str.toCharArray()[0];
		
		if(ascii1==61&&str.length()==1){
			return "asignacion";
		}
		int ascii2=(int) str.toCharArray()[1];
		if(ascii2==61&&str.length()==2){
			return "relacionales";
		}
		
		return "error";
		
		
	}
	
	public static String numeros (String x){ 
		int ascii;
		boolean bandera=false;
		for(char c: x.toCharArray()){
			ascii=(int)c;
			if(ascii==46&&bandera){
				//si es igual a "."
				return "error doble punto"; //error doble punto
			}
			if(ascii==46){
				bandera=true;		
			}
			else if(!((ascii>47&&ascii<58))){
				return "caracter no permitido";	//error caracter se sale de lo permitido
			}
		}
		if(bandera==true){
			return "real"; //punto decimal correcto
		}
		else{
			return "entero"; //numero entero aceptado
		}
	}

   
}


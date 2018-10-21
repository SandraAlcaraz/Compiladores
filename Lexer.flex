package analizadorlexico;

import static analisador.Token.*;
%%
%class Lexer
%type Token

L = [a-zA-Z_]
D = [0-9]
WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/*Ignore*/}



( "+" | "-" | "*" | "/" | "%" )    {lexeme = yytext(); return OPERADOR_ARITMETICO;}


("&&" | "||" | "!" | "&" | "|" )    {lexeme = yytext(); return OPERADOR_LOGICO;}


(">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>")   {lexeme = yytext(); return OP_RELACIONAL;}


("+=" | "-="  | "*=" | "/=" | "%=" | "++" | "--" | "=")      {lexeme = yytext(); return OP_ATRIBUTO;}


(true | false)      {lexeme=yytext(); return OP_BOOLEANO;}


("(" | ")" | "{" | "}" | "[" | "]" | ";" | "," |  "." |  "\"")      {lexeme = yytext(); return SEPARADOR;}


/*(\b | "\t" | "\n" | "\f")   {lexeme = yytext(); return ESPECIAL;} */


( "//" | "/*" | "*/")     {lexeme = yytext(); return COMENTARIO;}


(private | protected | public | abstract | class | extends | final
 | implements | interface | void | main | native | new | static | strictfp | synchronized
 | transient | volatile | break | case | continue | default | do | else
 | for | if | instanceof | return | switch | while | assert | catch | finally
 | throw | throws | try | import | package |
 | long | super | this | void | const | goto 
 | null | String ) {lexeme = yytext(); return PALABRA;}

(int | char | boolean | double | byte | float | short ){lexeme = yytext();return VAR;}


{L}({L}|{D})* {lexeme=yytext(); return ID;}
 ("(-"{D}+")")|{D}+ {lexeme=yytext(); return NUMERO;}
({VAR}|{ID}";")|({VAR}|{ID}"="+";") {lexeme= yytext(); return VARIABLE}

. {return ERROR;}

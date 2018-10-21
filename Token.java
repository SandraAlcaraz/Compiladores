/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

/**
 *
 * @author Alberto
 */
public enum Token {
    PALABRA,OPERADOR_ARITMETICO, OPERADOR_LOGICO,OP_ATRIBUTO, OP_RELACIONAL, OP_BOOLEANO, SEPARADOR,
    ESPECIAL, COMENTARIO, ID, INT,NUMERO, ERROR, VAR, VARIABLE 
}
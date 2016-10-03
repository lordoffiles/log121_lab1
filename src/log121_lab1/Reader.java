/*
 * Cours: LOG121
 * Session: A2016
 * Groupe: 02
 * Projet: Laboratoire 1
 * Étudiant(e)(s): Vincent Roy
 * Professeur: Vincent Lacasse
 * Num du fichier: Reader.java
 * Date création: 18-09
 * Date dern. modif.: 29-10
 */
package log121_lab1;
import java.util.ArrayList;

/**
 * Interface for reading and processing strings
 * @author Vincent Roy
 * @date 29/09/2015
 *
 */
public interface Reader {
	
	public ArrayList<String> split(String input, String splitPoint);
	
	public ArrayList<String> split(boolean b, String input, String...splitPoints);
	
	public String findTag(String input);
	

}

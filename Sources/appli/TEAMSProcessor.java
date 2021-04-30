package appli;

import java.io.File;
import java.util.*;

public class TEAMSProcessor {

    private Collection<People> _allpeople = null;
    private String _fileName;
    private String _startTime;
    private String _endTime;


    /*  Pour la question 5 nous avons utilisé le design Pattern strategy et le Design Pattern Factory*/

    //Ces deux instanciation sont utilisé pour le Design Pattern  strategy pour le tri par Id et Nom
    // ContextStrategyTri contextStrategyTriName = new ContextStrategyTri(new TriByName());
    //ContextStrategyTri contextStrategyTriId = new ContextStrategyTri(new TriById());

    // Voici les intanciations faites pour le Design Pattern Factory pour le tri par ID et Nom
    TriFactory triFactory = new TriFactory();
    InterfaceTri TriByName = triFactory.getTypetri("TriByName");
    InterfaceTri TriById = triFactory.getTypetri("TriById");


    public TEAMSProcessor(File _file, String _start, String _stop) {

        /*
         csv file to read
         start time of the course
         end time of the source
        */
        this._startTime = _start;
        this._endTime = _stop;

        // load CSV file
        this._fileName = _file.getName();

        var teamsFile = new TEAMSAttendanceList(_file);

        //Si le choix du nombre est 2 alors le bouton radio trie par id est appuyé et on aura un trie par nom
        if(MainController.choice==2)
        {

            this._allpeople= TriById.Tri(_file,_start,_stop);
        }
        //Sinon si le choix du nombre est 1 ou autre  alors  on aura un trie par nom
        else
        {
            this._allpeople = TriByName.Tri(_file,_start,_stop);

        }





    }

    public Collection<People> get_allpeople() {
        return _allpeople;
    }

    public String toHTMLCode() {

        String html = "<!DOCTYPE html> \n <html lang=\"fr\"> \n <head> \n <meta charset=\"utf-8\"> ";
        html += "<title> Attendance Report </title> \n <link rel=\"stylesheet\" media=\"all\" href=\"visu.css\"> \n";
        html += "</head> \n <body> \n ";
        html += "<h1> Rapport de connexion </h1>\n" +
                "\n" +
                "<div id=\"blockid\">\n" +
                "<table>\n" +
                "\t<tr>\n" +
                "\t\t<th> Date : </th>\n" +
                "\t\t<td> " + this._allpeople.iterator().next().getDate() + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Heure début : </th>\n" +
                "\t\t<td> " + MainController.datedeb+ " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Heure fin : </th>\n" +
                "\t\t<td> " + MainController.datefin + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Cours : </th>\n" +
                "\t\t<td>"+MainController.libelle+"</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Fichier analysé : </th>\n" +
                "\t\t<td> " + this._fileName + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Nombre de connectés : </th>\n" +
                "\t\t<td> " + this._allpeople.size() + "  </td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "\n" +
                "<h2> Durées de connexion</h2>\n" +
                "\n" +
                "<p> Pour chaque personne ci-dessous, on retrouve son temps total de connexion sur la plage déclarée du cours, ainsi qu'un graphe qui indique les périodes de connexion (en vert) et d'absence de connexion (en blanc). En pointant la souris sur une zone, une bulle affiche les instants de début et de fin de période. \n" +
                "</p>";
        html += "<div id=\"blockpeople\"> ";



        //On a remplacé ici le parcours de collection par un itérator

        Iterator<People> iterator = this._allpeople.iterator();
        while(iterator.hasNext())
        {
            html += iterator.next().getHTMLCode();
        }

        /*
        for (People people : this._allpeople) {

            html += people.getHTMLCode();
        }*/

	    html += "</div> \n </body> \n </html>";
        return html;
    }
}

package appli;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainController<stactic> {

    @FXML
    public Rectangle dragfile;

    @FXML
    public  javafx.scene.control.RadioButton trieidradiobutton;

    @FXML
    public Label labelfilename;

    @FXML
    public   javafx.scene.control.ToggleGroup triesortie = new javafx.scene.control.ToggleGroup() ;


    @FXML
    public  javafx.scene.control.RadioButton trienomradiobutton  ;

    @FXML
    public  javafx.scene.control.CheckBox checkboxName;

    @FXML
    public TextField LibelleTextField;

    @FXML
    public javafx.scene.control.CheckBox checkboxPlanninng;

    @FXML
    public javafx.scene.control.CheckBox checkboxId;

    @FXML
    public DatePicker dateheuredebut= new DatePicker(LocalDate.now());

    @FXML
    public DatePicker dateheurefin = new DatePicker(LocalDate.now());


    File selectedFile;

    public static int choice;

    public  static String filtreId,filtreNAme,filtrePlanning;

    public static String libelle,datedeb,datefin;







    public void sayHelloWorld(ActionEvent actionEvent) {

        RadioSelected();
        CheckBoxSelected();

        //Pour les dates
        libelle = LibelleTextField.getText();

        LocalDate valuedeb = dateheuredebut.getValue();

        Instant instantdeb = Instant.from(valuedeb.atStartOfDay(ZoneId.systemDefault()));
        Date datestart = Date.from(instantdeb);
        datedeb = datestart.toString();


        LocalDate valuefin= dateheurefin.getValue();

        Instant instantfin = Instant.from(valuefin.atStartOfDay(ZoneId.systemDefault()));
        Date dateend = Date.from(instantfin);
        datefin = dateend.toString();


        // process the file, and limit periods to given time interval
        var teamsProcessor = new TEAMSProcessor(selectedFile,"19/01/2021 à 10:15:00", "19/01/2021 à 11:45:00");

/*
        var allpeople = teamsProcessor.get_allpeople();
        for (People people : allpeople) {
            System.out.println( people );
        }



*/

        try {
            FileWriter myWriter = new FileWriter("index.html");
            myWriter.write(teamsProcessor.toHTMLCode());
            myWriter.close();  File htmlFile = new File("index.html");
            Desktop.getDesktop().browse(htmlFile.toURI()); }
        catch (IOException e) { // TODO Auto-generated catch block  e.printStackTrace();
             }
            System.out.println(teamsProcessor.toHTMLCode());


        }

    @FXML
    public void handleDragOver(DragEvent dragEvent) {
        if(dragEvent.getDragboard().hasFiles())
        {
            dragEvent.acceptTransferModes(TransferMode.ANY);

        }


    }

    @FXML
    public void handleDrop(DragEvent dragEvent) {
           //List<File> files = dragEvent.getDragboard().getFiles();


            Dragboard db = dragEvent.getDragboard();
            File file = db.getFiles().get(0);
            selectedFile = file;
            String nom = null;
            nom = file.getName();
            boolean success = false;
            if (db.hasFiles()) {
                labelfilename.setText(nom);
                success = true;
            }


        /* let the source know whether the string was successfully
             * transferred and used */
            dragEvent.setDropCompleted(success);

            dragEvent.consume();
        }




        //Trie avec Les RadioButton
        public void RadioSelected()
        {
            trieidradiobutton.setToggleGroup(triesortie);
            trieidradiobutton.setToggleGroup(triesortie);

            if(trienomradiobutton==triesortie.getSelectedToggle())
            {
                choice =1;
            }
            if(trieidradiobutton==triesortie.getSelectedToggle())
            {
                choice=2;
            }
        }



        // Filtres avec les CheckBox
        public void CheckBoxSelected()
        {
            if(checkboxName.isSelected())
            {
                filtreNAme = "sans_nom";

            }
            if(checkboxId.isSelected())
            {
                filtreId = "sans_id";
            }

            if(checkboxPlanninng.isSelected())
            {
                filtrePlanning = "sans_planning";
            }

            if(!checkboxName.isSelected())
            {
                filtreNAme = "with_nom";

            }
            if(!checkboxId.isSelected())
            {
                filtreId = "with_id";
            }

            if(!checkboxPlanninng.isSelected())
            {
                filtrePlanning = "with_planning";

            }
        }


}

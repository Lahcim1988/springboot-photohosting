package com.company.springbootphotohosting.gui;

import com.company.springbootphotohosting.ImageUpader;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

// Ostatnim etapem jest przygotowanie GUI, które umożliwi zapis i odczyt danych obrazków.
// Tu jest pełna dobrowolność. Ja moja propozycja wygląda następująco:

@Route("upload")        // musi byc to jest nazocone przez specyfikacje springa
public class UploadGui extends VerticalLayout {

    private ImageUpader imageUpader;

    @Autowired
    public UploadGui(ImageUpader imageUpader) {
        this.imageUpader = imageUpader;

        Label label = new Label();

        TextField textField = new TextField();      // bede dodawac sciezke
        Button button = new Button("upload");     // klikanie buttona ktory uploaduje
        button.addClickListener(clickEvent ->       // w momencie przycisniecia, to wywolam metode uloadFile ze sciezka ktora podana jest w textField
        {
            String uploadedImage = imageUpader.uploadFileAndSaveToDb(textField.getValue());      //1. wrzocam obrazek, 2. chce dac informacje do uzytkownika ze udalo ci sie go wrzocic
            Image image = new Image(uploadedImage, "nie ma obrazka :(");           // alternative text
            label.setText("Udalo sie wrzucic obrazek!!!");
            add(label);     // dodaje komunikat
            add(image);     // dodajemy obrazek
        });

        add(textField);         // dodaje kontroli zeby byly widoczne na moim widoku
        add(button);
    }

}

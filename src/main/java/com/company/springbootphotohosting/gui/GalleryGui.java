package com.company.springbootphotohosting.gui;

import com.company.springbootphotohosting.ImageUpader;
import com.company.springbootphotohosting.model.Image;
import com.company.springbootphotohosting.repo.ImageRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

    private ImageRepo imageUpader;

    @Autowired
    public GalleryGui(ImageRepo imageUpader) {
        this.imageUpader = imageUpader;

        List<Image> all = imageUpader.findAll();           // biore wszystkie obrazki, wszystkie elementy w bazie danych

        all.stream().forEach(element -> {     // dla kazdego elementu w mojej liscie, bede tworzyl nowy element Obiekty typu Image vaddin
            com.vaadin.flow.component.html.Image image =
                    new com.vaadin.flow.component.html.Image(element.getImageAdress(), "brak");
            add(image);     // dodaje te wszystkie elementy do GUI VerticalLayout

        });
    }
}
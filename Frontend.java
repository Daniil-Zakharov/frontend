package ru.nntu.Git.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nntu.Git.backend.Backend;

@Route("")
@PageTitle("Start")
public class Frontend extends VerticalLayout {
    private final TextField input = new TextField("", "Type here...");
    private final Button inputBtn = new Button("Save", VaadinIcon.CHECK.create());

    @Autowired
    private Backend backend;


    @Autowired
    public Frontend() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setHeight("100%");
        layout.setAlignItems(Alignment.CENTER);
        layout.add(input, inputBtn);

        inputBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        inputBtn.addClickListener(e -> {
            backend.write(input.getValue());
            input.setValue("");
        });

        add(layout);
        setHeight("100%");
        setAlignItems(Alignment.CENTER);
        getElement().setAttribute("theme", Lumo.DARK);
    }
}

package ru.nntu.Git.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
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
    private final Button viewBtn = new Button("Show");
    private TextArea textArea = new TextArea();

    @Autowired
    private Backend backend;


    @Autowired
    public Frontend() {
        HorizontalLayout upper_layout = new HorizontalLayout();
        HorizontalLayout lower_layout = new HorizontalLayout();

        upper_layout.setHeight("100%");
        upper_layout.setAlignItems(Alignment.CENTER);

        lower_layout.setHeight("100%");
        lower_layout.setAlignItems(Alignment.CENTER);

        inputBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        inputBtn.addClickListener(e -> {
            backend.write(input.getValue());
            input.setValue("");
        });
        viewBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        viewBtn.addClickListener(e -> textArea.setValue(backend.read()));

        textArea.setWidth("300px");
        textArea.setLabel("Output");
        textArea.setValue("");
        textArea.setReadOnly(true);

        upper_layout.add(input, inputBtn, viewBtn);
        lower_layout.add(textArea);

        add(upper_layout, lower_layout);
        setHeight("100%");
        setAlignItems(Alignment.CENTER);
        getElement().setAttribute("theme", Lumo.DARK);
    }
}

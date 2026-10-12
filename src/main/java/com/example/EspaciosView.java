package com.example;

import com.example.model.Espacio;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("espacios") // URL en el navegador: http://localhost:8080/espacios
public class EspaciosView extends VerticalLayout {

    // 1. Tabla para mostrar la lista de espacios (Operación Read)
    private Grid<Espacio> grid = new Grid<>(Espacio.class);

    // 2. Campos del formulario con tipos de datos correctos (Validación de la rúbrica)
    private TextField txtNombre = new TextField("Nombre del Espacio");
    private IntegerField txtCapacidad = new IntegerField("Capacidad (personas)");
    private NumberField txtPrecio = new NumberField("Precio por Hora ($)");
    private TextField txtEstado = new TextField("Estado");

    // 3. Botones de acción (Crear / Actualizar / Eliminar)
    private Button btnGuardar = new Button("Guardar");
    private Button btnEliminar = new Button("Eliminar");
    private Button btnLimpiar = new Button("Limpiar");

    public EspaciosView() {
        // Configuración básica del contenedor de la pantalla
        setSizeFull();
        add(new H2("Gestión de Espacios de Coworking"));

        // Aplicar temas visuales de Vaadin a los botones
        btnGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnEliminar.addThemeVariants(ButtonVariant.LUMO_ERROR);

        // Agrupar los botones de la interfaz
        HorizontalLayout acciones = new HorizontalLayout(btnGuardar, btnEliminar, btnLimpiar);

        // Organizar los campos en el formulario
        FormLayout formulario = new FormLayout();
        formulario.add(txtNombre, txtCapacidad, txtPrecio, txtEstado, acciones);

        // Disponer la tabla a la izquierda y el formulario a la derecha
        HorizontalLayout mainContent = new HorizontalLayout(grid, formulario);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(mainContent);
    }
}
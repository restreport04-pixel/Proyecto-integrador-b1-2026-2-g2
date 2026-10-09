package com.example;

import com.example.model.Reserva; // Importación del POJO de la capa de datos
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("reservas") // URL en el navegador: http://localhost:8080/reservas
public class ReservasView extends VerticalLayout {

    // 1. Tabla para listar todas las reservas registradas (Operación Read)
    private Grid<Reserva> grid = new Grid<>(Reserva.class);

    // 2. Campos del formulario con validación nativa de tipos de datos
    private IntegerField txtUsuarioId = new IntegerField("ID del Usuario");
    private IntegerField txtEspacioId = new IntegerField("ID del Espacio");
    private DateTimePicker txtFechaInicio = new DateTimePicker("Fecha y Hora de Inicio");
    private DateTimePicker txtFechaFin = new DateTimePicker("Fecha y Hora de Fin");
    private NumberField txtCostoTotal = new NumberField("Costo Total ($)");
    private TextField txtEstado = new TextField("Estado de Reserva");

    // 3. Botones de acción CRUD
    private Button btnGuardar = new Button("Guardar Reserva");
    private Button btnEliminar = new Button("Eliminar Reserva");
    private Button btnLimpiar = new Button("Limpiar Formulario");

    public ReservasView() {
        // Configuración visual de la pantalla
        setSizeFull();
        add(new H2("Gestión de Reservas de Coworking"));

        // Marcar campos obligatorios para prevenir inconsistencias
        txtUsuarioId.setRequired(true);
        txtEspacioId.setRequired(true);

        // Aplicar estilos estandarizados de Lumo a los botones
        btnGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnEliminar.addThemeVariants(ButtonVariant.LUMO_ERROR);

        // Agrupar botones de la interfaz
        HorizontalLayout acciones = new HorizontalLayout(btnGuardar, btnEliminar, btnLimpiar);

        // Disposición de los componentes en el formulario
        FormLayout formulario = new FormLayout();
        formulario.add(
            txtUsuarioId,
            txtEspacioId,
            txtFechaInicio,
            txtFechaFin,
            txtCostoTotal,
            txtEstado,
            acciones
        );

        // Disposición lado a lado (Tabla a la izquierda, Formulario a la derecha)
        HorizontalLayout mainContent = new HorizontalLayout(grid, formulario);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(mainContent);
    }
}

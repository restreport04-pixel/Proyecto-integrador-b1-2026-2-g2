package com.example;

import com.example.model.Usuario; 
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("usuarios") // URL en el navegador: http://localhost:8080/usuarios
public class UsuariosView extends VerticalLayout {

    // 1. Tabla para listar la información de los usuarios (Operación Read)
    private Grid<Usuario> grid = new Grid<>(Usuario.class);

    // 2. Campos del formulario con componentes específicos para validación de datos
    private TextField txtNombre = new TextField("Nombre Completo");
    private TextField txtCedula = new TextField("Cédula / Documento");
    private EmailField txtEmail = new EmailField("Correo Electrónico"); // Valida automáticamente el formato de correo
    private TextField txtTelefono = new TextField("Teléfono");
    private TextField txtRol = new TextField("Rol / Tipo de Usuario");

    // 3. Botones de acción CRUD
    private Button btnGuardar = new Button("Guardar Usuario");
    private Button btnEliminar = new Button("Eliminar");
    private Button btnLimpiar = new Button("Limpiar Formulario");

    public UsuariosView() {
        // Configuración visual de la pantalla
        setSizeFull();
        add(new H2("Gestión de Usuarios del Coworking"));

        // Configuración de validaciones estéticas y requeridas
        txtNombre.setRequired(true);
        txtEmail.setErrorMessage("Ingrese un correo válido (ejemplo@dominio.com)");

        // Aplicar temas visuales Lumo a los botones
        btnGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnEliminar.addThemeVariants(ButtonVariant.LUMO_ERROR);

        // Disposición de botones
        HorizontalLayout acciones = new HorizontalLayout(btnGuardar, btnEliminar, btnLimpiar);

        // Organizar los campos en el formulario
        FormLayout formulario = new FormLayout();
        formulario.add(txtNombre, txtCedula, txtEmail, txtTelefono, txtRol, acciones);

        // Distribuir la tabla a la izquierda y el formulario a la derecha
        HorizontalLayout mainContent = new HorizontalLayout(grid, formulario);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(mainContent);
    }
}
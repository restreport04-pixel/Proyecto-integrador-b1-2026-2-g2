package com.example;

import com.example.dao.UsuarioDao;
import com.example.model.Usuario;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final UsuarioDao usuarioDao;
    private final Grid<Usuario> grid = new Grid<>(Usuario.class, false);
    private final TextField txtNombre = new TextField("Nombre");
    private final TextField txtEmail = new TextField("Email");
    private Usuario usuarioSeleccionado;

    public MainView(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;

        setSizeFull();
        configureGrid();
        configureForm();
        refreshGrid();
    }

    private void configureGrid() {
        grid.addColumn(Usuario::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Usuario::getNombre).setHeader("Nombre");
        grid.addColumn(Usuario::getEmail).setHeader("Email");
        grid.setSizeFull();

        grid.asSingleSelect().addValueChangeListener(e -> {
            usuarioSeleccionado = e.getValue();
            if (usuarioSeleccionado != null) {
                txtNombre.setValue(usuarioSeleccionado.getNombre());
                txtEmail.setValue(usuarioSeleccionado.getEmail());
            } else {
                limpiarFormulario();
            }
        });
    }

    private void configureForm() {
        Button btnGuardar = new Button("Guardar", e -> guardar());
        btnGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button btnActualizar = new Button("Actualizar", e -> actualizar());
        Button btnEliminar = new Button("Eliminar", e -> eliminar());
        btnEliminar.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button btnLimpiar = new Button("Limpiar", e -> limpiarFormulario());

        HorizontalLayout form = new HorizontalLayout(txtNombre, txtEmail, btnGuardar, btnActualizar, btnEliminar, btnLimpiar);
        form.setVerticalComponentAlignment(Alignment.END, btnGuardar, btnActualizar, btnEliminar, btnLimpiar);

        add(form, grid);
    }

    private void guardar() {
        if (validar()) {
            Usuario u = new Usuario(null, txtNombre.getValue(), txtEmail.getValue());
            usuarioDao.guardar(u);
            Notification.show("Usuario guardado con éxito");
            limpiarFormulario();
            refreshGrid();
        }
    }

    private void actualizar() {
        if (usuarioSeleccionado == null) {
            Notification.show("Selecciona un usuario de la tabla");
            return;
        }
        if (validar()) {
            usuarioSeleccionado.setNombre(txtNombre.getValue());
            usuarioSeleccionado.setEmail(txtEmail.getValue());
            usuarioDao.actualizar(usuarioSeleccionado);
            Notification.show("Usuario actualizado con éxito");
            limpiarFormulario();
            refreshGrid();
        }
    }

    private void eliminar() {
        if (usuarioSeleccionado == null) {
            Notification.show("Selecciona un usuario de la tabla");
            return;
        }
        usuarioDao.eliminar(usuarioSeleccionado.getId());
        Notification.show("Usuario eliminado con éxito");
        limpiarFormulario();
        refreshGrid();
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtEmail.clear();
        usuarioSeleccionado = null;
        grid.asSingleSelect().clear();
    }

    private boolean validar() {
        if (txtNombre.isEmpty() || txtEmail.isEmpty()) {
            Notification.show("Todos los campos son obligatorios");
            return false;
        }
        return true;
    }

    private void refreshGrid() {
        grid.setItems(usuarioDao.obtenerTodos());
    }
}
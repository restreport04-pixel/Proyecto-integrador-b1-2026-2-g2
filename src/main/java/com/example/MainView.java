package com.example;

import com.example.dao.UsuarioDao;
import com.example.model.Usuario;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final UsuarioDao usuarioDao;
    private final Grid<Usuario> grid = new Grid<>(Usuario.class, false);
    private final TextField nombre = new TextField("Nombre");
    private final EmailField email = new EmailField("Email");
    private final Binder<Usuario> binder = new BeanValidationBinder<>(Usuario.class);
    private Usuario usuarioSeleccionado;

    @SuppressWarnings("this-escape")
    public MainView(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;

        binder.bindInstanceFields(this);

        grid.addColumn(Usuario::getId).setHeader("ID");
        grid.addColumn(Usuario::getNombre).setHeader("Nombre");
        grid.addColumn(Usuario::getEmail).setHeader("Email");

        grid.asSingleSelect().addValueChangeListener(e -> {
            usuarioSeleccionado = e.getValue();
            if (usuarioSeleccionado != null) {
                binder.readBean(usuarioSeleccionado);
            } else {
                limpiarFormulario();
            }
        });

        Button guardarBtn = new Button("Guardar", e -> guardarUsuario());
        guardarBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button eliminarBtn = new Button("Eliminar", e -> eliminarUsuario());
        eliminarBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button limpiarBtn = new Button("Limpiar", e -> limpiarFormulario());

        HorizontalLayout form = new HorizontalLayout(nombre, email, guardarBtn, eliminarBtn, limpiarBtn);
        form.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        add(form, grid);
        actualizarGrid();
    }

    private void guardarUsuario() {
        Usuario usuario = (usuarioSeleccionado != null) ? usuarioSeleccionado : new Usuario();
        if (binder.writeBeanIfValid(usuario)) {
            if (usuario.getId() == null) {
                usuarioDao.guardar(usuario);
                Notification.show("Usuario guardado con éxito");
            } else {
                usuarioDao.actualizar(usuario);
                Notification.show("Usuario actualizado con éxito");
            }
            limpiarFormulario();
            actualizarGrid();
        } else {
            Notification.show("Por favor, corrige los errores en el formulario");
        }
    }

    private void eliminarUsuario() {
        if (usuarioSeleccionado != null) {
            usuarioDao.eliminar(usuarioSeleccionado.getId());
            Notification.show("Usuario eliminado");
            limpiarFormulario();
            actualizarGrid();
        } else {
            Notification.show("Selecciona un usuario de la lista");
        }
    }

    private void limpiarFormulario() {
        usuarioSeleccionado = null;
        binder.readBean(null);
        grid.asSingleSelect().clear();
    }

    private void actualizarGrid() {
        grid.setItems(usuarioDao.obtenerTodos());
    }
}
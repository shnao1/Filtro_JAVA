package controller;

import entity.Vacante;
import model.VacanteModel;
import utils.Utils;

import javax.swing.*;

import java.util.List;

public class VacanteController {

    public static void insert(){
        String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante: ");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante: ");
        String duracion = JOptionPane.showInputDialog("Ingrese la duracion de la vacante: ");
        String estado = JOptionPane.showInputDialog("Ingrese el estado de la vacante: ");
        String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnologia que domina el coder: ");

        instaceModel().insert(new Vacante(titulo, descripcion, duracion, estado, tecnologia));
    }

    public static String getAll(List<Object> list){

        String listString = "LISTA DE VACANTES: \n";

        for (Object temp: list){
            Vacante objVacante = (Vacante) temp;
            listString+= objVacante.toString() + "\n";
        }

        return listString;
    }

    public static void getAll(){
        String list = getAll(instaceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public static String getAllString(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listVacantes = "Lista de Vacantes \n";

        for (Object iterador : objVacanteModel.findAll()){
            Vacante objVacante = (Vacante) iterador;
            listVacantes += objVacante.toString() + "\n";
        }
        return listVacantes;
    }

    public static VacanteModel instaceModel() { return new VacanteModel();}

    public static void delete(){
        Object[] options = Utils.listToArray(instaceModel().findAll());

        Vacante objSlected = (Vacante) JOptionPane.showInputDialog(
                null,
                "Selecciona una vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instaceModel().delete(objSlected);
    }

    public static void update(){
        try {
            VacanteModel objVacanteModel = new VacanteModel();
            String listVacante = getAllString();
            int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listVacante + "\n Ingresa el id de la vacante a actualizar: "));

            Vacante objVacante = objVacanteModel.findVacanteById(idUpdate);

            if (objVacante == null){
                JOptionPane.showMessageDialog(null, "Vacante no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                String titulo = JOptionPane.showInputDialog(null, "Ingrese el nuevo titulo de la vacante: ",
                        objVacante.getTitulo());
                String descripcion = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la vacante: ",
                        objVacante.getDescripcion());
                String duracion = JOptionPane.showInputDialog(null, "Ingrese la nueva duracion de la vacante: ",
                        objVacante.getDuracion());
                String estado = JOptionPane.showInputDialog(null, "Ingrese el nuevo estado de la vacante: ",
                        objVacante.getEstado());
                String tecnologia = JOptionPane.showInputDialog(null, "Ingrese la nueva tecnologia de la vacante: ",
                        objVacante.getTecnologia());

                objVacante.setTitulo(titulo);
                objVacante.setDescripcion(descripcion);
                objVacante.setDuracion(duracion);
                objVacante.setEstado(estado);
                objVacante.setTecnologia(tecnologia);


                objVacanteModel.update(objVacante);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El id de la vacante debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

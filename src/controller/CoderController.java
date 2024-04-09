package controller;

import entity.Coder;
import model.CoderModel;
import utils.Utils;

import javax.swing.*;

import java.util.List;

import static controller.EmpresaController.instaceModel;

public class CoderController {
    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del coder: ");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del coder: ");
        String documento = JOptionPane.showInputDialog("Ingrese el documento del coder: ");
        String cohorte = JOptionPane.showInputDialog("Ingrese la cohorte del coder: ");
        String cv = JOptionPane.showInputDialog("Ingrese el cv del coder: ");
        String clan = JOptionPane.showInputDialog("Ingrese el clan del coder: ");

        instaceModel().insert(new Coder(nombre, apellidos, documento, cohorte, cv, clan));
    }

    public static String getAll(List<Object> list){

        String listString = "LISTA DE CODERS: \n";

        for (Object temp: list){
            Coder objCoder = (Coder) temp;
            listString+= objCoder.toString() + "\n";
        }

        return listString;
    }

    public static void getAll(){
        String list = getAll(instaceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public static String getAllString(){
        CoderModel objCoderModel = new CoderModel();
        String listCoders = "Lista de Coders \n";

        for (Object iterador : objCoderModel.findAll()){
            Coder objCoder = (Coder) iterador;
            listCoders += objCoder.toString() + "\n";
        }
        return listCoders;
    }

    public static void delete(){
        Object[] options = Utils.listToArray(instaceModel().findAll());

        Coder objSlected = (Coder) JOptionPane.showInputDialog(
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
            CoderModel objCoderModel = new CoderModel();
            String listCoder = getAllString();
            int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoder + "\n Ingresa el id del coder a actualizar: "));

            Coder objCoder = objCoderModel.findCoderById(idUpdate);

            if (objCoder == null){
                JOptionPane.showMessageDialog(null, "Coder no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo titulo de la vacante: ",
                        objCoder.getNombre());
                String apellidos = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la vacante: ",
                        objCoder.getApellidos());
                String documento = JOptionPane.showInputDialog(null, "Ingrese la nueva duracion de la vacante: ",
                        objCoder.getDocumento());
                String cohorte = JOptionPane.showInputDialog(null, "Ingrese el nuevo estado de la vacante: ",
                        objCoder.getCohorte());
                String cv = JOptionPane.showInputDialog(null, "Ingrese la nueva tecnologia de la vacante: ",
                        objCoder.getCv());
                String clan = JOptionPane.showInputDialog(null, "Ingrese el nuevo clan del coder",
                        objCoder.getClan());

                objCoder.setNombre(nombre);
                objCoder.setApellidos(apellidos);
                objCoder.setDocumento(documento);
                objCoder.setCohorte(cohorte);
                objCoder.setCv(cv);
                objCoder.setClan(clan);


                objCoderModel.update(objCoder);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El id del coder debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

package controller;

import entity.Empresa;
import model.EmpresaModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class EmpresaController {

    public static void insert(){
        String name = JOptionPane.showInputDialog("Ingrese el nombre de la empresa: ");
        String sector = JOptionPane.showInputDialog("Ingrese el sector de la empresa: ");
        String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicacion de la empresa: ");
        String contacto = JOptionPane.showInputDialog("Ingrese el contacto de la empresa: ");

        instaceModel().insert(new Empresa(name, sector, ubicacion, contacto));
    }

    public static void getAll(){
        String list = getAll(instaceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public static String getAll(List<Object> list){

        String listString = "LISTA DE EMPRESAS: \n";

        for (Object temp: list){
            Empresa objEmpresa = (Empresa) temp;
            listString+= objEmpresa.toString() + "\n";
        }

        return listString;
    }

    public static String getAllString(){
        EmpresaModel objEmpresaModel = new EmpresaModel();
        String listEmpresas = "Lista de empresas \n";

        for (Object iterador : objEmpresaModel.findAll()){
            Empresa objEmpresa = (Empresa) iterador;
            listEmpresas += objEmpresa.toString() + "\n";
        }
        return listEmpresas;
    }

    public static EmpresaModel instaceModel() { return new EmpresaModel();}

    public static void delete(){
        Object[] options = Utils.listToArray(instaceModel().findAll());

        Empresa objSlected = (Empresa) JOptionPane.showInputDialog(
                null,
                "Selecciona una empresa",
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
            EmpresaModel objEmpresaModel = new EmpresaModel();
            String listEmpresa = getAllString();
            int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listEmpresa + "\n Ingresa el id de la empresa a actualizar: "));

            Empresa objEmpresa = objEmpresaModel.findEmpresaById(idUpdate);

            if (objEmpresa == null){
                JOptionPane.showMessageDialog(null, "Empresa no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de la empresa: ",
                        objEmpresa.getName());
                String sector = JOptionPane.showInputDialog(null, "Ingrese el nuevo sector de la empresa: ",
                        objEmpresa.getSector());
                String ubicacion = JOptionPane.showInputDialog(null, "Ingrese la nueva ubicacion de la empresa: ",
                        objEmpresa.getUbicacion());
                String contacto = JOptionPane.showInputDialog(null, "Ingrese el nuevo contacto de la empresa: ",
                        objEmpresa.getContacto());

                objEmpresa.setName(nombre);
                objEmpresa.setSector(sector);
                objEmpresa.setUbicacion(ubicacion);
                objEmpresa.setContacto(contacto);

                objEmpresaModel.update(objEmpresa);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El id de la empresa debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

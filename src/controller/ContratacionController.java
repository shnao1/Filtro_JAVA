package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import model.ContratacionModel;
import model.EmpresaModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ContratacionController {
    public static void insert(){
        String fechaAplicacion = JOptionPane.showInputDialog("Ingrese la fecha de aplicacion a la vacante: ");
        String estado = JOptionPane.showInputDialog("Ingrese el estado de la vacante: ");
        String salario = JOptionPane.showInputDialog("Ingrese el salario ofertado: ");

        instaceModel().insert(new Contratacion(fechaAplicacion, estado, salario));
    }

    public static void getAll(){
        String list = getAll(instaceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public static String getAll(List<Object> list){

        String listString = "LISTA DE CONTRATOS: \n";

        for (Object temp: list){
            Contratacion objContratacion = (Contratacion) temp;
            listString+= objContratacion.toString() + "\n";
        }

        return listString;
    }

    public static String getAllString(){
        ContratacionModel objContractModel = new ContratacionModel();
        String listContract = "Lista de contratos \n";

        for (Object iterador : objContractModel.findAll()){
            Contratacion objContract = (Contratacion) iterador;
            listContract += objContract.toString() + "\n";
        }
        return listContract;
    }

    public static ContratacionModel instaceModel() { return new ContratacionModel();}

    public static void delete(){
        Object[] options = Utils.listToArray(instaceModel().findAll());

        Contratacion objSlected = (Contratacion) JOptionPane.showInputDialog(
                null,
                "Selecciona un contrato",
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
            ContratacionModel objContractModel = new ContratacionModel();
            String listEmpresa = getAllString();
            int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listEmpresa + "\n Ingresa el id del contrato a actualizar: "));

            Contratacion objContract = objContractModel.findContractById(idUpdate);

            if (objContract == null){
                JOptionPane.showMessageDialog(null, "Contrato no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                String fechaAplicacion = JOptionPane.showInputDialog(null, "Ingrese la nueva fecha de aplicacion: ",
                        objContract.getFechaAplicacion());
                String estado = JOptionPane.showInputDialog(null, "Ingrese el nuevo estado de la vacante: ",
                        objContract.getEstado());
                String salario = JOptionPane.showInputDialog(null, "Ingrese el nuevo salario del contrato: ",
                        objContract.getSalario());

                objContract.setFechaAplicacion(fechaAplicacion);
                objContract.setEstado(estado);
                objContract.setSalario(salario);

                objContractModel.update(objContract);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El id del contrato debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Contratacion objContract = (Contratacion) obj;

        try {
            String sql = "INSERT INTO contratacion (fecha_aplicacion, estado, salario) VALUES (?,?,?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objContract.getFechaAplicacion());
            objPrepare.setString(2, objContract.getEstado());
            objPrepare.setString(3, objContract.getSalario());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objContract.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "El contrato fue agregado correctamente.");

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objContract;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listContract = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM contratacion;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Contratacion objContract = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();

                objContract.setId(objResult.getInt("id"));
                objVacante.setId(objResult.getInt("vacante_id"));
                objCoder.setId(objResult.getInt("coder_id"));
                objContract.setFechaAplicacion(objResult.getString("fecha_aplicacion"));
                objContract.setEstado(objResult.getString("estado"));
                objContract.setSalario(objResult.getString("salario"));

                listContract.add(objContract);
            }
        }catch (SQLException e){
            System.out.println("ERROR > "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return listContract;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    public Contratacion findContractById(int id_contract){

        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContract = null;
        Vacante objVacante = null;
        Coder objCoder = null;


        try {
            String sql = "SELECT * FROM contratacion = contratacion WHERE id =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id_contract);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objContract = new Contratacion();
                objVacante = new Vacante();
                objCoder = new Coder();

                objContract.setId(objResult.getInt("id"));
                objVacante.setId(objResult.getInt("vacante_id"));
                objCoder.setId(objResult.getInt("coder_id"));
                objContract.setFechaAplicacion(objResult.getString("fecha_aplicacion"));
                objContract.setEstado(objResult.getString("estado"));
                objContract.setSalario(objResult.getString("salario"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objContract;
    }

    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContract = (Contratacion) obj;

        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM contratacion WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objContract.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDeleted = true;

                JOptionPane.showMessageDialog(null, "Contrato eliminado correctamente");
            }
        }catch (SQLException e){
            System.out.println("ERROR "+ e.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }
}

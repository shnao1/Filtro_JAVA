package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel implements CRUD {
    @Override
    public Object insert(Object obj) {

    Connection objConnection = ConfigDB.openConnection();

        Empresa objEmpresa = (Empresa) obj;

        try {
            String sql = "INSERT INTO empresa (nombre, sector, ubicacion, contacto) VALUES (?,?,?,?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objEmpresa.getName());
            objPrepare.setString(2, objEmpresa.getSector());
            objPrepare.setString(3, objEmpresa.getUbicacion());
            objPrepare.setString(4, objEmpresa.getContacto());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objEmpresa.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "La empresa fue agregada correctamente.");

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objEmpresa;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listEmpresas = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setName(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                listEmpresas.add(objEmpresa);
            }
        }catch (SQLException e){
            System.out.println("ERROR > "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return listEmpresas;
    }

    public Empresa findEmpresaById(int id_empresa){
        Connection objConnection = ConfigDB.openConnection();
        Empresa objEmpresa = null;

        try {
            String sql = "SELECT * FROM empresa = empresa WHERE id =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id_empresa);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objEmpresa = new Empresa();
                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setName(objResult.getString("name"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objEmpresa;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Empresa objEmpresa = (Empresa) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE empresa SET nombre = ?, sector = ?, ubicacion = ?, contacto = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objEmpresa.getName());
            objPrepare.setString(2, objEmpresa.getSector());
            objPrepare.setString(3, objEmpresa.getUbicacion());
            objPrepare.setString(4,objEmpresa.getContacto());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "La empresa se actualizo correctamente");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Empresa objEmpresa = (Empresa) obj;

        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM empresa WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objEmpresa.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDeleted = true;

                JOptionPane.showMessageDialog(null, "Empresa eliminada correctamente");
            }
        }catch (SQLException e){
            System.out.println("ERROR "+ e.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }
}

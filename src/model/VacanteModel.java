package model;

import database.CRUD;
import database.ConfigDB;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD{
    @Override
    public List<Object> findAll() {
        List<Object> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM vacante;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objVacante  = new Vacante();

                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));


                listVacantes.add(objVacante);
            }
        }catch (SQLException e){
            System.out.println("ERROR > "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacantes;
    }


    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE vacante SET titulo = ?, descripcion = ?, duracion = ?, estado = ?, tecnologia = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objVacante.getTitulo());
            objPrepare.setString(2, objVacante.getDescripcion());
            objPrepare.setString(3, objVacante.getDuracion());
            objPrepare.setString(4, objVacante.getEstado());
            objPrepare.setString(5, objVacante.getTecnologia());


            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "La vacante se actualizo correctamente");
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
        Vacante objVacante = (Vacante) obj;

        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM vacante WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objVacante.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDeleted = true;

                JOptionPane.showMessageDialog(null, "Vacante eliminada correctamente");
            }
        }catch (SQLException e){
            System.out.println("ERROR "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Vacante findVacanteById(int id_vacante){
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;

        try {
            String sql = "SELECT * FROM vacante = vacante WHERE id =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id_vacante);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objVacante = new Vacante();
                objVacante.setId(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("nombre"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));


            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objVacante;
    }

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Vacante objVacante = (Vacante) obj;

        try {
            String sql = "INSERT INTO vacante (titulo, descripcion, duracion, estado, tecnologia) VALUES (?,?,?,?,?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            System.out.println(objVacante);
            objPrepare.setString(1, objVacante.getTitulo());
            objPrepare.setString(2, objVacante.getDescripcion());
            objPrepare.setString(3, objVacante.getDuracion());
            objPrepare.setString(4, objVacante.getEstado());
            objPrepare.setString(5, objVacante.getTecnologia());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVacante.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "La vacante fue agregada correctamente.");

        }catch (SQLException e){
            System.out.println("ERROR > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVacante;
    }
}

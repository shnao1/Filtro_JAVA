import controller.CoderController;
import controller.EmpresaController;
import controller.VacanteController;
import database.ConfigDB;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int option = 0, option2 = 0, option3 = 0, option4 = 0;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar Empresas
                    2. Administrar Vacantes
                    3. Administrar Contrataciones
                    4. Administrar Coders
                    5. Salir
                    
                    Ingrese una opcion:
                    """));

            switch (option){
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Empresas
                                2. Crear Empresa
                                3. Eliminar Empresa
                                4. Actualizar Empresa
                                5. Salir
                                
                                Ingrese una opcion:
                                """));
                        switch (option2){

                            case 1:
                                EmpresaController.getAll();
                                break;
                            case 2:
                                EmpresaController.insert();
                                break;
                            case 3:
                                EmpresaController.delete();
                                break;
                            case 4:
                                EmpresaController.update();
                        }
                    }while (option2 != 5);
                    break;
                case 2:
                    do {
                        option3 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Vacantes
                                2. Crear Vacante
                                3. Eliminar Vacante
                                4. Actualizar Vacante
                                5. Salir
                                
                                Ingrese una opcion:
                                """));
                        switch (option3){

                            case 1:
                                VacanteController.getAll();
                                break;
                            case 2:
                                VacanteController.insert();
                                break;
                            case 3:
                                VacanteController.delete();
                                break;
                            case 4:
                                VacanteController.update();
                                break;
                        }
                    }while (option3 != 5);
                    break;
                case 4:
                    do {
                        option4 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Coders
                                2. Crear Coder
                                3. Eliminar Coder
                                4. Actualizar Coder
                                5. Salir
                                
                                Ingrese una opcion:
                                """));
                        switch (option4){
                            case 1:
                                CoderController.getAll();
                                break;
                            case 2:
                                CoderController.insert();
                                break;
                            case 3:
                                CoderController.delete();
                                break;
                            case 4:
                                CoderController.update();
                                break;
                        }
                    }while (option4 != 5);
                    break;
            }
        }while (option != 5);
    }
}
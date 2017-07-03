/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pickadosdesktop.dao.IDAO;
import pickadosdesktop.dao.LocalDAO;
import pickadosdesktop.entity.Match;
import pickadosdesktop.exceptions.ParsingResponseException;
import pickadosdesktop.exceptions.WrongRequestException;
import pickadosdesktop.model.Modelo;
import pickadosdesktop.model.OddRow;
import pickadosdesktop.service.ApiFootballServices;
import pickadosdesktop.service.ApiParser;
import pickadosdesktop.service.DialogService;
import pickadosdesktop.service.Utils;

/**
 * FXML Controller class
 *
 * @author JoseAntonio
 */
public class DashboardController implements Initializable {

    @FXML
    private ListView<Match> comingEventsList;

    @FXML
    private TableView<OddRow> oddTable;

    @FXML
    private TableColumn<OddRow, String> bookie_column;
    @FXML
    private TableColumn<OddRow, String> home_column;
    @FXML
    private TableColumn<OddRow, String> draw_column;
    @FXML
    private TableColumn<OddRow, String> away_column;
    @FXML
    private TableColumn<OddRow, String> over_column;
    @FXML
    private TableColumn<OddRow, String> under_column;

    
    private final ApiFootballServices apiFootballServices = new ApiFootballServices(Utils.getProperty("api"), Utils.getProperty("apiKey"), new ApiParser());
    
    Modelo model;

    IDAO clientDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientDAO = new LocalDAO();
        model = new Modelo(clientDAO, 0);
        initializeMatches();
        initOddTable();
    }

    public void initializeMatches() {
        String currentDate = Utils.getFormattedCurrentDate();
        try{
            List<Match> matchesRetrieved = apiFootballServices.getLiveMatches(currentDate, currentDate);
            comingEventsList.getItems().addAll(matchesRetrieved);
        } catch(WrongRequestException ex) {
           DialogService.showDialog("Error en la petición","No se han podido obtener los partidos", "Se produjo un error mientras se intentaban recuperar los partidos que se están disputando. Se volverá a intentar de nuevo automáticamente.", Alert.AlertType.WARNING);
        } catch(ParsingResponseException ex) {
            DialogService.showDialog("Sin eventos","No hay ningun partidos", "Por el momento no se está disputando ningún partido. Aparecerán automáticamente a medida que vayan comenzado.", Alert.AlertType.INFORMATION);
        }
       
        

        model.matchesProperty().get().addListener(new ListChangeListener<Match>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Match> c) {
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (Match match : c.getAddedSubList()) {
                            comingEventsList.getItems().add(match);
                        }
                    }
                }
            }
        });

        comingEventsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Match>() {
            @Override
            public void changed(ObservableValue<? extends Match> observable, Match oldValue, Match newValue) {
                if (newValue != null) {
                    newValue.onFocus();
                }
            }
        });
    }

    private void initOddTable() {
        oddTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        oddTable.itemsProperty().bind(new SimpleObjectProperty<>(model.oddRowsProperty()));

        bookie_column.setCellValueFactory(new PropertyValueFactory<>("odd_bookmakers"));
        home_column.setCellValueFactory(new PropertyValueFactory<>("odd_1"));
        draw_column.setCellValueFactory(new PropertyValueFactory<>("odd_x"));
        away_column.setCellValueFactory(new PropertyValueFactory<>("odd_2"));
        over_column.setCellValueFactory(new PropertyValueFactory<>("over"));
        under_column.setCellValueFactory(new PropertyValueFactory<>("under"));
    }

    public void loadEventOdds(String id) {
        String currentDate = Utils.getFormattedCurrentDate();
        model.oddRowsProperty().clear();
        try {
            model.oddRowsProperty().addAll(apiFootballServices.getOddsFromMatch(id, currentDate, currentDate));
        } catch(WrongRequestException ex) {
           DialogService.showDialog("Error en la petición","No se han podido obtener las cuotas", "Se produjo un error mientras se intentaban recuperar las cuotas del partido. Intentelo de nuevo", Alert.AlertType.WARNING);
        } catch(ParsingResponseException ex) {
            DialogService.showDialog("Sin cuotas","No hay cuotas para este evento", "Por el momento no hay cuotas para este evento. Intentelo de nuevo más tarde.", Alert.AlertType.INFORMATION);
        }

        if (oddTable.getItems().isEmpty()) {
            oddTable.setPlaceholder(new Label("No hay cuotas disponibles para este partido"));
        }
    }

    @FXML
    public void lvOnClick(MouseEvent arg0) {
        loadEventOdds(comingEventsList.getSelectionModel().getSelectedItem().getId());
    }
}

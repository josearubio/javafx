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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pickadosdesktop.dao.IDAO;
import pickadosdesktop.dao.LocalDAO;
import pickadosdesktop.entity.Match;
import pickadosdesktop.model.Modelo;
import pickadosdesktop.model.OddRow;
import pickadosdesktop.service.ApiFootballServices;

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
    
    private final ApiFootballServices apiFootballServices = new ApiFootballServices();;
    
    Modelo model;
    
    IDAO clientDAO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        clientDAO = new LocalDAO();
        model = new Modelo(clientDAO, 0);
       // comingEventsList.itemsProperty().bind(modelo.matchesProperty());
        initializeMatches();
        initOddTable();
    }    
    
    public void initializeMatches(){
        List<Match> matchList = clientDAO.getMatches();
        for(Match match : matchList){
            comingEventsList.getItems().add(match);
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
        
        public void loadEventOdds(String id){
            model.oddRowsProperty().clear();
            model.oddRowsProperty().addAll(apiFootballServices.getOddsFromMatch(id));
        }
        
        @FXML 
        public void lvOnClick(MouseEvent arg0) {
            loadEventOdds(comingEventsList.getSelectionModel().getSelectedItem().getId());
        }
}

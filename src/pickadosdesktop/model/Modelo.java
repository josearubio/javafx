/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pickadosdesktop.model;

import java.util.Date;
import pickadosdesktop.entity.Match;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import pickadosdesktop.dao.IDAO;
import pickadosdesktop.exceptions.ParsingResponseException;
import pickadosdesktop.exceptions.WrongRequestException;
import pickadosdesktop.service.ApiFootballServices;
import pickadosdesktop.service.ApiParser;
import pickadosdesktop.service.DialogService;
import pickadosdesktop.service.Utils;

/**
 *
 * @author JoseAntonio
 */
public class Modelo {

    private final ObjectProperty<ObservableList<Match>> matches;
    private final IDAO clientDAO;
    private final ApiFootballServices apiFootballServices;
    private final ObservableList<OddRow> oddRows;
    private static final long DELAY = 3000;

    public Modelo(IDAO clientDAO, long msRefresco) {
        matches = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        apiFootballServices = new ApiFootballServices(Utils.getProperty("api"), Utils.getProperty("apiKey"), new ApiParser());
        oddRows = FXCollections.observableArrayList();
        this.clientDAO = clientDAO;

        TimerTask delayedTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Date today = new Date();
                        String currentDate = Utils.formatDate(today);
                        try {
                            List<Match> matchesRetrieved = apiFootballServices.getLiveMatchs(currentDate, currentDate);
                             matches.get().addAll(matchesRetrieved);
                        } catch (WrongRequestException ex) {
                        } catch (ParsingResponseException ex) {
                        }
                    }
                });
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(delayedTask, DELAY);
    }

    public ObservableList<OddRow> oddRowsProperty() {
        return oddRows;
    }

    public ObjectProperty<ObservableList<Match>> matchesProperty() {
        return matches;
    }

    public IDAO getDAOClient() {
        return clientDAO;
    }
}

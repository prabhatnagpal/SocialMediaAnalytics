package com.sociogo.fbanalytics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FbAnalyticsDashboardController implements Initializable
{
    @FXML
    private Label fname;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    void postsView(ActionEvent event) throws IOException
    {
    	
    }

    @FXML
    void viewProfile(ActionEvent event) {

    }

    @FXML
    void logoutHandle(ActionEvent event) {

    }

    @FXML
    void dashboardCallback(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

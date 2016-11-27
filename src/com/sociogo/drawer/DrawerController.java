package com.sociogo.drawer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;

public class DrawerController implements Initializable
{
	@FXML
	private JFXButton fbutton;
	@FXML
	private JFXButton tbutton;

	// Event Listener on JFXButton[#fbutton].onAction
	@FXML
	public void facebookAnalytics(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/com/sociogo/fbanalytics/FbAnalyticsDashboard.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setTitle("Dashboard");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show(); 
	}
	// Event Listener on JFXButton[#tbutton].onAction
	@FXML
	public void twitterAnalytics(ActionEvent event) {
		// TODO Autogenerated
	}
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		try
		{
			Image icon = new Image(getClass().getResourceAsStream("/com/sociogo/icons/fblogo.png"));
			fbutton.setGraphic(new ImageView(icon));
			fbutton.getStylesheets().add(getClass().getResource("fbutton.css").toExternalForm());
			icon = new Image(getClass().getResourceAsStream("/com/sociogo/icons/twitterlogo.png"));
			tbutton.setGraphic(new ImageView(icon));
			tbutton.getStylesheets().add(getClass().getResource("tbutton.css").toExternalForm());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

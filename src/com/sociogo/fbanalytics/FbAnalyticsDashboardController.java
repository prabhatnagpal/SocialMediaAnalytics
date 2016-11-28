package com.sociogo.fbanalytics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sociogo.dashboard.DashboardController;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class FbAnalyticsDashboardController implements Initializable
{
	@FXML
	private JFXDrawer postdrawer;
	
	@FXML
	private JFXButton postbutton;
	
	@FXML
	private JFXButton gobutton;
	
    @FXML
    private Label fname;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    void postsView(ActionEvent event) throws IOException, InterruptedException
    {
    	Pane pane = FXMLLoader.load(getClass().getResource("PostDatePicker.fxml"));
    	postdrawer.setSidePane(pane);
    	if(!postdrawer.isShown())
		{
			postdrawer.open();
		}
    	PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
    	visiblePause.setOnFinished(e -> gobutton.setVisible(true));
    	visiblePause.play();
    }
    
    @FXML
    void startAnalytics(ActionEvent event) throws IOException
    {
    	if((PostDatePickerController.from_date!=null) && (PostDatePickerController.to_date!=null))
    	{
    		
    	}
    }
    
    @FXML
    void viewProfile(ActionEvent event)
    {
    	
    }

    @FXML
    void logoutHandle(ActionEvent event)
    {
    	
    }

    @FXML
    void dashboardCallback(ActionEvent event)
    {
    	
    }

    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	fname.setText(DashboardController.first_name);
		
		gobutton.setVisible(false);
		
		postbutton.getStylesheets().add(getClass().getResource("postbutton.css").toExternalForm());
		
		try
		{
			VBox box = FXMLLoader.load(getClass().getResource("/com/sociogo/drawer/Drawer.fxml"));
			drawer.setSidePane(box);
			HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
			transition.setRate(-1);
			hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) ->
			{
				transition.setRate(transition.getRate() * -1);
				transition.play();
				if(drawer.isShown())
				{
					drawer.close();
				}
				else
				{
					drawer.open();
				}
			});
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

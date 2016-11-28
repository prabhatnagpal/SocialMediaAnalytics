package com.sociogo.fbanalytics;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PostDatePickerController implements Initializable
{
	public static String from_date, to_date;
	
	@FXML
	private JFXDatePicker since;
	
	@FXML
	private JFXDatePicker until;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	    since.setOnAction(sinceAction ->
		{
			LocalDate from = since.getValue();
			from_date = from.toString();
			until.setOnAction(untilAction ->
			{
				LocalDate to = until.getValue();
				to_date = to.toString();
			});
		});
	}
}

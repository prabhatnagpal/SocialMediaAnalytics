package com.sociogo.signup;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CreateAccountController implements Initializable
{
	boolean flag = true;
	
	@FXML
	private JFXTextField fname;
	
	@FXML
	private JFXTextField lname;
	
	@FXML
	private JFXTextField uname;
	
	@FXML
	private JFXPasswordField pass;
	
	@FXML
	private JFXTextField email;
	
	@FXML
	public void newUser(ActionEvent event) throws IOException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/social_media_analytics","root","admin");
		
		String query = "insert into users(first_name,last_name,username,password,email_id) values(?,?,?,?,?)";
		
		if(uname.getText().isEmpty() || pass.getText().isEmpty())
		{
			Parent root = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.hide();
			stage.setTitle("Create Account");
			stage.setScene(scene);
			stage.show();
			//flag = false;
		}
		else
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, fname.getText());
			preparedStatement.setString(2, lname.getText());
			preparedStatement.setString(3, uname.getText());
			preparedStatement.setString(4, pass.getText());
			preparedStatement.setString(5, email.getText());
			
			int i = preparedStatement.executeUpdate();
			System.out.println(i+" row has been affected");
			connection.close();
			
			Parent root = FXMLLoader.load(getClass().getResource("/com/sociogo/login/Login.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.hide();
			stage.setTitle("Dashboard Login");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	@FXML
    void loginCallback(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/com/sociogo/login/Login.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setTitle("Dashboard Login");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Username Not Unique");
		alert.setHeaderText("Username Already Taken!");
		alert.setContentText("Username Already Exists, Please choose a new Username!");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/social_media_analytics","root","admin");
			String query = "select username from users";
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			RequiredFieldValidator unameValidator = new RequiredFieldValidator();
			Image icon = new Image(getClass().getResourceAsStream("/com/sociogo/icons/warning.png"));
			unameValidator.setIcon(new ImageView(icon));
			uname.getValidators().add(unameValidator);
			unameValidator.setMessage("Username can't be empty");
			uname.focusedProperty().addListener(new ChangeListener<Boolean>()
			{
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
				{
					if(!newValue)
					{
						uname.validate();
					}
				}
			});
			
			RequiredFieldValidator passValidator = new RequiredFieldValidator();
			pass.getValidators().add(passValidator);
			passValidator.setMessage("Password can't be empty");
			passValidator.setIcon(new ImageView(icon));
			pass.focusedProperty().addListener(new ChangeListener<Boolean>() 
			{
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
				{
					if(!newValue)
					{
						pass.validate();
					}
				}
			});
			
			while(rs.next())
			{
				String user = rs.getString("username");
				uname.focusedProperty().addListener(new ChangeListener<Boolean>()
				{
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
					{
						if(!newValue)
						{
							if(uname.getText().equals(user))
							{
								alert.show();
								uname.setText("");
							}
						}
					}
				});
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

package com.sociogo.login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements Initializable
{
	public static String fname;
	
	@FXML
    private JFXTextField uname;

    @FXML
    private JFXPasswordField pass;
    
	@FXML
	private void loginUser(ActionEvent event) throws IOException, SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/social_media_analytics","root","admin");
		String query = "select username, first_name, password from users";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			if((uname.getText().equals(rs.getString("username")) && (pass.getText().equals(rs.getString("password")))))
			{
				fname = rs.getString("first_name");
				Parent root = FXMLLoader.load(getClass().getResource("/com/sociogo/dashboard/Dashboard.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				stage.hide();
				stage.setTitle("Dashboard");
				stage.setResizable(false);
				stage.setScene(scene);
				stage.show();
			}
		}
	}
	
	@FXML
	private void createAccount(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/com/sociogo/signup/CreateAccount.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setTitle("Create Account");
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void authUser(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/com/sociogo/fblogin/FacebookLogin.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setTitle("Facebook Authorization");
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

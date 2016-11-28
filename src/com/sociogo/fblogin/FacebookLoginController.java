package com.sociogo.fblogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FacebookLoginController implements Initializable
{
	public static String accessToken;
	
	String oauthurl;
	
	public static String fname;
	
	@FXML
	private WebView webview;

	private WebEngine engine;
	
	String appId = "1507754589241093";
	String redirectUri = "https://www.facebook.com/connect/login_success.html";
	String authURL = "https://www.facebook.com/dialog/oauth?";
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{	
		engine = webview.getEngine();
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>()
		{
			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue)
			{
				if(engine.getLocation().contains(redirectUri))
				{
					String url = webview.getEngine().getLocation();
					accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
					FacebookClient client = new DefaultFacebookClient(accessToken, Version.LATEST);
					User user = client.fetchObject("me", User.class, Parameter.with("fields", "first_name"));
					fname = user.getFirstName();
					try
					{
						Parent root = FXMLLoader.load(getClass().getResource("/com/sociogo/dashboard/Dashboard.fxml"));
						Scene scene = new Scene(root);
						Stage stage = (Stage) webview.getScene().getWindow();
						stage.close();
						stage.setTitle("Dashboard");
						stage.setResizable(false);
						stage.setScene(scene);
						stage.show();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		engine.load(buildURL());
		
	}

	private String buildURL()
	{
		try
		{
			oauthurl = OAuthClientRequest.authorizationLocation(authURL).setClientId(appId).setRedirectURI(redirectUri).setResponseType("token").setScope("email").buildQueryMessage().getLocationUri();
		}
		catch (OAuthSystemException e)
		{
			e.printStackTrace();
		}
		return oauthurl;
	}
}

package com.az.gitember.ui;

import com.az.gitember.misc.RepoInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Created by Igor_Azarny on 25 - Dec -2016.
 */
public class LoginDialog extends Dialog<RepoInfo> {

    public LoginDialog(String title, String header,
                       RepoInfo repoInfo) {

        super();
        this.setTitle(title);
        this.setHeaderText(header);
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString())); todo

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 20));

        TextField username = new TextField();
        username.setPromptText("Username");
        HBox.setHgrow(username, Priority.ALWAYS);
        if (repoInfo.getLogin() != null) {
            username.setText(repoInfo.getLogin());
        }
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        if (repoInfo.getPwd() != null) {
            password.setText(repoInfo.getPwd());
        }

        CheckBox rememberPassword = new CheckBox();
        rememberPassword.setText("Remember me");
        rememberPassword.setSelected(repoInfo.isRememberMe());

        grid.add(new Label("Username : "), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password : "), 0, 1);
        grid.add(password, 1, 1);
        grid.add(rememberPassword, 1, 2);


        Node loginButton = this.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(username.textProperty().isEmpty().get());

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });


        this.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        this.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return RepoInfo.of(
                        repoInfo.getUrl(),
                        username.getText(),
                        password.getText(),
                        repoInfo.getKey(),
                        rememberPassword.isSelected()

                );
            }
            return null;
        });

    }


}

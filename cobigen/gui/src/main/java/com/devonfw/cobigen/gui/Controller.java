package com.devonfw.cobigen.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the Template Set Management GUI
 */
public class Controller implements Initializable {

  ArrayList<String> templateSets = new ArrayList<>(Arrays.asList("templates-devon4j-tests", "templates-devon4j-utils",
      "crud-openapi-net", "crud-angular-client-app", "crud-ionic-client-app", "rest-documentation"));

  @FXML
  private AnchorPane detailsPane;

  @FXML
  private AnchorPane homePane;

  @FXML
  public Button homeButton;

  @FXML
  public Button clearSearchResultsButton;

  @FXML
  public TextField searchBar;

  @FXML
  public Button goSearch;

  // TODO: Transform to ListView<HBox>
  @FXML
  public ListView<String> searchResultsView;

  /**
   * Initial View
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    this.searchResultsView.getItems().addAll(this.templateSets);
  }

  @FXML
  public void loadHome(javafx.event.ActionEvent actionEvent) throws IOException {

    AnchorPane homePane = FXMLLoader.load(getClass().getResource("Home.fxml"));
    this.detailsPane.getChildren().setAll(homePane);
  }

  /**
   * @param actionEvent
   * @throws IOException
   */
  public void details(javafx.event.ActionEvent actionEvent) throws IOException {

  }

  /**
   * @param event
   * @throws IOException
   */
  @FXML
  public void search(javafx.event.ActionEvent event) throws IOException {

    this.searchResultsView.getItems().clear();
    this.searchResultsView.getItems().addAll(searchTemplateSets(this.searchBar.getText(), this.templateSets));
  }

  /**
   * Called when clearSearchResultsButton is pressed
   */
  @FXML
  public void clearSearchResults() {

    this.searchBar.clear();
    this.searchResultsView.getItems().clear();
    this.searchResultsView.getItems().addAll(this.templateSets);
  }

  /**
   * @param text
   * @param templateSets2
   * @return
   */
  private List<String> searchTemplateSets(String searchWords, List<String> listOfStrings) {

    List<String> searchTemplateSetsArray = Arrays.asList(searchWords.trim().split(" "));

    return listOfStrings.stream().filter(input -> {
      return searchTemplateSetsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
    }).collect(Collectors.toList());
  }

}

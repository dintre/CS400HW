import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 * UI javaFX class of the Meal Helper program
 */
public class FoodBuildUI extends Application {
    
    static ObservableList<String> names = FXCollections.observableArrayList();
    String nfoodName;
    String nfoodID;
    Double nfoodcalories;
    Double nfoodcarbs;
    Double nfoodfat;
    Double nfoodfiber;
    Double nfoodprotein;
    
    @Override
    public void start(Stage primaryStage) {
        
        // set title of the window that opens
        primaryStage.setTitle("Food Helper");
        primaryStage.show();    
        
        // instantiate other classes that are needed at startup -------------------------------
        // FoodData - the list of food
        FoodData foodList = new FoodData();
        
        
        
        // establish initial borderPane layout
        BorderPane border = new BorderPane();
        
        // create sections
        
        // top section  --------------------------------------------------------------------------------------------------------------
        VBox headingbox = new VBox();
        headingbox.setPadding(new Insets(20, 100, 20, 100));
        headingbox.setSpacing(10);
        headingbox.setStyle("-fx-background-color: #4527A0;");
        // set label for title of the program
        Label programTitle = new Label();
        programTitle.setText("Meal Helper");
        programTitle.setTextFill(Color.web("#FAFAFA"));
        programTitle.setFont(Font.font("Cambria", 32));
        
        ObservableList food = FXCollections.observableArrayList();
        
                
        
        // left section - food list  --------------------------------------------------------------------------------------------------------------
        VBox foodBox = new VBox();
        foodBox.setPadding(new Insets (20, 200, 20, 200));
        foodBox.setSpacing(10);
        foodBox.setStyle("-fx-background-color: #B39DDB;");
        
        // title for food section
        Label foodLabel = new Label();
        foodLabel.setText("Food");
        foodLabel.setPadding(new Insets(10));
        foodLabel.setFont(Font.font("Cambria", 24));
        
        
        
       // food.addAll("Blueberries", "Flour", "Sugar", "Chicken Breast", "Maple Syrup", "Bacon", "Spaghetti Noodles", "Tomato Sauce", "Peanut Butter", "Raspberry Jelly", "Whole Wheat Bread",
                //"Lettuce", "Button Mushrooms", "Egg");
                
        final ListView foodView = new ListView(food);
        foodView.setPrefSize(300, 300);
        foodView.setEditable(true);
        
        
        // search field by name
        TextField foodNameSearch = new TextField();
        foodNameSearch.setPromptText("Search by name...");
        
        // search field by nutrient
        TextField nutrientSearch = new TextField();
        nutrientSearch.setPromptText("Search by nutrient...");
        
        // run query button
        Button runSearchQuery = new Button("Search Food");
        runSearchQuery.setPrefSize(100, 20);
        
        // create food button
        Button createFood = new Button("Create Food");
        createFood.setPrefSize(100, 20);
        createFood.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
 
                Label nameLabel = new Label("Name of Food:");
                Label IDLabel = new Label("ID of Food:");
                Label calorieLabel = new Label("Calories:");
                Label carbsLabel = new Label("Carbs:");
                Label fatLabel = new Label("Fat:");
                Label fiberLabel = new Label("Fiber:");
                Label proteinLabel = new Label("Protein:");
 
                GridPane addFoodLayout = new GridPane();
                addFoodLayout.setPadding(new Insets(10, 10, 10, 10));
                addFoodLayout.setVgap(5);
                addFoodLayout.setHgap(5);
                TextField name = new TextField();
                TextField ID = new TextField();
                TextField calories = new TextField();
                TextField carbs = new TextField();
                TextField fat = new TextField();
                TextField fiber = new TextField();
                TextField protein = new TextField();
                DecimalFormat format = new DecimalFormat( "#.0" );
                calories.setTextFormatter( new TextFormatter<>(c ->
                {
                    if ( c.getControlNewText().isEmpty() )
                    {
                        return c;
                    }

                    ParsePosition parsePosition = new ParsePosition( 0 );
                    Object object = format.parse( c.getControlNewText(), parsePosition );

                    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
                    {
                        return null;
                    }
                    else
                    {
                        return c;
                    }
                }));
                carbs.setTextFormatter( new TextFormatter<>(c ->
                {
                    if ( c.getControlNewText().isEmpty() )
                    {
                        return c;
                    }

                    ParsePosition parsePosition = new ParsePosition( 0 );
                    Object object = format.parse( c.getControlNewText(), parsePosition );

                    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
                    {
                        return null;
                    }
                    else
                    {
                        return c;
                    }
                }));
                fat.setTextFormatter( new TextFormatter<>(c ->
                {
                    if ( c.getControlNewText().isEmpty() )
                    {
                        return c;
                    }

                    ParsePosition parsePosition = new ParsePosition( 0 );
                    Object object = format.parse( c.getControlNewText(), parsePosition );

                    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
                    {
                        return null;
                    }
                    else
                    {
                        return c;
                    }
                }));
                fiber.setTextFormatter( new TextFormatter<>(c ->
                {
                    if ( c.getControlNewText().isEmpty() )
                    {
                        return c;
                    }

                    ParsePosition parsePosition = new ParsePosition( 0 );
                    Object object = format.parse( c.getControlNewText(), parsePosition );

                    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
                    {
                        return null;
                    }
                    else
                    {
                        return c;
                    }
                }));
                protein.setTextFormatter( new TextFormatter<>(c ->
                {
                    if ( c.getControlNewText().isEmpty() )
                    {
                        return c;
                    }

                    ParsePosition parsePosition = new ParsePosition( 0 );
                    Object object = format.parse( c.getControlNewText(), parsePosition );

                    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
                    {
                        return null;
                    }
                    else
                    {
                        return c;
                    }
                }));
                Button generateFood = new Button("Create Food!");
                generateFood.setPrefSize(230, 20);
                Button cancelFood = new Button("Cancel");
                cancelFood.setPrefSize(230,20);
                addFoodLayout.add(nameLabel,0,0,1,1);
                addFoodLayout.add(IDLabel,0,1,1,1);
                addFoodLayout.add(calorieLabel,0,2,1,1);
                addFoodLayout.add(carbsLabel,0,3,1,1);
                addFoodLayout.add(fatLabel,0,4,1,1);
                addFoodLayout.add(fiberLabel,0,5,1,1);
                addFoodLayout.add(proteinLabel,0,6,1,1);
                addFoodLayout.add(name,1,0,1,1);
                addFoodLayout.add(ID,1,1,1,1);
                addFoodLayout.add(calories,1,2,1,1);
                addFoodLayout.add(carbs,1,3,1,1);
                addFoodLayout.add(fat,1,4,1,1);
                addFoodLayout.add(fiber,1,5,1,1);
                addFoodLayout.add(protein,1,6,1,1);
                addFoodLayout.add(generateFood, 0, 7,2,1);
                addFoodLayout.add(cancelFood, 0, 8,2,1);
                Scene CrFoodScene = new Scene(addFoodLayout, 285, 290);
 
                // New window (Stage)
                Stage CrFood = new Stage();
                CrFood.setTitle("Add Food");
                CrFood.setScene(CrFoodScene);
 
                // Set position of second window, related to primary window.
                CrFood.setX(primaryStage.getX() + 700);
                CrFood.setY(primaryStage.getY() + 350);
 
                CrFood.show();
                generateFood.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        boolean invalid = false;
                        nfoodName=name.getText();
                        nfoodID = ID.getText();
                        String tempfoodcalories = calories.getText();
                        String tempfoodcarbs = carbs.getText();
                        String tempfoodfat = fat.getText();
                        String tempfoodfiber = fiber.getText();
                        String tempfoodprotein = protein.getText();
                        if (nfoodName.isEmpty()) {
                            invalid = true;
                        }
                        if (nfoodID.isEmpty()) {
                            invalid = true;
                        }
                        if (!tempfoodcalories.isEmpty()) {
                            nfoodcalories = Double.parseDouble(tempfoodcalories);
                        }
                        else {
                            invalid = true;
                        }
                        if (!tempfoodcarbs.isEmpty()) {
                            nfoodcarbs = Double.parseDouble(tempfoodcarbs);
                        }
                        else {
                            invalid = true;
                        }
                        if (!tempfoodfat.isEmpty()) {
                            nfoodfat = Double.parseDouble(tempfoodfat);
                        }
                        else {
                            invalid = true;
                        }
                        if (!tempfoodfiber.isEmpty()) {
                            nfoodfiber = Double.parseDouble(tempfoodfiber);
                        }
                        else {
                            invalid = true;
                        }
                        if (!tempfoodprotein.isEmpty()) {
                            nfoodprotein = Double.parseDouble(tempfoodprotein);
                        }
                        else {
                            invalid = true;
                        }
                        if (!invalid) {
                            GridPane verifyFoodLayout = new GridPane();
                            verifyFoodLayout.setPadding(new Insets(10, 10, 10, 10));
                            verifyFoodLayout.setVgap(5);
                            verifyFoodLayout.setHgap(5);
                            Label nameLabel = new Label("Name of Food: " + nfoodName);
                            Label IDLabel = new Label("ID of Food: " + nfoodID);
                            Label calorieLabel = new Label("Calories: " + Double.toString(nfoodcalories));
                            Label carbsLabel = new Label("Carbs: " + Double.toString(nfoodcarbs));
                            Label fatLabel = new Label("Fat: " + Double.toString(nfoodfat));
                            Label fiberLabel = new Label("Fiber: " + Double.toString(nfoodfiber));
                            Label proteinLabel = new Label("Protein: " + Double.toString(nfoodprotein));
                            //Label nameLabel = new Label("Name of Food: ");
                            //Label IDLabel = new Label("ID of Food: ");
                            //Label calorieLabel = new Label("Calories: ");
                            //Label carbsLabel = new Label("Carbs: ");
                            //Label fatLabel = new Label("Fat: ");
                            //Label fiberLabel = new Label("Fiber: ");
                            //Label proteinLabel = new Label("Protein: ");
                            Button verify = new Button("Okay!");
                            verify.setPrefSize(230, 20);
                            Button cancel = new Button("Cancel!");
                            cancel.setPrefSize(230, 20);
                            verifyFoodLayout.add(nameLabel,0,0,1,1);
                            verifyFoodLayout.add(IDLabel,0,1,1,1);
                            verifyFoodLayout.add(calorieLabel,0,2,1,1);
                            verifyFoodLayout.add(carbsLabel,0,3,1,1);
                            verifyFoodLayout.add(fatLabel,0,4,1,1);
                            verifyFoodLayout.add(fiberLabel,0,5,1,1);
                            verifyFoodLayout.add(proteinLabel,0,6,1,1);
                            verifyFoodLayout.add(verify, 0, 7,1,1);
                            verifyFoodLayout.add(cancel,1,7,1,1);
                            Scene VerifyFoodScene = new Scene(verifyFoodLayout, 285, 275);
             
                            //New window (Stage)
                            Stage VerifyFood = new Stage();
                            VerifyFood.setTitle("Verify");
                            VerifyFood.setScene(VerifyFoodScene);
             
                            // Set position of second window, related to primary window.
                            VerifyFood.setX(primaryStage.getX() + 700);
                            VerifyFood.setY(primaryStage.getY() + 350);
             
                            VerifyFood.show();
                            verify.setOnAction(new EventHandler<ActionEvent>() {
                                 
                                @Override
                                public void handle(ActionEvent event) {
                                    Food newFood = new Food(nfoodName,nfoodID,nfoodcalories,nfoodfat,nfoodcarbs,nfoodfiber,nfoodprotein);
                                    foodList.addFood(newFood);
                                    food.add(newFood.getName());
                                    VerifyFood.close();
                                    CrFood.close();
                                }
                            });
                            cancel.setOnAction(new EventHandler<ActionEvent>() {
                                
                                @Override
                                public void handle(ActionEvent event) {
                                    VerifyFood.close();
                                }
                            });   
                        }
                        else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Missing Food Data or Incorrect Food Data!");
                            //alert.setContentText("Ooops, there was an error!");

                            alert.showAndWait();
                        }
                    }
                });
                cancelFood.setOnAction(new EventHandler<ActionEvent>() {
                     
                    @Override
                    public void handle(ActionEvent event) {
                        CrFood.close();
                    }
                });
            }
        });
        
        // add a new food item to a meal button -------
        Button addFood = new Button("Add to Meal");
        addFood.setPrefSize(100, 20);
        
        // add all foodBox (left pane) components  TODO - readd this?
        foodBox.getChildren().addAll(foodLabel, foodView, foodNameSearch, nutrientSearch, createFood, addFood, runSearchQuery);
        

        
        
        
        
        // right section - meal list  --------------------------------------------------------------------------------------------------------------
        VBox mealBox = new VBox();
        mealBox.setPadding(new Insets (20, 200, 20, 200));
        mealBox.setSpacing(10);
        mealBox.setStyle("-fx-background-color: #B39DDB;");
        Label mealLabel = new Label();
        mealLabel.setText("Meals");
        mealLabel.setPadding(new Insets(10));
        mealLabel.setFont(Font.font("Cambria", 24));
        ObservableList meals = FXCollections.observableArrayList();
        meals.addAll("Blueberry Pancakes", "Chicken and Waffles", "Spaghetti and Meatballs", "PBJ", "Shahi Paneer", "Burger and Fries", "Biscuits and Gravy", "Pepperoni Pizza",
                "Rice and Beans", "Steak with Mushrooms", "Wedge Salad", "Barbecue Ribs", "Shrimp and Grits");
        final ListView mealView = new ListView(meals);
        mealView.setPrefSize(300, 300);
        mealView.setEditable(true);
        
        // meal name text field
        TextField mealNameField = new TextField();
        mealNameField.setPromptText("meal name...");
        mealNameField.setPrefSize(250, 20);
        
        // add meal button
        Button addMeal = new Button();
        addMeal.setText("Create Meal");
        addMeal.setPrefSize(100, 20);
        addMeal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Add Meal button pressed. "); // TODO - remove test code
                String mealName = mealNameField.getText();
                Meal newMeal = new Meal(mealName);
                System.out.println(newMeal); // TODO - remove test code
                
                // add new meal to the meal list
                meals.add(newMeal.getName());
                };
            } );

                
        
        // analyze meal button
        Button analyzeMeal = new Button();
        analyzeMeal.setText("Analyze Meal");
        analyzeMeal.setPrefSize(100, 20);
        mealBox.getChildren().addAll(mealLabel, mealView, mealNameField, addMeal, analyzeMeal);
        
        
        // bottom section - exit button --------------------------------------------------------------------------------------------------------------
        HBox bottomMenu = new HBox();
        bottomMenu.setPadding(new Insets (10) );
        bottomMenu.setSpacing(10);
        bottomMenu.setStyle("-fx-background-color: #4527A0;");
        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(100, 20);
        bottomMenu.getChildren().addAll(exitButton);
        
        
        // import and export buttons
        // import foods from a file
        Button importButton = new Button("Import");
        importButton.setPrefSize(100, 20);
        importButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Load button pressed. "); // TODO - remove
                foodList.loadFoods("foodItems.csv");
                food.addAll(foodList.getFoodNames());

            }
        } ); // importButton.setOnAction()
        
        // save foods to a file
        Button exportButton = new Button("Export");
        exportButton.setPrefSize(100, 20);
        exportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save button pressed. "); // TODO - remove
                foodList.saveFoods("savedFoods.csv");
                };
            } );
        
        
        // add buttons and title to the heading box
        headingbox.getChildren().addAll(programTitle, importButton, exportButton);    

        
        
        
        
        
        // middle section for results to be displayed  --------------------------------------------------------------------------------------------------------------
        
        
        VBox middle = new VBox();
        middle.setPadding(new Insets(20));
        middle.setSpacing(10);
        middle.setStyle("-fx-background-color: #B39DDB;");
        
        // for displaying the selected meals' ingredients
        Label mealIngredientsDisplay = new Label();
        mealIngredientsDisplay.setText("Ingredients for: ");
        mealIngredientsDisplay.setFont(Font.font("Cambria", 24));
        mealIngredientsDisplay.setPadding(new Insets(10));
        ObservableList ingredientsList = FXCollections.observableArrayList();
        // select a meal - display its ingredients
         Meal currentMeal = (Meal) mealView.getSelectionModel().getSelectedItem(); // TODO - find how to identify this is selected properly, then add its ingredients
         //ingredientsList.addAll(currentMeal.getIngredientList());
        
         
        
        addFood.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Add food to meal button pressed. "); // TODO - remove test code
                ingredientsList.add(foodView.getSelectionModel().getSelectedItem());
                
                
               
                };
            } );
        
        
        Label tempResultText = new Label();
        tempResultText.setText("Meal: Blueberry Pancakes");
        tempResultText.setFont(Font.font("Cambria", 24));
        tempResultText.setPadding(new Insets(10));
        
        ObservableList tempMealDisplay = FXCollections.observableArrayList();
        tempMealDisplay.addAll("Blueberries", "Flour", "Sugar", "Egg");
        final ListView tempMeal = new ListView(tempMealDisplay);
        tempMeal.setPrefSize(300, 300);
        tempMeal.setEditable(true);
        
        Label tempAnalysis = new Label();
        tempAnalysis.setLineSpacing(20);
        tempAnalysis.setText("<meal analysis will be here>");
        
        
        middle.getChildren().addAll(tempResultText, tempMeal, tempAnalysis);
        
        
        
        
        
        
        
        
        
        
        
        

        
        
        
        
        // add sections to borderPane
        border.setTop(headingbox);
        border.setLeft(foodBox);
        border.setRight(mealBox);
        border.setBottom(bottomMenu);
        border.setCenter(middle);
        //border.setCenter(listView);
        
        
        
        // create scene with the borderPane
        Scene scene = new Scene(border, Color.DARKGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
    } // start()
    

    public static void main(String[] args) {
        
        launch(args);
        
    } // Main()
    
    
} // FoodBuildUI class
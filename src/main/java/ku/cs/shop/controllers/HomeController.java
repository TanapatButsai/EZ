package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import ku.cs.shop.models.MemberCard;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class HomeController {



    @FXML
    public void initialize() {

    }
    @FXML
    public void handleGoToWordList(ActionEvent actionEvent){
        try {
            FXRouter.goTo("word_list");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า word list ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleGoToWordAdd(ActionEvent actionEvent){
        try {
            FXRouter.goTo("word_add");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า word add ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}

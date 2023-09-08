package Robots;

import trello.BasePage;
import trello.BoardPage;

public class BaseRobot {

    public void createBoardByUI(String boardName) {
        new BasePage()
                .createNewBoard(boardName);
    }

    public void createTemplateBoardByUI(String boardName) {
        new BasePage()
                .createNewBoardFromTemplate(boardName);
    }

    public boolean showClosedBoardByUI() {
        return new BasePage().showClosedBoards();
    }

    public String getNameFirstBoardByUI(){
        return new BasePage().getNameFirstBoard();
    }

    public void logInByUI() {
        new BasePage()
                ;
    }

    public void logOutByUI() {
        new BasePage()
                .logOut();
    }

    //checking
    public Boolean checkingExistenceCardByUI(String cardName) {
        return new BoardPage().cardByNameIsExist(cardName);
    }

    public String currentUserNameByUI() {
        return new BasePage().getCurrentUserName();
    }

    public Boolean checkingOpenedMainPageByUI() {
        return new BasePage().mainPageIsOpen();
    }


}

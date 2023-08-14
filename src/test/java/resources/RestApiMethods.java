package resources;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import entities.Board;
import entities.Card;
import entities.Column;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class RestApiMethods {
    static String key = "071815818d26f6434f3fc4e0ee46fc56";
    static String token = "ATTAf983a9423e40ba35f912ae4d70b0351c23cde66706caad0584c53eb47b8cfddfFED14199";
    static String boardId;
    static String cardId;
    static String columnId;
    private static Logger LOGGER = Logger.getLogger(RestApiMethods.class.getName());

    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://api.trello.com/1")
                    .setContentType(ContentType.JSON)
                    .addHeader("Accept", "application/json")
                    .addHeader("accept", "*/*")
                    .addQueryParam("key", key)
                    .addQueryParam("token", token)
                    .build();

    public static String getInfoUser() {
        String userId = given()
                .spec(REQ_SPEC)
                .basePath("/members/me")
                .when().get()
                .then().statusCode(200)
                .extract().jsonPath().get("id");
        LOGGER.info("User_id: " + userId);
        return userId;
    }

    public static String createBoard(String nameBoard) {
        try {
            LOGGER.info("Send request to create Board");
            boardId = given()
                    .spec(REQ_SPEC)
                    .basePath("/boards/")
                    .queryParams("name", nameBoard)
                    .log().uri()
                    .when().post()
                    .then().statusCode(200)
                    .extract().jsonPath().get("id");
            LOGGER.info("Successful create board with id: " + boardId + "and name: " + nameBoard);
        } catch (AssertionError ex) {
            LOGGER.info("Failed Create board with name: " + nameBoard);
            throw ex;
        }
        return boardId;
    }

    public static Board getBoard(String boardId) {
        String resGetBoard = null;
        try {
            LOGGER.info("Start send request GET/boards/" + boardId);
            resGetBoard = given()
                    .spec(REQ_SPEC)
                    .basePath("/boards/" + boardId)
                    .when().get()
                    .then().statusCode(200)
                    .extract().body().asString();
            LOGGER.info("RESPONSE:" + resGetBoard);
        } catch (AssertionError ex) {
            LOGGER.info("Failed GET/boards/:" + boardId);
            throw ex;
        }
        Gson gson = new Gson();
        Board board = gson.fromJson(resGetBoard, Board.class);
        return board;
    }

    public static String createColumns(String nameColumn, String boardId) {
        try {
            LOGGER.info("Send request to create column");
            columnId = given()
                    .spec(REQ_SPEC)
                    .basePath("/boards/" + boardId + "/lists")
                    .queryParams("name", nameColumn)
                    .log().uri()
                    .when().post()
                    .then().statusCode(200)
                    .extract().jsonPath().get("id");
            LOGGER.info("Create column with id: " + columnId);
        } catch (AssertionError ex) {
            LOGGER.info("Failed Create column with name: " + nameColumn);
            throw ex;
        }
        return columnId;
    }

    public static List<Column> getColumn(String boardId) {
        List<Column> column = null;
        try {
            LOGGER.info("Start send request GET/boards/" + boardId + "lists/");
            String respColumn = given()
                    .spec(REQ_SPEC)
                    .basePath("/boards/" + boardId + "/lists")
                    .when().get()
                    .then().statusCode(200)
                    .extract().body().asString();
            Gson gson = new Gson();
            column = gson.fromJson(respColumn, new TypeToken<List<Column>>() {
            }.getType());
            LOGGER.info("Result:" + respColumn);

        } catch (AssertionError ex) {
            LOGGER.info("Failed GET/boards/" + boardId + "lists/");
            throw ex;
        }
        return column;

    }

    public static String createCard(String columnId, String cardName) {
        try {
            LOGGER.info("Send request to create cards");
            cardId = given()
                    .spec(REQ_SPEC)
                    .basePath("/cards")
                    .queryParams("idList", columnId)
                    .queryParams("name", cardName)
                    .log().uri()
                    .when().post()
                    .then().statusCode(200)
                    .extract().jsonPath().get("id");
            LOGGER.info("Create card with id: " + cardId + "and name: " + cardName);
        } catch (AssertionError ex) {
            LOGGER.info("Failed Create card with name: " + cardName + "in column: " + columnId);
            throw ex;
        }
        return cardId;
    }

    public static void updateCard(String newCardName, String cardId) {
        try {
            LOGGER.info("Send request to update cards");
            given()
                    .spec(REQ_SPEC)
                    .basePath("/cards/" + cardId)
                    .queryParams("name", newCardName)
                    .log().uri()
                    .when().put()
                    .then().statusCode(200);
            LOGGER.info("Card id:" + cardId + "successfully update");
            LOGGER.info("New card name: " + newCardName);
        } catch (AssertionError ex) {
            LOGGER.info("Failed update card id: " + cardId);
            throw ex;
        }

    }

    public static Card getCard(String cardId) {
        String resGet;
        try {
            resGet = given()
                    .spec(REQ_SPEC)
                    .basePath("/cards/" + cardId)
                    .when().get()
                    .then().statusCode(200)
                    .extract().body().asString();
        } catch (AssertionError ex) {
            LOGGER.info("Not found card: " + cardId);
            throw ex;
        }
        Gson gson = new Gson();
        Card card = gson.fromJson(resGet, Card.class);
        LOGGER.info("Response: name: " + card.getName() + " id: " + card.getId());
        return card;

    }

    public static void deleteBoard(String boardId) {
        try {
            LOGGER.info("Start send request DELETE/board/" + boardId);
            given()
                    .spec(REQ_SPEC)
                    .basePath("/boards/" + boardId)
                    .when().delete()
                    .then().statusCode(200);
            LOGGER.info("Successful delete board with id:" + boardId);
        } catch (AssertionError ex) {
            LOGGER.info("Failed DELETE/board/:" + boardId);
            throw ex;
        }
    }

    public static String getFilteredNameColumn(String boardId, String columnId) {
        LOGGER.info("Start filter");
        String columnName = getColumn(boardId)
                .stream()
                .filter(column -> column.getId().equals(columnId))
                .findFirst()
                .orElseThrow(() -> new AssertionError("element not found"))
                .getName();
        LOGGER.info("Find columnName: " + columnName);
        return columnName;
    }

    public static void preconditionCreate () {
        //String resBoardId = RestApiMethods.createBoard("myTestDesk15");
        //String resColumnId = RestApiMethods.getColumn(resBoardId).get(0).id;
    }
}

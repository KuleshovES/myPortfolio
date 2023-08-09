package resources;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import entities.Card;
import entities.Column;
import groovy.util.logging.Slf4j;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class RestApiMethods {
    static String key = "071815818d26f6434f3fc4e0ee46fc56";
    static String token = "ATTAf983a9423e40ba35f912ae4d70b0351c23cde66706caad0584c53eb47b8cfddfFED14199";
    static String boardId;
    static String cardId;
    static String columnId;
    static Logger logger = LoggerFactory.getLogger(RestApiMethods.class);

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
        logger.info("User_id: " + userId);
        return userId;
    }

    public static String createBoard(String nameBoard) {
        boardId = given()
                .spec(REQ_SPEC)
                .basePath("/boards/")
                .queryParams("name", nameBoard)
                .when().post()
                .then().statusCode(200)
                .extract().jsonPath().get("id");
        logger.info("Board_id: " + boardId);
        return boardId;
    }

    public static void getBoards() {
        boardId = "64d21340478af561ad7b5ab9";
        String resGet = given()
                .spec(REQ_SPEC)
                .basePath("/boards/" + boardId)
                .when().get()
                .then().statusCode(200)
                .extract().body().asString();
        System.out.println(resGet);
        //десереализуем в объект и возвращаем его
        Gson gson = new Gson();

    }

    public static String createColumns(String nameColumn, String boardId) {
        columnId = given()
                .spec(REQ_SPEC)
                .basePath("/boards/" + boardId + "/lists")
                .queryParams("name", nameColumn)
                .when().post()
                .then().statusCode(200)
                .extract().jsonPath().get("id");
        System.out.println("Column_id = " + columnId);
        return columnId;
    }

    public static List<Column> getColumn(String boardId) {
        String respColumn = given()
                .spec(REQ_SPEC)
                .basePath("/boards/" + boardId + "/lists")
                .when().get()
                .then().statusCode(200)
                .extract().body().asString();
        //TODO make logging result respColumn
        Gson gson = new Gson();
        List<Column> column = gson.fromJson(respColumn, new TypeToken<List<Column>>(){}.getType());
        System.out.println("Result:");
        System.out.println(column.get(0).id);
        return column;


    }

    public static String createCard(String columnId, String cardName) {
        cardId = given()
                .spec(REQ_SPEC)
                .basePath("/cards")
                .queryParams("idList", columnId)
                .queryParams("name", cardName)
                .when().post()
                .then().statusCode(200)
                .extract().jsonPath().get("id");
        System.out.println("Card_id = " + cardId);
        return cardId;
    }

    public static void updateCard(String newCardName, String cardId) {
        given()
                .spec(REQ_SPEC)
                .basePath("/cards/" + cardId)
                .queryParams("name", newCardName)
                .when().put()
                .then().statusCode(200);
    }

    public static Card getCard(String cardId) {
        String resGet = given()
                .spec(REQ_SPEC)
                .basePath("/cards/" + cardId)
                .when().get()
                .then().statusCode(200)
                .extract().body().asString();
        //TODO make logging result resGet
        Gson gson = new Gson();
        Card card = gson.fromJson(resGet, Card.class);
        logger.info("Result:");
        logger.info("id: " + card.id + "\nname: " + card.name + "\nstatus: " + card.isClosed,
                "id column: " + card.idList, "id board: " + card.idBoard);
        System.out.println();
        return card;
    }


}

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TrelloTest {
    private String  idBoard,
                    idList,
                    idCard;

    // Создание доски
    @Test(priority = 1)
    public void checkCreateBoard() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

        Board board = new Board();
        String name = "New board";

        Board createdBoard = retrofitBuilder.getTrelloApi().createBoard(board, name).execute().body();

        idBoard = createdBoard.getId();

        Assert.assertEquals(createdBoard.getName(), name);
    }

    // Перейменование доски
    @Test(priority = 2)
    public void classUpdateTest() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Board board = new Board();
        String updatedName = "Update board name";
        board.setName(updatedName);

        Board updatedBoard = retrofitBuilder.getTrelloApi().updateBoard(board, idBoard).execute().body();

        Assert.assertEquals(updatedBoard.getName(), updatedName);
    }

    // Создание списка
    @Test(priority = 3)
    public void classCreateListTest() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Board board = new Board();
        String listName = "New list";

        List createdList = retrofitBuilder.getTrelloApi().createList(board, idBoard, listName).execute().body();

        idList = createdList.getListId();

        Assert.assertEquals(createdList.getListName(), listName);
    }

    // Создание карточки в списке
    @Test(priority = 4)
    public void classCreateCardTest() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        List list = new List();

        Card createdCard = retrofitBuilder.getTrelloApi().createCard(list, idList).execute().body();

        idCard = createdCard.getCardId();

        Assert.assertEquals(createdCard.getListId(), idList);
    }

    // Перейменование карточки
    @Test(priority = 5)
    public void classUpdateCardTest() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Card card = new Card();
        String cardUpdatedName = "Update card name";
        card.setCardName(cardUpdatedName);

        Card updatedCard = retrofitBuilder.getTrelloApi().updateCard(card, idCard).execute().body();

        Assert.assertEquals(updatedCard.getCardName(), cardUpdatedName);
    }

    // Запрос информации по карточке
    @Test(priority = 6)
    public void classGetCardTest() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Card card = new Card();

        Card getsCard = retrofitBuilder.getTrelloApi().getCard(idCard, card.getKey(), card.getToken()).execute().body();

        Assert.assertEquals(getsCard.getCardId(), idCard);

    }

    // Удаление карточки
    @Test(priority = 7)
    public void classDeleteCardTest() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Card card = new Card();

        int code = retrofitBuilder.getTrelloApi()
                .deleteCard(idCard, card.getKey(), card.getToken()).execute().code();

        Assert.assertEquals(code, 200);
    }

    // Удаление доски
    @Test(priority = 8)
    public void classDeleteTest() throws IOException {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Board board = new Board();

        int code = retrofitBuilder.getTrelloApi()
                .deleteBoard(idBoard, board.getKey(), board.getToken()).execute().code();

        Assert.assertEquals(code, 200);
    }
}

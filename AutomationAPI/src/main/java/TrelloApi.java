import retrofit2.Call;
import retrofit2.http.*;

public interface TrelloApi {
    // Create a new board
    // The new name for the board. 1 to 16384 characters long.
    @POST("1/boards/")
    Call <Board> createBoard(@Body Board board, @Query("name") String name);

    // Update an existing board by id
    @PUT("/1/boards/{id}")
    Call <Board> updateBoard(@Body Board board, @Path("id") String id);

    // Delete a board.
    @DELETE("/1/boards/{id}")
    Call <Board> deleteBoard(@Path("id") String id, @Query("key") String key, @Query("token") String token);

    // Create a new List on a Board
    @POST("/1/boards/{id}/lists")
    Call<List> createList(@Body Board board, @Path("id") String idBoard, @Query("name") String listName);

    // Create a new card
    @POST("/1/cards")
    Call<Card> createCard(@Body List list, @Query("idList") String idList);

    // Update a Card
    @PUT("/1/cards/{id}")
    Call<Card> updateCard(@Body List list, @Path("id") String idCard);

    // Get a Card
    @GET("/1/cards/{id}")
    Call<Card> getCard(@Path("id") String idCard, @Query("key") String key, @Query("token") String token);

    // Delete a Card
    @DELETE("/1/cards/{id}")
    Call<Card> deleteCard(@Path("id") String idCard, @Query("key") String key, @Query("token") String token);

}

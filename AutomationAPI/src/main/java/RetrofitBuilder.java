import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private TrelloApi trelloApi;

    public RetrofitBuilder(){
        Gson gson = new GsonBuilder().create();

        HTTPLogInterceptor interceptor = new HTTPLogInterceptor();
        interceptor.setLevel(HTTPLogInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        /*
        Создадим реализацию самого Retrofit, для этого вызовем Builder() и зададим ему путь к нашему api-адресу
        параметром baseUrl() передав ему на вход неполный url-адрес (в нашем случае https://api.github.com/users/list).
        Стоит отметить, что адрес должен заканчиваться на слэш («/») иначе получим исключение.
        Командой build() построим наш Retrofit.
        После чего вызвав у него метод create() и передав на вход имя нашего интерфейса мы создадим наш сервис.
         */

        // экземпляр класса Retrofit (Билдер), который содержит базовый URL и способность преобразовывать json
        // данные с помощью Gson.
        Retrofit retrofit = new Retrofit.Builder()
                .client(client) // client с 14й строки
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.trello.com")
                .build();

        // реализация TrelloApi от Retrofit. В этой реализации соединены настройки билдера
        // (базовый URL и Gson конвертер) и описания методов из интерфейса
        // В итоге Retrofit будет брать базовый URL из билдера, присоединять к нему остаток пути,
        // который указываем в GET в интерфейсе, и тем самым получит полную ссылку.
        trelloApi = retrofit.create(TrelloApi.class);
    }

    public TrelloApi getTrelloApi() {
        return trelloApi;
    }

}

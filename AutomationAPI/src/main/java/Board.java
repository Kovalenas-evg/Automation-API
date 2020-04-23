public class Board {
    public String name;
    public String id;
    private String key = "e63dde8225173848d630f549bd6cd1b6";
    private String token = "13d2bf26b83809c400886eedfabba358c8b70d1c89e8d4c4d4dd709e0f2d2ef2";

    public  String getKey(){
        return key;
    }

    public  String getToken(){
        return token;
    }

    // board

    public  String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}

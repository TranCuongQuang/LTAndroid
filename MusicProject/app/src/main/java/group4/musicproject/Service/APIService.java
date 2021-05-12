package group4.musicproject.Service;

public class APIService {
    private static String base_url = "http://android-api-v1.savequiz.com/api/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}

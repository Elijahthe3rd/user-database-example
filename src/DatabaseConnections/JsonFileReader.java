//package DatabaseConnections;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//
//public final class JsonFileReader {
//    private final static Gson gson = new Gson();
//
//    public final static String[] jsonData(String jsonFilePath,String jsonFileName) throws FileNotFoundException {
//        String path =jsonFilePath; //"JsonFiles/DB_Credentials.json";
//        BufferedReader br = new BufferedReader(new FileReader(path));
//        JsonObject jsonObject = gson.fromJson(br, JsonObject.class);
//        String jsonResponse = String.valueOf(jsonObject.get(jsonFileName));
//        String[] JsonArr = jsonResponse.replaceAll("\\{", "").replaceAll("\\}", "").split(",");
//        return JsonArr;
//    }
//
//    public final void printJsonData(String [] jsonArr){
//            int counter=0;
//            for (String xx:jsonArr
//                 ) {
//                System.out.flush();
//                System.out.print("@ index: "+counter+" : ");
//                System.out.println(xx);
//                counter+=1;
//                System.out.flush();
//            }
//    }
//}
//

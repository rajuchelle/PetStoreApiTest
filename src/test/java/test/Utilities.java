package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Utilities {
    public static String environment;
    public static String userName;
    public static String password;
    public static Response response;
    public static String path;
    public static String pathParam;
    public static String queryParam;
    public static String requestBody;
    public static String statusCode;

    public static JsonPath petServiceJsonPath = readJsonFile("./src/test/Resources/TestData/pet_service.json");
    public static JsonPath storeServiceJsonPath = readJsonFile("./src/test/Resources/TestData/store_service.json");
    public static JsonPath userServiceJsonPath = readJsonFile("./src/test/Resources/TestData/user_service.json");

    public static String baseUrl = "";

    public static String congfigFilePath = "./src/test/Resources/Config/config.json";

    public static void getTestProperties() throws IOException {
        Properties testProperties = new Properties();
        testProperties.load(new FileReader("./src/test/Resources/testProperties.properties"));
        environment = testProperties.getProperty("environment");
    }

    public static void getTestDetails(String testName) throws IOException {
        JsonPath serviceTestPath = null;
        if(testName.contains("ps")){
            serviceTestPath = petServiceJsonPath;
        }else if(testName.contains("ss")){
            serviceTestPath = storeServiceJsonPath;
        }else if(testName.contains("ss")){
            serviceTestPath = userServiceJsonPath;
        }

        path = serviceTestPath.getString(testName+".endpointPath");
        pathParam = serviceTestPath.getString(testName+".pathParam");
        queryParam = serviceTestPath.getString(testName+".queryParam");
        statusCode = serviceTestPath.getString(testName+".responseCode");
    }

    public static void getConfig(String envName){
        JsonPath json = readJsonFile(congfigFilePath);
        baseUrl = json.get(envName.toLowerCase()+".baseURL");
    }

    public static JsonPath readJsonFile(String filePath){
        return new JsonPath(new File(filePath));
    }

    public static Response get(String uriPath){
        return given()
                .with()
//                .contentType(ContentType.JSON)
                .when()
                .get(uriPath).thenReturn();
    }

    public static Response getWithHeaders(String uriPath, Map<String,String> headers){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .headers(headers)
                .when()
                .get(uriPath).thenReturn();
    }

    public static Response getWithHeadersAndQueryParameters(String uriPath, Map<String,String> headers, Map queryParameters){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .headers(headers)
                .queryParams(queryParameters)
                .when()
                .get(uriPath).thenReturn();
    }

    public static Response getWithHeadersAndPathParameters(String uriPath, Map<String,String> headers, Map pathParameters){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .headers(headers)
                .pathParams(pathParameters)
                .when()
                .get(uriPath).thenReturn();
    }

    public static Response postWithBody(String uriPath, String requestBody){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(uriPath)
                .thenReturn();
    }

    public static Response postWithHeadersAndBody(String uriPath,Map<String,String> headers, String requestBody){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .headers(headers)
                .and()
                .body(requestBody)
                .when()
                .post(uriPath)
                .thenReturn();
    }

    public static Response postWithHeadersAndQueryParameters(String uriPath,Map<String,String> headers, Map queryParams){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .headers(headers)
                .and()
                .queryParams(queryParams)
                .when()
                .post(uriPath)
                .thenReturn();
    }

    public static Response postWithHeadersAndQueryParametersAndBody(String uriPath,Map<String,String> headers, Map queryParams, String body){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .headers(headers)
                .and()
                .queryParams(queryParams)
                .and()
                .body(body)
                .when()
                .post(uriPath)
                .thenReturn();
    }

    public static Response putWithBody(String uriPath, String body){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .and()
                .body(body)
                .when()
                .put(uriPath)
                .thenReturn();
    }

    public static Response putWithPathParametersAndBody(String uriPath,Map pathParameters, String body){
        return given()
                .with()
                .contentType(ContentType.JSON)
                .pathParams(pathParameters)
                .and()
                .body(body)
                .when()
                .put(uriPath)
                .thenReturn();
    }





}

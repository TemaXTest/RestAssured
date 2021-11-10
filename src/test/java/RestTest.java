
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestTest extends BaseUrl {
    
    @Test
    public void createUser1() {
    String a = "qwerty";
    String c = "flower";

       given()

                .baseUri(BaseUrl)
               .header("Accept", ContentType.JSON.getAcceptHeader())
               .header("firstName","val "+a+"val")
               .header("lastName","Som"+c+"ings")
                .post("/users")
                .then().statusCode(200)
//               .body(equalTo("[{ID=10, FIRSTNAME=val qwertyval, LASTNAME=Somflowerings}]"))
               .body(containsString("val qwertyval"))
               .body(containsString("Somflowerings"))
               .body(containsString("ID"));

    }
    @Test
    public void createUser2() {
        String a = "22222";

        given()

                .baseUri(BaseUrl)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("firstName","val "+a+"val")
                .when()
                .post("/users")
                .then().statusCode(200)
                .body(containsString("FIRSTNAME"))
                .body(containsString("22222"))
                .body(containsString("ID"));
    }
    @Test
    public void createUser3() {
        String a = "33333";

        given()

                .baseUri(BaseUrl)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("lastName","val "+a+"val")
                .when()
                .post("/users")
                .then().statusCode(200)
                .body(containsString("LASTNAME"))
                .body(containsString("33333"))
                .body(containsString("ID"));
    }
    @Test
    public void createEmpty() {

        given()

                .baseUri(BaseUrl)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .when()
                .post("/users")
                .then().statusCode(200)
                .body(containsString("LASTNAME"))
                .body(containsString("FIRSTNAME"))
                .body(containsString("ID"));
    }
    @Test
    public void delUsers() {

        given()
                .baseUri(BaseUrl)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void putAll() {
        String a = "tooooo";
        String c = "short";

        given()
                .baseUri(BaseUrl)
                .header("firstName","val "+a+"val")
                .header("lastName","Som"+c+"ings")
                .when()
                .put("/users/3?restletMethods=PUT")
                .then()
                .statusCode(200);
    }
    @Test
    public void putFirst() {
        String a = "tooooo";

        given()
                .baseUri(BaseUrl)
                .header("firstName","val "+a+" val")
                .when()
                .put("/users/3?restletMethods=PUT")
                .then()
                .statusCode(200);
    }
    @Test
    public void putLast() {
        String c = "short";

        given()
                .baseUri(BaseUrl)
                .header("lastName","Som "+c+" ings")
                .when()
                .put("/users/3?restletMethods=PUT")
                .then()
                .statusCode(200);
    }
    @Test
    public void putEmpty() {

        given()
                .baseUri(BaseUrl)
                .when()
                .put("/users/3?restletMethods=PUT")
                .then()
                .statusCode(200);
    }
    @Test
    public void getUsers() {

        given()
                .baseUri(BaseUrl)
                .when()
                .get("/users/2?restletMethods=GET")
                .then()
                .statusCode(200);
    }
}

package org.acme.kogito

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.acme.kogito.model.Person
import org.acme.kogito.model.PersonEnvelope
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class RestTest {
    companion object {
        init {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        }
    }

    @Test
    fun shouldDetermineSallyIsAnAdult() {
        val sally = Person(name = "Sally", age = 20)
        given().contentType(ContentType.JSON)
            .`when`().body(PersonEnvelope(sally)).post("/persons")
            .then().statusCode(200).body("person.adult", equalTo(true))
    }

    @Test
    fun shouldDetermineDanIsNotAnAdult() {
        val dan = Person(name = "Dan", age = 13)
        given().contentType(ContentType.JSON)
            .`when`().body(PersonEnvelope(dan)).post("/persons")
            .then().statusCode(200).body("person.adult", equalTo(false))
    }
}

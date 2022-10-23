package com.jetbrains.marco;
;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.java.Log;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.xmlunit.assertj.XmlAssert;

import java.time.LocalDate;
@Log
public class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User("Paul", 56, false, LocalDate.now().minusYears(56));
        log.info("setUp Called..");


    }

    @AfterEach
    void cleanup() {
        user = null;
        log.info("cleanup Called..");
    }


    @Test
    @DisplayName("*** User should be at least 18 ***")
    void userShouldBeAtLeast18() {
        assertThat(user.age()).isGreaterThanOrEqualTo(18);
        /*
        assertThat(user.blocked()).
                as("User %s should be blocked", user.name()).
                isTrue();

         */
        assertThatJson(user).isEqualTo("{\"name\":\"Paul\",\"age\":56,\"blocked\":false,\"birthDate\":[1966, 10, 23]}");

        XmlAssert.assertThat( "<a><b attr=\"abc\"></b></a>").nodesByXPath("//a/b/@attr").exist();
    }

    @ParameterizedTest
    //@ValueSource(ints = {20, 50 , 80})
    @CsvFileSource(resources = "/friends.csv", numLinesToSkip = 1)
    void all_friends_must_be_18(String name, int age) {
        assertThat(age).isGreaterThanOrEqualTo(18);
    }


    @Test
    void userShouldBePaul() {
        assertThat(user.name()).isEqualTo("Paul");
    }

    @Test
    void userShouldBeBlocked() {
        assertThat(user.blocked()).isFalse();
    }


}
package com.jetbrains.marco;
;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserTest {

    User user = new User("Paul", 56, false, LocalDate.now().minusYears(56));

    @Test
    void userShouldBeAtLeast18() {
        assertThat(user.age()).isGreaterThanOrEqualTo(18);
        assertThat(user.blocked()).
                as("User %s should be blocked", user.name()).
                isTrue();
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
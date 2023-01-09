package org.prgrms.kdt.voucher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

/*
        컬렉션에대한 검증은 Hamcrest가 Best !!
 */
public class HamcrestAssertionTests {

    @Test
    @DisplayName("여러 hamcrest matcher 테스트")
    void hamcrestTest() {
        assertEquals(2, 1+1);
        assertThat(1 + 1, is(2));
        assertThat(1 + 1, equalTo(2));
        assertThat(1 + 1, anyOf(is(2), is(1)));

        assertNotEquals(1, 1 + 1);
        assertThat(1 + 1, not(equalTo(1)));
    }

    @Test
    @DisplayName("컬렉션에 대한 matcher 테스트")
    void hamcrestListMatcher() {
        var prices = List.of(1, 2, 3);
        assertThat(prices, hasSize(3));
        // 리스트 순회하기 -> everyItem
        assertThat(prices, everyItem(greaterThan(0)));
        assertThat(prices, containsInAnyOrder(3, 1, 2));
        assertThat(prices, contains(1, 2, 3));
        assertThat(prices, hasItem(2));
        assertThat(prices, hasItem(greaterThanOrEqualTo(1)));

    }
}

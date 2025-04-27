package com.devmaro.db;

import com.devmaro.db.domain.Author;
import com.devmaro.db.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static Author CreateTestAuthorA() {
        return Author.builder().id(1L).name("Marouane mar").age(40).build();
    }
    public static Author CreateTestAuthorB() {
        return Author.builder().id(2L).name("ahmed mar").age(20).build();
    }

    public static Author CreateTestAuthorC() {
        return Author.builder().id(3L).name("brahim mar").age(54).build();
    }

    public static Author CreateTestAuthorD() {
        return Author.builder().id(4L).name("jamal mar").age(50).build();
    }


    public static Book CreateTestBookA() {
        return Book.builder().isbn("sh1").title("clean code").authorId(1L).build();
    }
    public static Book CreateTestBookB() {
        return Book.builder().isbn("sh3").title("algo").authorId(1L).build();
    }
    public static Book CreateTestBookC() {
        return Book.builder().isbn("sh2").title("data cleaning").authorId(1L).build();
    }
    public static Book CreateTestBookD() {
        return Book.builder().isbn("sh4").title("data structure").authorId(1L).build();
    }
}

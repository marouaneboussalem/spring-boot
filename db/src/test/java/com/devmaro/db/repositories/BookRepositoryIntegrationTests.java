package com.devmaro.db.repositories;

import com.devmaro.db.TestDataUtil;
import com.devmaro.db.dao.BookDao;
import com.devmaro.db.dao.impl.AuthorDaoImpl;
import com.devmaro.db.dao.impl.BookDaoImpl;
import com.devmaro.db.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {


    private BookRepository underTest;

    @Autowired

    public BookDaoImplIntegrationTests(BookRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatBookcanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtil.CreateTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> results = underTest.findOne(book.getIsbn());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthorA();
        authorDao.create(author);

        Book book1 = TestDataUtil.CreateTestBookA();
        book1.setAuthorId(author.getId());
        underTest.create(book1);

        Book book2 = TestDataUtil.CreateTestBookB();
        book2.setAuthorId(author.getId());
        underTest.create(book2);

        Book book3 = TestDataUtil.CreateTestBookC();
        book3.setAuthorId(author.getId());
        underTest.create(book3);

        Book book4 = TestDataUtil.CreateTestBookD();
        book4.setAuthorId(author.getId());
        underTest.create(book4);

        List<Book> results= underTest.find();
        assertThat(results)
                .hasSize(4)
                .contains(book1,book2,book3,book4);


    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.CreateTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtil.CreateTestBookA();
        bookA.setAuthorId(author.getId());

        underTest.create(bookA);

        System.out.println(bookA.getIsbn()+" 111111111111111"+bookA.getTitle());

        bookA.setTitle("UPDATed");

        underTest.update(bookA.getIsbn(),bookA);

        Optional<Book> result= underTest.findOne(bookA.getIsbn());
        System.out.println(result);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);

        System.out.println(bookA.getIsbn()+" 22222222222222222"+bookA.getTitle());

    }
    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.CreateTestAuthorA();
        authorDao.create(author);

        Book book = TestDataUtil.CreateTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);

       underTest.delete(book.getIsbn());
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isEmpty();
    }
}

package com.devmaro.db.repositories;

import com.devmaro.db.TestDataUtil;
import com.devmaro.db.dao.impl.AuthorDaoImpl;
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
public class AuthorDaoImplIntergationTests {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntergationTests(AuthorDaoImpl underTest){
        this.underTest = underTest;
    }
    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthorA();
        underTest.create(author);
       Optional<Author> results = underTest.findOne(author.getId());
       assertThat(results).isPresent();
       assertThat(results.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuhtorCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.CreateTestAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtil.CreateTestAuthorB();
        underTest.create(authorB);
        Author authorC = TestDataUtil.CreateTestAuthorC();
        underTest.create(authorC);
        Author authorD = TestDataUtil.CreateTestAuthorD();
        underTest.create(authorD);
        List<Author> result = underTest.find();
        assertThat(result)
                .hasSize(4).
                contains(authorA,authorB,authorC,authorD);


    }
    @Test
    public  void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.CreateTestAuthorA();
        underTest.create(authorA);
        authorA.setName("UPdated");
        underTest.update(authorA.getId(),authorA);
        Optional<Author> results = underTest.findOne(authorA.getId());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(authorA);

    }
    @Test
    public void  TestThatAuthorCanBeDeleted(){
        Author authorA = TestDataUtil.CreateTestAuthorA();
        underTest.create(authorA);
        underTest.delete(authorA.getId());
        Optional <Author> result = underTest.findOne(authorA.getId());
        assertThat(result).isEmpty();


    }

}

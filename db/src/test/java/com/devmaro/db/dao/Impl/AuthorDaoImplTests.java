package com.devmaro.db.dao.Impl;

import com.devmaro.db.TestDataUtil;
import com.devmaro.db.dao.impl.AuthorDaoImpl;
import com.devmaro.db.domain.Author;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class AuthorDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDaoImpl underTest;
    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = TestDataUtil.CreateTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(eq("INSERT INTO authors (id,name,age) values (?,?,?)"),eq(1L),eq("Marouane mar"),eq(40));

    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql(){
            underTest.findOne(1L);
            verify(jdbcTemplate).query(eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1")
                    , ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),eq(1L));
    }
    @Test
    public void testThatFindManyGeneratesTheCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(eq("SELECT id,name,age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }
    @Test
     public void testThatUpdateGeneratesTheCorrectSql(){
        Author author = TestDataUtil.CreateTestAuthorA();
        underTest.update(3L,author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id=?, name=? ,age=? WHERE id = ?",
                1L ,"Marouane mar" , 40, 3L

        );

    }

    @Test
    public void TestThatCanGenerateTheCorrectSql(){
        underTest.delete(1L);
        verify(jdbcTemplate).update("delete from authors where id=?" ,1L);
    }

}

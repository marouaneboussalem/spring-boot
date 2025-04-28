package com.devmaro.db.dao.Impl;

import com.devmaro.db.TestDataUtil;
import com.devmaro.db.dao.impl.AuthorDaoImpl;
import com.devmaro.db.dao.impl.BookDaoImpl;
import com.devmaro.db.domain.Author;
import com.devmaro.db.domain.Book;
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

public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookDaoImpl undertest;
    @Test

    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.CreateTestBookA();
        undertest.create(book);
        verify(jdbcTemplate).update(eq("insert into books (isbn,title,author_id) values (?,?,?)"),eq("sh1"),eq("clean code"),eq(1L));


    }

    @Test
    public void testThatFindOneGeneratesThatCorrectSql(){
        undertest.findOne("sh1");
        verify(jdbcTemplate).query(eq("SELECT isbn,title,author_id FROM books WHERE isbn = ? LIMIT 1")
        , ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),eq("sh1"));
    }
    @Test
    public void testThatFindManyGeneratesTheCorrectSql(){
        undertest.find();
        verify(jdbcTemplate).query(eq("SELECT isbn,title,author_id FROM Books") ,
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );


    }

    @Test

    public void  testThatUpdateGenerateTheCorrectSql(){
        Book book = TestDataUtil.CreateTestBookA();
        undertest.update("sh1",book);
        verify(jdbcTemplate).update("UPDATE books SET isbn=?, title=?, author_id=?  WHERE isbn=?", "sh1", "clean code",1L,"sh1");
    }


}

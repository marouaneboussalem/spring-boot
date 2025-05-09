package com.devmaro.db.dao.impl;

import com.devmaro.db.dao.BookDao;
import com.devmaro.db.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class BookDaoImpl implements BookDao {
    private  final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("insert into books (isbn,title,author_id) values (?,?,?)",book.getIsbn(),book.getTitle(),book.getAuthorId());


    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results=
                jdbcTemplate.query("SELECT isbn,title,author_id FROM books WHERE isbn = ? LIMIT 1", new BookRowMapper() ,isbn);
        return results.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query("SELECT isbn,title,author_id FROM Books", new BookRowMapper());
    }



    public static class BookRowMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder().isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update(
        "UPDATE books SET isbn=?, title=?, author_id=?  WHERE isbn=?",book.getIsbn(),book.getTitle(),book.getAuthorId(),isbn );}

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("DELETE from books where isbn=?",isbn);
    }
}


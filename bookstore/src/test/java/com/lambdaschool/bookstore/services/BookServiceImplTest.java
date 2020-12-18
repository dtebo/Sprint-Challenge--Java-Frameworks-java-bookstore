package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplicationTest;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplicationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Autowired
    private SectionService sectionService;

    @Before
    public void setUp() throws
            Exception
    {

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void findAll()
    {
        List<Book> myBooks = bookService.findAll();

        assertEquals(5,
                myBooks.size());
    }

    @Test
    public void findBookById()
    {
        assertNotNull(bookService.findBookById(26L));
        assertEquals("Flatterland",
                bookService.findBookById(26L).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void notFindBookById()
    {
        assertNull(bookService.findBookById(24L));
        assertEquals("Flatterland",
                bookService.findBookById(24L).getTitle());
    }

    @Test
    public void delete()
    {
        bookService.delete(26L);
        assertEquals(4,
                bookService.findAll().size());
    }

    @Test
    public void save()
    {
        Section mySection = sectionService.save(new Section("TEST"));

        Book myBook = new Book("TestBook", "aasdfdfaher343", 2, mySection);

        Book addBook = bookService.save(myBook);

        assertNotNull(addBook);
        assertEquals(myBook.getTitle(),
                addBook.getTitle());
    }

    @Test
    public void update()
    {
    }

    @Test
    public void deleteAll()
    {
    }
}
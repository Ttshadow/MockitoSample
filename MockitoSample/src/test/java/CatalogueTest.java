import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalgueTest {
    Catalogue c;

    @Mock
    ReadItemCommand mockR;

    @Mock
    WriteItemCommand mockW;

    @Mock
    Book mockBook;

    @BeforeEach
    void init() {
        c = new Catalogue(mockR, mockW);
    }


    @Test
    public void getAllBooks_ReturnsEmptyBookList_IfNoBooksAreInTheCatalogue() {
        List<Book> list = c.getAllBooks();
        assertEquals(0, list.size());
    }

    @Test
    public void getAllBooks_CallsReadAllMethodOfReadItemCommand_WhenCalled() {
        c.getAllBooks();
        verify(mockR, times(1)).readAll();
    }

    @Test
    public void getAllBooks_ReturnsListOfBooksItReceivesFromReadAllMethodOfReadItemCommand_WhenCalled() {
        List<Book> list = new ArrayList<>();
        list.add(new Book());
        when(mockR.readAll()).thenReturn(list);

        List<Book> returnedList = c.getAllBooks();
        assertEquals(list, returnedList);
    }

    @Test
    public void test4() {
        c.addBook(mockBook);
        verify(mockW, times(1)).insertItem(mockBook);
    }

    @Test
    public void test5() {
        List<Book> books = new ArrayList<>();
        books.add(mockBook);
        books.add(mockBook);
        c.addBooks(books);
        verify(mockW, times(2)).insertItem(mockBook);
    }

    @Test
    public void test6() {
        String str = "123";
        c.getBook(str);
        verify(mockR, times(1)).getItem(str);
    }

    @Test
    public void test7() {
        Book b = new Book();
        c.deleteBook(b);
        verify(mockW, times(1)).deleteItem(b);
    }

    @Test
    public void test8() {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        Book b2 = new Book();

        books.add(b1);
        books.add(b2);

        when(mockR.readAll()).thenReturn(books);

        c.deleteAllBooks();

        verify(mockR, times(1)).readAll();
        verify(mockW, times(1)).deleteItem(b2);
        verify(mockW, times(1)).deleteItem(b1);
    }
}

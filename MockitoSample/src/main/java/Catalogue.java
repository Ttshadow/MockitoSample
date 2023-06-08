import java.util.List;

public class Catalogue {
    ReadItemCommand r;
    WriteItemCommand w;

    public Catalogue(ReadItemCommand r, WriteItemCommand w) {
        super();
        this.r = r;
        this.w = w;
    }

    public List<Book> getAllBooks() {
        // TODO Auto-generated method stub
        return r.readAll();
    }

    public void addBook(Book mockBook) {
        // TODO Auto-generated method stub
        w.insertItem(mockBook);
    }

    public void addBooks(List<Book> books) {
        // TODO Auto-generated method stub
        for (Book book : books) {
            addBook(book);
        }
    }

    public Book getBook(String isbn) {
        // TODO Auto-generated method stub
        return r.getItem(isbn);
    }

    public void deleteBook(Book book) {
        // TODO Auto-generated method stub
        w.deleteItem(book);
    }

    public void deleteAllBooks() {
        // TODO Auto-generated method stub
        List<Book> readAll = r.readAll();
        for (Book book : readAll) {
            deleteBook(book);
        }
    }
}

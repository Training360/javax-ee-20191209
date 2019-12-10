package jtechlog.frontend;


import jtechlog.backend.Bookmark;
import jtechlog.backend.impl.BookmarkServiceImpl;

public class App {

    public static void main(String[] args) {
        var service = new BookmarkServiceImpl();
        service.addBookmark(new Bookmark("Training360", "http://www.training360.com"));
        System.out.println(service.listBookmarks());
    }
}

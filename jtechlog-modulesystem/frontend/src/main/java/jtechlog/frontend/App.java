package jtechlog.frontend;


import jtechlog.backend.Bookmark;
import jtechlog.backend.BookmarkService;

import java.util.ServiceLoader;

public class App {

    public static void main(String[] args) {
        var loader =
                ServiceLoader.load(BookmarkService.class);
        var service = loader
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Can not find implementation"));

        service.addBookmark(new Bookmark("Training360", "http://www.training360.com"));
        System.out.println(service.listBookmarks());
    }
}

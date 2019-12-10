package jtechlog.backend;

import java.util.List;

public interface BookmarkService {

    List<Bookmark> listBookmarks();

    void addBookmark(Bookmark bookmark);
}

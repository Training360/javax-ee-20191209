package jtechlog.backend.impl;

import jtechlog.backend.Bookmark;
import jtechlog.backend.BookmarkService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookmarkServiceImpl implements BookmarkService {

    private static final List<Bookmark> BOOKMARKS =
            Collections.synchronizedList(new ArrayList<>());

    @Override
    public List<Bookmark> listBookmarks() {
        return new ArrayList<>(BOOKMARKS);
    }

    @Override
    public void addBookmark(Bookmark bookmark) {
        BOOKMARKS.add(bookmark);
    }
}

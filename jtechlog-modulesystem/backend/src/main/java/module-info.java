module backend {
    exports jtechlog.backend;

    provides jtechlog.backend.BookmarkService with jtechlog.backend.impl.BookmarkServiceImpl;
}
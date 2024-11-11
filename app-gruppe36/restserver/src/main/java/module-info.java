module restserver {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires model;
    requires core;
    exports restserver;
}

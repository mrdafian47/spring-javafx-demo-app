package com.github.desktop.demo.library;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.nio.file.Paths;

public interface CocoaLibrary extends Library {

//    CocoaLibrary INSTANCE = Native.load("/Users/rizki/Workspace/ASLIRI/Desktop/spring-javafx-demo-app/native/libMyWKWebView.dylib", CocoaLibrary.class);
    CocoaLibrary INSTANCE = Native.load(
        Paths.get(System.getProperty("user.dir"), "native", "libMyWKWebView.dylib").toString(),
        CocoaLibrary.class
);

    // Load the macOS application environment (system-provided function)
    boolean NSApplicationLoad();

    // Create and embed a WKWebView
    Pointer createWKWebView(double x, double y, double width, double height);

    // Load a URL into the WKWebView
    void loadURL(Pointer webView, String url);

    // Destroy the WKWebView
    void destroyWKWebView(Pointer webView);
}

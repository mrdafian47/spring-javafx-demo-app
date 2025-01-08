#import <Cocoa/Cocoa.h>
#import <WebKit/WebKit.h>

// Global variable to hold the WKWebView instance
WKWebView *webView;

// Ensure exported functions are visible to the linker
void* createWKWebView(double x, double y, double width, double height) __attribute__((visibility("default")));
void loadURL(void* webViewPtr, const char* url) __attribute__((visibility("default")));
void destroyWKWebView(void* webViewPtr) __attribute__((visibility("default")));

void* createWKWebView(double x, double y, double width, double height) {
    if (!webView) {
        // Create a frame for the WKWebView
        NSRect frame = NSMakeRect(x, y, width, height);
        webView = [[WKWebView alloc] initWithFrame:frame];

        // Add the WKWebView to the main window's content view
        NSView *contentView = [[[NSApplication sharedApplication] mainWindow] contentView];
        [contentView addSubview:webView];
    }
    return (__bridge void*)webView; // Return the WKWebView pointer
}

void loadURL(void* webViewPtr, const char* url) {
    WKWebView *webView = (__bridge WKWebView*)webViewPtr; // Convert pointer back to WKWebView
    NSString *urlString = [NSString stringWithUTF8String:url];
    NSURL *nsURL = [NSURL URLWithString:urlString];
    NSURLRequest *request = [NSURLRequest requestWithURL:nsURL];
    [webView loadRequest:request];
}

void destroyWKWebView(void* webViewPtr) {
    WKWebView *webView = (__bridge WKWebView*)webViewPtr; // Convert pointer back to WKWebView
    [webView removeFromSuperview];
    webView = nil;
}

# WebViewDemo

The Android counterpart of [WebKitDemo](https://github.com/azisramdhan/WebKitDemo): a small
app whose only job is to give [Loupe](https://github.com/azisramdhan/loupe) something to
screenshot from a pull request.

Comment `/farm <screen>` on a pull request and Loupe builds that commit, installs it on an
emulator, walks to the screen and posts the capture back.

## Screens

| `/farm` | Screen | What it shows |
| --- | --- | --- |
| `main`, `login` | `LoginActivity` | The launcher screen. `main` is an alias for `login`. |
| `web` | `WebActivity` | A `WebView` on example.com, reached by signing in as `admin` / `password`. |
| `local-demo` | `LocalDemoActivity` | The bundled `assets/index.html`, deliberately longer than the screen so a capture has to scroll. |
| `legacy` | `LegacyActivity` | Migration notes, two taps deep, reached from the toolbar's **Legacy** action. |

Every control that automation touches carries a `contentDescription` matching the
`accessibilityIdentifier` its iOS twin uses, so one capture recipe reads the same on both
platforms.

## Building

```sh
./gradlew assembleDebug        # app/build/outputs/apk/debug/app-debug.apk
./gradlew test                 # unit tests
```

Requires JDK 17 and the Android SDK. Loupe runs the same Gradle task itself when it needs a
build for a commit it has not seen.

## The bridge

`JsBridge` is exposed to the asset page as `Bridge`. An `@JavascriptInterface` method can
only return a primitive or a `String`, so `requestDeviceData()` hands back JSON that the page
parses. On iOS the same call resolves a promise through `WKScriptMessageHandler` — the one
place where the two apps genuinely differ.

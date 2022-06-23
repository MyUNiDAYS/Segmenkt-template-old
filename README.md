<h1 align="left">SegmenKT Kotlin SDK <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/reedyuk/segmenkt?style=flat-square"> <a href="https://git.live"><img src="https://img.shields.io/badge/collaborate-on%20gitlive-blueviolet?style=flat-square"></a></h1>

Segment + Kotlin = SegmenKT

The SegmenKT Kotlin SDK is a Kotlin-first SDK for Segment. Its API is similar to the <a href="https://github.com/segmentio/analytics-android/">Segment Android SDK</a> but also supports <b>multiplatform</b> projects, enabling you to use Segment directly from your common source targeting <strong>iOS</strong> and <strong>Android</strong>.

## Installation

### KMM

```
implementation("com.myunidays:segmenkt:0.0.4")
```

### Android

```
implementation("com.myunidays:segmenkt-android:0.0.4")
```

### iOS

Run gradle task in this project
```
./gradlew assembleXCFramework
```

Locate the framework in the following directory
```
build/XCFrameworks/
```
Add the xcframework file to your xcode project, OR you can grab it from this repo in Examples/ios/frameworks/

### JS

At the moment there is a JS target but it is currently filled with TODO's so it's not recommended to be used yet.

#### Important

In order for ios to work you will also need to include the segment framework to your ios project.

## How to use

Initialise segment with a config

### Kotlin
```
val segmentConfig = Configuration(
    writeKey = WriteKey(
        android = "123",
        ios = "ABC",
        js = "1"
    ),
    context = context
)
Analytics.setupWithConfiguration(segmentConfig)
```

No need to define a key for iOS if you are using just Android.

### Swift
```
let segmentConfig = Configuration(writeKey: WriteKey(android: nil, ios: "", js: nil), context: nil)
Analytics.Companion().setupWithConfiguration(configuration: segmentConfig)
```

Then when you want to **Track**, **Identify**, **Group**, **Screen**

### Kotlin
```
Analytics.shared().track("Cool Event")
Analytics.shared().identify("1")
Analytics.shared().group("1")
Analytics.shared().screen("Cool Screen")
```

### Swift
```
Analytics.Companion().shared(context: nil).track(name: "Cool Event", properties: nil)
Analytics.Companion().shared(context: nil).identify(userId: "1", traits: nil)
Analytics.Companion().shared(context: nil).group(groupId: "1", traits: nil)
Analytics.Companion().shared(context: nil).screen(screenTitle: "Cool Screen", properties: nil)
```

## Examples

Run the examples from the examples directory for KMM, Android and iOS

## Contributing

This project is set up as an open source project. As such, if there are any suggestions that you have for features, for improving the code itself, or you have come across any problems; you can raise them and/or suggest changes in implementation.

If you are interested in contributing to this codebase, please follow the contributing guidelines. This contains guides on both contributing directly and raising feature requests or bug reports. Please adhere to our code of conduct when doing any of the above.

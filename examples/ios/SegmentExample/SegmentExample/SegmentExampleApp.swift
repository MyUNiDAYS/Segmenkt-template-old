//
//  SegmentExampleApp.swift
//  SegmentExample
//
//  Created by Andrew Reed on 17/02/2022.
//

import SwiftUI
import segmenkt

@main
struct SegmentExampleApp: App {
    
    init() {
        let segmentConfig = Configuration(writeKey: WriteKey(android: nil, ios: ""), context: nil)
        Analytics.Companion().setupWithConfiguration(configuration: segmentConfig)
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

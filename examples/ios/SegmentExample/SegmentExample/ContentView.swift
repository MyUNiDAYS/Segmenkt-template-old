//
//  ContentView.swift
//  SegmentExample
//
//  Created by Andrew Reed on 17/02/2022.
//

import SwiftUI
import segmenkt

struct ContentView: View {
    var body: some View {
        Text("Hello, world!")
            .padding()
            .onAppear {
                Analytics.Companion().shared(context: nil).track(name: "Cool Event", properties: nil)
                
                Analytics.Companion().shared(context: nil).identify(userId: "1", traits: nil)
                
                Analytics.Companion().shared(context: nil).group(groupId: "1", traits: nil)
                
                Analytics.Companion().shared(context: nil).screen(screenTitle: "ContentView", properties: nil)
            }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

//
//  TabNavigationView.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import KotlinCore

struct TabNavigationView: View {

  @State var selectedIndex = 0
  private let assembler = AppAssembler()

  var body: some View {
    ZStack {
      switch selectedIndex {
      case 1:
        MyMangaView(viewModel: assembler.resolve())
      default:
        BrowseView(viewModel: assembler.resolve())
          .animation(.none)
      }

      VStack {
        Spacer()
        tabView.padding(.bottom, 30)
      }
    }
  }
}

extension TabNavigationView {

  var tabView: some View {
    HStack {

      Button(action: {
        selectedIndex = 0
      }, label: {
        VStack {
          Image(selectedIndex != 0 ? "icBrowseUn" : "icBrowse")
            .resizable()
            .frame(width: 25, height: 25, alignment: .center)
        }
      }).padding(.horizontal, 35)

      Button(action: {
        selectedIndex = 1
      }, label: {
        VStack {
          Image(selectedIndex != 1 ? "icSavedUn" : "icSaved")
            .resizable()
            .frame(width: 25, height: 30, alignment: .center)
        }
      }).padding(.horizontal, 35)

    }.frame(maxWidth: 230, minHeight: 80)
    .background(
      Color.white
        .cornerRadius(12)
        .shadow(radius: 20)
    )
    .padding(.horizontal, 40)
  }
}
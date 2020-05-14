//
//  MotionSplashModule.swift
//  MotionSplash
//
//  Created by Mai Trung Duc on 13/5/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation
import RevealingSplashView

@objc open class MotionSplashModule: NSObject {
    @objc open var revealingSplashView: RevealingSplashView
    
    @objc public init(iconImage: UIImage, iconInitialSize: CGSize, backgroundColor: UIColor) {
        revealingSplashView = RevealingSplashView(iconImage: iconImage,iconInitialSize: iconInitialSize, backgroundColor: backgroundColor)
        super.init()
    }
    
    @objc public init(iconImage: UIImage, iconInitialSize: CGSize, backgroundImage: UIImage) {
        revealingSplashView = RevealingSplashView(iconImage: iconImage,iconInitialSize: iconInitialSize, backgroundImage: backgroundImage)
        super.init()
    }
    
    // TODO: custom color is not working for background image
    @objc open func useCustomColor(applyForIconImage: Bool, color: UIColor) {
        revealingSplashView.useCustomIconColor = applyForIconImage
        revealingSplashView.iconColor = color
    }
    
    @objc open func animate() {
        revealingSplashView.startAnimation()
    }
    
    @objc open func timing(duration: Double, delay: Double) {
        revealingSplashView.duration = duration
        revealingSplashView.delay = delay
    }
    
    @objc open var type: String {
        set {
            switch(newValue) {
                case "RotateOut":
                    revealingSplashView.animationType = .rotateOut
                    break
                case "PopAndZoomOut":
                    revealingSplashView.animationType = .popAndZoomOut
                    break
                case "SqueezeAndZoomOut":
                    revealingSplashView.animationType = .squeezeAndZoomOut
                    break
                case "SwingAndZoomOut":
                    revealingSplashView.animationType = .swingAndZoomOut
                    break
                case "WobbleAndZoomOut":
                    revealingSplashView.animationType = .woobleAndZoomOut // TODO: review typo here
                    break
                case "HeartBeat":
                    revealingSplashView.animationType = .heartBeat
                    break
                default:
                    revealingSplashView.animationType = .twitter
            }
        }
        get {
            return ""
        }
    }
    
    @objc open func hide() {
        revealingSplashView.heartAttack = true
    }
    
    @objc open func setMinimumBeats(value: Int) {
        revealingSplashView.minimumBeats = value > 0 ? value : 1
    }
}


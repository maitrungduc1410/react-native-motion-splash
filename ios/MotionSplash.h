#import <React/RCTBridgeModule.h>

@class MotionSplashModule;

@interface MotionSplash : NSObject <RCTBridgeModule>

+ (void)initWithIconImage: (UIImage *)iconImage iconInitialSize:(CGSize *)iconInitialSize backgroundColor:(UIColor *)backgroundColor inRootView:(UIView*)rootView;

+ (void)initWithIconImage: (UIImage *)iconImage iconInitialSize:(CGSize *)iconInitialSize backgroundImage:(UIImage *)backgroundImage inRootView:(UIView*)rootView;

@end

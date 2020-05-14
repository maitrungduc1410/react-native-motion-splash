#import "MotionSplash.h"
#import <react_native_motion_splash-Swift.h>
#import <React/RCTConvert.h>
#import <React/RCTBridge.h>

static MotionSplashModule* instance = nil;

@implementation MotionSplash

RCT_EXPORT_MODULE()

- (dispatch_queue_t)methodQueue{
    return dispatch_get_main_queue();
}

+ (void) jsLoadError:(NSNotification*)notification
{
    // hide splash screen if JS load error
    [MotionSplash hide];
}

+ (UIColor *)colorFromHexString:(NSString *)hexString {
    unsigned rgbValue = 0;
    NSScanner *scanner = [NSScanner scannerWithString:hexString];
    [scanner setScanLocation:1]; // bypass '#' character
    [scanner scanHexInt:&rgbValue];
    return [UIColor colorWithRed:((rgbValue & 0xFF0000) >> 16)/255.0 green:((rgbValue & 0xFF00) >> 8)/255.0 blue:(rgbValue & 0xFF)/255.0 alpha:1.0];
}

+ (void) initWithIconImage:(UIImage *)iconImage iconInitialSize:(CGSize *)iconInitialSize backgroundColor:(NSString *)backgroundColor inRootView:(UIView*)rootView {
    
    UIColor *bgColorFromHex = [MotionSplash colorFromHexString:backgroundColor];
    instance = [[MotionSplashModule alloc] initWithIconImage:iconImage iconInitialSize:*iconInitialSize backgroundColor:bgColorFromHex];
    UIView *revealingSplashView = (UIView *)instance.revealingSplashView;
    [rootView addSubview:revealingSplashView];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(jsLoadError:) name:RCTJavaScriptDidFailToLoadNotification object:nil];
}

+ (void) initWithIconImage:(UIImage *)iconImage iconInitialSize:(CGSize *)iconInitialSize backgroundImage:(UIImage *)backgroundImage inRootView:(UIView*)rootView {
    
    instance = [[MotionSplashModule alloc] initWithIconImage:iconImage iconInitialSize:*iconInitialSize backgroundImage:backgroundImage];
    UIView *revealingSplashView = (UIView *)instance.revealingSplashView;
    [rootView addSubview:revealingSplashView];

    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(jsLoadError:) name:RCTJavaScriptDidFailToLoadNotification object:nil];
}

+ (void)useCustomColor:(BOOL)isApplyForIconImage iconColor:(NSNumber *)color {
    [instance useCustomColorWithApplyForIconImage: isApplyForIconImage color:[RCTConvert UIColor:color]];
}

+ (void) animate {
    [instance animate];
}

+ (void) hide {
    [instance hide];
}

+ (void) setMinimumBeats: (NSNumber *)minimumBeats {
    [instance setMinimumBeatsWithValue:[RCTConvert NSInteger:minimumBeats]];
}

RCT_EXPORT_METHOD(animate:(NSString *)animationType) {
    [instance setType:animationType];
    [MotionSplash animate];
}

//RCT_EXPORT_METHOD(animateWithType:(NSString *)animationType) {
//    [instance setType:animationType];
//    [RevealSplash animate];
//}
//
//RCT_EXPORT_METHOD(animateWithTiming:(nonnull NSNumber *)duration delay:(nonnull NSNumber *)delay) {
//    [instance timingWithDuration:[RCTConvert double:duration] delay:[RCTConvert double:delay]];
//    [RevealSplash animate];
//}
//
//RCT_EXPORT_METHOD(animateWithTypeAndTiming:(NSString *)animationType duration:(nonnull NSNumber *)duration delay:(nonnull NSNumber *)delay) {
//    [instance setType:animationType];
//    [instance timingWithDuration:[RCTConvert double:duration] delay:[RCTConvert double:delay]];
//    [RevealSplash animate];
//}

RCT_EXPORT_METHOD(hide) {
    [MotionSplash hide];
}

RCT_EXPORT_METHOD(setMinimumBeats: (nonnull NSNumber *)minimumBeats) {
    [MotionSplash setMinimumBeats: minimumBeats];
}

RCT_EXPORT_METHOD(useCustomColor:(BOOL)isApplyForIconImage iconColor:(nonnull NSNumber *)color) {
    [MotionSplash useCustomColor:isApplyForIconImage iconColor:color];
}

@end

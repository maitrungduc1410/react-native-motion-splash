import { NativeModules, processColor } from 'react-native';
const { MotionSplash } = NativeModules;

const animate = (animationType) => {
  if (typeof animationType !== 'string') {
    throw new TypeError('Property "animationType" must be string')
  }
  MotionSplash.animate(animationType || '')
}

const setMinimumBeats = (minimumBeats) => {
  if (typeof minimumBeats !== 'number') {
    throw new TypeError('Property "minimumBeats" must be number')
  }
  MotionSplash.setMinimumBeats(minimumBeats || 1)
}

const useCustomColor = (isApplyForIconImage, color) => {
  if (typeof isApplyForIconImage !== 'boolean') {
    throw new TypeError('Property "isApplyForIconImage" must be boolean')
  }

  if (typeof color !== 'string') {
    throw new TypeError('Property "color" must be string')
  }
  MotionSplash.useCustomColor(isApplyForIconImage, processColor(color))
}

export default {
  animate,
  hide: MotionSplash.hide,
  setMinimumBeats,
  useCustomColor
};

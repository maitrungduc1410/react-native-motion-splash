import React, {Component} from 'react';
import {StyleSheet, Text, View} from 'react-native';
import MotionSplash from 'react-native-motion-splash';

export default class App extends Component {
  componentDidMount() {
    MotionSplash.useCustomColor(false, 'red');
    // MotionSplash.setMinimumBeats(3);
    MotionSplash.animate();
    // setTimeout(() => {
    //   MotionSplash.hide();
    // }, 3000);
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>☆MotionSplash example☆</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
